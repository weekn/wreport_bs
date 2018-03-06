package com.nfjd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.nfjd.mapper")
public class Application  implements EmbeddedServletContainerCustomizer {  
	 @RequestMapping("/weekn")
     String home() {
         return "Hello World,i m wreport bs!";
     }
     public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
     }
     
     
     private CorsConfiguration buildConfig() {
 		CorsConfiguration corsConfiguration = new CorsConfiguration();
 		corsConfiguration.addAllowedOrigin("*");
 		corsConfiguration.addAllowedHeader("*");
 		corsConfiguration.addAllowedMethod("*");
 		return corsConfiguration;
 	}
 	
 	/**
 	 * 跨域过滤器
 	 * @return
 	 */
 	@Bean
 	public CorsFilter corsFilter() {
 		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
 		source.registerCorsConfiguration("/**", buildConfig()); // 4
 		return new CorsFilter(source);
 	}
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {//设置端口
		container.setPort(8081);  
		
	}
}
