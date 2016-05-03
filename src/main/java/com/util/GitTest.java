package com.util;

public class GitTest {
	public static void main(String[] args) {

		GitRepoDO gitRepoDO = new GitRepoDO(Constants.userName,
				Constants.password, "https://github.com/saiinfra/salesforcedev.git");
        RepoUtil.CheckOut(gitRepoDO);
		
	}

}
