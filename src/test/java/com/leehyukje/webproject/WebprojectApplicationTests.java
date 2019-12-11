package com.leehyukje.webproject;


import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.leehyukje.webproject.search.common.ShRunner;

//@SpringBootTest
@RunWith(SpringRunner.class)
class WebprojectApplicationTests {
	
	
//	@Test
//	public void shellTest() throws Exception{
//		ShRunner shRunner = new ShRunner();
//		String dir = "C:\\wisenut\\sf-1\\batch\\static\\";
//		String command = "stc_clien.cmd";
//        String[] callCmd = {"cmd", dir, command};
//        Map map = shRunner.execCommand("C:\\wisenut\\sf-1\\batch\\static\\stc_clien.cmd");
//
//        System.out.println(map);
//	}
	
	@Test
	public void path() throws Exception{
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM");
		SimpleDateFormat format3 = new SimpleDateFormat("yyyyMMdd");
		String currentYear = format.format(date);
		String currentMonth = format2.format(date);
		String currentYearMonthDay = format3.format(date);
		String collection = "clien";
		
		Path path = Paths.get("C:\\wisenut\\sf-1\\log\\indexer\\",collection,currentYear,currentMonth,currentYearMonthDay+"_error.log");
		assertThat(path.toString(),is("C:\\wisenut\\sf-1\\log\\indexer\\clien\\2019\\12\\20191211_error.log"));
	}

}
