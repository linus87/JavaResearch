//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.26 at 02:37:53 PM CST 
//


package com.linus.xml.jaxb.test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocDetach.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DocDetach">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="30"/>
 *     &lt;enumeration value="ShpRcpt"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DocDetach")
@XmlEnum
public enum DocDetach {

    @XmlEnumValue("ShpRcpt")
    SHP_RCPT("ShpRcpt");
    private final String value;

    DocDetach(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DocDetach fromValue(String v) {
        for (DocDetach c: DocDetach.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
