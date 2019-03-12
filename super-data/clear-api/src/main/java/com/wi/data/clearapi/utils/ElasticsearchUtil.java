package com.wi.data.clearapi.utils;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018-06-13.
 */
@Component
public class ElasticsearchUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchUtil.class);
    @Autowired
    private TransportClient client;

    /**
     * 查询存在某域的数据，默认前10条，按_id生序
     *
     * @param index
     * @param type
     * @param field
     * @return
     * @throws Exception
     */
    @Async
    public SearchResponse getExsitsField(TransportClient targetClient,String index, String type, String field) throws Exception {
        if(targetClient == null){
            targetClient = client;
        }
        QueryBuilder qb = QueryBuilders.existsQuery(field);
        SearchResponse response = null;
        response = targetClient.prepareSearch(index)
                .setTypes("type", type)
                .addSort("_id", SortOrder.ASC)
                .setSearchType(SearchType.DEFAULT).setQuery(qb)
                .addSort("_id", SortOrder.ASC)
                .setScroll(new TimeValue(6000000)).execute().actionGet();
        return response;
    }

    /**
     * 查询存在某域的数据，指定区间，按_id生序,
     *
     * @param index
     * @param type
     * @param field
     * @param from
     * @param size
     * @return
     * @throws Exception
     */
    @Async
    public SearchResponse getExsitsField(TransportClient targetClient,String index, String type, String field, int from, int size) throws Exception {
        if(targetClient == null){
            targetClient = client;
        }
        QueryBuilder qb = QueryBuilders.existsQuery(field);
        SearchResponse response = null;
        response = targetClient.prepareSearch(index)
                .setTypes("type", type)
                .addSort("_id", SortOrder.ASC)
                .setSearchType(SearchType.DEFAULT).setQuery(qb)
                .setFrom(from).setSize(size)
                .setExplain(true)
                .get();
        return response;
    }

    /**
     * 查询不存在某域的数据，默认前10条，按_id生序
     *
     * @param index
     * @param type
     * @param field
     * @return
     * @throws Exception
     */
    @Async
    public SearchResponse getNotExsitsField(TransportClient targetClient,String index, String type, String field) throws Exception {
        if(targetClient == null){
            targetClient = client;
        }
        SearchResponse response = null;
        boolean runFlag = true;
        while (runFlag){
            try {
                BoolQueryBuilder bqb = new BoolQueryBuilder().mustNot(new ExistsQueryBuilder(field));
                response = targetClient.prepareSearch(index)
                        .setTypes("type", type)
                        .addSort("_id", SortOrder.ASC)
                        .setSearchType(SearchType.DEFAULT)
                        .setQuery(bqb)
                        .execute()
                        .actionGet();
                runFlag = false;
            }catch (Exception e){
                runFlag = true;
                try {
                    Thread.sleep(3000);
                }catch (Exception ex){
                }
            }
        }
        return response;
    }

    /**
     * 查询不存在某域的数据，指定区间，按_id生序,
     *
     * @param index
     * @param type
     * @param field
     * @param from
     * @param size
     * @return
     * @throws Exception
     */
    @Async
    public SearchResponse getNotExsitsField(TransportClient targetClient,String index, String type, String field, int from, int size) throws Exception {
        if(targetClient == null){
            targetClient = client;
        }
        SearchResponse response = null;
        boolean runFlag = true;
        while (runFlag){
            try {
                BoolQueryBuilder bqb = new BoolQueryBuilder().mustNot(new ExistsQueryBuilder(field));
                response = targetClient.prepareSearch(index)
                        .setTypes("type", type)
                        .addSort("_id", SortOrder.ASC)
                        .setSearchType(SearchType.DEFAULT).setQuery(bqb)
                        .setFrom(from).setSize(size)
                        .setExplain(true)
                        .get();
                runFlag = false;
            }catch (Exception e){
                runFlag = true;
                try {
                    Thread.sleep(3000);
                }catch (Exception ex){
                }
            }
        }
        return response;
    }

    /**
     * 查询，按_id生序,
     *
     * @param index
     * @param type
     * @return
     * @throws Exception
     */

    @Async
    public SearchResponse get(TransportClient targetClient,String index, String type) throws Exception {
        if(targetClient == null){
            targetClient = client;
        }
        SearchResponse response = null;
        response = targetClient.prepareSearch(index)
                .setTypes("type", type)
                .addSort("_id", SortOrder.ASC)
                .setSearchType(SearchType.DEFAULT)
                .setScroll(new TimeValue(6000000))
                .execute()
                .actionGet();
        return response;
    }


    /**
     * 查询数据，指定区间，按_id生序,
     *
     * @param index
     * @param type
     * @param from
     * @param size
     * @return
     * @throws Exception
     */
    @Async
    public SearchResponse get(TransportClient targetClient,String index, String type, int from, int size) throws Exception {
        if(targetClient == null){
            targetClient = client;
        }
        SearchResponse response = targetClient.prepareSearch(index)
                .addSort("_id", SortOrder.ASC)
                .setTypes("type", type)
                .setSearchType(SearchType.DEFAULT)
                .setFrom(from).setSize(size)
                .setExplain(true)
                .get();
        return response;
    }


    @Async
    public SearchResponse search (TransportClient targetClient, String index, Map<String,String> filterMap, int from, int size){
        if(targetClient == null){
            targetClient = client;
        }
        BoolQueryBuilder bqb = new BoolQueryBuilder();
        for (String key : filterMap.keySet()) {
           if(StringUtils.isNotEmpty(key)){
               bqb = bqb.must( QueryBuilders.multiMatchQuery(filterMap.get(key),key));
           }
        }
        SearchResponse response = null;
        try {
            SearchRequestBuilder  builder = targetClient.prepareSearch(index);
            //页码
            builder = builder.setSearchType(SearchType.DEFAULT).setFrom(from).setSize(size).setExplain(true).setSearchType(SearchType.DEFAULT).setQuery(bqb);
            response = builder.get();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return response;
    }


    /**
     * 获取elasticsearch 连接客户端
     * @param clusterName
     * @param host
     * @param port
     * @return
     * @throws Exception
     */
    public  TransportClient getClient(String clusterName, String host, int port){
        boolean running = true;
        TransportClient client = null;
        while (running){
            try {
               // Settings esSettings = Settings.builder().put("cluster.name",clusterName).put("client.transport.sniff", true).build();
                Settings esSettings = Settings.builder().put("cluster.name", clusterName).put("client.transport.ping_timeout","20s").build();
                client = new PreBuiltTransportClient(esSettings).addTransportAddress(new TransportAddress(InetAddress.getByName(host),port));
              //  client = new PreBuiltTransportClient(esSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),port));
                running = false;
            }catch (Exception ex){
                try {
                    Thread.sleep(60000);
                }catch (Exception e){}
                ex.printStackTrace();
            }
        }
        return client;
    }

    public Result saveData(TransportClient targetClient,String index,String type,List<Map<String,Object>> list) throws Exception{
        if(targetClient == null){
            targetClient = client;
        }
        if (list == null || list.size() < 1) {
            return ResultGenerator.genSuccessResult();
        }
        BulkRequestBuilder bulkRequest = targetClient.prepareBulk();
        for (int i=0;i<list.size();i++) {
            Map<String,Object> obj = list.get(i);
            Integer id = (Integer)obj.get("id");
            String _id = id.toString();
            HashMap<String, Object> dataAsMap = new HashMap<String, Object>(obj);
            dataAsMap.put("create_time", dataAsMap.get("create_time")==null? "" : dataAsMap.get("create_time").toString());
            dataAsMap.put("update_time",  dataAsMap.get("update_time")== null? "" : dataAsMap.get("update_time").toString());
            bulkRequest.add(new IndexRequest(index, type).id(_id).source(dataAsMap, XContentType.JSON));
        }
        int count = 0;
        while (true) {
            try {
                System.out.println("save mysql to es start============================");
                count++;
                BulkResponse bulkResponse =  bulkRequest.execute().actionGet();
                System.out.println("save mysql to es end=====================================");
                break;
            }
            catch (NoNodeAvailableException ex) {
                if(count++ > 5){
                    break;
                }
                ex.printStackTrace();
                Thread.sleep(5000);
                continue;
            }
        }
        return  ResultGenerator.genSuccessResult();
    }


}