package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Activities {
    @TableId(type = IdType.AUTO)
    private Integer activityId;
    @TableField(value = "activity_name")
    private String  activityName;
    private String  description;
    @TableField(value = "created_by")
    private Integer creatorId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date    startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date    endTime;
    private String  address;

    @TableField(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @TableField(value = "updated_at")
    private Date    updatedTime;  //活动的最后修改时间
    @TableField(value = "template_id")
    private Integer templateId; //模板ID
}
