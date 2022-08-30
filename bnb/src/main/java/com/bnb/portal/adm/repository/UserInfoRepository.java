package com.bnb.portal.adm.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnb.portal.adm.entity.UserInfoEntity;

@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {
	UserInfoEntity findByUserid(String userid);
}
