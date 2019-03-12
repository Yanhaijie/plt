package com.wi.data.clear.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wi.data.clear.entity.CDeviceApp;
import com.wi.data.clear.service.CDeviceService;
import com.wi.data.clear.thread.ElasticsearchClearThread;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.spans.FieldMaskingSpanQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.net.InetAddress;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by wenyuhao on 2018-06-13.
 */
@Component
public class ElasticsearchUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchUtil.class);
    @Autowired
    private TransportClient client;

    @Resource
    private CDeviceService cDeviceService;

    private String clearFieldName = "clear_status";


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
    public SearchResponse getExsitsField(String index, String type, String field) throws Exception {
        QueryBuilder qb = QueryBuilders.existsQuery(field);
        SearchResponse response = null;
        response = client.prepareSearch(index)
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
    public SearchResponse getExsitsField(String index, String type, String field, int from, int size) throws Exception {
        QueryBuilder qb = QueryBuilders.existsQuery(field);
        SearchResponse response = null;
        response = client.prepareSearch(index)
                .setTypes("type", type)
                .addSort("_id", SortOrder.ASC)
                .setSearchType(SearchType.DEFAULT).setQuery(qb)
                .setFrom(from).setSize(size)
                .setExplain(true)
                .get();
        return response;
    }




    @Async
    public SearchResponse getNotExsitsField(String index, String field) throws Exception {
        SearchResponse response = null;
        boolean runFlag = true;
        while (runFlag){
            try {
                BoolQueryBuilder bqb = new BoolQueryBuilder().mustNot(new ExistsQueryBuilder(field));
                response = client.prepareSearch(index)
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
     * 查询不存在某域的数据，默认前10条，按_id生序
     *
     * @param index
     * @param type
     * @param field
     * @return
     * @throws Exception
     */
    @Async
    public SearchResponse getNotExsitsField(String index, String type, String field) throws Exception {
        SearchResponse response = null;
        boolean runFlag = true;
        while (runFlag){
            try {
                BoolQueryBuilder bqb = new BoolQueryBuilder().mustNot(new ExistsQueryBuilder(field));
                response = client.prepareSearch(index)
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
    public SearchResponse getNotExsitsField(String index, String type, String field, int from, int size) throws Exception {
        SearchResponse response = null;
        boolean runFlag = true;
        while (runFlag){
            try {
                BoolQueryBuilder bqb = new BoolQueryBuilder().mustNot(new ExistsQueryBuilder(field));
                response = client.prepareSearch(index)
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


    @Async
    public SearchResponse getNotExsitsField(String index, String field, int from, int size) throws Exception {
        SearchResponse response = null;
        boolean runFlag = true;
        while (runFlag){
            try {
                BoolQueryBuilder bqb = new BoolQueryBuilder().mustNot(new ExistsQueryBuilder(field));
                response = client.prepareSearch(index)
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
    public SearchResponse get(String index, String type) throws Exception {
        SearchResponse response = null;
        response = client.prepareSearch(index)
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
    public SearchResponse get(String index, String type, int from, int size) throws Exception {
        SearchResponse response = client.prepareSearch(index)
                .addSort("_id", SortOrder.ASC)
                .setTypes("type", type)
                .setSearchType(SearchType.DEFAULT)
                .setFrom(from).setSize(size)
                .setExplain(true)
                .get();
        return response;
    }

    //更新es，添加已经清洗记录
    @Async
    public void updateData(SearchHits searchHits) throws Exception {
        if (searchHits.getHits().length == 0) {
            return;
        }
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (int i = 0; i < searchHits.getHits().length; i++) {
            String index = searchHits.getHits()[i].getIndex();
            String type = searchHits.getHits()[i].getType();
            String id = searchHits.getHits()[i].getId();
            bulkRequest.add(client.prepareUpdate(index, type, id).setDoc(XContentFactory
                            .jsonBuilder().startObject()
                            .field(clearFieldName, "1")//新添加字段,代表已经清洗过了
                            .endObject()
                    )
            );
        }
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            for (BulkItemResponse item : bulkResponse.getItems()) {
                System.out.println(item.getFailureMessage());
            }
            throw new Exception();
        } else {
            System.out.println("update ok");
        }
    }

    //更新es，添加已经清洗记录
    @Async
    public void updateData(List<SearchHit> searchHits,String field,String fieldValue) throws Exception {
        if (searchHits.size() == 0) {
            return;
        }
        boolean runFlag = true;
        while (runFlag){
            try {
                BulkRequestBuilder bulkRequest = client.prepareBulk();
                for (int i = 0; i < searchHits.size(); i++) {
                    String index = searchHits.get(i).getIndex();
                    String type = searchHits.get(i).getType();
                    String id = searchHits.get(i).getId();
                    bulkRequest.add(client.prepareUpdate(index, type, id).setDoc(XContentFactory
                                    .jsonBuilder().startObject()
                                    .field(field, fieldValue)//新添加字段,代表已经清洗过了
                                    .endObject()
                            )
                    );
                }
                BulkResponse bulkResponse = bulkRequest.get();
                if (bulkResponse.hasFailures()) {
                    for (BulkItemResponse item : bulkResponse.getItems()) {
                        System.out.println(item.getFailureMessage());
                    }
                    throw new Exception();
                } else {
                    System.out.println("update ok");
                }
                runFlag = false;
            }catch (Exception e){
                runFlag = true;
                try {
                    Thread.sleep(3000);
                }catch (Exception ex){
                }
            }
        }
    }

    /**
     * 清洗设备信息及设备用户关联信息
     *
     * @throws Exception
     */
    public void clearDeviceInfo() throws Exception {
        SearchResponse response = this.getNotExsitsField("supermoneylog", "applog", clearFieldName);
        SearchHits searchHits = response.getHits();
        long totalHits = searchHits.getTotalHits();
        int size = 10000;
        long totalSize = 0;
        if (totalHits % size > 0) {
            totalSize = totalHits / size + 1;
        } else {
            totalSize = totalHits / size;
        }
        for (int i = 0; i < totalSize; i++) {
            List<SearchHit> searchHitList = Lists.newArrayList();
            List<Map<String, Object>> clearDataList = Lists.newArrayList();
            SearchResponse responseDetail = this.getNotExsitsField("supermoneylog", "applog", clearFieldName, size * i, size);
            SearchHits searchHitsDetail = responseDetail.getHits();
            if (searchHitsDetail.getHits().length == 0) {
                return;
            }
            for (int j = 0; j < searchHitsDetail.getHits().length; j++) {
                String json = searchHitsDetail.getHits()[j].getSourceAsString();
                Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(json);
                HashMap<String, Object> dataAsMap = new HashMap<String, Object>(jsonMap);
                if (dataAsMap.get("data") != null && dataAsMap.get(clearFieldName) == null) {
                    Map<String, Object> itemMap = JSONObject.toJavaObject((JSONObject) dataAsMap.get("data"), Map.class);
                    clearDataList.add(itemMap);
                }
                if (dataAsMap.get(clearFieldName) == null) {
                    searchHitList.add(searchHitsDetail.getHits()[j]);
                }
            }
            System.out.println("some saveDeviceAndUserApp============start" + new Date());
            cDeviceService.saveDeviceAndUserApp(clearDataList);
            System.out.println("some saveDeviceAndUserApp============end" + new Date());
            this.updateData(searchHitList,clearFieldName,"1");
        }
    }


    /**
     * 不起作用
     * 清洗设备信息及设备用户关联信息
     *
     * @throws Exception
     */
    public void clearDeviceMixTure() throws Exception {
        System.out.println("clearDeviceMixTure======================start" + new Date());
        SearchResponse response = this.getNotExsitsField("supermoneymixture", "mixture", clearFieldName);
        SearchHits searchHits = response.getHits();
        int totalHits = (int) searchHits.getTotalHits();
 //      this.clearDeviceMixTure(0, totalHits, 50);
        int clearSize = 5;
        int processSize=2;
        int clearRange = totalHits / processSize;
        //分20段跑
        for (int i = 0; i < processSize; i++) {
            Runnable clear = new ElasticsearchClearThread(clearRange * i, clearRange * (i + 1),clearSize,this);
            Thread thread = new Thread(clear);
            thread.start();
        }
        System.out.println("clearDeviceMixTure======================end" + new Date());
    }

    /**
     * 分段清洗
     *
     * @param from
     * @param end
     * @param size
     * @throws Exception
     */
    public void clearDeviceMixTure(int from, int end, int size) throws Exception {
        System.out.println("clearDeviceMixTure detail start ==========from=" + from + "=====end=" + end + "========size=" + size);
        long totalHits = end - from;
        long totalSize = 0;
        if (totalHits % size > 0) {
            totalSize = totalHits / size + 1;
        } else {
            totalSize = totalHits / size;
        }
        for (int i = 0; i < totalSize; i++) {
            List<SearchHit> searchHitList = Lists.newArrayList();
            List<Map<String, Object>> clearDataList = Lists.newArrayList();
            SearchResponse responseDetail = this.getNotExsitsField("supermoneymixture", "mixture", clearFieldName, from + size * i, size);
            SearchHits searchHitsDetail = responseDetail.getHits();
            if (searchHitsDetail.getHits().length == 0) {
                return;
            }
            for (int j = 0; j < searchHitsDetail.getHits().length; j++) {
                String json = searchHitsDetail.getHits()[j].getSourceAsString();
                Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(json);
                HashMap<String, Object> dataAsMap = new HashMap<String, Object>(jsonMap);
                if (dataAsMap.get(clearFieldName) == null) {
                    clearDataList.add(dataAsMap);
                }
                if (dataAsMap.get(clearFieldName) == null) {
                    searchHitList.add(searchHitsDetail.getHits()[j]);
                }
            }
            try {
                //TODO：暂时忽略，跳过异常
                cDeviceService.saveDeviceMixture(clearDataList);
                this.updateData(searchHitList,clearFieldName,"1");
            } catch (Exception e) {
                System.out.println("Error ============================");
                e.printStackTrace();
            }

        }

    }


}