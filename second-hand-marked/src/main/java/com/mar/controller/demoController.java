package com.mar.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guokaifeng
 * @createDate: 2022/5/4
 **/

@RestController
@CrossOrigin
@RequestMapping("/de")
public class demoController {

    @RequestMapping("/demo")
    public String huoqu() throws IOException {
        int page = 1;
        Connection connect = Jsoup.connect("https://www.315jiage.cn/mc118p1.aspx");
        Map<String, String> header = new HashMap<String, String>();
        header.put("Host", "http://info.bet007.com");
        header.put("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-cn,zh;q=0.5");
        header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
        header.put("Connection", "keep-alive");
        Connection data = connect.headers(header);
        Document document = data.get();
        for (Element sCard : document.getElementsByClass("sCard")) {
            String s = sCard.getElementsByClass("badge badge-warning pull-right badge-rounded").text();
            if (s.length() > 0) {
                String src = sCard.getElementsByClass("thumbnail").get(0).attr("src");
                Connection connection = Jsoup.connect("https://www.315jiage.cn/" + src);
                Connection.Response execute = connection.method(Connection.Method.GET).ignoreContentType(true).execute();
                byte[] bytes2 = execute.bodyAsBytes();
                String[] s1 = src.split("/");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s1.length - 1; i++) {
                    sb.append(s1[i]);
                    sb.append("/");
                }
                File file = new File(sb.toString());
                if (!file.exists()) {
                    file.mkdirs();
                }
                OutputStream outputStream = new FileOutputStream(sb + s1[s1.length - 1]);
                outputStream.write(bytes2);
                outputStream.close();
                String url = sCard.select("a").attr("href");
                String a = sCard.getElementsByClass("title text-oneline").get(0).select("a").text();
                System.out.println(url);
                String mn = url.replace("mn", "");
                String replace = mn.replace(".aspx", "");
            }
        }
            return "success";
    }

    @RequestMapping("/get")
    public String getSt(HttpServletResponse response) throws IOException {
        InputStream inputStream = new FileInputStream("upload/2022-04/22042817579056t.jpg");
        byte[] bytes = inputStream.readAllBytes();
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        return "success";
    }

}
