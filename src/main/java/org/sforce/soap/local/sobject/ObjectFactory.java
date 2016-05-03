
package org.sforce.soap.local.sobject;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sforce.soap.enterprise.sobject package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _OFSClientMetadataLogInformationCOFSClientTargetOrgTokenNonEncryptedC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__TargetOrgTokenNonEncrypted__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientIDC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__ID__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientActionC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__Action__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientBaseOrgIdC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__BaseOrgId__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientStatusC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__Status__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientBaseOrgTokenC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__BaseOrgToken__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientSourceOrganizationURLC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__SourceOrganizationURL__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientTargetOrgRefreshTokenC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__TargetOrgRefreshToken__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientRecordIdC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__RecordId__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientSourceOrgTokenC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__SourceOrgToken__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientTargetOrgTokenC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__TargetOrgToken__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientBaseOrgUrlC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__BaseOrgUrl__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientBaseOrgRefreshTokenC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__BaseOrgRefreshToken__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientOrganizationIdC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__OrganizationId__c");
    private final static QName _OFSClientMetadataLogInformationCOFSClientSourceOrgRefreshTokenC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OFSClient__SourceOrgRefreshToken__c");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sforce.soap.enterprise.sobject
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SObject }
     * 
     */
    public com.sforce.soap.enterprise.sobject.SObject createSObject() {
        return new com.sforce.soap.enterprise.sobject.SObject();
    }

    /**
     * Create an instance of {@link AggregateResult }
     * 
     */
    public AggregateResult createAggregateResult() {
        return new AggregateResult();
    }

    /**
     * Create an instance of {@link OFSClientMetadataLogInformationC }
     * 
     */
    public OFSClientMetadataLogInformationC createOFSClientMetadataLogInformationC() {
        return new OFSClientMetadataLogInformationC();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__TargetOrgTokenNonEncrypted__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientTargetOrgTokenNonEncryptedC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientTargetOrgTokenNonEncryptedC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__ID__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientIDC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientIDC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__Action__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientActionC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientActionC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__BaseOrgId__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientBaseOrgIdC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientBaseOrgIdC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__Status__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientStatusC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientStatusC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__BaseOrgToken__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientBaseOrgTokenC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientBaseOrgTokenC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__SourceOrganizationURL__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientSourceOrganizationURLC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientSourceOrganizationURLC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__TargetOrgRefreshToken__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientTargetOrgRefreshTokenC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientTargetOrgRefreshTokenC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__RecordId__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientRecordIdC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientRecordIdC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__SourceOrgToken__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientSourceOrgTokenC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientSourceOrgTokenC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__TargetOrgToken__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientTargetOrgTokenC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientTargetOrgTokenC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__BaseOrgUrl__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientBaseOrgUrlC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientBaseOrgUrlC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__BaseOrgRefreshToken__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientBaseOrgRefreshTokenC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientBaseOrgRefreshTokenC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__OrganizationId__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientOrganizationIdC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientOrganizationIdC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OFSClient__SourceOrgRefreshToken__c", scope = OFSClientMetadataLogInformationC.class)
    public JAXBElement<String> createOFSClientMetadataLogInformationCOFSClientSourceOrgRefreshTokenC(String value) {
        return new JAXBElement<String>(_OFSClientMetadataLogInformationCOFSClientSourceOrgRefreshTokenC_QNAME, String.class, OFSClientMetadataLogInformationC.class, value);
    }

}
