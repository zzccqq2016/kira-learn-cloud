package jsoup;

import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/2/28 12:16
 */
public class WebCrawler {


    private Set<String> links;

    public WebCrawler() {
        links = new HashSet<String>();
    }

    public void getPageLinks(String url) {
        if (!links.contains(url)) {
            try {
                Document document = Jsoup.connect(url).get();
                Elements linksOnPage = document.select("a[href]");
                for (Element page : linksOnPage) {
                    links.add(page.absUrl("href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + url + "': " + e.getMessage());
            }
        }
    }




}