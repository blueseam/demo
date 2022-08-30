package com.bnb.portal.adm.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnb.portal.adm.dao.TestDao;
import com.bnb.portal.adm.entity.UserInfoEntity;
import com.bnb.portal.adm.repository.UserInfoRepository;
import com.mysql.cj.xdevapi.Result;

@Service
public class TestService {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	TestDao testDao;
 
    /* select dual */
	public String getDual() throws Exception{
		return testDao.getDual();
	}
	

	public UserInfoEntity getUserInfo(String iUserID) throws Exception{
		return userInfoRepository.findByUserid(iUserID);
	}

}
