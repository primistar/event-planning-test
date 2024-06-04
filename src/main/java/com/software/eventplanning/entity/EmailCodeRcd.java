package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("email_code_rcd")
public class EmailCodeRcd {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value="email")
    private String  email;
    private String  code;
    @TableField(value = "created_at")
    private Date    createdTime;
}
