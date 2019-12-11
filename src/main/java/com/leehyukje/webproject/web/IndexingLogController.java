package com.leehyukje.webproject.web;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leehyukje.webproject.search.service.IndexingLogService;

import lombok.extern.java.Log;

@Controller
@Log
public class IndexingLogController {
	
	final private IndexingLogService logService;
	
	public IndexingLogController(IndexingLogService logService) {
		this.logService = logService;
	}
	
	@Value("${batch.indexer.logpath}")
	private String indexerLogPath;

	@GetMapping("/indexing_errorlog")
	public String showErrorLog(@RequestParam String collection, Model model) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM");
		SimpleDateFormat format3 = new SimpleDateFormat("yyyyMMdd");
		String currentYear = format.format(date);
		String currentMonth = format2.format(date);
		String currentYearMonthDay = format3.format(date);
		
		log.info("현재년도"+currentYear);
		log.info("현재월"+currentMonth);
		log.info("현재년도월일"+currentYearMonthDay);
		
		
		Path path = Paths.get(indexerLogPath,collection,currentYear,currentMonth,currentYearMonthDay+"_error.log");
		File file = new File(path.toString());
		
		
		String errorLog = logService.showIndexingErrorLog(file);
		model.addAttribute("errorLog",errorLog);
		return "indexing_errorlog";
	}
}
