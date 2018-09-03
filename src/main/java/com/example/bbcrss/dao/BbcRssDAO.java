package com.example.bbcrss.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bbcrss.dto.BbcRssDTO;
 
@Repository
public interface BbcRssDAO extends CrudRepository<BbcRssDTO, Long> {
 
    public List<BbcRssDTO> findFirst10ByOrderByPublicationDateDesc();
}
