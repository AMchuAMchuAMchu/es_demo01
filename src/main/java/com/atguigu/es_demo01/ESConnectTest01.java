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

    public static RestHighLevelClient rHLC01 = new RestHighLevelClient(RestClient.builder(new
            HttpHost("localhost",9200,"http")));




    @Test
    public void test02() throws IOException {





    }


    @Test
    public void test01() throws IOException {

        RestHighLevelClient rHC01 = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost"
                , 9200, "http")));
        rHC01.close();


    }


}
