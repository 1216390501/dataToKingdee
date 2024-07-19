package com.example.webapp.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test")
public class YourEntity {
    @TableId
    private Long id;
    private String name;
//    private String name2;
    // 其他字段...
}
