package com.leehyukje.webproject.web;

import com.leehyukje.webproject.crawal.WebCrawler;
import com.leehyukje.webproject.search.common.ShRunner;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.List;
import java.util.concurrent.*;

@Controller
@Log
public class MainController {

	@GetMapping("/index")
	public void index(HttpServletRequest request, Model model) throws Exception {
		log.info("@@사용자 정보1" + request.isUserInRole("ROLE_NORMAL"));
		if (request.getRemoteUser() != null) {
			log.info("@@사용자 정보2" + request.getRemoteUser());
			model.addAttribute("isLogin", request.getRemoteUser());
		}

		// webCrawler.crawling("https://www.clien.net/service/board/news",5);

	}

	

	// 상품 등록 컨트롤러
	@GetMapping("/item_create")
	public String item_create() throws Exception {

		return "item_create";
	}
}
