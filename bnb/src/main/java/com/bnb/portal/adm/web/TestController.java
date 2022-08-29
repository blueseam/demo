package com.bnb.portal.adm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bnb.common.RestServiceClient;
import com.bnb.portal.adm.svc.TestService;
import com.bnb.portal.adm.vo.UserInfoVO;

@RestController
public class TestController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	TestService testService;
	
	@RequestMapping(value = {"/test"}, method = RequestMethod.GET)
	public String test(Model model) throws Exception{
//		model.addAttribute("time", testService.getDual());
//		return "test";
		model.addAttribute("time", "what fuck");
		
        logger.trace("Trace");
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");	
        
		
//		return "test";
        return new RestServiceClient().setServiceData(200, "status", "debut", model);
	}
	
	@RequestMapping(value = {"/test2"}, method = RequestMethod.GET)
	public void test2(Model model) throws Exception{
		model.addAttribute("time", "what fuck");
//		return (ModelAndView) model;
	}
	
	@RequestMapping(value = {"/test3"}, method = RequestMethod.GET)
	public UserInfoVO test3() throws Exception{
		UserInfoVO userInfoVo = new UserInfoVO();
		userInfoVo.setUserName("jude");
		userInfoVo.setEmail("jude@bnbcompany.io");
		userInfoVo.setBirthDate("2022-08-25");
		
		return userInfoVo;
	}
	
	@RequestMapping(value = {"/test4"}, method = RequestMethod.GET)
	public UserInfoVO test4() throws Exception{
		UserInfoVO userInfoVo = new UserInfoVO();
		userInfoVo.setUserName("jude");
		userInfoVo.setEmail("jude@bnbcompany.io");
		userInfoVo.setBirthDate(testService.getDual());
		
		return userInfoVo;
	}
}
