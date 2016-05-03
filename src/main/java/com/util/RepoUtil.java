package com.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class RepoUtil {

	public static void CheckIn(GitRepoDO gitRepoDO,String  metatadataLogId) {
		File checkOutDir = new File(Constants.CheckoutPath1);
		RepoClass.deleteDirectory(checkOutDir);
		Git git = null;
		try {
			git = RepoClass.cloneRepository(gitRepoDO, checkOutDir);
			FileBasedDeploy.readFile(metatadataLogId);

		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			RepoClass.addFile(git);
		} catch (IOException | GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RepoClass.commit(git, gitRepoDO);
	}
	public static void CheckIn1(GitRepoDO gitRepoDO) {
		File checkOutDir = new File(Constants.CheckoutPath1);
		RepoClass.deleteDirectory(checkOutDir);
		Git git = null;
		try {
			git = RepoClass.cloneRepository(gitRepoDO, checkOutDir);
			//FileBasedQuickDeploy.readFile();

		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			RepoClass.addFile(git);
		} catch (IOException | GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RepoClass.commit(git, gitRepoDO);
	}

	public static void CheckOut(GitRepoDO gitRepoDO) {
		File checkOutDir = new File(Constants.CheckoutPath1);
		RepoClass.deleteDirectory(checkOutDir);
		Git git = null;
		try {
			git = RepoClass.cloneRepository(gitRepoDO, checkOutDir);
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getCurrentPath() {
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		return path;
	}
}