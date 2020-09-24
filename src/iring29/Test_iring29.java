package iring29;

import java.io.IOException;

import pojo.AccountDO;

public class Test_iring29 {
	public static void main(String[] args) throws IOException {
		HomepageInit test = new HomepageInit();
		AccountDO accDo = new AccountDO();
		accDo.setUsername("test");
		System.out.println(accDo.getUsername());
		test.searchUsername(accDo);
	}
}
