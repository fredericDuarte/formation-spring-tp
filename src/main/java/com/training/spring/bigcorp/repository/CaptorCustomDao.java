package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.Captor;

import java.util.List;

public interface CaptorCustomDao {
    List<Captor> findBySiteId(String siteId);
}
