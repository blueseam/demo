package com.bnb.common;

import java.util.HashMap;
import java.util.Map;

public class RestServiceVO<E> {
	private E dataHeader;
	private E dataBody;
	
	public E getDataHeader() {
		return dataHeader;
	}
	@SuppressWarnings("unchecked")
	public void setDataHeader(int iStatus, String iStatusMsg, String iDebugMsg) {
		Map<String, Object> serviceDataHeader = new HashMap<String, Object>();
		serviceDataHeader.put("status", iStatus);
		serviceDataHeader.put("statusMsg", iStatusMsg);
		serviceDataHeader.put("debugMsg", iDebugMsg);
		
		this.dataHeader = (E) serviceDataHeader;
	}
	public void setDataHeader(E dataHeader) {
		this.dataHeader = dataHeader;
	}
	
	public E getDataBody() {
		return dataBody;
	}
	public void setDataBody(E dataBody) {
		this.dataBody = dataBody;
	}

}
