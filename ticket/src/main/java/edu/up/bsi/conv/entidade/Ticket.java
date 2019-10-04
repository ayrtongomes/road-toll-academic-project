//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.09.19 às 08:31:44 PM BRT 
//


package edu.up.bsi.conv.entidade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="praca" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cancela" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataHora" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="tarifa" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="categoria" type="{http://cancela.conv.up.edu.br/ticket}categoria"/>
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
    "praca",
    "cancela",
    "dataHora",
    "tarifa",
    "categoria"
})
@XmlRootElement(name = "ticket")
public class Ticket {

    @XmlElement(required = true)
    protected String praca;
    @XmlElement(required = true)
    protected String cancela;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHora;
    protected double tarifa;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Categoria categoria;

    /**
     * Obtém o valor da propriedade praca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPraca() {
        return praca;
    }

    /**
     * Define o valor da propriedade praca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPraca(String value) {
        this.praca = value;
    }

    /**
     * Obtém o valor da propriedade cancela.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancela() {
        return cancela;
    }

    /**
     * Define o valor da propriedade cancela.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancela(String value) {
        this.cancela = value;
    }

    /**
     * Obtém o valor da propriedade dataHora.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataHora() {
        return dataHora;
    }

    /**
     * Define o valor da propriedade dataHora.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataHora(XMLGregorianCalendar value) {
        this.dataHora = value;
    }

    /**
     * Obtém o valor da propriedade tarifa.
     * 
     */
    public double getTarifa() {
        return tarifa;
    }

    /**
     * Define o valor da propriedade tarifa.
     * 
     */
    public void setTarifa(double value) {
        this.tarifa = value;
    }

    /**
     * Obtém o valor da propriedade categoria.
     * 
     * @return
     *     possible object is
     *     {@link Categoria }
     *     
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Define o valor da propriedade categoria.
     * 
     * @param value
     *     allowed object is
     *     {@link Categoria }
     *     
     */
    public void setCategoria(Categoria value) {
        this.categoria = value;
    }

	@Override
	public String toString() {
		return "Ticket [praca=" + praca + ", cancela=" + cancela + ", dataHora=" + dataHora + ", tarifa=" + tarifa
				+ ", categoria=" + categoria + "]";
	}

}
