package com.software.eventplanning.controller.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivitiesDTO implements Serializable {
    @ApiModelProperty("活动主键")
    @TableId(value = "activity_id", type = IdType.AUTO)
    private Integer activityId;

    @ApiModelProperty("活动名称")
    private String activityName;

    @ApiModelProperty("活动描述")
    private String description;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String address;

    @ApiModelProperty("活动状态")
    private String status;

}
