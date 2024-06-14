package com.yc.sevnenstore.en;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("prize")
public class Prize {
    @TableId
    private Long id;
    private String name;
    private Double probability;
    private Double displayProbability;
    private Boolean disabled;
}
