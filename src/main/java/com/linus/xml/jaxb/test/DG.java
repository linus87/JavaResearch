//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.26 at 02:37:53 PM CST 
//


package com.linus.xml.jaxb.test;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Dangerous Goods
 * 
 * <p>Java class for DG complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DG_ContentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DG_LabelDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DG_NetWeight" type="{http://www.dhl.com/datatypes_global}Weight" minOccurs="0"/>
 *         &lt;element name="DG_UOM" type="{http://www.dhl.com/datatypes_global}DG_UOM" minOccurs="0"/>
 *         &lt;element name="DG_UNCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DG", propOrder = {
    "dgContentID",
    "dgLabelDesc",
    "dgNetWeight",
    "dguom",
    "dgunCode"
})
public class DG {

    @XmlElement(name = "DG_ContentID", required = true)
    protected String dgContentID;
    @XmlElement(name = "DG_LabelDesc")
    protected String dgLabelDesc;
    @XmlElement(name = "DG_NetWeight")
    protected BigDecimal dgNetWeight;
    @XmlElement(name = "DG_UOM")
    @XmlSchemaType(name = "string")
    protected DGUOM dguom;
    @XmlElement(name = "DG_UNCode")
    protected String dgunCode;

    /**
     * Gets the value of the dgContentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDGContentID() {
        return dgContentID;
    }

    /**
     * Sets the value of the dgContentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDGContentID(String value) {
        this.dgContentID = value;
    }

    /**
     * Gets the value of the dgLabelDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDGLabelDesc() {
        return dgLabelDesc;
    }

    /**
     * Sets the value of the dgLabelDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDGLabelDesc(String value) {
        this.dgLabelDesc = value;
    }

    /**
     * Gets the value of the dgNetWeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDGNetWeight() {
        return dgNetWeight;
    }

    /**
     * Sets the value of the dgNetWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDGNetWeight(BigDecimal value) {
        this.dgNetWeight = value;
    }

    /**
     * Gets the value of the dguom property.
     * 
     * @return
     *     possible object is
     *     {@link DGUOM }
     *     
     */
    public DGUOM getDGUOM() {
        return dguom;
    }

    /**
     * Sets the value of the dguom property.
     * 
     * @param value
     *     allowed object is
     *     {@link DGUOM }
     *     
     */
    public void setDGUOM(DGUOM value) {
        this.dguom = value;
    }

    /**
     * Gets the value of the dgunCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDGUNCode() {
        return dgunCode;
    }

    /**
     * Sets the value of the dgunCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDGUNCode(String value) {
        this.dgunCode = value;
    }

}
