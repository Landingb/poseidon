package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CurvePointServiceImpl implements CurvePointService{

    private static Logger log = LoggerFactory.getLogger(CurvePointServiceImpl.class);

    private CurvePointRepository curvePointRepository;

    @Autowired
    public CurvePointServiceImpl(CurvePointRepository p_curvePointRepo) {
        curvePointRepository = p_curvePointRepo;
    }

    @Override
    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }


    @Override
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }


    @Override
    public void delete(Integer id) {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if(curvePoint.isPresent()) {
            log.info("Deleting CurvePoint id:" + id);
            curvePointRepository.delete(curvePoint.get());
        } else {
            log.error("Error deleting CurvePoint id not found: " + id);
        }
    }

    @Override
    public CurvePoint findById(Integer id) {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if(curvePoint.isPresent()) {
            return curvePoint.get();
        } else {
            return null;
        }
    }

}
