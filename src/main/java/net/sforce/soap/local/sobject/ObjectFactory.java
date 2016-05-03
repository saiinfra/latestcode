
package net.sforce.soap.local.sobject;

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

    private final static QName _MetadataLogCStatusC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Status__c");
    private final static QName _MetadataLogCScriptC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Script__c");
    private final static QName _MetadataLogCIDC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "ID__c");
    private final static QName _MetadataLogCExecutionResultsC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Execution_Results__c");
    private final static QName _MetadataLogCNameC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Name__c");
    private final static QName _MetadataLogCActionC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Action__c");
    private final static QName _MetadataLogCRecordIdC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "RecordId__c");
    private final static QName _MetadataLogCMessageC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Message__c");
    private final static QName _MetadataLogCTestInformationC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Test_Information__c");

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
     * Create an instance of {@link MetadataLogC }
     * 
     */
    public MetadataLogC createMetadataLogC() {
        return new MetadataLogC();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Status__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCStatusC(String value) {
        return new JAXBElement<String>(_MetadataLogCStatusC_QNAME, String.class, MetadataLogC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Script__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCScriptC(String value) {
        return new JAXBElement<String>(_MetadataLogCScriptC_QNAME, String.class, MetadataLogC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "ID__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCIDC(String value) {
        return new JAXBElement<String>(_MetadataLogCIDC_QNAME, String.class, MetadataLogC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Execution_Results__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCExecutionResultsC(String value) {
        return new JAXBElement<String>(_MetadataLogCExecutionResultsC_QNAME, String.class, MetadataLogC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Name__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCNameC(String value) {
        return new JAXBElement<String>(_MetadataLogCNameC_QNAME, String.class, MetadataLogC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Action__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCActionC(String value) {
        return new JAXBElement<String>(_MetadataLogCActionC_QNAME, String.class, MetadataLogC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "RecordId__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCRecordIdC(String value) {
        return new JAXBElement<String>(_MetadataLogCRecordIdC_QNAME, String.class, MetadataLogC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Message__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCMessageC(String value) {
        return new JAXBElement<String>(_MetadataLogCMessageC_QNAME, String.class, MetadataLogC.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Test_Information__c", scope = MetadataLogC.class)
    public JAXBElement<String> createMetadataLogCTestInformationC(String value) {
        return new JAXBElement<String>(_MetadataLogCTestInformationC_QNAME, String.class, MetadataLogC.class, value);
    }

}
