package com.leehyukje.webproject.web;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leehyukje.webproject.crawal.WebCrawler;
import com.leehyukje.webproject.search.common.ShRunner;

import lombok.extern.java.Log;

@Controller
@Log
public class IndexingController {
	
	private final WebCrawler webCrawler;
	private final TaskExecutor taskExecutor;
	private final ShRunner shRunner;
	
	@Autowired
	public IndexingController(WebCrawler webCrawler, TaskExecutor taskExecutor, ShRunner shRunner) {
		this.webCrawler = webCrawler;
		this.taskExecutor = taskExecutor;
		this.shRunner = shRunner;
	}

	@Value("${batch.path}")
	private String executePath;
	@Value("${crawling.path}")
	private String crawlingPath;
	
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
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}

	}
}
