package com.wsw;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * mongodb工作类
 */
public class MongoDBTask {

    private MongoTemplate mongoTemplate;
    private MongoClient mongoClient;

    private final static String collection_prefix = "TEST_";

    /**
     * 关闭连接
     */
    public void close(){
        if(mongoClient!=null) {
            mongoClient.close();
            mongoTemplate = null;
        }
    }

    public void run(Caipiao caipiao) {


        Thread.currentThread().setName(this.getClass().getName());
        initMongoTemplate();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mongoTemplate.insert(caipiao,collection_prefix + caipiao.getClass().getSimpleName());
        close();
    }


    private void initMongoTemplate(){
        String ip_port= "172.20.32.80:27017";
        String[] ip_port_array=null;
        if(ip_port.contains(",")){
            ip_port_array=ip_port.split(",");
        }else {
            ip_port_array=new String[]{ip_port};
        }
        List<ServerAddress> serverAddrList = new ArrayList<ServerAddress>();
        for(String ip_port_s:ip_port_array){
            String[] ip_port_s_array=ip_port_s.split(":");
            ServerAddress serverAddress = new ServerAddress(ip_port_s_array[0],Integer.valueOf(ip_port_s_array[1]));
            serverAddrList.add(serverAddress);
        }
        List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
        mongoClient = new MongoClient(serverAddrList, credentialList);
        mongoTemplate=new MongoTemplate(mongoClient,"LOCALTEST");

    }
}
