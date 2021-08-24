package com.jewelcse045.controller;


import com.jewelcse045.exception.ApplicationException;
import com.jewelcse045.utils.Params;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/data-scraper")
public class DataScraperController {




    @GetMapping("/get")
    @CrossOrigin
    public ResponseEntity<?> getData(@RequestParam("url") String url, Params params) throws IOException {
        if (url.isEmpty()){
            throw new ApplicationException("URL Can't be Empty");
        }

        if(params.getSelector().isEmpty() || params.getSelector() == null){
            throw new ApplicationException("Tag Selector Name Can't be Empty");
        }

        if(params.getName().isEmpty() || params.getName() == null){
            throw new ApplicationException("Tag Name Can't be Empty");
        }

        Document document = Jsoup.connect(url).get();
        List<String> results = new ArrayList<>();

        if (params.getSelector().equals("id")){
            Element web_data = document.getElementById(params.getName());
            results.add(web_data.text());
        }
        if (params.getSelector().equals("class")){
            Elements web_data = document.getElementsByClass(params.getName());
            results = extractElementData(web_data);
        }
        if (params.getSelector().equals("tag")){
            Elements web_data = document.getElementsByTag(params.getName());
            results = extractElementData(web_data);
        }

        return new ResponseEntity<>(results,HttpStatus.OK);
    }

    private List<String> extractElementData(Elements element){
        List<String> data = new ArrayList<>();
        for (Element web_item : element) {
            String str = removeQuotationFromText(web_item.text());
            data.add(str);
        }

        return data;
    }

    @GetMapping("/get/images")
    @CrossOrigin
    public ResponseEntity<?> getImages(@RequestParam("url") String url) throws IOException {

        if (url.isEmpty()){
            throw new ApplicationException("URL Can't be Empty");
        }

        Document doc = getDocument(url);
        Elements web_data = doc.select("img");

        Map<String,String> data = new HashMap<>();

        for (Element web_item : web_data) {
            String link = web_item.attr("src");
            String text = web_item.attr("alt");
            data.put(link,text);
        }
        return new ResponseEntity<>(data,HttpStatus.OK);

    }
    @GetMapping("/get/urls")
    @CrossOrigin
    public ResponseEntity<?> getDataByTagName(@RequestParam("url") String url) throws IOException {

        if (url.isEmpty()){
            throw new ApplicationException("URL Can't be Empty");
        }

        Document doc = getDocument(url);
        Elements web_data = doc.select("a");

        Map<String,String> data = new HashMap<>();

        for (Element web_item : web_data) {
            String link = web_item.attr("href");
            String text = web_item.text();
            data.put(link,text);
        }
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    private String removeQuotationFromText(String str){
        return str.replaceAll(" \" "," ");
    }

    private Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
