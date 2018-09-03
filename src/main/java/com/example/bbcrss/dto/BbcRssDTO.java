package com.example.bbcrss.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NEWS_ITEMS")
public class BbcRssDTO {
 
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
 
    @Column(name = "Title", length = 255, nullable = false)
    private String title;
 
    @Column(name = "Link", length = 4000, nullable = false)
    private String link;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pubDate", nullable = false)
    private Date publicationDate;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getLink() {
        return link;
    }
 
    public void setLink(String link) {
        this.link = link;
    }
 
    public Date getPublicationDate() {
        return publicationDate;
    }
 
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}