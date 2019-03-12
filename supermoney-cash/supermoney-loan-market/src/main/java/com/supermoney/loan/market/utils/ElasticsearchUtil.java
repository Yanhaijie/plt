package com.supermoney.loan.market.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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

    @Async
    /**
     * 如果为空
     * 放回格式[{"number":"+6283806860253","messaage":"Bu bisa ke kntor bfi sekarang, untuk pencairanya","username":"085257540010"},{"number":"999","messaage":"Cuma bayar Rp20rb (normal Rp29.900) lgsg bisa nelp 100mnt ke semua operator utk 30hr. Aktifkan Pktmu skrng dgn balas OKE hari ini! Inf838 ABV37B","username":"085257540010"},{"number":"DanaRupiah","messaage":"atkan lebih banyak lagi biaya Fee keterlambatan(DanaRupiah)","username":"085257540010"}]
     */
    public String getSourceAll(String index, String type,String[] fields,String fieldName,String fieldValue) throws Exception {
        MatchPhraseQueryBuilder mpq = QueryBuilders.matchPhraseQuery(fieldName,fieldValue);
        QueryBuilder qb = QueryBuilders.boolQuery().must(mpq);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.query(qb);
//        FetchSourceContext sourceContext = new FetchSourceContext(false,fields,null);
//        sourceBuilder.fetchSource(sourceContext);
        SearchResponse response= client.prepareSearch(index)
                .setTypes("type", type)
                .setQuery(qb)
                .setSearchType(SearchType.DEFAULT)
                .setExplain(true)
                .get();
        SearchHits searchHits = response.getHits();
        int size = (int) searchHits.getTotalHits();

        SearchResponse responseAll= client.prepareSearch(index)
                .addSort("_id", SortOrder.ASC)
                .setTypes("type", type)
                .setQuery(qb)
                .setSearchType(SearchType.DEFAULT)
                .setFrom(0).setSize(size)
                .setExplain(true)
                .get();

        List<Map<String,Object>> list = Lists.newArrayList();
        SearchHits searchHitsDetail = responseAll.getHits();
        for (int j = 0; j < searchHitsDetail.getHits().length; j++) {
            String json = searchHitsDetail.getHits()[j].getSourceAsString();
            System.out.println(json);
            Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(json);
            HashMap<String, Object> dataAsMap = new HashMap<String, Object>(jsonMap);
            if(fields != null && fields.length > 0){
                Map<String,Object> obj = Maps.newHashMap();
                for (String field: fields){
                    obj.put(field,dataAsMap.get(field));
                }
                list.add(obj);
            }else{
                list.add(dataAsMap);
            }
        }
        return JSONArray.toJSONString(list);
    }

}