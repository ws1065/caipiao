package com.wsw;

public class Test_caipiao implements Comparable<Test_caipiao>{

    @Override
    public String toString() {
        return "t1='" + t1 + '\'' +
                ", t2='" + t2 + '\'' +
                ", t3='" + t3 + '\'' +
                ", t4='" + t4 + '\'' +
                ", t5='" + t5 + '\'' +
                ", t6='" + t6 + '\'' +
                ", t7='" + t7 + '\'' +
                ", pool_bouns='" + pool_bouns + '\'' +
                '}';
    }

    private String page;
  private String t1;
  private String t2;
  private String t3;
  private String t4;
  private String t5;
  private String t6;
  private String t7;
  private String sales_total;
  private String every_bouns1;
  private String every_bouns2;
  private String every_bouns3;
  private String every_bouns4;
  private String every_bouns5;
  private String every_bouns6;
  private String pool_bouns;
  private String num_bouns1;
  private String num_bouns2;
  private String num_bouns3;
  private String num_bouns4;
  private String num_bouns5;
  private String num_bouns6;

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public String getT1() {
    return t1;
  }

  public void setT1(String t1) {
    this.t1 = t1;
  }

  public String getT2() {
    return t2;
  }

  public void setT2(String t2) {
    this.t2 = t2;
  }

  public String getT3() {
    return t3;
  }

  public void setT3(String t3) {
    this.t3 = t3;
  }

  public String getT4() {
    return t4;
  }

  public void setT4(String t4) {
    this.t4 = t4;
  }

  public String getT5() {
    return t5;
  }

  public void setT5(String t5) {
    this.t5 = t5;
  }

  public String getT6() {
    return t6;
  }

  public void setT6(String t6) {
    this.t6 = t6;
  }

  public String getT7() {
    return t7;
  }

  public void setT7(String t7) {
    this.t7 = t7;
  }

  public String getSales_total() {
    return sales_total;
  }

  public void setSales_total(String sales_total) {
    this.sales_total = sales_total;
  }

  public String getEvery_bouns1() {
    return every_bouns1;
  }

  public void setEvery_bouns1(String every_bouns1) {
    this.every_bouns1 = every_bouns1;
  }

  public String getEvery_bouns2() {
    return every_bouns2;
  }

  public void setEvery_bouns2(String every_bouns2) {
    this.every_bouns2 = every_bouns2;
  }

  public String getEvery_bouns3() {
    return every_bouns3;
  }

  public void setEvery_bouns3(String every_bouns3) {
    this.every_bouns3 = every_bouns3;
  }

  public String getEvery_bouns4() {
    return every_bouns4;
  }

  public void setEvery_bouns4(String every_bouns4) {
    this.every_bouns4 = every_bouns4;
  }

  public String getEvery_bouns5() {
    return every_bouns5;
  }

  public void setEvery_bouns5(String every_bouns5) {
    this.every_bouns5 = every_bouns5;
  }

  public String getEvery_bouns6() {
    return every_bouns6;
  }

  public void setEvery_bouns6(String every_bouns6) {
    this.every_bouns6 = every_bouns6;
  }

  public String getPool_bouns() {
    return pool_bouns;
  }

  public void setPool_bouns(String pool_bouns) {
    this.pool_bouns = pool_bouns;
  }

  public String getNum_bouns1() {
    return num_bouns1;
  }

  public void setNum_bouns1(String num_bouns1) {
    this.num_bouns1 = num_bouns1;
  }

  public String getNum_bouns2() {
    return num_bouns2;
  }

  public void setNum_bouns2(String num_bouns2) {
    this.num_bouns2 = num_bouns2;
  }

  public String getNum_bouns3() {
    return num_bouns3;
  }

  public void setNum_bouns3(String num_bouns3) {
    this.num_bouns3 = num_bouns3;
  }

  public String getNum_bouns4() {
    return num_bouns4;
  }

  public void setNum_bouns4(String num_bouns4) {
    this.num_bouns4 = num_bouns4;
  }

  public String getNum_bouns5() {
    return num_bouns5;
  }

  public void setNum_bouns5(String num_bouns5) {
    this.num_bouns5 = num_bouns5;
  }

  public String getNum_bouns6() {
    return num_bouns6;
  }

  public void setNum_bouns6(String num_bouns6) {
    this.num_bouns6 = num_bouns6;
  }

    @Override
    public int compareTo(Test_caipiao o) {

        return Integer.parseInt(o.getPool_bouns().replaceAll(",",""))
                -
                Integer.parseInt(this.getPool_bouns().replaceAll(",",""));
    }
}
