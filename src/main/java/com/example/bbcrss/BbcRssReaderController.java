package com.example.bbcrss;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BbcRssReaderController {
 
    @Autowired
    private com.example.bbcrss.dao.BbcRssDAO newsDAO;
 
    @ResponseBody
    @RequestMapping(value="/",produces = "application/json")
    public String showFirst10RSSElements() {
    		
        Iterable<com.example.bbcrss.dto.BbcRssDTO> all = newsDAO.findFirst10ByOrderByPublicationDateDesc();
 
        StringBuilder jsonString = new StringBuilder();
        
        jsonString.append("[");
        all.forEach(p -> jsonString.append("{\"name\":\"" + p.getTitle() + "\",\"link\":\"" +
        		p.getLink() + "\",\"date\":\"" + p.getPublicationDate() + "\"},"));
        if(jsonString.length() > 1) {
        	jsonString.deleteCharAt(jsonString.length() - 1); 
        }
        jsonString.append("]");

        return jsonString.toString();		
    }
}
