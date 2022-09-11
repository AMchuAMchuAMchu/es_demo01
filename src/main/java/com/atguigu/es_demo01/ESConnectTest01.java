package com.atguigu.es_demo01;

import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Test;

import java.io.IOException;

/**
 * @Description: TODO
 * @BelongsProject: es_demo01
 * @BelongsPackage: com.atguigu.es_demo01
 * @Version: 1.0
 * @CreateTime: 2022-09-03 20:18:36
 * @Author: 02雪乃赤瞳楪祈校条祭制作委员会 wyq_start
 */
public class ESConnectTest01 {

    /**
    　* @description: TODO
    　* @return: void
    　* @params: []
    　* @createTime:  2022/9/6 11:09
    　* @author: 02雪乃赤瞳楪祈校条祭制作委员会 wyq_start
      */
    public void add() {
        System.out.println(">>>");
    }

    @Test
    public void test02() throws IOException {

        RestHighLevelClient rHC = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//
//        CreateIndexRequest user = new CreateIndexRequest("user");
//
//        CreateIndexResponse response = rHC.indices().create(user, RequestOptions.DEFAULT);
//
//        boolean acknowledged = response.isAcknowledged();
//
//        System.out.println(" >>> " + acknowledged);

        GetIndexRequest user = new GetIndexRequest("user");

        GetIndexResponse response = rHC.indices().get(user, RequestOptions.DEFAULT);

        System.out.println("getAliases"+response.getAliases());

        System.out.println("getMappings"+response.getMappings());

        System.out.println("getSettings"+response.getSettings());


    }


    @Test
    public void test01() throws IOException {

        ESConnectTest01 esConnectTest01 = new ESConnectTest01();

//        Runnable add = esConnectTest01::add;
//
//        add.run();

        RestHighLevelClient localhost = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        System.out.println(" >> " + localhost);

        localhost.close();

    }


}
