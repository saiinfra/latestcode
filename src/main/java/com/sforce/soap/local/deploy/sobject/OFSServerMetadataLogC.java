
package com.sforce.soap.local.deploy.sobject;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for OFSServer__MetadataLog__c complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OFSServer__MetadataLog__c">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:sobject.enterprise.soap.sforce.com}sObject">
 *       &lt;sequence>
 *         &lt;element name="OFSServer__Action__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__BaseOrgId__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__BaseOrgRefreshToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__BaseOrgToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__BaseOrgUrl__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__ID__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__OrganizationId__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__RecordId__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__SourceOrgRefreshToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__SourceOrgToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__TargetOrgRefreshToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__TargetOrgTokenNonEncrypted__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__TargetOrgToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OFSServer__MetadataLog__c", propOrder = {
    "ofsServerActionC",
    "ofsServerBaseOrgIdC",
    "ofsServerBaseOrgRefreshTokenC",
    "ofsServerBaseOrgTokenC",
    "ofsServerBaseOrgUrlC",
    "ofsServerEnableVersionControlC",
    "ofsServergitServerURLC",
    "ofsServeridc",
    "ofsServerOrganizationIdC",
    "ofsServerRecordIdC",
    "ofsServerSourceOrgRefreshTokenC",
    "ofsServerSourceOrgTokenC",
    "ofsServersourceOrganizationURLC",
    "ofsServerTargetOrgRefreshTokenC",
    "ofsServerTargetOrgTokenNonEncryptedC",
    "ofsServerTargetOrgTokenC",
    "ofsServerNameC",
    "ofsServerStatusC"
})
public class OFSServerMetadataLogC
    extends SObject
{

    @XmlElementRef(name = "OFSServer__Action__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerActionC;
    @XmlElementRef(name = "OFSServer__BaseOrgId__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerBaseOrgIdC;
    @XmlElementRef(name = "OFSServer__BaseOrgRefreshToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerBaseOrgRefreshTokenC;
    @XmlElementRef(name = "OFSServer__BaseOrgToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerBaseOrgTokenC;
    @XmlElementRef(name = "OFSServer__BaseOrgUrl__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerBaseOrgUrlC;
    @XmlElementRef(name = "OFSServer__Enable_Version_Control__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ofsServerEnableVersionControlC;
    @XmlElementRef(name = "OFSServer__GIT_Server_URL__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ofsServergitServerURLC;
    @XmlElementRef(name = "OFSServer__ID__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServeridc;
    @XmlElementRef(name = "OFSServer__OrganizationId__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerOrganizationIdC;
    @XmlElementRef(name = "OFSServer__RecordId__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerRecordIdC;
    @XmlElementRef(name = "OFSServer__SourceOrgRefreshToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerSourceOrgRefreshTokenC;
    @XmlElementRef(name = "OFSServer__SourceOrgToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerSourceOrgTokenC;
    @XmlElementRef(name = "OFSServer__TargetOrgRefreshToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerTargetOrgRefreshTokenC;
    @XmlElementRef(name = "OFSServer__TargetOrgTokenNonEncrypted__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerTargetOrgTokenNonEncryptedC;
    @XmlElementRef(name = "OFSServer__TargetOrgToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServerTargetOrgTokenC;
    @XmlElementRef(name = "OFSServer__Name__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerNameC;

	@XmlElementRef(name = "OFSServer__Status__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerStatusC;
	
	@XmlElementRef(name = "OFSServer__SourceOrganizationURL__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServersourceOrganizationURLC;
	
	
    /**
     * Gets the value of the ofsServerActionC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerActionC() {
        return ofsServerActionC;
    }

    /**
     * Sets the value of the ofsServerActionC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerActionC(JAXBElement<String> value) {
        this.ofsServerActionC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerBaseOrgIdC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerBaseOrgIdC() {
        return ofsServerBaseOrgIdC;
    }

    /**
     * Sets the value of the ofsServerBaseOrgIdC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerBaseOrgIdC(JAXBElement<String> value) {
        this.ofsServerBaseOrgIdC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerBaseOrgRefreshTokenC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerBaseOrgRefreshTokenC() {
        return ofsServerBaseOrgRefreshTokenC;
    }

    /**
     * Sets the value of the ofsServerBaseOrgRefreshTokenC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerBaseOrgRefreshTokenC(JAXBElement<String> value) {
        this.ofsServerBaseOrgRefreshTokenC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerBaseOrgTokenC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerBaseOrgTokenC() {
        return ofsServerBaseOrgTokenC;
    }

    /**
     * Sets the value of the ofsServerBaseOrgTokenC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerBaseOrgTokenC(JAXBElement<String> value) {
        this.ofsServerBaseOrgTokenC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerBaseOrgUrlC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerBaseOrgUrlC() {
        return ofsServerBaseOrgUrlC;
    }

    /**
     * Sets the value of the ofsServerBaseOrgUrlC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerBaseOrgUrlC(JAXBElement<String> value) {
        this.ofsServerBaseOrgUrlC = ((JAXBElement<String> ) value);
    }
    
    /**
     * Gets the value of the ofsServerEnableVersionControlC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerEnableVersionControlC() {
        return ofsServerEnableVersionControlC;
    }

    /**
     * Sets the value of the ofsServerEnableVersionControlC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerEnableVersionControlC(JAXBElement<String> value) {
        this.ofsServerEnableVersionControlC = value;
    }

    /**
     * Gets the value of the ofsServergitServerURLC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerGITServerURLC() {
        return ofsServergitServerURLC;
    }

    /**
     * Sets the value of the ofsServergitServerURLC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerGITServerURLC(JAXBElement<String> value) {
        this.ofsServergitServerURLC = value;
    }


    /**
     * Gets the value of the ofsServeridc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerIDC() {
        return ofsServeridc;
    }

    /**
     * Sets the value of the ofsServeridc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerIDC(JAXBElement<String> value) {
        this.ofsServeridc = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerOrganizationIdC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerOrganizationIdC() {
        return ofsServerOrganizationIdC;
    }

    /**
     * Sets the value of the ofsServerOrganizationIdC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerOrganizationIdC(JAXBElement<String> value) {
        this.ofsServerOrganizationIdC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerRecordIdC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerRecordIdC() {
        return ofsServerRecordIdC;
    }

    /**
     * Sets the value of the ofsServerRecordIdC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerRecordIdC(JAXBElement<String> value) {
        this.ofsServerRecordIdC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerSourceOrgRefreshTokenC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerSourceOrgRefreshTokenC() {
        return ofsServerSourceOrgRefreshTokenC;
    }

    /**
     * Sets the value of the ofsServerSourceOrgRefreshTokenC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerSourceOrgRefreshTokenC(JAXBElement<String> value) {
        this.ofsServerSourceOrgRefreshTokenC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerSourceOrgTokenC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerSourceOrgTokenC() {
        return ofsServerSourceOrgTokenC;
    }

    /**
     * Sets the value of the ofsServerSourceOrgTokenC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerSourceOrgTokenC(JAXBElement<String> value) {
        this.ofsServerSourceOrgTokenC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerTargetOrgRefreshTokenC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerTargetOrgRefreshTokenC() {
        return ofsServerTargetOrgRefreshTokenC;
    }

    /**
     * Sets the value of the ofsServerTargetOrgRefreshTokenC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerTargetOrgRefreshTokenC(JAXBElement<String> value) {
        this.ofsServerTargetOrgRefreshTokenC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerTargetOrgTokenNonEncryptedC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerTargetOrgTokenNonEncryptedC() {
        return ofsServerTargetOrgTokenNonEncryptedC;
    }

    /**
     * Sets the value of the ofsServerTargetOrgTokenNonEncryptedC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerTargetOrgTokenNonEncryptedC(JAXBElement<String> value) {
        this.ofsServerTargetOrgTokenNonEncryptedC = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ofsServerTargetOrgTokenC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerTargetOrgTokenC() {
        return ofsServerTargetOrgTokenC;
    }

    /**
     * Sets the value of the ofsServerTargetOrgTokenC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerTargetOrgTokenC(JAXBElement<String> value) {
        this.ofsServerTargetOrgTokenC = ((JAXBElement<String> ) value);
    }

    /**
	 * Gets the value of the ofsServerNameC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getOFSServerNameC() {
		return ofsServerNameC;
	}

	/**
	 * Sets the value of the ofsServerNameC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setOFSServerNameC(JAXBElement<String> value) {
		this.ofsServerNameC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerStatusC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getOFSServerStatusC() {
		return ofsServerStatusC;
	}

	/**
	 * Sets the value of the ofsServerStatusC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setOFSServerStatusC(JAXBElement<String> value) {
		this.ofsServerStatusC = ((JAXBElement<String>) value);
	}

	  /**
     * Gets the value of the sourceOrganizationURLC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOFSServerSourceOrganizationURLC() {
        return ofsServersourceOrganizationURLC;
    }

    /**
     * Sets the value of the sourceOrganizationURLC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOFSServerSourceOrganizationURLC(JAXBElement<String> value) {
        this.ofsServersourceOrganizationURLC = ((JAXBElement<String> ) value);
    }


}

