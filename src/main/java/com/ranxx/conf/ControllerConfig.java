package com.ranxx.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerConfig {

	@Autowired
	MyResourceUrlProvider resourceUrlProvider;

	@Autowired
	private MySiteSetting mySiteSetting;

	@Autowired
	@ModelAttribute("urls")
	public MyResourceUrlProvider urls() {
		this.resourceUrlProvider.setHost(mySiteSetting.getStaticHost());
		return this.resourceUrlProvider;
	}

}