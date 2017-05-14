package com.sternik.samujarek;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = { "com.sternik.samujarek" }, excludeFilters = {
		@Filter(type = FilterType.REGEX, pattern = "com\\.sternik\\.samujarek\\.web\\..*") })
@ImportResource({ "classpath:/applicationContext.xml" }) // ,"classpath:/database-config.xml"})
public class SpringBusinessConfig {
}