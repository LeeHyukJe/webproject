package com.leehyukje.webproject.crawal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebCrawler {

    public void crawling(String targetUrl,int pageNum){
        try{
            for(int i=0;i<pageNum;i++) {
                String URL = targetUrl + i;
                Document doc = Jsoup.connect(URL).get();
                Elements elem = doc.select(".list_content").select(".list_title").select(".list_subject");
                for (Element e : elem) {
                    System.out.println("<TITLE>" + e.getElementsByAttribute("title").attr("title"));
                    String depth2Url = "https://www.clien.net" + e.getElementsByAttribute("href").attr("href");
                    Document depth2Doc = Jsoup.connect(depth2Url).get();
                    Elements depth2Elems = depth2Doc.select(".post_article");

                    for (Element depth2Elem : depth2Elems) {
                        System.out.println("<CONTENT>" + depth2Elem.text());
                        //System.out.println("----------------------------");
                    }
                }
            }
        }catch (IOException io){
            io.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
