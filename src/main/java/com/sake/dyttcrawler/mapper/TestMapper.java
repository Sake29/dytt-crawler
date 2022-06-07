package com.sake.dyttcrawler.mapper;

import com.sake.dyttcrawler.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestMapper {
    List<Test> getAll();
}
