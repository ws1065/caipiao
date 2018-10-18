package com.wsw;

import com.alibaba.fastjson.util.Base64;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleTuPianXiaZai {

    public static void getAjaxPage() throws Exception{
        String uri = "http://jandan.net/ooxx/page-1#comments";

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setRedirectEnabled(true);
//        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(Integer.MAX_VALUE);
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        HtmlPage rootPage = webClient.getPage(uri);
        webClient.waitForBackgroundJavaScript(10* 1000);

        System.out.println();
        System.out.println(rootPage.asText());
        System.out.println(rootPage.asXml());
    }
    public static void a(String[] args) {
        try {
            String uri = "http://jandan.net/ooxx/page-1#comments";
            Document doc = Jsoup.connect(uri).get();


            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String uri = "http://jandan.net/ooxx/page-1#comments";
        while (true) {
            System.out.println(uri );
            uri = getYeMian(uri);
            if (uri == null) return;
        }

    }

    private static String getYeMian(String uri) {
        //HttpClient 超时配置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).setConnectionRequestTimeout(6000).setConnectTimeout(6000).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();

        //创建一个GET请求

        HttpGet httpGet1 = new HttpGet(uri);
        httpGet1.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        httpGet1.addHeader("Cookie", "__SDID=3f7b2c855b0d70f4; _ga=GA1.2.1073352931.1537106481; _gid=GA1.2.809290676.1539517317");
        httpGet1.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet1.addHeader("Accept-Encoding", "gzip, deflate");
        try {
            //发送请求，并执行
            CloseableHttpResponse response = httpClient.execute(httpGet1);
            InputStream in = response.getEntity().getContent();
            String html = convertStreamToString(in);
            String html1 = html.replaceAll("\t", "").replaceAll("/n", "").replace("&nbsp;", "");
            xiaZaiZhaoPian(html1);
            return huoDeXiaYiGeYeMian(html1);

//            for (int i = 1; i < p.length; i++) {
//                String substring = p[i].substring(0, p[i].indexOf(".shtml"));
//                int i1 = Integer.parseInt(substring);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String huoDeXiaYiGeYeMian(String html1) {
        return  "http:" +html1.substring(html1.indexOf("Newer Comments\" href=\"")+22,html1.indexOf("\" class=\"next-comment-page\">上一页"));
    }

    private static void xiaZaiZhaoPian(String html1) {
        String[] meiYIGeTuPian = html1.split("<span class=\"img-hash\">");
        for (String s : meiYIGeTuPian) {
            if (s.startsWith("Ly93")) {
                String tuPianURL = s.substring(0, s.indexOf("</span>"));
                String tuPianURLStr = new String(Base64.decodeFast(tuPianURL));
                tuPianURLStr = "http:" + tuPianURLStr;
                String tuPianURLRaw = tuPianURLStr.replaceAll("mw600", "large");
                downloadPicture(tuPianURLRaw,"E:\\delete\\"+tuPianURLRaw.substring(tuPianURLRaw.lastIndexOf("/")+1));
            }
        }

    }
    //链接url下载图片
    private static void downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
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