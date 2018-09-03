package com.example.bbcrss.init;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.example.bbcrss.dao.BbcRssDAO;
import com.example.bbcrss.dto.BbcRssDTO;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
 
@Component
public class BbcRssFetcher {
 
    private BbcRssDAO bbcRssDAO;
    private String url;

    @Autowired
    public BbcRssFetcher(BbcRssDAO bbcRssDAO) {
    	
        this.bbcRssDAO = bbcRssDAO;
        
        String filePath = "config.properties";
    	Properties prop = new Properties();
    	
    	try (InputStream inputStream = BbcRssFetcher.class.getClassLoader().getResourceAsStream(filePath)) {
			prop.load(inputStream);
			url = prop.getProperty("rss.url");
    	} catch (IOException ex) {
			System.out.println("Problem occurs when reading file !");
			ex.printStackTrace();
		} 
    }
	
    @Scheduled(fixedRate = 10000) //Schedule every 10 seconds
    public void fetchRSSintoDB() throws IllegalArgumentException, MalformedURLException, FeedException, IOException {
    		
    	bbcRssDAO.deleteAll();
	    
    	try (XmlReader reader = new XmlReader(new URL(url))) {
	    SyndFeed feed = new SyndFeedInput().build(reader);
	    	for (SyndEntry entry : feed.getEntries()) {
    			BbcRssDTO n1 = new BbcRssDTO();
	    		n1.setTitle(entry.getTitle());
	    		n1.setLink(entry.getLink());
	    		n1.setPublicationDate(entry.getPublishedDate());
	    		bbcRssDAO.save(n1);
	    	}
    	}
    }
}
