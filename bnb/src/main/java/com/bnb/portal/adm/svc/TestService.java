package com.bnb.portal.adm.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnb.portal.adm.dao.TestDao;

@Service
public class TestService {

	@Autowired
	TestDao testDao;
 
    /* select dual */
	public String getDual() throws Exception{
		return testDao.getDual();
	}

}
