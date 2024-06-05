package com.software.eventplanning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.mapper.BackgroundUserMapper;
import com.software.eventplanning.service.IBackgroundUserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.software.eventplanning.common.Constants.CODE_531;
import static com.software.eventplanning.common.Constants.CODE_532;
@Service
public class BackgroundUserServiceImpl extends ServiceImpl<BackgroundUserMapper, Users> implements IBackgroundUserService {
    //更改某一个用户的权限
    @Override
    public Result changeUserRole(int userId,int role)
    {
        //查询是否有该用户
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_id",userId);
        Users user=getOne(queryWrapper);
        if(user==null)
        {
            return Result.error(CODE_531,"该用户不存在");
        }
        user.setRole(role); //改变权限
        user.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));//更改记录中的最近修改时间
        boolean flag=update(user,queryWrapper);
        if(flag==true)
        {
            return Result.success("修改权限成功",user);
        }
        else
        {
            return Result.error(CODE_532,"修改权限操作失败");
        }
    }

    @Override
    public int getUserRole(int myId)
    {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_id",myId);
        Users user=getOne(queryWrapper);
        return user.getRole();
    }
}