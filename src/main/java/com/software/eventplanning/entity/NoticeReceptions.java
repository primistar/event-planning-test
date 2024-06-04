package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Data
@Getter
@Setter
@TableName("notification_receptions")
public class NoticeReceptions {
    @TableId(value = "reception_id", type = IdType.AUTO)
    Integer receptionId;
    Integer logId;
    Integer userId;
    @TableField(value="received_at")
    Timestamp receptionTime;
    @TableField(value="read_at")
    Timestamp readTime;
}