package com.example.webapp.service;

import com.example.webapp.entity.YourEntity;
import com.example.webapp.mapper.YourEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YourEntityService {
    private final YourEntityMapper yourEntityMapper;

    @Autowired
    public YourEntityService(YourEntityMapper yourEntityMapper) {
        this.yourEntityMapper = yourEntityMapper;
    }

    public List<YourEntity> fetchEntities() {
        return yourEntityMapper.selectList(null);
    }
}
