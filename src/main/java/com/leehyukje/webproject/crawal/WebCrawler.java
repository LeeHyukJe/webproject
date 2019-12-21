package com.leehyukje.webproject.crawal;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.java.Log;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
@Log
public class WebCrawler {

    public Map<String,List<ContentVO>> crawling(String targetUrl,int pageNum){
        try{

            Map<String,List<ContentVO>> crawlContent = new HashMap<>();
            for(int i=0;i<pageNum;i++) { //페이지
                String URL = targetUrl + "?po="+i;
                Document doc = Jsoup.connect(URL).get();
                Elements elem = doc.select(".list_content").select(".list_title").select(".list_subject");

                List<ContentVO> content = new ArrayList<>();
                for (Element e : elem) {
                    ContentVO contentVO = new ContentVO();
                    String uuid = UUID.randomUUID().toString();
                    contentVO.setUuid(uuid);
                    String title = e.getElementsByAttribute("title").attr("title");
                    contentVO.setTitle(title);

                    String depth2Url = "https://www.clien.net" + e.getElementsByAttribute("href").attr("href");
                    Document depth2Doc = Jsoup.connect(depth2Url).get();
                    Element depth2Elems = depth2Doc.select(".post_article").get(0);
                    contentVO.setCotent(depth2Elems.text());
                    content.add(contentVO);
                }
                crawlContent.put(i+"",content);
                System.out.println(i+"페이지 수집 중....");
            }

            return crawlContent;
        } catch (Exception io){
            io.printStackTrace();
            return null;
        }
    }


    
    public void crawlingToFile(String targetUrl,int pageNum) throws IOException {
        File file = null;
        BufferedWriter bufferedWriter = null;
        Date date = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm-ssS");
        try{
        	String completed="-I-F.SCD";
            file = new File("C:\\wisenut\\sf-1\\collection\\clien\\scd\\static\\B-00-"+dateFormat.format(date)+completed);
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for(int i=0;i<pageNum;i++) { //페이지
                String URL = targetUrl + "?po="+i;
                Document doc = Jsoup.connect(URL).get();
                Elements elem = doc.select(".list_content").select(".list_title").select(".list_subject");
                for (Element e : elem) {
                    String uuid = "<DOCID>"+UUID.randomUUID().toString();
                    bufferedWriter.write(uuid);
                    bufferedWriter.newLine();
                    String title = "<TITLE>"+e.getElementsByAttribute("title").attr("title");
                    bufferedWriter.write(title);
                    bufferedWriter.newLine();
                    String depth2Url = "https://www.clien.net" + e.getElementsByAttribute("href").attr("href");
                    Document depth2Doc = Jsoup.connect(depth2Url).get();
                    Element depth2Elems = depth2Doc.select(".post_article").get(0);
                    String content = "<CONTENT>"+depth2Elems.text();
                    bufferedWriter.write(content);
                    bufferedWriter.newLine();

                }

                log.info((i+1)+"페이지 수집 중....");
                
            }
            

        }catch (IOException io){
            io.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bufferedWriter.close();
            if(file.exists()) {
            	FileUtils.copyFile(file, new File("C:\\wisenut\\sf-1\\collection\\clien\\scd\\static\\B-00-"+dateFormat.format(date)+"-I-C.SCD"));
            	file.delete();
            }
        }
    }

    @Async
    public CompletableFuture<String> crawlingToJson(String targetUrl, int pageNum) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        StringWriter stringWriter = new StringWriter();
        JsonGenerator generator = jsonFactory.createGenerator(stringWriter);
        generator.useDefaultPrettyPrinter();

        generator.writeStartObject();
        generator.writeFieldName("result");
        generator.writeStartArray();

        for(int i=0;i<pageNum;i++) { //페이지
            String URL = targetUrl + "?po="+i;
            Document doc = Jsoup.connect(URL).get();
            Elements elem = doc.select(".list_content").select(".list_title").select(".list_subject");

            generator.writeStartObject();
            generator.writeFieldName("pageNum");
            generator.writeString(i+"");
            generator.writeFieldName("crawlingData");
            generator.writeStartArray();

            for (Element e : elem) {
                generator.writeStartObject();
                String uuid = UUID.randomUUID().toString();
                String title = e.getElementsByAttribute("title").attr("title");

                generator.writeFieldName("uuid");
                generator.writeString(uuid);
                generator.writeFieldName("title");
                generator.writeString(title);

                String depth2Url = "https://www.clien.net" + e.getElementsByAttribute("href").attr("href");
                Document depth2Doc = Jsoup.connect(depth2Url).get();
                Element depth2Elems = depth2Doc.select(".post_article").get(0);
                generator.writeFieldName("content");
                generator.writeString(depth2Elems.text());
                generator.writeEndObject();

            }
            generator.writeEndArray();
            generator.writeEndObject();
            log.info((i+1)+"페이지 수집 중....");
        }
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();


        return CompletableFuture.completedFuture(stringWriter.toString());
    }



    public static void main(String[] args) throws Exception {
        WebCrawler webCrawler = new WebCrawler();
////        System.out.println(webCrawler.crawling("https://www.clien.net/service/board/news",5).toString());
        //System.out.println(webCrawler.crawlingToJson("https://www.clien.net/service/board/news",5).get());
//        System.out.println(webCrawler.crawlingToJson("https://www.clien.net/service/board/news",5).get());
//        System.out.println(webCrawler.crawlingToJson("https://www.clien.net/service/board/news",5).get());
//        System.out.println(webCrawler.crawlingToJson("https://www.clien.net/service/board/news",5).get().toString());
        webCrawler.crawlingToFile("https://www.clien.net/service/board/news",1);
    }

}
