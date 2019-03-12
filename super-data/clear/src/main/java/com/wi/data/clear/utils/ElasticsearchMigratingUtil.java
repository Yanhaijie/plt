package com.wi.data.clear.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
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
import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.util.*;

/**
 * Created by wenyuhao on 2018-06-13.
 */
@Component
@Transactional
public class ElasticsearchMigratingUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchMigratingUtil.class);
    @Autowired
    private AppProperties appProperties;

    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    private String migratingFieldName = "migrating_status";

    public void migratingDataAndUpdate() throws Exception{
        System.out.println("start=====================supermoneymixture");
        this.migratingData("supermoneymixture");
        System.out.println("end=====================supermoneymixture");
        System.out.println("start=====================supermoneyapp");
        this.migratingData("supermoneyapp");
        System.out.println("end=====================supermoneyapp");
        System.out.println("start=====================supermoneylog");
        this.migratingData("supermoneylog");
        System.out.println("end=====================supermoneylog");

    }


    /**
     * 按非指定_index,_type,_id迁移数据，迁移完后把数据源清除,乱序迁移
     * @throws Exception
     */
    public void migratingData(Integer maxNum) throws Exception{
        SearchHits searchHits = exportData(appProperties.getSourceCluster(),appProperties.getSourceHost(),appProperties.getSourcePort(),maxNum,"supermoneymixture");
        importData(appProperties.getTargetCluster(),appProperties.getTargetHost(),appProperties.getTargetPort(),searchHits);
        searchHits = exportData(appProperties.getSourceCluster(),appProperties.getSourceHost(),appProperties.getSourcePort(),maxNum,"supermoneylog");
        importData(appProperties.getTargetCluster(),appProperties.getTargetHost(),appProperties.getTargetPort(),searchHits);
        //deleteData(appProperties.getSourceCluster(),appProperties.getSourceHost(),appProperties.getSourcePort(),searchHits);
    }

    /**
     * 按指定_index,_type,_id迁移数据，迁移完后把数据源清除,指
     * @throws Exception
     */
    public void migratingData(String index,Integer maxNum) throws Exception{
        SearchHits searchHits = exportData(appProperties.getSourceCluster(),appProperties.getSourceHost(),appProperties.getSourcePort(),index,maxNum);
        importData(appProperties.getTargetCluster(),appProperties.getTargetHost(),appProperties.getTargetPort(),searchHits);
        //deleteData(appProperties.getSourceCluster(),appProperties.getSourceHost(),appProperties.getSourcePort(),searchHits);
    }
    @Async
    public void migratingData(String index) throws Exception {
        System.out.println("migrating start==="+index);
        SearchHits searchHits = this.getNotExsitsField(appProperties.getSourceCluster(),appProperties.getSourceHost(),appProperties.getSourcePort(),index, migratingFieldName);
        long totalHits = searchHits.getTotalHits();
        int size = 59;
        long totalSize = 0;
        if (totalHits % size > 0) {
            totalSize = totalHits / size + 1;
        } else {
            totalSize = totalHits / size;
        }
        for (int i = 0; i < totalSize; i++) {
            List<SearchHit> searchHitList = Lists.newArrayList();
            SearchHits searchHitsDetail = this.getNotExsitsField(appProperties.getSourceCluster(),appProperties.getSourceHost(),appProperties.getSourcePort(),index, migratingFieldName, size * i, size);
            if (searchHitsDetail.getHits().length == 0) {
                return;
            }
            for (int j = 0; j < searchHitsDetail.getHits().length; j++) {
                searchHitList.add(searchHitsDetail.getHits()[j]);
            }
            //开始迁移
            importData(appProperties.getTargetCluster(),appProperties.getTargetHost(),appProperties.getTargetPort(),searchHitsDetail);
            //更新已经迁移的
            this.updateData(appProperties.getSourceCluster(),appProperties.getSourceHost(),appProperties.getSourcePort(),searchHitList,migratingFieldName,"1");
            System.out.println("migrating end==="+index);
        }
    }

    @Async
    public SearchHits getNotExsitsField(String clusterName, String host, int port,String index, String field) throws Exception {
        TransportClient client = getClient(clusterName,host,port);
        SearchResponse response = null;
        while (true) {
            try {
                BoolQueryBuilder bqb = new BoolQueryBuilder().mustNot(new ExistsQueryBuilder(field));
                response = client.prepareSearch(index)
                        .addSort("_id", SortOrder.ASC)
                        .setSearchType(SearchType.DEFAULT)
                        .setQuery(bqb)
                        .execute()
                        .actionGet();
                client.close();
                break;
            }
            catch (NoNodeAvailableException ex) {
                ex.printStackTrace();
                Thread.sleep(5000);
                continue;
            }
        }
        SearchHits searchHit = response.getHits();
        return searchHit;
    }
    @Async
    public SearchHits getNotExsitsField(String clusterName, String host, int port,String index, String field, int from, int size) throws Exception {
        TransportClient client = getClient(clusterName,host,port);
        SearchResponse response = null;
        while (true) {
            try {
                BoolQueryBuilder bqb = new BoolQueryBuilder().mustNot(new ExistsQueryBuilder(field));
                response = client.prepareSearch(index)
                        .addSort("_id", SortOrder.ASC)
                        .setSearchType(SearchType.DEFAULT).setQuery(bqb)
                        .setFrom(from).setSize(size)
                        .setExplain(true)
                        .get();
                client.close();
                break;
            }
            catch (NoNodeAvailableException ex) {
                ex.printStackTrace();
                Thread.sleep(5000);
                continue;
            }
        }
        SearchHits searchHit = response.getHits();
        return searchHit;
    }


    public void updateData(String clusterName, String host, int port,List<SearchHit> searchHits,String field,String fieldValue) throws Exception {
        TransportClient client = getClient(clusterName,host,port);
        if (searchHits.size() == 0) {
            client.close();
            return;
        }

        while (true) {
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
                client.close();
                break;
            }
            catch (NoNodeAvailableException ex) {
                ex.printStackTrace();
                Thread.sleep(5000);
                continue;
            }
        }
    }


    /**
     * 导出数据，指定索引
     * @param maxNum 最大数码
     * @return
     * @throws Exception
     */
    public SearchHits exportData(String clusterName, String host, int port, String index, Integer maxNum) throws Exception{
        TransportClient client = getClient(clusterName,host,port);
        SearchResponse response = null;
        if(maxNum != null){
            //TODO:测试阶段只迁移设备信息，待更改
            response=  client.prepareSearch("supermoneylog").setQuery(QueryBuilders.matchAllQuery()).setSize(maxNum).setScroll(new TimeValue(600000))
                    .setSearchType(SearchType.DEFAULT).execute().actionGet();
        }else{
            //TODO:待优化,默认时10条，并不是所有数据，es默认设置
            response =  client.prepareSearch("supermoneylog").setQuery(QueryBuilders.matchAllQuery()).setScroll(new TimeValue(600000))
                    .setSearchType(SearchType.DEFAULT).execute().actionGet();
        }
        SearchHits searchHit = response.getHits();
        client.close();
        return searchHit;
    }


    /**
     * 导出数据,非指定索引
     * @param maxNum 最大数码
     * @return
     * @throws Exception
     */
    public SearchHits exportData(String clusterName, String host, int port, Integer maxNum,String index) throws Exception{
        TransportClient client = getClient(clusterName,host,port);
        SearchResponse response = null;
        if(maxNum != null){
            //TODO:测试阶段只迁移设备信息，待更改
            response=  client.prepareSearch(index).setQuery(QueryBuilders.matchAllQuery()).setSize(maxNum).setScroll(new TimeValue(600000))
                    .setSearchType(SearchType.DEFAULT).execute().actionGet();
        }else{
            //TODO:测试阶段只迁移设备信息，待更改
            response =  client.prepareSearch(index).setQuery(QueryBuilders.matchAllQuery()).setScroll(new TimeValue(600000))
                    .setSearchType(SearchType.DEFAULT).execute().actionGet();
        }
        //每次返回数据10000条。一直循环查询直到所有的数据都查询出来
        SearchHits searchHit = response.getHits();
        client.close();
        return searchHit;
    }

    /**
     * 导入数据
     * @param searchHits
     * @throws Exception
     */
    public void importData(String clusterName,String host,int port,SearchHits searchHits) throws Exception{
        if (searchHits.getHits().length == 0) {
            return;
        }
        TransportClient client = getClient(clusterName,host,port);
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        System.out.println( searchHits.getHits().length+"============================");
        for (int i = 0; i < searchHits.getHits().length; i++) {
            String index = searchHits.getHits()[i].getIndex();
            String type = searchHits.getHits()[i].getType();
            String id = searchHits.getHits()[i].getId();
            String json = searchHits.getHits()[i].getSourceAsString();
            Map<String,Object> jsonMap = (Map<String,Object>) JSON.parse(json);
            HashMap<String, Object> dataAsMap = new HashMap<String, Object>(jsonMap);
            bulkRequest.add(new IndexRequest(index, type).id(id).source(dataAsMap, XContentType.JSON));
        }
        while (true) {
            try {
                BulkResponse bulkResponse =  bulkRequest.execute().actionGet();
                client.close();
                System.out.println("import ok");
                break;
            }
            catch (NoNodeAvailableException ex) {
                ex.printStackTrace();
                Thread.sleep(5000);
                continue;
            }
        }
    }


    /**
     * 删除数据，按_index,_type,_id删除
     * @param searchHits
     * @throws Exception
     */
    public void deleteData(String clusterName,String host,int port,SearchHits searchHits) throws Exception{
        if (searchHits.getHits().length == 0) {
            return;
        }
        TransportClient client = getClient(clusterName,host,port);
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        System.out.println( searchHits.getHits().length+"============================");
        for (int i = 0; i < searchHits.getHits().length; i++) {
            String index = searchHits.getHits()[i].getIndex();
            String type = searchHits.getHits()[i].getType();
            String id = searchHits.getHits()[i].getId();
            bulkRequest.add(client.prepareDelete(index, type, id).request());
        }
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            for(BulkItemResponse item : bulkResponse.getItems()){
                System.out.println(item.getFailureMessage());
            }
            throw new Exception();
        }else {
            System.out.println("delete ok");
        }
        client.close();
    }

    /**
     * 删除指定索引库及其数据
     * @param indexName
     * @param clusterName
     * @param host
     * @param port
     * @throws Exception
     */
    public void deleteIndex(String clusterName,String host,int port,String indexName) throws Exception{
        TransportClient client = getClient(clusterName,host,port);
        //索引库是否存在
        IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indexName);
        IndicesExistsResponse inExistsResponse = client.admin().indices().exists(inExistsRequest).actionGet();
        //删除该索引库及其数据
        if(inExistsResponse.isExists()){
            DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(indexName).execute().actionGet();
        }
    }


    /**
     * 获取所有index
     */
    public  Set getAllIndices(String clusterName,String host,int port) throws Exception{
        TransportClient client = getClient(clusterName,host,port);
        ActionFuture<IndicesStatsResponse> isr = client.admin().indices().stats(new IndicesStatsRequest().all());
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        Map<String, IndexStats> indexStatsMap = isr.actionGet().getIndices();
        Set<String> set = isr.actionGet().getIndices().keySet();
        return set;
    }


    /**
     * 获取elasticsearch 连接客户端
     * @param clusterName
     * @param host
     * @param port
     * @return
     * @throws Exception
     */
    private  TransportClient getClient(String clusterName, String host, int port) throws Exception{
       Settings esSettings = Settings.builder()
                .put("cluster.name", clusterName) //设置ES实例的名称
               // .put("client.transport.sniff", true) //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .build();
        TransportClient client = new PreBuiltTransportClient(esSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),port));

        return client;
    }


}