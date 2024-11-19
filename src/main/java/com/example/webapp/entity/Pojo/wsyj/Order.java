package com.example.webapp.entity.Pojo.wsyj;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ord_ct")
public class Order {
    @TableId
    private Long id;
    private String address;
    private String agntcde;
    private String asscde;
}