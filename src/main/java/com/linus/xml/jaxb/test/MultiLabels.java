//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.26 at 02:37:53 PM CST 
//


package com.linus.xml.jaxb.test;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MultiLabels complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultiLabels">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MultiLabel" maxOccurs="99">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DocName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DocFormat" type="{http://www.dhl.com/datatypes_global}DocFormat"/>
 *                   &lt;element name="DocImageVal" type="{http://www.dhl.com/datatypes_global}DocImageVal"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiLabels", propOrder = {
    "multiLabel"
})
public class MultiLabels {

    @XmlElement(name = "MultiLabel", required = true)
    protected List<MultiLabels.MultiLabel> multiLabel;

    /**
     * Gets the value of the multiLabel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the multiLabel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMultiLabel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MultiLabels.MultiLabel }
     * 
     * 
     */
    public List<MultiLabels.MultiLabel> getMultiLabel() {
        if (multiLabel == null) {
            multiLabel = new ArrayList<MultiLabels.MultiLabel>();
        }
        return this.multiLabel;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="DocName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DocFormat" type="{http://www.dhl.com/datatypes_global}DocFormat"/>
     *         &lt;element name="DocImageVal" type="{http://www.dhl.com/datatypes_global}DocImageVal"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "docName",
        "docFormat",
        "docImageVal"
    })
    public static class MultiLabel {

        @XmlElement(name = "DocName", required = true)
        protected String docName;
        @XmlElement(name = "DocFormat", required = true)
        protected String docFormat;
        @XmlElement(name = "DocImageVal", required = true)
        protected byte[] docImageVal;

        /**
         * Gets the value of the docName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDocName() {
            return docName;
        }

        /**
         * Sets the value of the docName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDocName(String value) {
            this.docName = value;
        }

        /**
         * Gets the value of the docFormat property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDocFormat() {
            return docFormat;
        }

        /**
         * Sets the value of the docFormat property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDocFormat(String value) {
            this.docFormat = value;
        }

        /**
         * Gets the value of the docImageVal property.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getDocImageVal() {
            return docImageVal;
        }

        /**
         * Sets the value of the docImageVal property.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setDocImageVal(byte[] value) {
            this.docImageVal = value;
        }

    }

}
