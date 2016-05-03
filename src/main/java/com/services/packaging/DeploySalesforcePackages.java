package com.services.packaging;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import com.sforce.ws.ConnectionException;
import com.util.Constants;
import com.util.ZipHelper;
import com.util.common.LoadSFConfigurations;
import com.util.common.file.FileUtil;

public class DeploySalesforcePackages {

	String sfServerSrcFilePath;
	String sfClientSrcFilePath;
	String sfTestSrcFilePath;
	String sfCodePkgSrcFilePath;
	String targetFilePath;
	LoadSFConfigurations loadSFConfig;
	
	public static void main(String[] args) {
		DeploySalesforcePackages zf = new DeploySalesforcePackages();
	}

	public DeploySalesforcePackages() {
		init();
		// deploy server packages
		System.out.println("Deploying Salesforce Server Packages....");
		prepareServerPkgs();
		DeployPackages(Constants.SERVER);

		System.out.println("Deploying Salesforce Client Packages....");
		// deploy client packages
		prepareClientPkgs();
		DeployPackages(Constants.CLIENT);
	}

	public void DeployPackages(String instance) {
		try {
			DeployService deploy = new DeployService(getLoadSFConfig(),instance);
			deploy.deployZip(instance);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init() {
		try {
			loadSFConfig = new LoadSFConfigurations();
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(
					"sf-resource/" + Constants.SF_REPO_CLIENT_PKG).getFile());
			setSfClientSrcFilePath(file.getAbsolutePath());
			file = new File(classLoader.getResource(
					"sf-resource/" + Constants.SF_REPO_SERVER_PKG).getFile());
			setSfServerSrcFilePath(file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deployTestPkgs() {
		// create Server packages
		String packagePath = FileUtil.createUnPackageFolder();
		setTargetFilePath(packagePath);
		try {
			FileUtil.copyFolder(new File(getSfTestSrcFilePath()), new File(
					getTargetFilePath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileUtil.delete(new File(Constants.Component_Zip_FileName));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createZipFile("unpackaged");
	}

	public void deployCodePkgs() {
		// create Server packages
		String packagePath = FileUtil.createUnPackageFolder();
		setTargetFilePath(packagePath);
		try {
			FileUtil.copyFolder(new File(getSfCodePkgSrcFilePath()), new File(
					getTargetFilePath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileUtil.delete(new File(Constants.Component_Zip_FileName));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createZipFile("unpackaged");
	}

	public void prepareServerPkgs() {
		// create Server packages
		String packagePath = FileUtil.createUnPackageFolder();
		setTargetFilePath(packagePath);
		try {
			FileUtil.copyFolder(new File(getSfServerSrcFilePath()), new File(
					getTargetFilePath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileUtil.delete(new File(Constants.Component_Zip_FileName));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createZipFile("unpackaged");
	}

	public void prepareClientPkgs() {
		// create Client packages
		String packagePath = FileUtil.createUnPackageFolder();
		setTargetFilePath(packagePath);
		try {
			FileUtil.copyFolder(new File(getSfClientSrcFilePath()), new File(
					getTargetFilePath()));
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileUtil.delete(new File(Constants.Component_Zip_FileName));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createZipFile("unpackaged");
	}

	public void createZipFile(String pkgFolderName) {
		try {
			ZipHelper zippy = new ZipHelper();
			zippy.zipDir(pkgFolderName, Constants.Component_Zip_FileName);
		} catch (IOException e2) {
			System.err.println(e2);
		}
	}

	public String getTargetFilePath() {
		return targetFilePath;
	}

	public void setTargetFilePath(String targetFilePath) {
		this.targetFilePath = targetFilePath;
	}

	public String getSfServerSrcFilePath() {
		return sfServerSrcFilePath;
	}

	public void setSfServerSrcFilePath(String sfServerSrcFilePath) {
		this.sfServerSrcFilePath = sfServerSrcFilePath;
	}

	public String getSfClientSrcFilePath() {
		return sfClientSrcFilePath;
	}

	public void setSfClientSrcFilePath(String sfClientSrcFilePath) {
		this.sfClientSrcFilePath = sfClientSrcFilePath;
	}

	public String getSfTestSrcFilePath() {
		return sfTestSrcFilePath;
	}

	public void setSfTestSrcFilePath(String sfTestSrcFilePath) {
		this.sfTestSrcFilePath = sfTestSrcFilePath;
	}

	public String getSfCodePkgSrcFilePath() {
		return sfCodePkgSrcFilePath;
	}

	public void setSfCodePkgSrcFilePath(String sfCodePkgSrcFilePath) {
		this.sfCodePkgSrcFilePath = sfCodePkgSrcFilePath;
	}

	public LoadSFConfigurations getLoadSFConfig() {
		return loadSFConfig;
	}

	public void setLoadSFConfig(LoadSFConfigurations loadSFConfig) {
		this.loadSFConfig = loadSFConfig;
	}

	
}