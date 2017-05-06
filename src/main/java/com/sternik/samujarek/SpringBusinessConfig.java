package com.sternik.samujarek;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = { "com.sternik.samujarek" }, excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "com\\.sternik\\.samujarek\\.web\\..*") })

public class SpringBusinessConfig {
}