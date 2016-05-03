package com.sforce.soap.local.retrieve.sobject;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OFSServer__MetadataLog__c complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="OFSServer__MetadataLog__c">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:sobject.enterprise.soap.sforce.com}sObject">
 *       &lt;sequence>
 *         &lt;element name="OFSServer__BaseOrgId__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__BaseOrgRefreshToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__BaseOrgToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__BaseOrgUrl__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__ID__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__OrganizationId__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__RecordId__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__SourceOrgRefreshToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__SourceOrgToken__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFSServer__SourceOrganizationURL__c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "OFSServer__MetadataLog__c", propOrder = { "ofsServeractionC","ofsServerBaseOrgIdC",
		"ofsServerBaseOrgRefreshTokenC", "ofsServerBaseOrgTokenC", "ofsServerBaseOrgUrlC",
		"ofsServeridc", "ofsServerOrganizationIdC", "ofsServerRecordIdC",
		"ofsServerSourceOrgRefreshTokenC", "ofsServerSourceOrgTokenC",
		"ofsServerSourceOrganizationURLC", "ofsServerTargetOrgTokenNonEncryptedC",
		"ofsServerTargetOrgTokenC" })
public class OFSServerMetadataLogC extends SObject {

	@XmlElementRef(name = "OFSServer__Action__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
    protected JAXBElement<String> ofsServeractionC;
	@XmlElementRef(name = "OFSServer__BaseOrgId__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerBaseOrgIdC;
	@XmlElementRef(name = "OFSServer__BaseOrgRefreshToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerBaseOrgRefreshTokenC;
	@XmlElementRef(name = "OFSServer__BaseOrgToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerBaseOrgTokenC;
	@XmlElementRef(name = "OFSServer__BaseOrgUrl__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerBaseOrgUrlC;
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
	@XmlElementRef(name = "OFSServer__SourceOrganizationURL__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerSourceOrganizationURLC;
	@XmlElementRef(name = "OFSServer__TargetOrgTokenNonEncrypted__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerTargetOrgTokenNonEncryptedC;
	@XmlElementRef(name = "OFSServer__TargetOrgToken__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerTargetOrgTokenC;

	@XmlElementRef(name = "OFSServer__Name__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerNameC;

	@XmlElementRef(name = "OFSServer__Status__c", namespace = "urn:sobject.enterprise.soap.sforce.com", type = JAXBElement.class)
	protected JAXBElement<String> ofsServerStatusC;

	
	/**
     * Gets the value of the actionC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getASAActionC() {
        return ofsServeractionC;
    }

    /**
     * Sets the value of the actionC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setASAActionC(JAXBElement<String> value) {
        this.ofsServeractionC = ((JAXBElement<String> ) value);
    }

	
	/**
	 * Gets the value of the ofsServerBaseOrgIdC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASABaseOrgIdC() {
		return ofsServerBaseOrgIdC;
	}

	/**
	 * Sets the value of the ofsServerBaseOrgIdC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASABaseOrgIdC(JAXBElement<String> value) {
		this.ofsServerBaseOrgIdC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerBaseOrgRefreshTokenC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASABaseOrgRefreshTokenC() {
		return ofsServerBaseOrgRefreshTokenC;
	}

	/**
	 * Sets the value of the ofsServerBaseOrgRefreshTokenC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASABaseOrgRefreshTokenC(JAXBElement<String> value) {
		this.ofsServerBaseOrgRefreshTokenC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerBaseOrgTokenC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASABaseOrgTokenC() {
		return ofsServerBaseOrgTokenC;
	}

	/**
	 * Sets the value of the ofsServerBaseOrgTokenC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASABaseOrgTokenC(JAXBElement<String> value) {
		this.ofsServerBaseOrgTokenC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerBaseOrgUrlC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASABaseOrgUrlC() {
		return ofsServerBaseOrgUrlC;
	}

	/**
	 * Sets the value of the ofsServerBaseOrgUrlC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASABaseOrgUrlC(JAXBElement<String> value) {
		this.ofsServerBaseOrgUrlC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServeridc property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASAIDC() {
		return ofsServeridc;
	}

	/**
	 * Sets the value of the ofsServeridc property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASAIDC(JAXBElement<String> value) {
		this.ofsServeridc = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerOrganizationIdC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASAOrganizationIdC() {
		return ofsServerOrganizationIdC;
	}

	/**
	 * Sets the value of the ofsServerOrganizationIdC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASAOrganizationIdC(JAXBElement<String> value) {
		this.ofsServerOrganizationIdC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerRecordIdC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASARecordIdC() {
		return ofsServerRecordIdC;
	}

	/**
	 * Sets the value of the ofsServerRecordIdC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASARecordIdC(JAXBElement<String> value) {
		this.ofsServerRecordIdC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerSourceOrgRefreshTokenC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASASourceOrgRefreshTokenC() {
		return ofsServerSourceOrgRefreshTokenC;
	}

	/**
	 * Sets the value of the ofsServerSourceOrgRefreshTokenC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASASourceOrgRefreshTokenC(JAXBElement<String> value) {
		this.ofsServerSourceOrgRefreshTokenC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerSourceOrgTokenC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASASourceOrgTokenC() {
		return ofsServerSourceOrgTokenC;
	}

	/**
	 * Sets the value of the ofsServerSourceOrgTokenC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASASourceOrgTokenC(JAXBElement<String> value) {
		this.ofsServerSourceOrgTokenC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerSourceOrganizationURLC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASASourceOrganizationURLC() {
		return ofsServerSourceOrganizationURLC;
	}

	/**
	 * Sets the value of the ofsServerSourceOrganizationURLC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASASourceOrganizationURLC(JAXBElement<String> value) {
		this.ofsServerSourceOrganizationURLC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerTargetOrgTokenNonEncryptedC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASATargetOrgTokenNonEncryptedC() {
		return ofsServerTargetOrgTokenNonEncryptedC;
	}

	/**
	 * Sets the value of the ofsServerTargetOrgTokenNonEncryptedC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASATargetOrgTokenNonEncryptedC(JAXBElement<String> value) {
		this.ofsServerTargetOrgTokenNonEncryptedC = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the ofsServerTargetOrgTokenC property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getASATargetOrgTokenC() {
		return ofsServerTargetOrgTokenC;
	}

	/**
	 * Sets the value of the ofsServerTargetOrgTokenC property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setASATargetOrgTokenC(JAXBElement<String> value) {
		this.ofsServerTargetOrgTokenC = ((JAXBElement<String>) value);
	}

	
	
	
}
