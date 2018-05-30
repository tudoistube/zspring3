package org.joywins;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.joywins.filter.SampleFilter;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
/*
 * ...714p.@MapperScan 어노테이션으 value 속성으로 문자열의 배열을 이용할 수
 *    있는데, 이 때 Mapper 인터페이스들이 들어있는 패키지를 인식시킴.
 */
@MapperScan(value={"org.joywins.dao"})
public class Zex07Appendix739pApplication {

	public static void main(String[] args) {
		SpringApplication.run(Zex07Appendix739pApplication.class, args);
	}
	
	/*...709p.
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		
		return sessionFactory.getObject();
		
	}
	 ...before/since 719p.
	    main/resources/mappers/msgUserMapper.xml XML Mapper 를 인식시하게 함.
	*/
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		
		Resource[] res = new PathMatchingResourcePatternResolver().
				getResources("classpath:mappers/*Mapper.xml");
		
		sessionFactory.setMapperLocations(res);
		
		return sessionFactory.getObject();
		
	}
	
	/*
	 * ...730p.Filter 설정.
	 *    스프링부트에서 필터 설정은 web.xml 없이 Application 실행파일에서 처리할 수 있음.
	 *    http://stackoverflow.com/questions/38243381/how-to-set-up-filter-chain-in-spring-boot
	 *    
	 */
	@Bean
	public FilterRegistrationBean sampleFilterRegistration() {
		
		FilterRegistrationBean registration = new FilterRegistrationBean();
		
		
		SampleFilter sampleFilter = new SampleFilter();
		
		registration.setFilter(sampleFilter);
		
		registration.addUrlPatterns("/hiJsp/*");
		
		registration.setName("sampleFilter");
		
		return registration;
		
	}
	
	
	
	
}





































