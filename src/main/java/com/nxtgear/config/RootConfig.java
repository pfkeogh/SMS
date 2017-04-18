package com.nxtgear.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
* @author: Patrick F
* @Date:Apr 17, 2017
* Spring configuration for Service, DAO beans
**/

@Configuration
@ComponentScan({"com.nxtgear"})
public class RootConfig {
	
}