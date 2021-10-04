package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {

    CurvePoint save(CurvePoint curvePoint);

    List<CurvePoint> findAll();

    void delete(Integer id);

    CurvePoint findById(Integer id);
}
