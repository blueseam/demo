package com.bnb.common;

import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestServiceClient {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public String setServiceData(int iStatus, String iStatusMsg, String iDebugMsg, Object iData) throws Exception{
//		Map<String, Object> serviceDataHeader = new HashMap<String, Object>();
//		serviceDataHeader.put("status", iStatus);
//		serviceDataHeader.put("statusMsg", iStatusMsg);
//		serviceDataHeader.put("debugMsg", iDebugMsg);
		
		RestServiceVO<Object> restServiceVO = new RestServiceVO<Object>();
		restServiceVO.setDataHeader(iStatus, iStatusMsg, iDebugMsg);
		restServiceVO.setDataBody(iData);
		
		String resultDataString = objectMapper.writeValueAsString(restServiceVO);
		return resultDataString;
	}
	

	//RestTemplate, WebClient 는 기존 spring-web 에 있던 모듈을 개발 해야 함.. 위의 모들은 가짜임...
	
}
