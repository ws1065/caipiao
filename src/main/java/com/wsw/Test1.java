package com.wsw;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {

    public static void main(String[] args) throws IOException {
        List<Test_caipiao> list = getHistory();

//        String[] a = new String[]{"07","17","18","27","29","32","13"};
//        Test_caipiao t = getInputObj(a);
//        Map<String, String> pageAndTotalBouns = calculator(list, t);
//        printResult(pageAndTotalBouns);

        for (Test_caipiao test_caipiao : list) {
            System.out.println("-------------"+test_caipiao.getPage()+"-----------------");
            Map<String, String> pageAndTotalBouns = calculator(list, test_caipiao);
            printResult(pageAndTotalBouns);
        }
    }

    private static void printResult(Map<String, String> pageAndTotalBouns) {
        System.out.println(pageAndTotalBouns.size());

        int count=0;
        List<String> big = new ArrayList();

        for (Map.Entry<String, String> stringStringEntry : pageAndTotalBouns.entrySet()) {
            int currCount = Integer.parseInt(stringStringEntry.getValue().replaceAll(",", ""));
            if (currCount>=3000){
                big.add(stringStringEntry.getKey());
            }
            count += currCount;
        }
        System.out.println("总计中奖"+pageAndTotalBouns.size()+"期,总共奖金"+count+"元");
        System.out.println("------------------");
        if (big.size()>1){
            System.out.println("大奖共有"+big.size()+"期");
            for (String s : big) {
                System.out.println("第"+s+"期中奖    "+pageAndTotalBouns.get(s)+"元");
            }
        }
//        System.out.println("------------------");
//        for (Map.Entry<String, String> stringStringEntry : pageAndTotalBouns.entrySet()) {
//            System.out.println(stringStringEntry.getKey()+":"+stringStringEntry.getValue());
//        }
    }

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

    private static Map<String, String> calculator(List<Test_caipiao> list, Test_caipiao t) {
        Map<String,String> pageAndTotalBouns = new HashMap<>();
        for (Test_caipiao c : list) {
            int sameNum = 0;
            if (c==null || c.getT7()==null) {
                System.out.println();
            }
            if (c.getT7().equals(t.getT7())){
                //对了多少位
                sameNum= numSame(t, c, sameNum);
            }else {
                sameNum= numSame(t, c, sameNum);
            }
            //中了多钱
            if (!totalBouns(sameNum,c,c.getT7().equals(t.getT7())).equals("--")) {
                pageAndTotalBouns.put(c.getPage(),totalBouns(sameNum,c,c.getT7().equals(t.getT7())));
            }
        }
        return pageAndTotalBouns;
    }

    private static String totalBouns(int sameNum, Test_caipiao c, boolean isSame) {
            switch (sameNum) {
                case 6:
                    return isSame?c.getEvery_bouns1():c.getEvery_bouns2();
                case 5:
                    return isSame?c.getEvery_bouns3():c.getEvery_bouns4();
                case 4:
                    return isSame?c.getEvery_bouns4():c.getEvery_bouns5();
                case 3:
                    return isSame?c.getEvery_bouns5():"--";
                case 2:
                case 1:
                case 0:
                    return isSame?c.getEvery_bouns6():"--";
            }

        return "--";
    }

    private static int numSame(Test_caipiao t, Test_caipiao c, int sameNum) {
        if(isSame(c.getT6(),t)){
           //t6相同
            sameNum++;
        }
        if (isSame(c.getT5(),t)){
            //t6不同 t5有相同
            sameNum++;
        }
        if (isSame(c.getT4(),t)){
            //t5不同 t4有相同
            sameNum++;
        }
        if (isSame(c.getT3(),t)){
            //t4不同 t3有相同
            sameNum++;
        }
        if (isSame(c.getT2(),t)){
            //t3不同 t2有相同
            sameNum++;
        }
        if (isSame(c.getT1(),t)){
            //t2不同 t1有相同
            sameNum++;
        }
        return sameNum;
    }

    private static boolean isSame(String t6, Test_caipiao t) {
        if (t6!=null && !t6.equals("")){
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

}