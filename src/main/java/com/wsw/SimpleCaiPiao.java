package com.wsw;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;

public class SimpleCaiPiao {
    //起始页码
    private static final int page = 1538;
    public static void main(String[] args) {
        //HttpClient 超时配置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).setConnectionRequestTimeout(6000).setConnectTimeout(6000).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();


        //创建一个GET请求
        HttpGet httpGet = new HttpGet("http://kaijiang.500.com/shtml/ssq/03002.shtml");
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        httpGet.addHeader("Cookie","_gat=1; ck_regchanel=baidu; regfrom=0%7Cala%7Cbaidu; sdc_session=1537106735463; bdshare_firstime=1537106735537; _jzqy=1.1537106736.1537106736.1.jzqsr=baidu.-; _jzqckmp=1; __utmt=1; seo_key=baidu%7C%7Chttps://www.baidu.com/link?url=D1N4tf-fgloiO1ZXzZyd9E7e4sk2cVPEu2d0Zn4X9STD0VdkWnLhkGgjbGxuHG_E&wd=&eqid=b4e1d05a00005443000000065b9e6325; ck_RegUrl=kaijiang.500.com; ck_RegFromUrl=http%3A//kaijiang.500.com/%3F0_ala_baidu; WT_FPC=id=undefined:lv=1537106765817:ss=1537106735461; sdc_userflag=1537106735463::1537106765829::5; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1537106735; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1537106766; _qzja=1.486111922.1537106735602.1537106735602.1537106735603.1537106756731.1537106766010.0.0.0.5.1; _qzjb=1.1537106735602.5.0.0.0; _qzjc=1; _qzjto=5.1.0; _jzqa=1.2403698331785671000.1537106736.1537106736.1537106736.1; _jzqc=1; _jzqb=1.5.10.1537106736.1; __utma=63332592.1847551773.1537106737.1537106737.1537106737.1; __utmb=63332592.5.10.1537106737; __utmc=63332592; __utmz=63332592.1537106737.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; motion_id=1537106868057_0.3170700300715521; CLICKSTRN_ID=111.19.39.11-1537106733.570126::E7AFD1B52D3BF81A20263B59DC7C5BC6");
        httpGet.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.addHeader("Accept-Encoding","gzip, deflate");
        try {
            //发送请求，并执行
            CloseableHttpResponse response = httpClient.execute(httpGet);
            InputStream in = response.getEntity().getContent();
            String html = SimpleSpider.convertStreamToString(in);
            System.out.println(html);

            html = html.substring(html.indexOf("ball_red\">") + 10);
            String t1 = html.substring(0, html.indexOf("</li>"));
            html = html.substring(html.indexOf("ball_red\">") + 10);
            String t2 = html.substring(0, html.indexOf("</li>"));
            html = html.substring(html.indexOf("ball_red\">") + 10);
            String t3 = html.substring(0, html.indexOf("</li>"));
            html = html.substring(html.indexOf("ball_red\">") + 10);
            String t4 = html.substring(0, html.indexOf("</li>"));
            html = html.substring(html.indexOf("ball_red\">") + 10);
            String t5 = html.substring(0, html.indexOf("</li>"));
            html = html.substring(html.indexOf("ball_red\">") + 10);
            String t6 = html.substring(0, html.indexOf("</li>"));
            html = html.substring(html.indexOf("ball_blue\">") + 11);
            String t7 = html.substring(0, html.indexOf("</li>"));
            System.out.println();
            html = html.substring(html.indexOf("本期销量：<span class=\"cfont1 \">") + 27);
            String 本期销量 = html.substring(0, html.indexOf("</span>"));
            html  = html.substring(html.indexOf("奖池滚存：<span/n\t\t\t\t\tclass=\"cfont1 \">") +33);
            String 奖池滚存 = html.substring(0, html.indexOf("</span>"));
            //网页内容解析
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}