package com.wsw;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.*;

/**
 * Created by 3 on 2018/9/25.
 */
public class Test2 {

    private static Map<String,Integer> map = new TreeMap<>();
    private static Map<String,Integer> map1 = new HashMap<>();
    private static Map<String,Integer> map2 = new HashMap<>();
/**
 * //14 01 26 20 22 18     12
 * @url http://127.0.0.1:2011/VIID/
 */
    public static void main(String[] args) {
        List<Test_caipiao> history = getHistory();
        for (Test_caipiao test_caipiao : history) {
            getNum(test_caipiao.getT1());
            getNum(test_caipiao.getT2());
            getNum(test_caipiao.getT3());
            getNum(test_caipiao.getT4());
            getNum(test_caipiao.getT5());
            getNum(test_caipiao.getT6());
            getNum1(test_caipiao.getT7());
        }

        map = sortMapByValue(map);
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            System.out.println(stringIntegerEntry.getKey()+":"+stringIntegerEntry.getValue());
        }
        System.out.println("***********");
        map1 = sortMapByValue(map1);
        for (Map.Entry<String, Integer> stringIntegerEntry : map1.entrySet()) {
            System.out.println(stringIntegerEntry.getKey()+":"+stringIntegerEntry.getValue());
        }


    }

    private static void getNum(String num) {
        boolean flag = false;
        String space = "";
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            if (num.equals(stringIntegerEntry.getKey())) {
                flag = true;
                space = stringIntegerEntry.getKey();
            }
        }
        if (!flag) {
            map.put(num,1);
        }else {
            Integer integer = map.get(space);
            integer = integer+1;
            map.put(space,integer);
        }
    }
    private static void getNum1(String num) {
        boolean flag = false;
        String space = "";
        for (Map.Entry<String, Integer> stringIntegerEntry : map1.entrySet()) {
            if (num.equals(stringIntegerEntry.getKey())) {
                flag = true;
                space = stringIntegerEntry.getKey();
            }
        }
        if (!flag) {
            map1.put(num,1);
        }else {
            Integer integer = map1.get(space);
            integer = integer+1;
            map1.put(space,integer);
        }
    }
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
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
}
class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

        return me1.getValue().compareTo(me2.getValue());
    }
}