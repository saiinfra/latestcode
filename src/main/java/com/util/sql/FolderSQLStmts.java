package com.util.sql;

public class  FolderSQLStmts {

	public static String getFolder(String folderType,String folderName){
		String sql = "Select Id, Name,Type,DeveloperName"
				+ " FROM Folder where Type = '"+folderType+"' and DeveloperName='"+folderName+"'";
		System.out.println(sql);
		return sql;
	}
}

