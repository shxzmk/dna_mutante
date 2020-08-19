//package com.meli.mutant;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
//
//	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//			"classpath:/META-INF/resources/", "classpath:/static/",
//			"classpath:/WEB-INF/classes/static/"};
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//			registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//	}
//	
//	@Bean
//    public ModelMapper modelMapper() {
//    	return new ModelMapper();
//    }
//       
//}
