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
package fr.gouv.vitamui.cas.mfa.config;

import fr.gouv.vitamui.cas.mfa.authentication.IamMultifactorAuthenticationProviderBypass;
import fr.gouv.vitamui.cas.mfa.authentication.SmsAuthenticationHandler;
import fr.gouv.vitamui.cas.mfa.SmsMultifactorAuthenticationProvider;
import fr.gouv.vitamui.cas.util.Constants;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationMetaDataPopulator;
import org.apereo.cas.authentication.OneTimeTokenCredential;
import org.apereo.cas.authentication.metadata.AuthenticationContextAttributeMetaDataPopulator;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.configuration.model.support.mfa.MultifactorAuthenticationProviderBypassProperties;
import org.apereo.cas.services.MultifactorAuthenticationProvider;
import org.apereo.cas.services.RegisteredServiceMultifactorPolicy;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The MFA SMS authentication configuration.
 *
 *
 */
@Configuration("smsAuthenticationEventExecutionPlanConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class SmsMfaAuthenticationEventExecutionPlanConfiguration {

    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Autowired
    @Qualifier("principalFactory")
    private PrincipalFactory principalFactory;

    @Bean
    public IamMultifactorAuthenticationProviderBypass iamMultifactorAuthenticationProviderBypass() {
        final MultifactorAuthenticationProviderBypassProperties prop = new MultifactorAuthenticationProviderBypassProperties();
        prop.setCredentialClassType(OneTimeTokenCredential.class.toString());
        return new IamMultifactorAuthenticationProviderBypass(prop);
    }

    @Bean
    public MultifactorAuthenticationProvider smsAuthenticatorAuthenticationProvider() {
        final SmsMultifactorAuthenticationProvider p = new SmsMultifactorAuthenticationProvider();
        p.setBypassEvaluator(iamMultifactorAuthenticationProviderBypass());
        p.setFailureMode(RegisteredServiceMultifactorPolicy.FailureModes.CLOSED.toString());
        p.setOrder(1);
        p.setId(Constants.MFA_SMS_EVENT_ID);
        return p;
    }

    @Bean
    public AuthenticationMetaDataPopulator smsAuthenticationMetaDataPopulator() {
        return new AuthenticationContextAttributeMetaDataPopulator(
                casProperties.getAuthn().getMfa().getAuthenticationContextAttribute(),
                smsAuthenticationHandler(),
                smsAuthenticatorAuthenticationProvider().getId()
        );
    }

    @Bean
    public AuthenticationHandler smsAuthenticationHandler() {
        return new SmsAuthenticationHandler(Constants.MFA_SMS_EVENT_ID, servicesManager, principalFactory, null);
    }

    @Bean
    public AuthenticationEventExecutionPlanConfigurer smsAuthenticationEventExecutionPlanConfigurer() {
        return plan -> {
            plan.registerAuthenticationHandler(smsAuthenticationHandler());
            plan.registerMetadataPopulator(smsAuthenticationMetaDataPopulator());
        };
    }
}
