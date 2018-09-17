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
        httpGet1.addHeader("Cookie", "sdc_session=1537149671465; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1537149672; bdshare_firstime=1537149671855; _qzjc=1; _jzqc=1; _jzqckmp=1; __utmc=63332592; __utmz=63332592.1537149673.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ck_RegUrl=kaijiang.500.com; ck_RegFromUrl=http%3A//kaijiang.500.com/shtml/ssq/03002.shtml; WT_FPC=id=undefined:lv=1537155732274:ss=1537155732274; _qzja=1.2143396619.1537149671861.1537152882554.1537155732535.1537152882554.1537155732535.0.0.0.4.3; _qzjto=4.3.0; _jzqa=1.2046596389977329400.1537149672.1537152883.1537155733.3; _jzqx=1.1537152883.1537155733.2.jzqsr=kaijiang%2E500%2Ecom|jzqct=/shtml/ssq/03002%2Eshtml.jzqsr=kaijiang%2E500%2Ecom|jzqct=/shtml/ssq/03089%2Eshtml; _qzjb=1.1537155732535.1.0.0.0; __utma=63332592.1675891417.1537149673.1537152883.1537155733.3; sdc_userflag=1537155732277::1537157402642::7; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1537157403; _jzqb=1.7.10.1537155733.1; __utmt=1; __utmb=63332592.7.10.1537155733; CLICKSTRN_ID=1.80.218.173-1537149664.528767::16B1D937ED722FF6F94810E7758DDA62; motion_id=1537157412260_0.12137685854805791");
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        execute(httpClient, ids);
    }

    private static void execute(CloseableHttpClient httpClient, List<Integer> ids) {
        for (Integer id : ids) {
            //创建一个GET请求
            HttpGet httpGet =null;
            if (String.valueOf(id).length()<5) {
                httpGet = new HttpGet("http://kaijiang.500.com/shtml/ssq/0"+id+".shtml");
            }else {
                httpGet = new HttpGet("http://kaijiang.500.com/shtml/ssq/"+id+".shtml");
            }
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            httpGet.addHeader("Cookie", "sdc_session=1537149671465; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1537149672; bdshare_firstime=1537149671855; _qzjc=1; _jzqc=1; _jzqckmp=1; __utmc=63332592; __utmz=63332592.1537149673.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ck_RegUrl=kaijiang.500.com; ck_RegFromUrl=http%3A//kaijiang.500.com/shtml/ssq/03002.shtml; WT_FPC=id=undefined:lv=1537155732274:ss=1537155732274; _qzja=1.2143396619.1537149671861.1537152882554.1537155732535.1537152882554.1537155732535.0.0.0.4.3; _qzjto=4.3.0; _jzqa=1.2046596389977329400.1537149672.1537152883.1537155733.3; _jzqx=1.1537152883.1537155733.2.jzqsr=kaijiang%2E500%2Ecom|jzqct=/shtml/ssq/03002%2Eshtml.jzqsr=kaijiang%2E500%2Ecom|jzqct=/shtml/ssq/03089%2Eshtml; _qzjb=1.1537155732535.1.0.0.0; __utma=63332592.1675891417.1537149673.1537152883.1537155733.3; sdc_userflag=1537155732277::1537157402642::7; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1537157403; _jzqb=1.7.10.1537155733.1; __utmt=1; __utmb=63332592.7.10.1537155733; CLICKSTRN_ID=1.80.218.173-1537149664.528767::16B1D937ED722FF6F94810E7758DDA62; motion_id=1537157412260_0.12137685854805791");
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