package qinghua;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/2/28 12:27
 */
public class Demo {


    //http://mds.nmdis.org.cn/service/sinfo/front/solr/getSiteSearchList?keyWords=%E6%B5%B7%E6%B4%8B%E7%A7%91%E5%AD%A6%E8%B0%83%E6%9F%A5&page=1&pageSize=10
    public static void main(String[] args) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter("E:\\work\\a.xlsx");
        List<String> result = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("keyWords", URLEncoder.encode("深海科学调查"));
        param.put("page", 1);
        param.put("pageSize", 1000);
        JSONObject retJson = exec("http://mds.nmdis.org.cn/service/sinfo/front/solr/getSiteSearchList", param);
        JSONArray dataList = retJson.getJSONObject("data").getJSONArray("dataList");
        for (Object o : dataList) {
            JSONObject element = (JSONObject) o;
            String dataType = element.getString("dataType");
            if (Objects.equals("article", dataType)) {
                String articleId = element.getString("articleId");
                if (!StrUtil.isEmpty(articleId)) {
                    JSONObject ret = exec("http://mds.nmdis.org.cn/service/scms/front/article/info?articleId=" + articleId);
                    String content = ret.getJSONObject("data").getString("contentStr");
                    result.add(StringUtils.collectionToDelimitedString(getRet(content, "p"), "\t"));
                }
            }
        }
        writer.write(result);
    }

    private static List<String> getRet(String html, String cssQuery) {
        List<String> ret = new ArrayList<>();
        Document htmlDoc = Jsoup.parse(html);
        Elements elements = htmlDoc.select("p");
        for (Element element : elements) {
            String text = element.text();
            if (StringUtils.hasText(text)) {
                ret.add(text);
            }
        }
        return ret;
    }


    private static JSONObject exec(String url, Map<String, Object> param) {
        String ret = HttpUtil.get(url, param);
        return JSONObject.parseObject(ret);
    }

    private static JSONObject exec(String url) {
        String ret = HttpUtil.get(url);
        return JSONObject.parseObject(ret);
    }


}