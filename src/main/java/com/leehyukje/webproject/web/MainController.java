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
	private final WebCrawler webCrawler;
	private final TaskExecutor taskExecutor;
	private final ShRunner shRunner;

	@Value("${batch.path}")
	private String executePath;
	@Value("${crawling.path}")
	private String crawlingPath;

	@Autowired
	public MainController(WebCrawler webCrawler, TaskExecutor taskExecutor, ShRunner shRunner) {
		this.webCrawler = webCrawler;
		this.taskExecutor = taskExecutor;
		this.shRunner = shRunner;
	}

	@GetMapping("/index")
	public void index(HttpServletRequest request, Model model) throws Exception {
		log.info("@@사용자 정보1" + request.isUserInRole("ROLE_NORMAL"));
		if (request.getRemoteUser() != null) {
			log.info("@@사용자 정보2" + request.getRemoteUser());
			model.addAttribute("isLogin", request.getRemoteUser());
		}

		// webCrawler.crawling("https://www.clien.net/service/board/news",5);

	}

	@GetMapping("/indexAjax")
	public ResponseEntity<String> indexByJson(HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		try {
			CompletableFuture<String> content = webCrawler.crawlingToJson(crawlingPath,
					5);

			entity = new ResponseEntity<>(content.get(), HttpStatus.OK);
			return entity;
		} catch (IOException e) {
			return entity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return entity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (ExecutionException e) {
			e.printStackTrace();
			return entity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

//    @GetMapping("/indexing")
//    public ResponseEntity<String> indexing(@RequestParam String targetUrl, @RequestParam int pageNum){
//    	ResponseEntity<String> entity=null;
//    	try {
//    		webCrawler.crawlingToFile(targetUrl, pageNum);
//    		log.info("result"+shRunner.execCommand(executePath).toString());
//    		entity = new ResponseEntity<>("success",HttpStatus.OK);
//    		return entity;
//    	}catch(Exception e) {
//    		entity = new ResponseEntity<>(e.getStackTrace().toString(),HttpStatus.BAD_REQUEST);
//    		return entity;
//    	}
//    }

	@GetMapping("/indexing")
	public ResponseEntity<String> indexing(@RequestParam String targetUrl, @RequestParam int pageNum) throws Exception {
		ResponseEntity<String> entity = null;
		try {
			CompletableFuture.runAsync(() -> {
				try {
					webCrawler.crawlingToFile(crawlingPath, pageNum);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},taskExecutor).thenRunAsync(() -> {
				log.info("크롤링 후 파일 출력 완료!");
				log.info(shRunner.execCommand(executePath).toString());
			});

			
			return entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}

	}

	// 상품 등록 컨트롤러
	@GetMapping("/item_create")
	public String item_create() throws Exception {

		return "item_create";
	}
}
