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
package fr.gouv.vitamui.commons.api.domain;

import static fr.gouv.vitamui.commons.api.CommonConstants.CHECK_ROLE_PREFIX;
import static fr.gouv.vitamui.commons.api.CommonConstants.CREATE_ROLE_PREFIX;
import static fr.gouv.vitamui.commons.api.CommonConstants.DELETE_ROLE_PREFIX;
import static fr.gouv.vitamui.commons.api.CommonConstants.GET_ROLE_PREFIX;
import static fr.gouv.vitamui.commons.api.CommonConstants.ROLE_PREFIX;
import static fr.gouv.vitamui.commons.api.CommonConstants.UPDATE_ME_ROLE_PREFIX;
import static fr.gouv.vitamui.commons.api.CommonConstants.UPDATE_ROLE_PREFIX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * All the services.
 *
 *
 */
public class ServicesData {

    //------------------------------------ USERS -------------------------------------------
    public static final String SERVICE_USERS = "USERS";

    public static final String ROLE_GET_USERS = GET_ROLE_PREFIX + SERVICE_USERS;

    public static final String ROLE_CREATE_USERS = CREATE_ROLE_PREFIX + SERVICE_USERS;

    public static final String ROLE_UPDATE_USERS = UPDATE_ROLE_PREFIX + SERVICE_USERS;

    public static final String ROLE_UPDATE_STANDARD_USERS = UPDATE_ROLE_PREFIX + "STANDARD_" + SERVICE_USERS;

    public static final String ROLE_GENERIC_USERS = "ROLE_GENERIC_" + SERVICE_USERS;

    public static final String ROLE_ANONYMIZATION_USERS = "ROLE_ANONYMIZATION_" + SERVICE_USERS;

    public static final String ROLE_MFA_USERS = "ROLE_MFA_" + SERVICE_USERS;

    public static final String ROLE_UPDATE_ME_USERS = UPDATE_ME_ROLE_PREFIX + SERVICE_USERS;

    //------------------------------------ CUSTOMERS -------------------------------------------

    public static final String SERVICE_CUSTOMERS = "CUSTOMERS";

    public static final String ROLE_GET_CUSTOMERS = GET_ROLE_PREFIX + SERVICE_CUSTOMERS;

    public static final String ROLE_CREATE_CUSTOMERS = CREATE_ROLE_PREFIX + SERVICE_CUSTOMERS;

    public static final String ROLE_UPDATE_CUSTOMERS = UPDATE_ROLE_PREFIX + SERVICE_CUSTOMERS;

    //------------------------------------ OWNERS -------------------------------------------

    public static final String SERVICE_OWNERS = "OWNERS";

    public static final String ROLE_GET_OWNERS = GET_ROLE_PREFIX + SERVICE_OWNERS;

    public static final String ROLE_CREATE_OWNERS = CREATE_ROLE_PREFIX + SERVICE_OWNERS;

    public static final String ROLE_UPDATE_OWNERS = UPDATE_ROLE_PREFIX + SERVICE_OWNERS;

    //------------------------------------ TENANTS -------------------------------------------

    public static final String SERVICE_TENANTS = "TENANTS";

    public static final String ROLE_GET_TENANTS = GET_ROLE_PREFIX + SERVICE_TENANTS;

    public static final String ROLE_GET_ALL_TENANTS = GET_ROLE_PREFIX + "ALL_" + SERVICE_TENANTS;

    public static final String ROLE_CREATE_TENANTS = CREATE_ROLE_PREFIX + SERVICE_TENANTS;

    public static final String ROLE_CREATE_TENANTS_ALL_CUSTOMERS = ROLE_CREATE_TENANTS + "_ALL_CUSTOMERS";

    public static final String ROLE_UPDATE_TENANTS = UPDATE_ROLE_PREFIX + SERVICE_TENANTS;

    public static final String ROLE_UPDATE_TENANTS_ALL_CUSTOMERS = ROLE_UPDATE_TENANTS + "_ALL_CUSTOMERS";

    //------------------------------------ PROVIDERS -------------------------------------------

    public static final String SERVICE_PROVIDERS = "PROVIDERS";

    public static final String ROLE_GET_PROVIDERS = GET_ROLE_PREFIX + SERVICE_PROVIDERS;

    public static final String ROLE_CREATE_PROVIDERS = CREATE_ROLE_PREFIX + SERVICE_PROVIDERS;

    public static final String ROLE_UPDATE_PROVIDERS = UPDATE_ROLE_PREFIX + SERVICE_PROVIDERS;

    public static final String ROLE_DELETE_PROVIDERS = DELETE_ROLE_PREFIX + SERVICE_PROVIDERS;

    //------------------------------------  GROUPS -------------------------------------------

    public static final String SERVICE_GROUPS = "GROUPS";

    public static final String ROLE_GET_GROUPS = GET_ROLE_PREFIX + SERVICE_GROUPS;

    public static final String ROLE_GET_ALL_GROUPS = GET_ROLE_PREFIX + "ALL_" + SERVICE_GROUPS;

    public static final String ROLE_CREATE_GROUPS = CREATE_ROLE_PREFIX + SERVICE_GROUPS;

    public static final String ROLE_UPDATE_GROUPS = UPDATE_ROLE_PREFIX + SERVICE_GROUPS;

    public static final String ROLE_DELETE_GROUPS = DELETE_ROLE_PREFIX + SERVICE_GROUPS;

    //------------------------------------ PROFILES -------------------------------------------

    public static final String SERVICE_PROFILES = "PROFILES";

    public static final String ROLE_GET_PROFILES = GET_ROLE_PREFIX + SERVICE_PROFILES;

    public static final String ROLE_CREATE_PROFILES = CREATE_ROLE_PREFIX + SERVICE_PROFILES;

    public static final String ROLE_UPDATE_PROFILES = UPDATE_ROLE_PREFIX + SERVICE_PROFILES;

    public static final String ROLE_DELETE_PROFILES = DELETE_ROLE_PREFIX + SERVICE_PROFILES;

    public static final String ROLE_GET_PROFILES_ALL_TENANTS = GET_ROLE_PREFIX + SERVICE_PROFILES + "_ALL_" + SERVICE_TENANTS;

    //------------------------------------ SUBROGATIONS -------------------------------------------

    public static final String SERVICE_SUBROGATIONS = "SUBROGATIONS";

    public static final String ROLE_GET_SUBROGATIONS = GET_ROLE_PREFIX + SERVICE_SUBROGATIONS;

    public static final String ROLE_GET_USERS_SUBROGATIONS = GET_ROLE_PREFIX + "USERS_" + SERVICE_SUBROGATIONS;

    public static final String ROLE_GET_GROUPS_SUBROGATIONS = GET_ROLE_PREFIX + "GROUPS_" + SERVICE_SUBROGATIONS;

    public static final String ROLE_CREATE_SUBROGATIONS = CREATE_ROLE_PREFIX + SERVICE_SUBROGATIONS;

    public static final String ROLE_DELETE_SUBROGATIONS = DELETE_ROLE_PREFIX + SERVICE_SUBROGATIONS;

    //------------------------------------ CAS -------------------------------------------

    public static final String ROLE_CAS_LOGIN = ROLE_PREFIX + "CAS_LOGIN";

    public static final String ROLE_CAS_LOGOUT = ROLE_PREFIX + "CAS_LOGOUT";

    public static final String ROLE_CAS_CHANGE_PASSWORD = ROLE_PREFIX + "CAS_CHANGE_PASSWORD";

    public static final String ROLE_CAS_USERS = ROLE_PREFIX + "CAS_USERS";

    public static final String ROLE_CAS_SUBROGATIONS = ROLE_PREFIX + "CAS_SUBROGATIONS";

    public static final String ROLE_LOGBOOKS = "ROLE_LOGBOOKS";

    //------------------------------------ TECHNICAL CHECKS -------------------------------------------

    public static final String ROLE_CHECK_TENANTS = CHECK_ROLE_PREFIX + SERVICE_TENANTS;

    public static final String ROLE_CHECK_USERS = CHECK_ROLE_PREFIX + SERVICE_USERS;

    //------------------------------------ OPERATION -------------------------------------------

    public static final String SERVICE_OPERATIONS = "OPERATIONS";

    public static final String ROLE_GET_OPERATIONS = GET_ROLE_PREFIX + SERVICE_OPERATIONS;

    public static final String ROLE_GET_FILE_OPERATION = GET_ROLE_PREFIX + "FILE_" + SERVICE_OPERATIONS;

    //------------------------------------ ACCOUNTS -------------------------------------------

    public static final String SERVICE_ACCOUNTS = "ACCOUNTS";

    //------------------------------------ APPLICATIONS -------------------------------------------

    public static final String SERVICE_APPLICATIONS = "APPLICATIONS";

    //------------------------------------ ARCHIVE PROFILES -------------------------------------------

    public static final String SERVICE_ARCHIVES_PROFILES = "ARCHIVE_PROFILE";

    public static final String ROLE_GET_ALL_ACCESS_CONTRACTS = GET_ROLE_PREFIX + "ALL_ACCESS_CONTRACTS";

    public static final String ROLE_GET_TENANT_HOLDING = GET_ROLE_PREFIX + "TENANT_HOLDING";

    //------------------------------------ HIERARCHY PROFILES -----------------------------------------

    public static final String SERVICE_HIERARCHY_PROFILES = "HIERARCHY_PROFILES";

    // tests
    public static final String APP_SAE = "sae";

    public static final String APP_GED = "ged";

    //------------------------------------ ACCESS CONTRACT -----------------------------------------

    public static final String ROLE_CREATE_ACCESS_CONTRACTS = CREATE_ROLE_PREFIX + "ACCESS_CONTRACTS";

    //@formatter:off

    /**
     * List of the admin roles for the VITAMUI application.
     */

    private static final List<String> ADMIN_VITAMUI_ROLES = Arrays.asList(
            ROLE_GET_CUSTOMERS,
            ROLE_CREATE_CUSTOMERS,
            ROLE_UPDATE_CUSTOMERS,

            ROLE_CREATE_TENANTS_ALL_CUSTOMERS,
            ROLE_GET_ALL_TENANTS,
            ROLE_UPDATE_TENANTS_ALL_CUSTOMERS,

            ROLE_GET_PROVIDERS,
            ROLE_CREATE_PROVIDERS,
            ROLE_UPDATE_PROVIDERS,
            ROLE_DELETE_PROVIDERS,


            ROLE_GET_PROFILES_ALL_TENANTS,

            ROLE_GET_OWNERS,
            ROLE_CREATE_OWNERS,
            ROLE_UPDATE_OWNERS,

            ROLE_GET_SUBROGATIONS,
            ROLE_CREATE_SUBROGATIONS,
            ROLE_DELETE_SUBROGATIONS,
            ROLE_GET_USERS_SUBROGATIONS,
            ROLE_GET_GROUPS_SUBROGATIONS,

            ROLE_CAS_LOGIN,
            ROLE_CAS_LOGOUT,
            ROLE_CAS_CHANGE_PASSWORD,
            ROLE_CAS_USERS,
            ROLE_CAS_SUBROGATIONS,


            ROLE_CHECK_USERS, ROLE_CHECK_TENANTS,
            ROLE_CREATE_ACCESS_CONTRACTS
            );

    /**
     * List of all the roles in the VITAMUI application (including the admin roles present in the ADMIN_VITAMUI_ROLES list)
     */

    private static final List<String> ROLE_NAMES = Arrays.asList(
            ROLE_GET_USERS, ROLE_CREATE_USERS,
            ROLE_UPDATE_USERS,
            ROLE_UPDATE_STANDARD_USERS,
            ROLE_GENERIC_USERS,
            ROLE_MFA_USERS,
            ROLE_ANONYMIZATION_USERS,
            ROLE_UPDATE_ME_USERS,


            ROLE_GET_CUSTOMERS,
            ROLE_CREATE_CUSTOMERS,
            ROLE_UPDATE_CUSTOMERS,

            ROLE_GET_TENANTS,
            ROLE_CREATE_TENANTS,
            ROLE_CREATE_TENANTS_ALL_CUSTOMERS,
            ROLE_UPDATE_TENANTS,
            ROLE_GET_ALL_TENANTS,
            ROLE_UPDATE_TENANTS_ALL_CUSTOMERS,

            ROLE_GET_PROVIDERS,
            ROLE_CREATE_PROVIDERS,
            ROLE_UPDATE_PROVIDERS,
            ROLE_DELETE_PROVIDERS,

            ROLE_GET_GROUPS,
            ROLE_GET_ALL_GROUPS,
            ROLE_CREATE_GROUPS,
            ROLE_UPDATE_GROUPS,
            ROLE_DELETE_GROUPS,

            ROLE_GET_PROFILES,
            ROLE_CREATE_PROFILES,
            ROLE_UPDATE_PROFILES,
            ROLE_DELETE_PROFILES,
            ROLE_GET_PROFILES_ALL_TENANTS,

            ROLE_GET_OWNERS,
            ROLE_CREATE_OWNERS,
            ROLE_UPDATE_OWNERS,

            ROLE_GET_SUBROGATIONS,
            ROLE_CREATE_SUBROGATIONS,
            ROLE_DELETE_SUBROGATIONS,
            ROLE_GET_USERS_SUBROGATIONS,
            ROLE_GET_GROUPS_SUBROGATIONS,

            ROLE_CAS_LOGIN,
            ROLE_CAS_LOGOUT,
            ROLE_CAS_CHANGE_PASSWORD,
            ROLE_CAS_USERS,
            ROLE_CAS_SUBROGATIONS,

            ROLE_CHECK_USERS, ROLE_CHECK_TENANTS,
            ROLE_GET_ALL_ACCESS_CONTRACTS,
            ROLE_CREATE_ACCESS_CONTRACTS,

            ROLE_GET_OPERATIONS,
            ROLE_GET_FILE_OPERATION

            );
    //@formatter:on

    public static List<String> getAdminVitamUIRoleNames() {
        return new ArrayList<>(ADMIN_VITAMUI_ROLES);
    }

    public static List<String> getAllRoleNames() {
        return new ArrayList<>(ROLE_NAMES);
    }

    public static List<Role> getAdminVitamUIRoles() {
        return ADMIN_VITAMUI_ROLES.stream().map(Role::new).collect(Collectors.toList());
    }

    public static List<Role> getAllRoles() {
        return ROLE_NAMES.stream().map(Role::new).collect(Collectors.toList());
    }

    public static boolean checkIfRoleNameExists(final List<String> roleNames) {
        return roleNames.stream().allMatch(role -> ROLE_NAMES.contains(role));
    }

    public static boolean checkIfRoleExists(final List<Role> roles) {
        return roles.stream().allMatch(role -> ROLE_NAMES.contains(role.getName()));
    }

    public static List<String> getServicesByName(final String... serviceName) {
        return ROLE_NAMES.stream().filter(role -> StringUtils.endsWithAny(role, serviceName)).collect(Collectors.toList());
    }
}
