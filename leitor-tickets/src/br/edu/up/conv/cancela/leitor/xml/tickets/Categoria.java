//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.09.19 às 08:31:44 PM BRT 
//


package br.edu.up.conv.cancela.leitor.xml.tickets;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de categoria.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="categoria">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="C1"/>
 *     &lt;enumeration value="C2"/>
 *     &lt;enumeration value="C3"/>
 *     &lt;enumeration value="C4"/>
 *     &lt;enumeration value="C5"/>
 *     &lt;enumeration value="C6"/>
 *     &lt;enumeration value="C7"/>
 *     &lt;enumeration value="C8"/>
 *     &lt;enumeration value="C9"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "categoria")
@XmlEnum
public enum Categoria {

    @XmlEnumValue("C1")
    C_1("C1"),
    @XmlEnumValue("C2")
    C_2("C2"),
    @XmlEnumValue("C3")
    C_3("C3"),
    @XmlEnumValue("C4")
    C_4("C4"),
    @XmlEnumValue("C5")
    C_5("C5"),
    @XmlEnumValue("C6")
    C_6("C6"),
    @XmlEnumValue("C7")
    C_7("C7"),
    @XmlEnumValue("C8")
    C_8("C8"),
    @XmlEnumValue("C9")
    C_9("C9");
    private final String value;

    Categoria(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Categoria fromValue(String v) {
        for (Categoria c: Categoria.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
