package com.example.bbcrss;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.bbcrss.dao.BbcRssDAO;
import com.example.bbcrss.dto.BbcRssDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BbcRssReaderApplicationTests {

	@Autowired
	private BbcRssDAO bbcRssDAO;
	
	@Autowired
    private MockMvc mvc;

    @Test
    public void testRSSinsertOK() throws Exception {
    	
    	bbcRssDAO.deleteAll();
    	
    	BbcRssDTO testNews = new BbcRssDTO();
    	testNews.setTitle("Test title");
    	testNews.setLink("https://test.com/");
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	testNews.setPublicationDate(formatter.parse("2018-01-01 01:02:03"));
    	bbcRssDAO.save(testNews);
    	
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"name\":\"Test title\",\"link\""
                		+ ":\"https://test.com/\",\"date\":\"2018-01-01 01:02:03.0\"}]")));
        
        bbcRssDAO.deleteAll();
    }
    
    @Test
    public void testReceiveEmptyRSSList() throws Exception {
    	
    	bbcRssDAO.deleteAll();
    	
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
        
        bbcRssDAO.deleteAll();
    }
}
