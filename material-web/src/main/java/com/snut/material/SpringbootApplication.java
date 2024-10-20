package com.snut.material;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
	springboot项目启动类，启动了内置tomcat

	springboot自动配置如何实现
	本质使用大量注解标签
	SpringbootApplication.class  获取SpringbootApplication类的Class对象(加载主类)
	类名.class  对象名.getClass()   Class.forName()
	目的就是加载@SpringBootApplication注解，是一个复合注解标签，
	@SpringBootApplication注解中包含
		@SpringBootConfiguration
			springboot项目中虽然不适用xml配置，但是也是有配置的，使用的是java类作为配置的
			此注解会扫描项目中自己定义的各种带有@Configuration注解的配置类

		@EnableAutoConfiguration
			最核心的自动配置注解标签，会根据配置信息(jdbc,mybatis,redis....)去加载启动相关的第三方组件
			@Import({AutoConfigurationImportSelector.class})
			使用AutoConfigurationImportSelector根据配置信息(pom.xml中所添加的组件)加载相关的组件


		@ComponentScan   扫描本包下所有的类，生成对象

*/

@SpringBootApplication
@EnableScheduling
@MapperScan("com.snut.material.dao")
public class SpringbootApplication {

	public static String tomcatAddress = "F:/Tomcat/apache-tomcat-9.0.43/webapps/materialFile/";
// 	public static String tomcatAddress = "/opt/apache-tomcat-9.0.37/webapps/shuqg/";
	public static void main(String[] args) {


		//之前的
//		SpringApplication.run(SpringbootApplication.class, args);

		//云端项目
		SpringApplication application = new SpringApplication(SpringbootApplication.class);
		//项目启动后生成一个存放进程id的文件，用于关闭服务的
		application.addListeners(new ApplicationPidFileWriter("/opt/app.pid"));
		application.run();
	}




}
