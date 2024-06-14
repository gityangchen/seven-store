package com.yc.sevnenstore.en;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("prize_history")
public class PrizeHistory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String prizeName;
    private Double probability;
    private Timestamp createdAt;
}
