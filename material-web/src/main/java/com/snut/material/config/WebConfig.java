package com.snut.material.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;



@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration inter = registry.addInterceptor(new TokenInterceptor());//注册拦截器
		inter.addPathPatterns("/admin/**"); //管理员需要拦截过滤地址
		inter.excludePathPatterns("/admin/loginCtl/login");//放行地址
		InterceptorRegistration interUser = registry.addInterceptor(new TokenInterceptorUser());//注册拦截器
		interUser.addPathPatterns("/user/**"); // 用户需要拦截过滤地址
		interUser.excludePathPatterns("/user/loginCtl/**");//放行地址
//		InterceptorRegistration interFront = registry.addInterceptor(new TokenInterceptorUser());//注册拦截器
//		interFront.addPathPatterns("/front/**"); // 用户需要拦截过滤地址

//		interFront.excludePathPatterns("/front/emotionCtl/**");//放行地址





		//放行行前台首页,文章详细信息等地址
		//放行swagger页面请求，不让其进入到DispatcherServlet中
//		inter.excludePathPatterns("/swagger*/**"); //放行swagger
//		inter.excludePathPatterns("/v2/**");//放行swagger
//		inter.excludePathPatterns("/webjars/**");//放行swagger
	}

	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations(
				"classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/");
	}*/
}
