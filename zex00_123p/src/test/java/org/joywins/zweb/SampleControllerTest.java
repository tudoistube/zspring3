package org.joywins.zweb;
//...120p.
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.joywins.zweb.SampleControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class SampleControllerTest {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleControllerTest.class);

	@Inject
    private WebApplicationContext wac;

	//...MockMvc : 브라우저에서 요청과 응답을 의미하는 객체.
	
    private MockMvc mockMvc;
    
    
    //...가상의 요청과 응답을 처리하기 위해 setup()에서는 @Before로 처리되어
    //...매번 테스트 메서드의 실행전에 MockMvc 객체를 만듦.
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.info("zweb.setup............");
    }
    
    @Test
    public void testDoA() throws Exception{
    	
    	//...MockMvc를 사용해서 perform()을 실행하는데, 이때 get(), post()등을
    	//...이용해서 GET/POST방식의 호출을 사용함.
    	mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
    }

}
