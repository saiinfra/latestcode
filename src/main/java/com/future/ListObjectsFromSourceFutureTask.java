package com.future;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;

import com.domain.MetaBean;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.metadata.DescribeMetadataObject;
import com.sforce.soap.metadata.DescribeMetadataResult;
import com.sforce.soap.metadata.FileProperties;
import com.sforce.soap.metadata.ListMetadataQuery;
import com.sforce.ws.ConnectionException;
import com.util.Constants;
import com.util.SFoAuthHandle;

/**
 * 
 * @author ListObjectsFromSourceFutureTask Used For Providing List of Objects
 *         From Source
 *
 */
public class ListObjectsFromSourceFutureTask implements Callable<List<MetaBean>> {
	private long waitTime;
	String metadataLogId;
	SFoAuthHandle sfHandle;
	String contentType;

	public ListObjectsFromSourceFutureTask(String metadataLogId, String contentType, SFoAuthHandle sfHandle) {
		this.metadataLogId = metadataLogId;
		this.contentType = contentType;
		this.sfHandle = sfHandle;
	}

	@Override
	public List<MetaBean> call() throws Exception {
		// MetaBean[] objlist = null;
		List<MetaBean> list = new ArrayList<MetaBean>();
		com.sforce.soap.enterprise.sobject.Folder retObj = null;
		MetaBean bean = null;
		try {
			ListMetadataQuery query = null;
			query = new ListMetadataQuery();
			query.setType(this.contentType);
			String Emailtype = "Email";
			String ReportType = "Report";
			String DashboardType = "Dashboard";
			String DocumentType = "Document";

			double asOfVersion = (new Double(Constants.API_VERSION)).doubleValue();
			// Assuming that the SOAP binding has already been established.
			if (this.contentType == "Report") {

				query.setFolder("unfiled$public");

				FileProperties[] lmr = sfHandle.getMetadataConnection().listMetadata(new ListMetadataQuery[] { query },
						asOfVersion);

				// Write the name of each sObject to the console
				for (int j = 0; j < lmr.length; j++) {

					String objName = lmr[j].getFullName().trim();
					if (!objName.startsWith("agf")) {

						String fullname = java.net.URLDecoder.decode(lmr[j].getFullName(), "UTF-8");

						bean = new MetaBean(metadataLogId, fullname, lmr[j].getType(), sfHandle.getOrgId(),
								lmr[j].getLastModifiedById(), lmr[j].getLastModifiedByName(),
								lmr[j].getLastModifiedDate());
						list.add(bean);
					}
				}
			}
			

			if (this.contentType == "Report") {
				try {

					String emailSQL = "Select Id, Name,Type,DeveloperName" + " FROM Folder where Type = '" + ReportType + "'";
					EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
					QueryResult queryResults = conn.query(emailSQL);
					System.out.println("Query Size...." + queryResults.getSize());
					if (queryResults.getSize() > 0) {
						for (int i = 0; i < queryResults.getRecords().length; i++) {
							// cast the SObject to a strongly-typed Contact
							retObj = (com.sforce.soap.enterprise.sobject.Folder) queryResults.getRecords()[i];

							try {
								query.setFolder(retObj.getDeveloperName());

							//	describeMetadata(sfHandle);
								// query.setFolder(retObj.getName().replaceAll("\\s",""));

							}

							catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println("Folder name" + retObj.getName());
							System.out.println("Access Type" + retObj.getAccessType());

							FileProperties[] lmr = sfHandle.getMetadataConnection()
									.listMetadata(new ListMetadataQuery[] { query }, asOfVersion);

							// Write the name of each sObject to the console
							for (int j = 0; j < lmr.length; j++) {
								System.out.println("Object Name Size ....." + lmr.length);

								String objName = lmr[j].getFullName().trim();
								if (!objName.startsWith("agf")) {

									String fullname = java.net.URLDecoder.decode(lmr[j].getFullName(), "UTF-8");

									System.out.println("Object Name FULL NAME ....." + fullname);

									bean = new MetaBean(metadataLogId, fullname, lmr[j].getType(), sfHandle.getOrgId(),
											lmr[j].getLastModifiedById(), lmr[j].getLastModifiedByName(),
											lmr[j].getLastModifiedDate());
									list.add(bean);
								}
								System.out.println("Total Count of loop 2 " + j);
							}

							System.out.println("Total Count of loop 1 " + i);
						}
					}

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

			if (this.contentType == "EmailTemplate") {
				try {
					String emailSQL = "Select Id, Name,Type" + " FROM Folder where Type = '" + Emailtype + "'";
					EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
					QueryResult queryResults = conn.query(emailSQL);
					System.out.println("Query Size...." + queryResults.getSize());
					if (queryResults.getSize() > 0) {

						for (int i = 0; i < queryResults.getRecords().length; i++) {
							// cast the SObject to a strongly-typed Contact
							retObj = (com.sforce.soap.enterprise.sobject.Folder) queryResults.getRecords()[i];
							query.setFolder(retObj.getName());
							FileProperties[] lmr = sfHandle.getMetadataConnection()
									.listMetadata(new ListMetadataQuery[] { query }, asOfVersion);

							// Write the name of each sObject to the console
							for (int j = 0; j < lmr.length; j++) {

								String objName = lmr[j].getFullName().trim();
								if (!objName.startsWith("agf")) {

									String fullname = java.net.URLDecoder.decode(lmr[j].getFullName(), "UTF-8");

									bean = new MetaBean(metadataLogId, fullname, lmr[j].getType(), sfHandle.getOrgId(),
											lmr[j].getLastModifiedById(), lmr[j].getLastModifiedByName(),
											lmr[j].getLastModifiedDate());
									list.add(bean);
								}
							}

						}
					}

				} catch (Exception e) {

					e.printStackTrace();

				}

			}
			if (this.contentType == "Dashboard") {
				try {
					String emailSQL = "Select Id, Name,Type" + " FROM Folder where Type = '" + DashboardType + "'";
					EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
					QueryResult queryResults = conn.query(emailSQL);
					System.out.println("Query Size...." + queryResults.getSize());
					if (queryResults.getSize() > 0) {
						for (int i = 0; i < queryResults.getRecords().length; i++) {
							// cast the SObject to a strongly-typed Contact
							retObj = (com.sforce.soap.enterprise.sobject.Folder) queryResults.getRecords()[i];
							query.setFolder(retObj.getName());
							FileProperties[] lmr = sfHandle.getMetadataConnection()
									.listMetadata(new ListMetadataQuery[] { query }, asOfVersion);

							// Write the name of each sObject to the console
							for (int j = 0; j < lmr.length; j++) {

								String objName = lmr[j].getFullName().trim();
								if (!objName.startsWith("agf")) {

									String fullname = java.net.URLDecoder.decode(lmr[j].getFullName(), "UTF-8");

									bean = new MetaBean(metadataLogId, fullname, lmr[j].getType(), sfHandle.getOrgId(),
											lmr[j].getLastModifiedById(), lmr[j].getLastModifiedByName(),
											lmr[j].getLastModifiedDate());
									list.add(bean);
								}
							}

						}
					}

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

			if (this.contentType == "Document") {
				try {
					String emailSQL = "Select Id, Name,Type" + " FROM Folder where Type = '" + DocumentType + "'";
					EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
					QueryResult queryResults = conn.query(emailSQL);
					System.out.println("Query Size...." + queryResults.getSize());
					if (queryResults.getSize() > 0) {
						for (int i = 0; i < queryResults.getRecords().length; i++) {
							// cast the SObject to a strongly-typed Contact
							retObj = (com.sforce.soap.enterprise.sobject.Folder) queryResults.getRecords()[i];
							query.setFolder(retObj.getName());
							FileProperties[] lmr = sfHandle.getMetadataConnection()
									.listMetadata(new ListMetadataQuery[] { query }, asOfVersion);

							// Write the name of each sObject to the console
							for (int j = 0; j < lmr.length; j++) {

								String objName = lmr[j].getFullName().trim();
								if (!objName.startsWith("agf")) {

									String fullname = java.net.URLDecoder.decode(lmr[j].getFullName(), "UTF-8");

									bean = new MetaBean(metadataLogId, fullname, lmr[j].getType(), sfHandle.getOrgId(),
											lmr[j].getLastModifiedById(), lmr[j].getLastModifiedByName(),
											lmr[j].getLastModifiedDate());
									list.add(bean);
								}
							}

						}
					}

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

			else {
				FileProperties[] lmr = sfHandle.getMetadataConnection().listMetadata(new ListMetadataQuery[] { query },
						asOfVersion);

				// Write the name of each sObject to the console
				for (int j = 0; j < lmr.length; j++) {

					String objName = lmr[j].getFullName().trim();
					if (!objName.startsWith("agf")) {

						String fullname = java.net.URLDecoder.decode(lmr[j].getFullName(), "UTF-8");

						bean = new MetaBean(metadataLogId, fullname, lmr[j].getType(), sfHandle.getOrgId(),
								lmr[j].getLastModifiedById(), lmr[j].getLastModifiedByName(),
								lmr[j].getLastModifiedDate());
						list.add(bean);
					}
				}
			}
			if (sfHandle != null) {
				sfHandle.nullify();
			}
			sfHandle = null;
		} catch (ConnectionException ce) {
			System.out.println(ce.toString());
			if (sfHandle != null) {
				sfHandle.nullify();
			}
			sfHandle = null;
			throw new SFException(ce.toString(), SFErrorCodes.SFObjects_List_Error);
		} catch (Exception ce) {
			if (sfHandle != null) {
				sfHandle.nullify();
			}
			sfHandle = null;
			throw new SFException(ce.toString(), SFErrorCodes.SFObjects_List_Error);
		}
		return list;
	}

	public List<MetaBean> callBackup() throws Exception {
		// MetaBean[] objlist = null;
		List<MetaBean> list = new ArrayList<MetaBean>();
		MetaBean bean = null;
		try {
			ListMetadataQuery query = null;
			for (int i = 0; i < Constants.SFTypes.length; i++) {
				query = new ListMetadataQuery();
				query.setType(Constants.SFTypes[i]);
				// query.setFolder(null);
				double asOfVersion = (new Double(Constants.API_VERSION)).doubleValue();
				// Assuming that the SOAP binding has already been established.
				FileProperties[] lmr = sfHandle.getMetadataConnection().listMetadata(new ListMetadataQuery[] { query },
						asOfVersion);

				// Write the name of each sObject to the console
				for (int j = 0; j < lmr.length; j++) {
					String objName = lmr[j].getFullName().trim();
					if (!objName.startsWith("agf")) {
						Calendar c = lmr[j].getLastModifiedDate();
						c.setTime(c.getTime());
						bean = new MetaBean(metadataLogId, lmr[j].getFullName(), lmr[j].getType(), sfHandle.getOrgId(),
								lmr[j].getLastModifiedById(), lmr[j].getLastModifiedByName(), c);
						list.add(bean);
						/*
						 * System.out.println("listing retrieved objects----" +
						 * bean.getName());
						 */
					}
				}
			}
			if (sfHandle != null) {
				sfHandle.nullify();
			}
			sfHandle = null;
		} catch (ConnectionException ce) {
			System.out.println(ce.toString());
			if (sfHandle != null) {
				sfHandle.nullify();
			}
			sfHandle = null;
			throw new SFException(ce.toString(), SFErrorCodes.SFObjects_List_Error);
		} catch (Exception ce) {
			if (sfHandle != null) {
				sfHandle.nullify();
			}
			sfHandle = null;
			throw new SFException(ce.toString(), SFErrorCodes.SFObjects_List_Error);
		}
		return list;
	}

	public void describeMetadata(SFoAuthHandle sfhandle) {
		try {
			double apiVersion = 21.0;
			// Assuming that the SOAP binding has already been established.
			DescribeMetadataResult res = sfhandle.getMetadataConnection().describeMetadata(apiVersion);
			StringBuffer sb = new StringBuffer();
			if (res != null && res.getMetadataObjects().length > 0) {
				for (DescribeMetadataObject obj : res.getMetadataObjects()) {
					sb.append("***************************************************\n");
					sb.append("XMLName: " + obj.getXmlName() + "\n");
					sb.append("DirName: " + obj.getDirectoryName() + "\n");
					sb.append("Suffix: " + obj.getSuffix() + "\n");
					sb.append("***************************************************\n");
				}
			} else {
				sb.append("Failed to obtain metadata types.");
			}
			System.out.println(sb.toString());
		} catch (ConnectionException ce) {
			ce.printStackTrace();
		}
	}
}
