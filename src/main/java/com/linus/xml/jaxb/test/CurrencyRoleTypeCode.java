//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.26 at 02:37:53 PM CST 
//


package com.linus.xml.jaxb.test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CurrencyRoleTypeCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CurrencyRoleTypeCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="5"/>
 *     &lt;enumeration value="BILLC"/>
 *     &lt;enumeration value="PULCL"/>
 *     &lt;enumeration value="INVCU"/>
 *     &lt;enumeration value="BASEC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CurrencyRoleTypeCode")
@XmlEnum
public enum CurrencyRoleTypeCode {

    BILLC,
    PULCL,
    INVCU,
    BASEC;

    public String value() {
        return name();
    }

    public static CurrencyRoleTypeCode fromValue(String v) {
        return valueOf(v);
    }

}
