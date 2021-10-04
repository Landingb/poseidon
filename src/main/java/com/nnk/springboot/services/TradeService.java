package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

import java.util.List;

public interface TradeService {

    Trade save(Trade trade);

    List<Trade> findAll();

    Trade findById(Integer id);

    void delete(Integer id);
}
