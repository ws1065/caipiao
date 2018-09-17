package com.wsw;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleCaiPiao {
    //起始页码
    public static void main(String[] args) {
        //HttpClient 超时配置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).setConnectionRequestTimeout(6000).setConnectTimeout(6000).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();

        //页面ID
        List<Integer> ids = new ArrayList();

        //创建一个GET请求
        HttpGet httpGet1 = new HttpGet("http://kaijiang.500.com/shtml/ssq/03002.shtml");
        httpGet1.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        httpGet1.addHeader("Cookie", "_gat=1; ck_regchanel=baidu; regfrom=0%7Cala%7Cbaidu; sdc_session=1537106735463; bdshare_firstime=1537106735537; _jzqy=1.1537106736.1537106736.1.jzqsr=baidu.-; _jzqckmp=1; __utmt=1; seo_key=baidu%7C%7Chttps://www.baidu.com/link?url=D1N4tf-fgloiO1ZXzZyd9E7e4sk2cVPEu2d0Zn4X9STD0VdkWnLhkGgjbGxuHG_E&wd=&eqid=b4e1d05a00005443000000065b9e6325; ck_RegUrl=kaijiang.500.com; ck_RegFromUrl=http%3A//kaijiang.500.com/%3F0_ala_baidu; WT_FPC=ids=undefined:lv=1537106765817:ss=1537106735461; sdc_userflag=1537106735463::1537106765829::5; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1537106735; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1537106766; _qzja=1.486111922.1537106735602.1537106735602.1537106735603.1537106756731.1537106766010.0.0.0.5.1; _qzjb=1.1537106735602.5.0.0.0; _qzjc=1; _qzjto=5.1.0; _jzqa=1.2403698331785671000.1537106736.1537106736.1537106736.1; _jzqc=1; _jzqb=1.5.10.1537106736.1; __utma=63332592.1847551773.1537106737.1537106737.1537106737.1; __utmb=63332592.5.10.1537106737; __utmc=63332592; __utmz=63332592.1537106737.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; motion_id=1537106868057_0.3170700300715521; CLICKSTRN_ID=111.19.39.11-1537106733.570126::E7AFD1B52D3BF81A20263B59DC7C5BC6");
        httpGet1.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet1.addHeader("Accept-Encoding", "gzip, deflate");
        try {
            //发送请求，并执行
            CloseableHttpResponse response = httpClient.execute(httpGet1);
            InputStream in = response.getEntity().getContent();
            String html = convertStreamToString(in);
            String html1 = html.replaceAll("\t", "").replaceAll("/n", "").replace("&nbsp;", "");
            String[] p = html1.split("http://kaijiang.500.com/shtml/ssq/");

            for (int i = 1; i < p.length; i++) {
                String substring = p[i].substring(0, p[i].indexOf(".shtml"));
                int i1 = Integer.parseInt(substring);
                ids.add(i1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer id : ids) {
            //创建一个GET请求
            HttpGet httpGet = new HttpGet("http://kaijiang.500.com/shtml/ssq/"+id+".shtml");
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            httpGet.addHeader("Cookie", "_gat=1; ck_regchanel=baidu; regfrom=0%7Cala%7Cbaidu; sdc_session=1537106735463; bdshare_firstime=1537106735537; _jzqy=1.1537106736.1537106736.1.jzqsr=baidu.-; _jzqckmp=1; __utmt=1; seo_key=baidu%7C%7Chttps://www.baidu.com/link?url=D1N4tf-fgloiO1ZXzZyd9E7e4sk2cVPEu2d0Zn4X9STD0VdkWnLhkGgjbGxuHG_E&wd=&eqid=b4e1d05a00005443000000065b9e6325; ck_RegUrl=kaijiang.500.com; ck_RegFromUrl=http%3A//kaijiang.500.com/%3F0_ala_baidu; WT_FPC=ids=undefined:lv=1537106765817:ss=1537106735461; sdc_userflag=1537106735463::1537106765829::5; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1537106735; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1537106766; _qzja=1.486111922.1537106735602.1537106735602.1537106735603.1537106756731.1537106766010.0.0.0.5.1; _qzjb=1.1537106735602.5.0.0.0; _qzjc=1; _qzjto=5.1.0; _jzqa=1.2403698331785671000.1537106736.1537106736.1537106736.1; _jzqc=1; _jzqb=1.5.10.1537106736.1; __utma=63332592.1847551773.1537106737.1537106737.1537106737.1; __utmb=63332592.5.10.1537106737; __utmc=63332592; __utmz=63332592.1537106737.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; motion_id=1537106868057_0.3170700300715521; CLICKSTRN_ID=111.19.39.11-1537106733.570126::E7AFD1B52D3BF81A20263B59DC7C5BC6");
            httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httpGet.addHeader("Accept-Encoding", "gzip, deflate");
            try {
                Thread.sleep(1000);
                //发送请求，并执行
                CloseableHttpResponse response = httpClient.execute(httpGet);
                InputStream in = response.getEntity().getContent();
                String html = convertStreamToString(in);
                System.out.println("**************"+id+"页面正在爬取");
                //网页内容解析
                new Thread(new CaipiaoHtmlParser(html, id)).start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"GBK"));
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();

    }

}