/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.response.importing;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.xml.common.importing.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.xml.common.importing.EconomicOperatorImplTransformer;
import eu.europa.ec.grow.espd.xml.common.importing.PartyImplTransformer;
import eu.europa.ec.grow.espd.xml.common.importing.UblRequestResponseImporter;
import grow.names.specification.ubl.schema.xsd.espd_commonaggregatecomponents_1.EconomicOperatorPartyType;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ContractFolderIDType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Create an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDResponseType}.
 * <p/>
 * Created by ratoico on 1/6/16 at 5:41 PM.
 */
@Component
@Slf4j
public class UblResponseImporter extends UblRequestResponseImporter {

	@Autowired
	public UblResponseImporter(PartyImplTransformer partyImplTransformer,
			EconomicOperatorImplTransformer economicOperatorImplTransformer,
			CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator) {
		super(partyImplTransformer, economicOperatorImplTransformer, criteriaToEspdDocumentPopulator);
	}

	/**
	 * Build an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDResponseType}.
	 *
	 * @param responseType The XML object structure of an ESPD Response
	 *
	 * @return An {@link EspdDocument} entity containing the information coming from the XML response file.
	 */
	public EspdDocument importResponse(ESPDResponseType responseType) {
		EspdDocument espd = buildEspdDocument(null, responseType);
		if (responseType.getIssueDate() != null && responseType.getIssueDate().getValue() != null) {
			espd.setDocumentDate(responseType.getIssueDate().getValue().toDate());
		}

		readSignatureInformation(responseType, espd);
		readConsortiumName(responseType, espd);

		return espd;
	}

	private void readSignatureInformation(ESPDResponseType responseType, EspdDocument espdDocument) {
		if (CollectionUtils.isEmpty(responseType.getSignature())) {
			return;
		}

		SignatureType signatureType = responseType.getSignature().get(0);
		PartyType signatoryParty = signatureType.getSignatoryParty();
		if (signatoryParty == null || signatoryParty.getPhysicalLocation() == null) {
			return;
		}

		if (signatoryParty.getPhysicalLocation().getName() != null) {
			espdDocument.setLocation(signatoryParty.getPhysicalLocation().getName().getValue());
		}
	}

	private void readConsortiumName(ESPDResponseType responseType, EspdDocument espdDocument) {
		if (responseType.getEconomicOperatorGroupName() == null) {
			return;
		}
		espdDocument.setConsortiumName(responseType.getEconomicOperatorGroupName().getValue());
	}

	@Override
	protected ContractingPartyType provideContractingParty(ESPDRequestType requestType, ESPDResponseType responseType) {
		return responseType.getContractingParty();
	}

	@Override
	protected EconomicOperatorPartyType provideEconomicOperatorParty(ESPDRequestType requestType,
			ESPDResponseType responseType) {
		return responseType.getEconomicOperatorParty();
	}

	@Override
	protected List<CriterionType> provideCriteria(ESPDRequestType requestType, ESPDResponseType responseType) {
		return responseType.getCriterion();
	}

	@Override
	protected List<ProcurementProjectLotType> provideProjectLots(ESPDRequestType requestType,
			ESPDResponseType responseType) {
		return responseType.getProcurementProjectLot();
	}

	@Override
	protected List<DocumentReferenceType> provideDocumentReferences(ESPDRequestType requestType,
			ESPDResponseType responseType) {
		return responseType.getAdditionalDocumentReference();
	}
	
	@Override
	protected List<DocumentReferenceType> provideTedDocumentReferences(ESPDRequestType requestType,
			ESPDResponseType responseType) {
		return responseType.getAdditionalDocumentReference();
	}

	@Override
	protected ContractFolderIDType provideContractFolder(ESPDRequestType requestType, ESPDResponseType responseType) {
		return responseType.getContractFolderID();
	}
	
	@Override
	protected void addRequestInformation(ESPDRequestType requestType, ESPDResponseType responseType,
			EspdDocument espdDocument) {
		List<DocumentReferenceType> documentReferences = provideDocumentReferences(requestType, responseType);
		EspdRequestMetadata metadata = readRequestMetadata(documentReferences);
		espdDocument.setRequestMetadata(metadata);
		if (StringUtils.isBlank(metadata.getId())) {
			log.warn("No ESPD Request information found for response '{}'.", getResponseId(responseType));
		}
	}
}
