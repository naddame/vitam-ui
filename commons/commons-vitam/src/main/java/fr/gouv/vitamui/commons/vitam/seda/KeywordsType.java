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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Mots-clés.
 *
 * <p>Java class for KeywordsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="KeywordsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="KeywordContent" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}TextType"/&gt;
 *         &lt;element name="KeywordReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}IdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="KeywordType" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;fr:gouv:culture:archivesdefrance:seda:v2.1&gt;CodeKeywordType"&gt;
 *                 &lt;attribute name="listVersionID" type="{http://www.w3.org/2001/XMLSchema}token" default="edition 2009" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeywordsType", propOrder = {
    "keywordContent",
    "keywordReference",
    "keywordType"
})
public class KeywordsType {

    @XmlElement(name = "KeywordContent", required = true)
    protected TextType keywordContent;
    @XmlElement(name = "KeywordReference")
    protected IdentifierType keywordReference;
    @XmlElement(name = "KeywordType")
    protected KeywordsType.KeywordType keywordType;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the keywordContent property.
     *
     * @return
     *     possible object is
     *     {@link TextType }
     *
     */
    public TextType getKeywordContent() {
        return keywordContent;
    }

    /**
     * Sets the value of the keywordContent property.
     *
     * @param value
     *     allowed object is
     *     {@link TextType }
     *
     */
    public void setKeywordContent(TextType value) {
        this.keywordContent = value;
    }

    /**
     * Gets the value of the keywordReference property.
     *
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *
     */
    public IdentifierType getKeywordReference() {
        return keywordReference;
    }

    /**
     * Sets the value of the keywordReference property.
     *
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *
     */
    public void setKeywordReference(IdentifierType value) {
        this.keywordReference = value;
    }

    /**
     * Gets the value of the keywordType property.
     *
     * @return
     *     possible object is
     *     {@link KeywordsType.KeywordType }
     *
     */
    public KeywordsType.KeywordType getKeywordType() {
        return keywordType;
    }

    /**
     * Sets the value of the keywordType property.
     *
     * @param value
     *     allowed object is
     *     {@link KeywordsType.KeywordType }
     *
     */
    public void setKeywordType(KeywordsType.KeywordType value) {
        this.keywordType = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;fr:gouv:culture:archivesdefrance:seda:v2.1&gt;CodeKeywordType"&gt;
     *       &lt;attribute name="listVersionID" type="{http://www.w3.org/2001/XMLSchema}token" default="edition 2009" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class KeywordType {

        @XmlValue
        protected CodeKeywordType value;
        @XmlAttribute(name = "listVersionID")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "token")
        protected String listVersionID;

        /**
         * Table des types de mots-clés.
         *
         * @return
         *     possible object is
         *     {@link CodeKeywordType }
         *
         */
        public CodeKeywordType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value
         *     allowed object is
         *     {@link CodeKeywordType }
         *
         */
        public void setValue(CodeKeywordType value) {
            this.value = value;
        }

        /**
         * Gets the value of the listVersionID property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getListVersionID() {
            if (listVersionID == null) {
                return "edition 2009";
            } else {
                return listVersionID;
            }
        }

        /**
         * Sets the value of the listVersionID property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setListVersionID(String value) {
            this.listVersionID = value;
        }

    }

}
