package com.fable.kscc.api.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fable.kscc.api.model.common.EnumActivitiRestType;
import sun.net.www.HeaderParser;
import sun.net.www.http.HttpClient;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author duyang 20170805
 * 封装spring中RestTemplate
 */
public class RestHepler {

	private final static Logger LOGGER = LoggerFactory.getLogger(RestHepler.class);

	/**
	 * rest 接口请求
	 * 
	 * @param url
	 *            rest请求地址
	 * @param param
	 *            请求参数
	 * @param uriVariables
	 *            url地址中参数
	 * @return 响应结果
	 */
	public static String exchange(String url, Object param, Object... uriVariables) {
		try {

			HttpHeaders requestHeaders = new HttpHeaders();

			HttpEntity<Object> requestEntity = new HttpEntity<Object>(param, requestHeaders);
			RestTemplate rest = new RestTemplate();

			ResponseEntity<String> rssResponse = rest.exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);

			if (null != rssResponse) {
				return rssResponse.getBody();
			}

		} catch (RestClientException e) {

			LOGGER.error("The request failed，msg={}", e);

		} catch (Exception e) {
			LOGGER.error("The request failed，msg={}", e);
		}

		return null;
	}
	
	public static String exchange(String url, Object param,  EnumActivitiRestType restType, Object... uriVariables) {
		// 获取用户名和密码
		/*String username = SystemParamInit.getValueByKey("rest.username");
		String password = SystemParamInit.getValueByKey("rest.password");*/

		try {
			HttpHeaders requestHeaders = new  HttpHeaders();
			requestHeaders.add("app-key", "insightview");
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(param, requestHeaders);
			RestTemplate rest = new RestTemplate();

			ResponseEntity<String> resp = null;
			if(EnumActivitiRestType.GET == restType) {
				resp = rest.exchange(url, HttpMethod.GET, requestEntity, String.class, uriVariables);
			}
			else if(EnumActivitiRestType.POST == restType) {
				resp = rest.exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);
			}
			else if(EnumActivitiRestType.PUT == restType) {
				resp = rest.exchange(url, HttpMethod.PUT, requestEntity, String.class, uriVariables);
			}
			else if(EnumActivitiRestType.DELETE == restType) {
				resp = rest.exchange(url, HttpMethod.DELETE, requestEntity, String.class, uriVariables);
			}

			if (null != resp) {
				return resp.getBody();
			}

		} catch (RestClientException e) {

			LOGGER.error("The request failed，msg={}", e);

		} catch (Exception e) {
			LOGGER.error("The request failed，msg={}", e);
		}

		return null;
	}

	/**
	 * rest 接口请求
	 * @param url rest请求地址
	 * @param param 请求参数
	 * @param uriVariables url地址中参数  默认new Object()
	 * @return 响应结果
	 */
	public static <T> T sendHttpRequest(String url, Class<T> resultType, Object param, Object... uriVariables) {
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Set-Cookie","SSO_COOKIE_KEY=58039bb3-43b2-4d5e-a36d-a17a6c7ccd5c");
			/**
			 * 参数以表单形式提交 参照科达视讯平台文档;
			 */
			requestHeaders.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded;charset=utf-8;"));

			HttpEntity<Object> requestEntity = new HttpEntity<Object>(param, requestHeaders);
			System.out.println(requestEntity.toString());
			RestTemplate rest = new RestTemplate();

			ResponseEntity<T> rssResponse = rest.exchange(url, HttpMethod.POST, requestEntity, resultType, uriVariables);
			if (null != rssResponse) {
//				HttpHeaders headers=rssResponse.getHeaders();
				return rssResponse.getBody();
			}
		} catch (Exception e) {
			LOGGER.error("The request failed，msg={}", e);
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T sendHttpRequestXml(String url, Class<T> resultType, Object param, Object... uriVariables) {
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.parseMediaType("text/xml;charset=utf-8;"));

			HttpEntity<Object> requestEntity = new HttpEntity<>(param, requestHeaders);
			System.out.println(requestEntity.toString());
			RestTemplate rest = new RestTemplate();

			ResponseEntity<T> rssResponse = rest.exchange(url, HttpMethod.POST, requestEntity, resultType, uriVariables);
			if (null != rssResponse) {
//				HttpHeaders headers=rssResponse.getHeaders();
				return rssResponse.getBody();
			}
		} catch (Exception e) {
			LOGGER.error("The request failed，msg={}", e);
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String url = "http://58.211.191.7:90/api/v1/system/token";
		String res = sendHttpRequest(url, String.class, "oauth_consumer_key=kdvpb&oauth_consumer_secret=admin888", new Object());
		System.out.println(res);
	}
}
