package com.bnb.portal.adm.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {
	public String getDual() throws Exception;
}
