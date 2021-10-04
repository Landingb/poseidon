package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class BidListServiceImpl implements BidListService{

    private BidListRepository bidListRepository;

    @Autowired
    public BidListServiceImpl(BidListRepository p_bidRepo) {
        bidListRepository = p_bidRepo;
    }


    public List<BidList> findAll(){
        return bidListRepository.findAll();
    }


    @Override
    public BidList save(BidList bid) {

        return bidListRepository.save(bid);
    }


    @Override
    public BidList findById(Integer id) {
        Optional<BidList> bid = bidListRepository.findById(id);
        if(bid.isPresent())
        {
            return bid.get();
        }

        return null;
    }


    @Override
    public void delete(Integer id) {
        BidList bid = findById(id);
        if(bid != null) {
            bidListRepository.delete(bid);
        }

    }
}
