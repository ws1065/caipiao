package com.wsw;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Test1 {

    public static List<Test_caipiao> hasCalculator = new ArrayList<>();
    //14 01 26 20 22 18     12
    public static void a(String[] args) {
        Test_caipiao inputObj = getInputObj(new String[]{"01", "02", "03", "05", "06", "16", "13"});
        Test_caipiao inputObj1 = getInputObj(new String[]{"01", "02", "03", "05", "16", "06", "13"});
    }
    public static void main(String[] args) throws IOException {

        List<Test_caipiao> list = getHistory();

        //测试1
//        String[] a = new String[]{"14", "01", "26", "20", "22", "18", "12"};
//        Test_caipiao t = getInputObj(a);
//        Map<String, String> pageAndTotalBouns = calculator(list, t);
//        printResult(pageAndTotalBouns);
        //测试3
        String[] a = new String[]{"01", "02", "03", "04", "05", "06", "01"};
        Test_caipiao t = getInputObj(a);

        ArrayList<Test_caipiao> num = new ArrayList<>();
        while (true) {
            t = getAllNum(t);
            //判断这个数字之前计算过没有
            Test_caipiao inputObj = getInputObj(new String[]{"01", "02", "03", "05", "06", "16", "13"});
            Test_caipiao inputObj1 = getInputObj(new String[]{"01", "02", "03", "05", "16", "06", "13"});
            if(!isCalculatored(t)) {

                if (t != null) {
                    System.out.println(t);
                    Map<String, String> pageAndTotalBouns = calculator(list, t);
//                int ismall = printResultSmall(pageAndTotalBouns);
//                t.setPool_bouns(String.valueOf(ismall));
//
//                int ibig = printResultBig(pageAndTotalBouns);
//                t.setPool_bouns(String.valueOf(ibig));
//
//
//                int inomal = printResultNomal(pageAndTotalBouns);
//                t.setPool_bouns(String.valueOf(inomal));

                    int zhongjiangnum = printResultNum(pageAndTotalBouns);
                    t.setPool_bouns(String.valueOf(zhongjiangnum));
                    System.out.println(t);
                    num.add(t);

                    if (num.size() > 10000) {
                        sortList(num);

                        ArrayList<Test_caipiao> num1 = new ArrayList<>();
                        for (int i1 = 0; i1 < 1000; i1++) {
                            num1.add(num.get(i1));
                        }
                        num = num1;
                    }
                } else {
                    sortList(num);
                    for (Test_caipiao test_caipiao : num) {
                        System.out.println(test_caipiao.getPool_bouns() + ":" + test_caipiao);
                    }
                    System.exit(0);
                }
            }
        }

//测试2
//        for (Test_caipiao test_caipiao : list) {
//            System.out.println("-------------"+test_caipiao.getPage()+"-----------------");
//            Map<String, String> pageAndTotalBouns = calculator(list, test_caipiao);
//            printResult(pageAndTotalBouns);
//        }
    }

    public static void f(String[] args) {
        Test_caipiao inputObj = getInputObj(new String[]{"01", "02", "03", "05", "06", "16", "13"});
        Test_caipiao inputObj1 = getInputObj(new String[]{"01", "02", "03", "05", "16", "06", "13"});
        Test_caipiao inputObj2 = getInputObj(new String[]{"01", "02", "03", "05", "16", "06", "13"});
        Test_caipiao inputObj3 = getInputObj(new String[]{"01", "02", "03", "05", "16", "06", "13"});
        Test_caipiao inputObj4 = getInputObj(new String[]{"01", "02", "03", "05", "16", "06", "13"});
        Test_caipiao inputObj5 = getInputObj(new String[]{"01", "02", "03", "05", "16", "06", "13"});
        Test_caipiao inputObj6 = getInputObj(new String[]{"01", "02", "03", "05", "16", "06", "13"});
        inputObj.setPool_bouns("10");
        inputObj1.setPool_bouns("9");
        inputObj2.setPool_bouns("434");
        inputObj3.setPool_bouns("23");
        inputObj4.setPool_bouns("3");
        inputObj5.setPool_bouns("232");
        inputObj6.setPool_bouns("43");

        ArrayList<Test_caipiao> num = new ArrayList<>();
        num.add(inputObj);
        num.add(inputObj1);
        num.add(inputObj2);
        num.add(inputObj3);
        num.add(inputObj4);
        num.add(inputObj5);
        num.add(inputObj6);
        System.out.println(num);
        ArrayList<Test_caipiao> test_caipiaos = sortList(num);
        System.out.println(test_caipiaos);
    }
    private static ArrayList<Test_caipiao> sortList(ArrayList<Test_caipiao> num) {
        Test_caipiao b ;
        for (int i = 0; i < num.size(); i++) {
            for (int j = 0; j < num.size()-i-1; j++) {
                if (Integer.parseInt(num.get(j).getPool_bouns()) <Integer.parseInt(num.get(j+1).getPool_bouns())) {
                    b = num.get(j);
                    num.set(j, num.get(j+1));
                    num.set(j+1,b);
                }
            }
        }
        return num;

    }

    //已经计算过的数据有重复的数据
    private static boolean isCalculatored(Test_caipiao t) {
        for (Test_caipiao test_caipiao : hasCalculator) {
            if (numSame(test_caipiao, t, 0) == 6 && t.getT7().equals(test_caipiao.getT7())) {
                return true;
            }
        }
        hasCalculator.add(t);
        return false;
    }

    private static int printResultSmall(Map<String, String> pageAndTotalBouns) {
        int count = 0;
        List<String> big = new ArrayList();

        for (Map.Entry<String, String> stringStringEntry : pageAndTotalBouns.entrySet()) {
            int currCount = Integer.parseInt(stringStringEntry.getValue().replaceAll(",", ""));
            if (currCount >= 5000) {
                big.add(stringStringEntry.getKey());
            }else {
                count += currCount;
            }
        }
        return count;
    }
    private static int printResultBig(Map<String, String> pageAndTotalBouns) {
        int count = 0;
        List<String> big = new ArrayList();

        for (Map.Entry<String, String> stringStringEntry : pageAndTotalBouns.entrySet()) {
            int currCount = Integer.parseInt(stringStringEntry.getValue().replaceAll(",", ""));
            if (currCount >= 5000) {
                big.add(stringStringEntry.getKey());
                count += currCount;
            }else {

            }
        }
        return count;
    }
    private static int printResultNomal(Map<String, String> pageAndTotalBouns) {
        int count = 0;
        List<String> big = new ArrayList();

        for (Map.Entry<String, String> stringStringEntry : pageAndTotalBouns.entrySet()) {
            int currCount = Integer.parseInt(stringStringEntry.getValue().replaceAll(",", ""));
            if (currCount >= 5000) {
                big.add(stringStringEntry.getKey());
            }
            count += currCount;
        }
        return count;
    }
    private static int printResult(Map<String, String> pageAndTotalBouns) {
        System.out.println(pageAndTotalBouns.size());

        int count = 0;
        List<String> big = new ArrayList();

        for (Map.Entry<String, String> stringStringEntry : pageAndTotalBouns.entrySet()) {
            int currCount = Integer.parseInt(stringStringEntry.getValue().replaceAll(",", ""));
            if (currCount >= 5000) {
                big.add(stringStringEntry.getKey());
            }
            count += currCount;
        }
        System.out.println("总计中奖" + pageAndTotalBouns.size() + "期,总共奖金" + count + "元");
        System.out.println("------------------");
        if (big.size() > 1) {
            System.out.println("大奖共有" + big.size() + "期");
            for (String s : big) {
                System.out.println("第" + s + "期中奖    " + pageAndTotalBouns.get(s) + "元");
            }
        }
//        System.out.println("------------------");
//        for (Map.Entry<String, String> stringStringEntry : pageAndTotalBouns.entrySet()) {
//            System.out.println(stringStringEntry.getKey()+":"+stringStringEntry.getValue());
//        }
        return count;
    }
    private static int printResultNum(Map<String, String> pageAndTotalBouns) {
        return pageAndTotalBouns.size();
    }
    //获得历史中奖数据
    private static List<Test_caipiao> getHistory() {
        //mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Test1.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.wsw.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        return session.selectList(statement);
    }
    //数组转对象
    private static Test_caipiao getInputObj(String[] a) {
        Test_caipiao t = new Test_caipiao();
        t.setT1(a[0]);
        t.setT2(a[1]);
        t.setT3(a[2]);
        t.setT4(a[3]);
        t.setT5(a[4]);
        t.setT6(a[5]);
        t.setT7(a[6]);
        return t;
    }
    //当前数字和历史所有的数据比较
    private static Map<String, String> calculator(List<Test_caipiao> list, Test_caipiao t) {
        Map<String, String> pageAndTotalBouns = new HashMap<>();
        for (Test_caipiao c : list) {
            int sameNum = 0;
            if (c == null || c.getT7() == null) {
                System.out.println();
            }
            if (c.getT7().equals(t.getT7())) {
                //对了多少位
                sameNum = numSame(t, c, sameNum);
            } else {
                sameNum = numSame(t, c, sameNum);
            }
            //中了多钱
            if (!totalBouns(sameNum, c, c.getT7().equals(t.getT7())).equals("--")) {
                pageAndTotalBouns.put(c.getPage(), totalBouns(sameNum, c, c.getT7().equals(t.getT7())));
            }
        }
        return pageAndTotalBouns;
    }

    private static String totalBouns(int sameNum, Test_caipiao c, boolean isSame) {
        switch (sameNum) {
            case 6:
                return isSame ? c.getEvery_bouns1() : c.getEvery_bouns2();
            case 5:
                return isSame ? c.getEvery_bouns3() : c.getEvery_bouns4();
            case 4:
                return isSame ? c.getEvery_bouns4() : c.getEvery_bouns5();
            case 3:
                return isSame ? c.getEvery_bouns5() : "--";
            case 2:
            case 1:
            case 0:
                return isSame ? c.getEvery_bouns6() : "--";
        }

        return "--";
    }
    //前六个有几个相同
    private static int numSame(Test_caipiao t, Test_caipiao c, int sameNum) {
        if (isSame(c.getT6(), t)) {
            //t6相同
            sameNum++;
        }
        if (isSame(c.getT5(), t)) {
            //t6不同 t5有相同
            sameNum++;
        }
        if (isSame(c.getT4(), t)) {
            //t5不同 t4有相同
            sameNum++;
        }
        if (isSame(c.getT3(), t)) {
            //t4不同 t3有相同
            sameNum++;
        }
        if (isSame(c.getT2(), t)) {
            //t3不同 t2有相同
            sameNum++;
        }
        if (isSame(c.getT1(), t)) {
            //t2不同 t1有相同
            sameNum++;
        }
        return sameNum;
    }
    //一个数字与当前对象里面任一数字相同
    private static boolean isSame(String t6, Test_caipiao t) {
        if (t6 != null && !t6.equals("")) {
            if (t6.equals(t.getT1()) == true ||
                    t6.equals(t.getT2()) == true ||
                    t6.equals(t.getT3()) == true ||
                    t6.equals(t.getT4()) == true ||
                    t6.equals(t.getT5()) == true ||
                    t6.equals(t.getT6()) == true
                    ) {
                return true;
            }
        }
        return false;
    }

    public static Test_caipiao getAllNum(Test_caipiao t) {


        if (Integer.parseInt(t.getT1()) == 34){
            //最大后退出
            return null;
        }else if (Integer.parseInt(t.getT7()) !=16){
            t.setT7(getT(t.getT7()));
        }else {
            do  {

                if (Integer.parseInt(t.getT6())  != 33) {
                    t.setT6(getT(t.getT6()));
                } else if (Integer.parseInt(t.getT5())  != 33) {
                    t.setT6("01");
                    t.setT5(getT(t.getT5()));
                } else if (Integer.parseInt(t.getT4())  != 33) {
                    t.setT5("01");
                    t.setT4(getT(t.getT4()));
                } else if (Integer.parseInt(t.getT3())  != 33) {
                    t.setT4("01");
                    t.setT3(getT(t.getT3()));
                } else if (Integer.parseInt(t.getT2())  != 33) {
                    t.setT3("01");
                    t.setT2(getT(t.getT2()));
                } else if (Integer.parseInt(t.getT1())  != 33) {
                    t.setT2("01");
                    t.setT1(getT(t.getT1()));
                }
            }while (!allNumIsNotSame(t));
            t.setT7("01");
        }
        Test_caipiao clone = clone(t);
        clone.setPool_bouns(null);
        return clone;
    }
    //对当前对象进行克隆
    private static Test_caipiao clone(Test_caipiao t){
        Test_caipiao c = new Test_caipiao();
        c.setT1(t.getT1());
        c.setT2(t.getT2());
        c.setT3(t.getT3());
        c.setT4(t.getT4());
        c.setT5(t.getT5());
        c.setT6(t.getT6());
        c.setT7(t.getT7());
        c.setPool_bouns(t.getPool_bouns());
        return c;
    }
    //得到一个两位数的字符串
    private static String getT(String t) {
        return String.valueOf(Integer.parseInt( t)+1).length()
                >1?
                String.valueOf(Integer.parseInt( t)+1)
                :
                "0"+String.valueOf(Integer.parseInt( t)+1);
    }

    public static Map<Test_caipiao, Integer> sortMapByValue(Map<Test_caipiao, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<Test_caipiao, Integer> sortedMap = new LinkedHashMap<Test_caipiao, Integer>();
        List<Map.Entry<Test_caipiao, Integer>> entryList = new ArrayList<Map.Entry<Test_caipiao, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparatorTest_caipiao());

        Iterator<Map.Entry<Test_caipiao, Integer>> iter = entryList.iterator();
        Map.Entry<Test_caipiao, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
    //保证每组所有的数字都不相同
    private static boolean allNumIsNotSame(Test_caipiao t) {
        if (!t.getT1().equals(t.getT2()) &&
                !t.getT1().equals(t.getT2()) &&
                !t.getT1().equals(t.getT3()) &&
                !t.getT1().equals(t.getT4()) &&
                !t.getT1().equals(t.getT5()) &&
                !t.getT1().equals(t.getT6()) &&
                !t.getT2().equals(t.getT3()) &&
                !t.getT2().equals(t.getT4()) &&
                !t.getT2().equals(t.getT5()) &&
                !t.getT2().equals(t.getT6()) &&
                !t.getT3().equals(t.getT4()) &&
                !t.getT3().equals(t.getT5()) &&
                !t.getT3().equals(t.getT6()) &&
                !t.getT4().equals(t.getT5()) &&
                !t.getT4().equals(t.getT6()) &&
                !t.getT5().equals(t.getT6())) {
            return true;
        }
        return false;
    }
}
class MapValueComparatorTest_caipiao implements Comparator<Map.Entry<Test_caipiao, Integer>> {

    @Override
    public int compare(Map.Entry<Test_caipiao, Integer> me1, Map.Entry<Test_caipiao, Integer> me2) {

        return me1.getValue().compareTo(me2.getValue());
    }
}