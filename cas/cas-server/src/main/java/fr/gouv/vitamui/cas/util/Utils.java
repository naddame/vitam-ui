/**
 * Copyright French Prime minister Office/SGMAP/DINSIC/Vitam Program (2019-2020)
 * and the signatories of the "VITAM - Accord du Contributeur" agreement.
 *
 * contact@programmevitam.fr
 *
 * This software is a computer program whose purpose is to implement
 * implement a digital archiving front-office system for the secure and
 * efficient high volumetry VITAM solution.
 *
 * This software is governed by the CeCILL-C license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL-C
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 */
package fr.gouv.vitamui.cas.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apereo.cas.authentication.Authentication;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.authentication.surrogate.SurrogateAuthenticationService;
import org.apereo.cas.configuration.model.support.cookie.TicketGrantingCookieProperties;
import org.apereo.cas.services.UnauthorizedServiceException;
import org.apereo.cas.ticket.Ticket;
import org.apereo.cas.util.Pac4jUtils;
import org.apereo.cas.web.DelegatedClientWebflowManager;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.apereo.cas.web.pac4j.DelegatedSessionCookieManager;
import org.apereo.cas.web.support.WebUtils;
import org.jasig.cas.client.util.URIBuilder;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.redirect.RedirectAction;
import org.pac4j.saml.client.SAML2Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.webflow.context.ExternalContext;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import fr.gouv.vitamui.commons.api.CommonConstants;
import fr.gouv.vitamui.commons.api.domain.UserDto;
import fr.gouv.vitamui.commons.api.logger.VitamUILogger;
import fr.gouv.vitamui.commons.api.logger.VitamUILoggerFactory;
import fr.gouv.vitamui.commons.rest.client.ExternalHttpContext;
import fr.gouv.vitamui.iam.external.client.CasExternalRestClient;

/**
 * Helper class.
 *
 *
 */
public class Utils {

    private static final VitamUILogger LOGGER = VitamUILoggerFactory.getInstance(Utils.class);

    private static final int BROWSER_SESSION_LIFETIME = -1;

    private final CasExternalRestClient casExternalRestClient;

    private final DelegatedClientWebflowManager delegatedClientWebflowManager;

    private final DelegatedSessionCookieManager delegatedSessionCookieManager;

    private final String casToken;

    @Value("${vitamui.cas.tenant.identifier}")
    private Integer casTenantIdentifier;

    @Value("${vitamui.cas.identity}")
    private String casIdentity;

    public Utils(final CasExternalRestClient casExternalRestClient, final DelegatedClientWebflowManager delegatedClientWebflowManager,
            final DelegatedSessionCookieManager delegatedSessionCookieManager, final String casToken) {
        this.casExternalRestClient = casExternalRestClient;
        this.delegatedClientWebflowManager = delegatedClientWebflowManager;
        this.delegatedSessionCookieManager = delegatedSessionCookieManager;
        this.casToken = casToken;
    }

    public ExternalHttpContext buildContext(final String username) {
        return new ExternalHttpContext(casTenantIdentifier, casToken, "cas+" + username, casIdentity);
    }

    public Event performClientRedirection(final Action action, final SAML2Client client, final RequestContext requestContext) throws IOException {
        final HttpServletRequest request = WebUtils.getHttpServletRequestFromExternalWebflowContext(requestContext);
        final HttpServletResponse response = WebUtils.getHttpServletResponseFromExternalWebflowContext(requestContext);
        try {
            final J2EContext webContext = Pac4jUtils.getPac4jJ2EContext(request, response);
            final Ticket ticket = delegatedClientWebflowManager.store(webContext, client);

            final RedirectAction redirectAction = client.getRedirectAction(webContext);
            if (RedirectAction.RedirectType.SUCCESS.equals(redirectAction.getType())) {
                webContext.writeResponseContent(redirectAction.getContent());
            }
            else {
                final URIBuilder builder = new URIBuilder(redirectAction.getLocation());
                final String url = builder.toString();
                LOGGER.debug("Redirecting client [{}] to [{}] based on identifier [{}]", client.getName(), url, ticket.getId());
                response.sendRedirect(url);
            }
            delegatedSessionCookieManager.store(webContext);
        }
        catch (final HttpAction e) {
            if (e.getCode() == HttpStatus.UNAUTHORIZED.value()) {
                LOGGER.debug("Authentication request was denied from the provider [{}]", client.getName(), e);
            }
            else {
                LOGGER.warn(e.getMessage(), e);
            }
            throw new UnauthorizedServiceException(e.getMessage(), e);
        }

        final ExternalContext externalContext = requestContext.getExternalContext();
        externalContext.recordResponseComplete();
        return new Event(action, CasWebflowConstants.TRANSITION_ID_STOP);
    }

    public String getSuperUsername(final Authentication authentication) {
        final Map<String, Object> authAttributes = authentication.getAttributes();
        final String username = (String) authAttributes.get(SurrogateAuthenticationService.AUTHENTICATION_ATTR_SURROGATE_PRINCIPAL);
        LOGGER.debug("is it currently a superUser: {}", username);
        return username;
    }

    public UserDto getRealUser(final Authentication authentication) {
        final String username = getSuperUsername(authentication);
        final UserDto user;
        // it's a surrogation, we retrieve him by his email
        if (StringUtils.isNotBlank(username)) {
            user = casExternalRestClient.getUserByEmail(buildContext(username), username, Optional.empty());
        }
        else {
            // otherwise, we retrieve him by his identifier
            final Principal principal = authentication.getPrincipal();
            final String userId = principal.getId();
            user = casExternalRestClient.getUserById(buildContext(userId), userId);
        }
        return user;
    }

    public Cookie buildIdpCookie(final String value, final TicketGrantingCookieProperties tgc) {
        final Cookie cookie = new Cookie(CommonConstants.IDP_PARAMETER, value);
        cookie.setPath(tgc.getPath());
        cookie.setDomain(tgc.getDomain());
        cookie.setMaxAge(BROWSER_SESSION_LIFETIME);
        cookie.setSecure(tgc.isSecure());
        cookie.setHttpOnly(tgc.isHttpOnly());
        return cookie;
    }

    public Object getAttributeValue(final Principal principal, final String key) {
        final Object attribute = principal.getAttributes().get(key);
        if (attribute instanceof List) {
            final List values = (List) attribute;
            if (!values.isEmpty()) {
                return values.get(0);
            }
        }
        return attribute;
    }

    public String sanitizePasswordResetUrl(final String url) {
        if (url != null && url.length() > 15) {
            return url.substring(0, url.length() - 15) + "...";
        }
        else {
            return "\"passwordResetURL\"...";
        }
    }
}
