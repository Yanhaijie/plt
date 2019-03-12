package com.supermoney.open.platform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.open.platform.utils.Base64Util;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenPlatformApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void livingTest() throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\2018年5月\\RQX7Z{DNW@O0[PPZ~NG]9$L.png");
		FileInputStream idInputStream = new FileInputStream(file);
		String base64 = "data:image/png;base64," + getBase64ByString(idInputStream);
		base64 = URLEncoder.encode(base64,"utf-8");

		String url = "http://localhost:8905/living";
		//请求头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//请求体
		MultiValueMap<String,String> param = new LinkedMultiValueMap<String, String>();
		param.add("living_img", base64);
		param.add("merchantId", "test1");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate.exchange(url,
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String,String>>(param, headers),
				byte[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			byte[] responseData = response.getBody();
			Map<String, Object> responsMap = (Map<String, Object>) JSON.parse(responseData);
			System.out.println(responsMap);
		}
		else {
			System.out.println("fail");
		}
	}

	@Test
	public void compareTest() throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\2018年5月\\RQX7Z{DNW@O0[PPZ~NG]9$L.png");
		FileInputStream idInputStream = new FileInputStream(file);
		String face1 = "data:image/png;base64," + getBase64ByString(idInputStream);
		face1 = URLEncoder.encode(face1,"utf-8");

		File file2 = new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\2018年5月\\RQX7Z{DNW@O0[PPZ~NG]9$L.png");
		FileInputStream idInputStream2 = new FileInputStream(file2);
		String face2 = "data:image/png;base64," + getBase64ByString(idInputStream2);
		face2 = URLEncoder.encode(face2,"utf-8");

		String url = "http://localhost:8905/compare";
		//请求头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//请求体
		MultiValueMap<String,String> param = new LinkedMultiValueMap<String, String>();
		param.add("face1_img", face1);
		param.add("face2_img", face2);
		param.add("merchantId", "test1");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate.exchange(url,
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String,String>>(param, headers),
				byte[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			byte[] responseData = response.getBody();
			Map<String, Object> responsMap = (Map<String, Object>) JSON.parse(responseData);
			System.out.println(responsMap);
		}
		else {
			System.out.println("fail");
		}
	}


	@Test
	public void ocrTest() throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File("C:\\Users\\Chaplin\\Desktop\\临时文件\\新建文件夹\\ok\\1.jpg");
		FileInputStream idInputStream = new FileInputStream(file);
		String base64 = "data:image/png;base64," + getBase64ByString(idInputStream);
		base64 = URLEncoder.encode(base64,"utf-8");

		String url = "http://localhost:8905/ocr";
		//请求头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//请求体
		MultiValueMap<String,String> param = new LinkedMultiValueMap<String, String>();
		param.add("ocr_img", base64);
		param.add("merchantId", "test1");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate.exchange(url,
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String,String>>(param, headers),
				byte[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			byte[] responseData = response.getBody();
			Map<String, Object> responsMap = (Map<String, Object>) JSON.parse(responseData);
			System.out.println(responsMap);
		}
		else {
			System.out.println("fail");
		}
	}

	@Test
	public void blacklistTest(){
		String url = "http://localhost:8905/blacklist";
		//请求头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//请求体
		MultiValueMap<String,String> param = new LinkedMultiValueMap<String, String>();
		param.add("name", "name");
		param.add("idNumber", "3654654465");
		param.add("phoneNumber", "1591596159");
		param.add("merchantId", "test1");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate.exchange(url,
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String,String>>(param, headers),
				byte[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			byte[] responseData = response.getBody();
			Map<String, Object> responsMap = (Map<String, Object>) JSON.parse(responseData);
			System.out.println(responsMap);
		}
		else {
			System.out.println("fail");
		}
	}

    @Test
    public void nikTest(){
        String url = "http://localhost:8905/nik";
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //请求体
        MultiValueMap<String,String> param = new LinkedMultiValueMap<String, String>();
        param.add("nik", "1671045802880013");
        param.add("merchantId", "test1");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url,
                HttpMethod.POST,
                new HttpEntity<MultiValueMap<String,String>>(param, headers),
                byte[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            byte[] responseData = response.getBody();
            Map<String, Object> responsMap = (Map<String, Object>) JSON.parse(responseData);
            System.out.println(responsMap);
        }
        else {
            System.out.println("fail");
        }
    }


	/**
	 * 把流转base64
	 * @param inputStream
	 * @return
	 */
	public static String getBase64ByString(InputStream inputStream) {
		byte[] data = null;
		try {
			if (inputStream == null || inputStream.available() == 0 ){
				return null;
			}
			data = new byte[inputStream.available()];
			inputStream.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new String(Base64.encodeBase64(data));
	}

}
