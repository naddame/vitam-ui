package fr.gouv.vitamui.iam.internal.server.tenant.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gouv.vitam.common.exception.InvalidParseOperationException;
import fr.gouv.vitam.common.exception.VitamClientException;
import fr.gouv.vitam.common.json.JsonHandler;
import fr.gouv.vitam.common.model.RequestResponse;
import fr.gouv.vitam.common.model.administration.AccessContractModel;
import fr.gouv.vitam.common.model.administration.IngestContractModel;
import fr.gouv.vitamui.commons.api.domain.AccessContractModelDto;
import fr.gouv.vitamui.commons.api.domain.IngestContractDto;
import fr.gouv.vitamui.commons.api.domain.TenantDto;
import fr.gouv.vitamui.commons.test.utils.ServerIdentityConfigurationBuilder;
import fr.gouv.vitamui.commons.vitam.api.administration.AccessContractService;
import fr.gouv.vitamui.commons.vitam.api.administration.IngestContractService;
import fr.gouv.vitamui.commons.vitam.api.dto.AccessContractResponseDto;
import fr.gouv.vitamui.commons.vitam.api.dto.IngestContractResponseDto;
import fr.gouv.vitamui.iam.internal.server.tenant.converter.TenantConverter;
import fr.gouv.vitamui.iam.internal.server.tenant.domain.Tenant;
import fr.gouv.vitamui.iam.security.service.InternalSecurityService;

@RunWith(MockitoJUnitRunner.class)
public class InitVitamTenantServiceTest {

    @InjectMocks
    private InitVitamTenantService initVitamTenantService;

    @Mock
    private AccessContractService accessContractService;

    @Mock
    private IngestContractService ingestContractService;

    @Mock
    private InternalSecurityService internalSecurityService;

    @Mock
    private TenantConverter tenantConverter;

    private final Resource ingestContractHolding = new ClassPathResource("data/tenant/ingest-contract/holding.json");

    private final Resource fullAccessAccessContract = new ClassPathResource(
            "data/tenant/access-contract/full-access.json");

    private final Resource logbookAccessContract = new ClassPathResource(
            "data/tenant/access-contract/full-access.json");

    private final Resource holdingAccessContract = new ClassPathResource("data/tenant/access-contract/holding.json");

    private AccessContractModelDto fullAccessAccessContractDto;

    private AccessContractModelDto logbookAccessContractDto;

    private AccessContractModelDto holdingAccessContractDto;

    private IngestContractDto ingestContractHoldingDto;

    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws InvalidParseOperationException, IOException {
        initVitamTenantService.setHoldingIngestContract(ingestContractHolding);
        initVitamTenantService.setFullAccessAccessContract(fullAccessAccessContract);
        initVitamTenantService.setLogbookAccessContract(logbookAccessContract);
        initVitamTenantService.setHoldingAccessContract(holdingAccessContract);
        initVitamTenantService.setObjectMapper(objectMapper);
        initVitamTenantService.setMandatory(true);
        Mockito.when(tenantConverter.convertEntityToDto(ArgumentMatchers.any())).thenCallRealMethod();
        Mockito.when(tenantConverter.convertDtoToEntity(ArgumentMatchers.any())).thenCallRealMethod();
        fullAccessAccessContractDto = JsonHandler.getFromInputStream(fullAccessAccessContract.getInputStream(),
                AccessContractModelDto.class);
        logbookAccessContractDto = JsonHandler.getFromInputStream(logbookAccessContract.getInputStream(),
                AccessContractModelDto.class);
        holdingAccessContractDto = JsonHandler.getFromInputStream(holdingAccessContract.getInputStream(),
                AccessContractModelDto.class);
        ingestContractHoldingDto = JsonHandler.getFromInputStream(ingestContractHolding.getInputStream(),
                IngestContractDto.class);
        ServerIdentityConfigurationBuilder.setup("identityName", "identityRole", 1, 0);

    }

    @Test
    public void initTenantIsNotMandatory() {
        initVitamTenantService.setMandatory(false);
        initVitamTenantService.init(new Tenant());
    }

    @Test
    public void initTenantSucceedAsAccessAndIngestContractAlreadyExist()
            throws VitamClientException, InvalidParseOperationException, JsonProcessingException {
        TenantDto tenantDto = new TenantDto();
        tenantDto.setIdentifier(10);
        RequestResponse<AccessContractModel> requestResponse = Mockito.mock(RequestResponse.class);
        Mockito.when(accessContractService.findAccessContracts(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(requestResponse);
        List<AccessContractModelDto> results = Arrays.asList(holdingAccessContractDto, logbookAccessContractDto,
                fullAccessAccessContractDto);
        JsonHandler.toJsonNode(results);
        AccessContractResponseDto response = new AccessContractResponseDto();
        response.setResults(results);
        Mockito.when(objectMapper.treeToValue(requestResponse.toJsonNode(), AccessContractResponseDto.class))
                .thenReturn(response);

        RequestResponse<IngestContractModel> requestResponseIngest = Mockito.mock(RequestResponse.class);
        Mockito.when(ingestContractService.findIngestContracts(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(requestResponseIngest);
        List<IngestContractDto> ingestsContract = Arrays.asList(ingestContractHoldingDto);
        JsonHandler.toJsonNode(results);
        IngestContractResponseDto responseIngest = new IngestContractResponseDto();
        responseIngest.setResults(ingestsContract);
        Mockito.when(objectMapper.treeToValue(requestResponse.toJsonNode(), IngestContractResponseDto.class))
                .thenReturn(responseIngest);

        initVitamTenantService.init(tenantDto);

    }
}
