package com.nxtgear.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
* @author: Patrick F
* @Date:Apr 17, 2017
* ProjectConfig configures spring container
* 
**/

public class ProjectConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	/**
	 * Recieve request from root "/"
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	/**
	 * Non-web classes hooked here
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {  RootConfig.class };
	}
	/**
	 * Web Mvc configured in webconfig
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
}
