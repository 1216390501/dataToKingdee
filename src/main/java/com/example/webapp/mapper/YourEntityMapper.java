package com.example.webapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webapp.entity.YourEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YourEntityMapper extends BaseMapper<YourEntity> {

}
