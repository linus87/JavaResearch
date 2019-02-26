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
 * <p>Java class for InvLanguageCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InvLanguageCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="2"/>
 *     &lt;enumeration value="bg"/>
 *     &lt;enumeration value="br"/>
 *     &lt;enumeration value="cs"/>
 *     &lt;enumeration value="da"/>
 *     &lt;enumeration value="de"/>
 *     &lt;enumeration value="el"/>
 *     &lt;enumeration value="en"/>
 *     &lt;enumeration value="ep"/>
 *     &lt;enumeration value="et"/>
 *     &lt;enumeration value="fi"/>
 *     &lt;enumeration value="fr"/>
 *     &lt;enumeration value="he"/>
 *     &lt;enumeration value="hr"/>
 *     &lt;enumeration value="hu"/>
 *     &lt;enumeration value="is"/>
 *     &lt;enumeration value="it"/>
 *     &lt;enumeration value="lt"/>
 *     &lt;enumeration value="lv"/>
 *     &lt;enumeration value="mk"/>
 *     &lt;enumeration value="nl"/>
 *     &lt;enumeration value="no"/>
 *     &lt;enumeration value="pl"/>
 *     &lt;enumeration value="pt"/>
 *     &lt;enumeration value="ro"/>
 *     &lt;enumeration value="ru"/>
 *     &lt;enumeration value="si"/>
 *     &lt;enumeration value="sk"/>
 *     &lt;enumeration value="sp"/>
 *     &lt;enumeration value="sq"/>
 *     &lt;enumeration value="sr"/>
 *     &lt;enumeration value="sv"/>
 *     &lt;enumeration value="tr"/>
 *     &lt;enumeration value="uk"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InvLanguageCode")
@XmlEnum
public enum InvLanguageCode {

    @XmlEnumValue("bg")
    BG("bg"),
    @XmlEnumValue("br")
    BR("br"),
    @XmlEnumValue("cs")
    CS("cs"),
    @XmlEnumValue("da")
    DA("da"),
    @XmlEnumValue("de")
    DE("de"),
    @XmlEnumValue("el")
    EL("el"),
    @XmlEnumValue("en")
    EN("en"),
    @XmlEnumValue("ep")
    EP("ep"),
    @XmlEnumValue("et")
    ET("et"),
    @XmlEnumValue("fi")
    FI("fi"),
    @XmlEnumValue("fr")
    FR("fr"),
    @XmlEnumValue("he")
    HE("he"),
    @XmlEnumValue("hr")
    HR("hr"),
    @XmlEnumValue("hu")
    HU("hu"),
    @XmlEnumValue("is")
    IS("is"),
    @XmlEnumValue("it")
    IT("it"),
    @XmlEnumValue("lt")
    LT("lt"),
    @XmlEnumValue("lv")
    LV("lv"),
    @XmlEnumValue("mk")
    MK("mk"),
    @XmlEnumValue("nl")
    NL("nl"),
    @XmlEnumValue("no")
    NO("no"),
    @XmlEnumValue("pl")
    PL("pl"),
    @XmlEnumValue("pt")
    PT("pt"),
    @XmlEnumValue("ro")
    RO("ro"),
    @XmlEnumValue("ru")
    RU("ru"),
    @XmlEnumValue("si")
    SI("si"),
    @XmlEnumValue("sk")
    SK("sk"),
    @XmlEnumValue("sp")
    SP("sp"),
    @XmlEnumValue("sq")
    SQ("sq"),
    @XmlEnumValue("sr")
    SR("sr"),
    @XmlEnumValue("sv")
    SV("sv"),
    @XmlEnumValue("tr")
    TR("tr"),
    @XmlEnumValue("uk")
    UK("uk");
    private final String value;

    InvLanguageCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InvLanguageCode fromValue(String v) {
        for (InvLanguageCode c: InvLanguageCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
