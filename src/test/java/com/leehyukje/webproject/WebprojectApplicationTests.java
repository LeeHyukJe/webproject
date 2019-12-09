package com.leehyukje.webproject;


import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.leehyukje.webproject.search.common.ShRunner;

@SpringBootTest
class WebprojectApplicationTests {
	
	@Test
	public void shellTest() throws Exception{
		ShRunner shRunner = new ShRunner();
		String dir = "C:\\wisenut\\sf-1\\batch\\static\\";
		String command = "stc_clien.cmd";
        String[] callCmd = {"cmd", dir, command};
        Map map = shRunner.execCommand("C:\\wisenut\\sf-1\\batch\\static\\stc_clien.cmd");

        System.out.println(map);
	}

}
