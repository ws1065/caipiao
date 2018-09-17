package com.wsw;

public class CaipiaoHtmlParser implements Runnable {
    private String html;
    private int page;
    public CaipiaoHtmlParser(String html, int page) {
        this.html = html;
        this.page = page;
    }
    @Override
    public void run() {
        System.out.println("==========第"+page+"页============");
        Caipiao parser = parser();
        System.out.println("**************                    "+page+"页面正在入库");
        MongoDBTask mongoDBTask = new MongoDBTask();
        mongoDBTask.run(parser);
    }

    private Caipiao parser() {
        Caipiao caipiao = new Caipiao();
        html = html.replaceAll("\t", "").replaceAll("/n", "").replace("&nbsp;","");

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
        html  = html.substring(html.indexOf("奖池滚存：<spanclass=\"cfont1 \">") +26);
        String 奖池滚存 = html.substring(0, html.indexOf("</span>"));
        html = html.substring(html.indexOf("一等奖</td><td>")+12);
        String 中奖注数1 = html.substring(0, html.indexOf("</td>"));
        html = html.substring(html.indexOf("</td><td>")+9);
        String 单注奖金1 = html.substring(0, html.indexOf("</td>"));
        html = html.substring(html.indexOf("</td><td>")+9);
        String 中奖注数2 = html.substring(0, html.indexOf("</td><td>"));
        html= html.substring(html.indexOf("</td><td>") + 9);
        String 单注奖金2 = html.substring(0, html.indexOf("</td>"));
        html = html.substring(html.indexOf("</td><td>")+9);
        String 中奖注数3 = html.substring(0, html.indexOf("</td><td>"));
        html= html.substring(html.indexOf("</td><td>") + 9);
        String 单注奖金3 = html.substring(0, html.indexOf("</td>"));
        html = html.substring(html.indexOf("</td><td>")+9);
        String 中奖注数4 = html.substring(0, html.indexOf("</td><td>"));
        html= html.substring(html.indexOf("</td><td>") + 9);
        String 单注奖金4 = html.substring(0, html.indexOf("</td>"));
        html = html.substring(html.indexOf("</td><td>")+9);
        String 中奖注数5 = html.substring(0, html.indexOf("</td><td>"));
        html= html.substring(html.indexOf("</td><td>") + 9);
        String 单注奖金5 = html.substring(0, html.indexOf("</td>"));
        html = html.substring(html.indexOf("</td><td>")+9);
        String 中奖注数6 = html.substring(0, html.indexOf("</td><td>"));
        html= html.substring(html.indexOf("</td><td>") + 9);
        String 单注奖金6 = html.substring(0, html.indexOf("</td>"));
        caipiao.setPage(page);
        caipiao.setT1(t1);
        caipiao.setT2(t2);
        caipiao.setT3(t3);
        caipiao.setT4(t4);
        caipiao.setT5(t5);
        caipiao.setT6(t6);
        caipiao.setT7(t7);
        caipiao.set奖池滚存(奖池滚存);
        caipiao.set本期销量(本期销量);
        caipiao.set中奖注数1(中奖注数1);
        caipiao.set中奖注数2(中奖注数2);
        caipiao.set中奖注数3(中奖注数3);
        caipiao.set中奖注数4(中奖注数4);
        caipiao.set中奖注数5(中奖注数5);
        caipiao.set中奖注数6(中奖注数6);
        caipiao.set单注奖金1(单注奖金1);
        caipiao.set单注奖金2(单注奖金2);
        caipiao.set单注奖金3(单注奖金3);
        caipiao.set单注奖金4(单注奖金4);
        caipiao.set单注奖金5(单注奖金5);
        caipiao.set单注奖金6(单注奖金6);
        return caipiao;
    }
}