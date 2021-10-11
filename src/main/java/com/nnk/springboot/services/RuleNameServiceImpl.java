package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameServiceImpl implements RuleNameService  {

    private RuleNameRepository ruleNameRepository;

    public RuleNameServiceImpl(RuleNameRepository p_ruleNameRepository) {
        ruleNameRepository = p_ruleNameRepository;
    }

    @Override
    public RuleName save(RuleName rule) {
        return ruleNameRepository.save(rule);
    }


    @Override
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }


    @Override
    public void delete(Integer id) {
        RuleName ruleName = findById(id);
        if(ruleName != null) {
            ruleNameRepository.delete(ruleName);
        }
    }


    @Override
    public RuleName findById(Integer id) {
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);
        if(ruleName.isPresent()) {
            return ruleName.get();
        }
        return null;
    }
}
