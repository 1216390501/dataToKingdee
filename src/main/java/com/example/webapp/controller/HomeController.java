package com.example.webapp.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webapp.entity.Pojo.ResponseData;
import com.example.webapp.mapper.OrderMapper;
import com.example.webapp.service.InStockAndoutStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1")
public class HomeController {
    @Autowired
    private InStockAndoutStockService inStockAndoutStockService;

    @GetMapping("saveData")//http://127.0.0.1:8017/api/v1/saveData
    public long saveData(@RequestParam String startDate,
                               @RequestParam String endDate) {
        long dataLong = inStockAndoutStockService.instockData(startDate, endDate);
        ResponseData<String> stringResponseData = new ResponseData<>();
        stringResponseData.setCode(200);
        stringResponseData.setData("true");
        stringResponseData.setMessage("");
        return dataLong;
    }
    @GetMapping("saveOut")//http://127.0.0.1:8017/api/v1/saveData
    public long saveOut(@RequestParam String startDate,
                         @RequestParam String endDate) {
        long dataLong = inStockAndoutStockService.outstockData(startDate, endDate);
        ResponseData<String> stringResponseData = new ResponseData<>();
        stringResponseData.setCode(200);
        stringResponseData.setData("true");
        stringResponseData.setMessage("");
        return dataLong;
    }
}
