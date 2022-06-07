package com.sake.dyttcrawler.service.impl;

import com.sake.dyttcrawler.entity.Test;
import com.sake.dyttcrawler.mapper.TestMapper;
import com.sake.dyttcrawler.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Test> getAll(){
        return testMapper.getAll();
    }
}
