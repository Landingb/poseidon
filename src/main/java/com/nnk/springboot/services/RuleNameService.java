package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

public interface RuleNameService {

    RuleName save(RuleName rule);

    List<RuleName> findAll();

    void delete(Integer id);

    RuleName findById(Integer id);
}
