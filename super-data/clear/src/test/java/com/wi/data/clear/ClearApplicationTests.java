package com.wi.data.clear;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wi.data.clear.service.CDeviceService;
import com.wi.data.clear.service.ClearBussService;
import com.wi.data.clear.thread.ElasticsearchClearThread;
import com.wi.data.clear.timer.Scheduler;
import com.wi.data.clear.utils.ElasticsearchUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.assertj.core.util.Lists;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ClearApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

/*	@Autowired
	private ElasticsearchUtil elasticsearchUtil;
    @Resource
    private CDeviceService cDeviceService;

	@Resource
    private ClearBussService clearBussService;*/





	//@Test
	public  void  esGetTest() throws Exception{
	//	elasticsearchUtil.clearDeviceMixTure();
	/*	String field = "clear_status";
        System.out.println("1111111111111111111");
		SearchResponse responseDetail = elasticsearchUtil.getNotExsitsField("supermoneylog","applog",field,0,1000);
		SearchHits searchHitsDetail = responseDetail.getHits();
		System.out.println("==============");
		System.out.println(searchHitsDetail.getTotalHits());
		System.out.println("==============");
		for (int j = 0; j < searchHitsDetail.getHits().length; j++) {
			String index = searchHitsDetail.getHits()[j].getIndex();
			String type = searchHitsDetail.getHits()[j].getType();
			String id = searchHitsDetail.getHits()[j].getId();
			String json = searchHitsDetail.getHits()[j].getSourceAsString();
			Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(json);
			HashMap<String, Object> dataAsMap = new HashMap<String, Object>(jsonMap);
			System.out.println(index);
			System.out.println(type);
			System.out.println(id);
			System.out.println("===========json");
			if(json.indexOf(field)>0){
				System.out.println("----------------");
			}else{
				System.out.println(json);
				System.out.println("========================");
			}
			System.out.println(json);
		}
		System.out.println("====================");*/
	}



}
