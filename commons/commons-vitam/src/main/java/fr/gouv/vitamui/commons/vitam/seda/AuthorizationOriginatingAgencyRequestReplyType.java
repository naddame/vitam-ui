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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.02.12 at 11:35:36 AM CET
//


package fr.gouv.vitamui.commons.vitam.seda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorizationOriginatingAgencyRequestReplyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AuthorizationOriginatingAgencyRequestReplyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}BusinessAuthorizationRequestReplyMessageType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/&gt;
 *         &lt;element name="OriginatingAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizationOriginatingAgencyRequestReplyType", propOrder = {
    "archivalAgency",
    "originatingAgency"
})
public class AuthorizationOriginatingAgencyRequestReplyType
    extends BusinessAuthorizationRequestReplyMessageType
{

    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    @XmlElement(name = "OriginatingAgency", required = true)
    protected OrganizationWithIdType originatingAgency;

    /**
     * Gets the value of the archivalAgency property.
     *
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *
     */
    public OrganizationWithIdType getArchivalAgency() {
        return archivalAgency;
    }

    /**
     * Sets the value of the archivalAgency property.
     *
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *
     */
    public void setArchivalAgency(OrganizationWithIdType value) {
        this.archivalAgency = value;
    }

    /**
     * Gets the value of the originatingAgency property.
     *
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *
     */
    public OrganizationWithIdType getOriginatingAgency() {
        return originatingAgency;
    }

    /**
     * Sets the value of the originatingAgency property.
     *
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *
     */
    public void setOriginatingAgency(OrganizationWithIdType value) {
        this.originatingAgency = value;
    }

}
