package com.leehyukje.webproject.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leehyukje.webproject.crawal.WebCrawler;
import com.leehyukje.webproject.search.domain.SrchParamVO;
import com.leehyukje.webproject.search.service.ResultService;
import com.leehyukje.webproject.search.service.SearchService;

import lombok.extern.java.Log;

@RestController
@Log
public class SearchController {
	
	private final WebCrawler webCrawler;
	private final SearchService searchService;
	private final ResultService resultService;
	public SearchController (WebCrawler webCrawler ,SearchService searchService, ResultService resultService) {
		this.webCrawler = webCrawler;
		this.searchService = searchService;
		this.resultService = resultService;
	}
	
	@GetMapping("/collectContents")
	public String collect(SrchParamVO srchParamVO) throws Exception {
		try {
			String result = resultService.result(searchService.setting(srchParamVO), srchParamVO.getCollection());
			log.info(result);
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
//	@GetMapping("/crawlContentsToJson")
//	public String collectData(SrchParamVO srchParamVO, @RequestParam int pageNum) throws Exception {
//		try {
//			String result = webCrawler.crawlingToJson("https://www.clien.net/service/board/news", pageNum);
//			log.info(result);
//			return result;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
}
