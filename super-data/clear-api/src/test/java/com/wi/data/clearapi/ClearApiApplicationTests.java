package com.wi.data.clearapi;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wi.data.clearapi.service.CAppUserService;
import com.wi.data.clearapi.service.CreditService;
import com.wi.data.clearapi.thread.ElasticsearchSaveThread;
import com.wi.data.clearapi.utils.ElasticsearchUtil;
import com.wi.data.clearapi.utils.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClearApiApplicationTests {

    @Resource
    private CreditService creditService;
	@Resource
	private CAppUserService cAppUserService;
	@Value("${spring.data.elasticsearch.source.ip}")
	private String host;
	@Value("${spring.data.elasticsearch.source.port}")
	private int port;
	@Value("${spring.data.elasticsearch.source.cluster-name}")
	private String clusterName;
	@Resource
	private ElasticsearchUtil elasticsearchUtil;




/*
	@Test
	public void contextLoads() {

	}

*/



	@Test
	public  void  creditServiceTest() throws Exception{
		String generalUrl="http://127.0.0.1:8220/s/merchant/order/findClearUser?access_token=abb62ac9-2c50-454a-b391-2ebd79a585f8";
		String contentType="application/json;charset=UTF-8";
		String params = "page=1&size=10";
		String encoding="UTF-8";
		try {
			String json = HttpUtil.postGeneralUrl(generalUrl, contentType, params, encoding);
			Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(json);
			List<Map<String,Object>> list = (List) jsonMap.get("data");
			if(list != null && list.size()> 0){
				for(Map<String,Object> objectMap : list){
					String userName = (String)objectMap.get("userName");
					Map<String,Object> paramMap = Maps.newHashMap();
					paramMap.put("username",userName);
					List<Map<String,Object>> locationList = cAppUserService.selectLocation(paramMap);
					List<Map<String,Object>> appList = cAppUserService.selectApp(paramMap);
					List<Map<String,Object>> callRecordList = cAppUserService.selectCallrecords(paramMap);
					List<Map<String,Object>> msgList = cAppUserService.selectMsg(paramMap);
					List<Map<String,Object>> mobileList = cAppUserService.selectMobile(paramMap);
					elasticsearchUtil.saveData(host,port,clusterName,"device_location","location",locationList);
					elasticsearchUtil.saveData(host,port,clusterName,"device_app","mobile",appList);
					elasticsearchUtil.saveData(host,port,clusterName,"device_callrecord","mobile",callRecordList);
					elasticsearchUtil.saveData(host,port,clusterName,"device_massage","massage",msgList);
					elasticsearchUtil.saveData(host,port,clusterName,"device_mobile","mobile",mobileList);
				}
			}

		}catch (Exception ex){
			ex.printStackTrace();
		}

		System.out.println("==============================");
//		String jsonString = "{\"query\":{\"match\":{\"device_id\":\"deviceIdValue\"}}}";
//		Map<String,String> paramMap = Maps.newHashMap();
//		Map<String,Object> conditionValue = Maps.newHashMap();
//		paramMap.put("replaceKey","deviceIdValue");
//		paramMap.put("id","1");
//		paramMap.put("methodName","selectAppCount");
//		paramMap.put("uniqueId","0075734156871013");
//		paramMap.put("sql","SELECT * FROM super_data_clear.c_app_user where id =#{id}");
	//	paramMap.put("sql","update  super_data_clear.c_app_user set app_name='supermoneyApp123' where id ='1'");
//		paramMap.put("requestType","get");
//		paramMap.put("url","supermoneyapp/_search");
//		paramMap.put("jsonString",jsonString);
//		Map<String,Object> result = creditService.sqlInvoke(paramMap);
//		Map<String,Object> result = creditService.esInvoke(paramMap);
//		paramMap.put("requestType","post");
//		paramMap.put("url","supermoneyapp/_search");
//		paramMap.put("jsonString",jsonString);
//		conditionValue.put("deviceIdValue","310f02865e61356");
//		creditService.esInvoke(paramMap,conditionValue);
	}

}
