package com.bnb.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestServiceResult {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity setServiceData(int iStatus, String iStatusMsg, String iDebugMsg, Object iData) throws Exception {
		RestServiceVO<Object> restServiceVO = new RestServiceVO<Object>();
		restServiceVO.setDataHeader(iStatus, iStatusMsg, iDebugMsg);
		restServiceVO.setDataBody(iData);
		
		String resultDataString = objectMapper.writeValueAsString(restServiceVO);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");		
		
        return new ResponseEntity(resultDataString, responseHeaders, HttpStatus.OK);
	}
	
	public String setServiceStringData(int iStatus, String iStatusMsg, String iDebugMsg, Object iData) throws Exception{
		RestServiceVO<Object> restServiceVO = new RestServiceVO<Object>();
		restServiceVO.setDataHeader(iStatus, iStatusMsg, iDebugMsg);
		restServiceVO.setDataBody(iData);
		
		String resultDataString = objectMapper.writeValueAsString(restServiceVO);
		return resultDataString;
	}

}
