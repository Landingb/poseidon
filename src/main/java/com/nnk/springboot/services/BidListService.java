package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService {

    public List<BidList> findAll();

    public BidList save(BidList bid);

    public BidList findById(Integer id);

    public void delete(Integer id);
}
