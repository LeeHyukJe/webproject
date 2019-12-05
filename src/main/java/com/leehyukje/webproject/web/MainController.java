package com.leehyukje.webproject.web;

import com.leehyukje.webproject.crawal.WebCrawler;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    public MainController(WebCrawler webCrawler, TaskExecutor taskExecutor){
        this.webCrawler=webCrawler;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping("/index")
    public void index(HttpServletRequest request, Model model) throws Exception{
        log.info("@@사용자 정보1"+request.isUserInRole("ROLE_NORMAL"));
        if(request.getRemoteUser()!=null) {
            log.info("@@사용자 정보2" + request.getRemoteUser());
            model.addAttribute("isLogin",request.getRemoteUser());
        }

        //webCrawler.crawling("https://www.clien.net/service/board/news",5);

    }

    @GetMapping("/indexAjax")
    public ResponseEntity<String> indexByJson(HttpServletRequest request) {
        ResponseEntity<String> entity = null;
        try {
            CompletableFuture<String> content = webCrawler.crawlingToJson("https://www.clien.net/service/board/news", 5);
            entity = new ResponseEntity<>(content.get(), HttpStatus.OK);
            return entity;
        } catch (IOException e) {
            return entity = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return entity = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return entity = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/indexAjax")
//    public CompletableFuture<String> indexByJson(HttpServletRequest request) throws Exception {
//            Future<String> content = webCrawler.crawlingToJson("https://www.clien.net/service/board/news", 5);
//            return CompletableFuture.supplyAsync(()->{
//                try {
//                    return content.get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    return null;
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }, taskExecutor);
//    }



    // 상품 등록 컨트롤러
    @GetMapping("/item_create")
    public String item_create() throws Exception{

        return "item_create";
    }
}
