package com.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.domain.MetaBean;

public class CsvFileWriter {

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	//private static final String FILE_HEADER = "OFSServer__Name__c,OFSServer__Type__c,OFSServer__OrganizationId__c,OFSServer__MetadataLog__r.Name,OFSServer__LastModifiedById__c,OFSServer__LastModifiedByName__c";
	private static final String FILE_HEADER = "OFSServer__Name__c,OFSServer__Type__c,OFSServer__OrganizationId__c,OFSServer__MetadataLog__r.Name,OFSServer__LastModifiedById__c,OFSServer__LastModifiedByName__c,OFSServer__LastModifiedDate__c";

	// private static final String FILE_HEADER =
	// "Name__c,Type__c,OrganizationId__c";

	public static void writeCsvFile(List<MetaBean> metaBeanDOList,
			String fileName) {
		// Create a new list of student objects
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new student object list to the CSV file
			for (MetaBean metadataInformationDO : metaBeanDOList) {
				
				Calendar cal=metadataInformationDO.getLastModifiedByDate();
				cal.add(Calendar.DATE, 1);
				Date date = cal.getTime();         
				//DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");


				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");          
				String modifiedDate = format1.format(date);
				
				fileWriter.append(metadataInformationDO.getName());
	            fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(metadataInformationDO.getType());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(metadataInformationDO.getSourceOrg());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(metadataInformationDO.getMetadataLogId());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(metadataInformationDO.getLastModifiedById());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(metadataInformationDO.getLastModifiedByName());
				fileWriter.append(COMMA_DELIMITER);
			   fileWriter.append(modifiedDate);
				fileWriter.append(NEW_LINE_SEPARATOR);

			}
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out
						.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
}
