package com.leehyukje.webproject.web;

import java.io.IOException;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leehyukje.webproject.crawal.WebCrawler;
import com.leehyukje.webproject.search.domain.SrchParamVO;
import com.leehyukje.webproject.search.service.ResultService;
import com.leehyukje.webproject.search.service.SearchService;

import lombok.extern.java.Log;

@Controller
@Log
public class SearchController {
	
	private final SearchService searchService;
	private final ResultService resultService;
	public SearchController (SearchService searchService, ResultService resultService) {
		this.searchService = searchService;
		this.resultService = resultService;
	}
	
	@GetMapping("/collectContents")
	@ResponseBody
	public String collect(SrchParamVO srchParamVO) throws Exception {
		try {
			String result = resultService.result(searchService.setting(srchParamVO), srchParamVO.getCollection());
			//log.info(result);
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	// 검색 관리자 페이지
	@Secured({"ROLE_MANAGER"})
	@GetMapping("/searchAdmin")
	public String searchAdmin(){
		log.info("관리자 페이지 입장");
		return "searchAdmin";
	}


}
