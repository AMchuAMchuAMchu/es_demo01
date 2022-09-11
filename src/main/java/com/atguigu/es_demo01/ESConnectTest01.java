package com.atguigu.es_demo01;

import com.atguigu.es_demo01.bean.Anime01;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

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
    public void test06() throws Exception {

        IndexRequest anime01 = new IndexRequest();

        anime01.index("anime01").id("1001");

        Anime01 anime011 = new Anime01("影宅", "2022", 4);

        ObjectMapper objectMapper = new ObjectMapper();

        String s = objectMapper.writeValueAsString(anime011);

        anime01.source(s, XContentType.JSON);

        IndexResponse indexAnime01 = rHLC01.index(anime01, RequestOptions.DEFAULT);

        System.out.println(indexAnime01.status());

        System.out.println(indexAnime01.getIndex());

        System.out.println(indexAnime01.getId());


//        GetIndexRequest anime012 = new GetIndexRequest("anime01");
//
//        GetIndexResponse response = rHLC01.indices().get(anime012, RequestOptions.DEFAULT);
//
//
//        System.out.println(response.getAliases().toString());
//
//        System.out.println(response.getMappings().toString());
//
//        System.out.println(response.getSettings().toString());


    }



    @Test
    public void test05(){

//        String s = UUID.randomUUID().toString();

        String s = UUID.randomUUID() + "";


    }



    @Test
    public void test04() throws IOException {

        DeleteIndexRequest user = new DeleteIndexRequest("user");

        AcknowledgedResponse delete = rHLC01.indices().delete(user, RequestOptions.DEFAULT);

        System.out.println(delete.isAcknowledged());


    }


    @Test
    public void test03() throws IOException {

        GetIndexRequest user = new GetIndexRequest("user");

        GetIndexResponse response = rHLC01.indices().get(user, RequestOptions.DEFAULT);

        System.out.println(" >> "+response.getAliases());
        System.out.println(" >> "+response.getMappings());
        System.out.println(" >> "+response.getSettings());


    }



    @Test
    public void test02() throws IOException {

        CreateIndexRequest anime01 = new CreateIndexRequest("user");

        CreateIndexResponse response = rHLC01.indices().create(anime01, RequestOptions.DEFAULT);

        System.out.println(" >> "+response.isAcknowledged());

    }


    @Test
    public void test01() throws IOException {

        RestHighLevelClient rHC01 = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost"
                , 9200, "http")));
        rHC01.close();


    }


}
