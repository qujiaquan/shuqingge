package com.snut.material.config;



import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
;

import org.springframework.boot.context.properties.ConfigurationProperties;
import com.alibaba.druid.pool.DruidDataSource;


/*
 @Configuration  配置注解  表示此类是springBoot项目中一个配置类,sprngboot启动时会扫描
*/
@Configuration
public class DruidDataSourceConfig {

	/*
	   @Bean  == <bean  id=""  class="">  作用在方法上,方法中会产生一个对象,最终把此对象交给spring容器
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druid() {
		DruidDataSource  dataSource = new DruidDataSource();
		//dataSource.setInitialSize();
		return dataSource;
	}




	 
}
