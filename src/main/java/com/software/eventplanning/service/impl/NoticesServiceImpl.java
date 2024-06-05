package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.controller.dto.NoticesReceptionsDTO;
import com.software.eventplanning.entity.NoticeReceptions;
import com.software.eventplanning.entity.Notices;
import com.software.eventplanning.entity.NoticesInfo;
import com.software.eventplanning.mapper.NoticesMapper;
import com.software.eventplanning.service.INoticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticesServiceImpl extends ServiceImpl<NoticesMapper, Notices> implements INoticesService {

    private static final Log LOG = Log.get();
    @Autowired
    private NoticesMapper noticesMapper;

    @Override
    public Notices sendnotice(NoticesDTO noticesDTO){
        Notices one = new Notices();
        BeanUtil.copyProperties(noticesDTO, one, true);
        save(one);
        return one;
    }

    @Override
    public List<NoticesInfo> getnoticesInfoByUserId(Integer userId){
        List<NoticesInfo> noticesinfo=noticesMapper.getnoticesInfoByUserId(userId);
        return noticesinfo;
    }

    @Override
    public NoticeReceptions receivenotice(NoticesReceptionsDTO noticesReceptionsDTO){
        NoticeReceptions  one = new NoticeReceptions();
        BeanUtil.copyProperties(noticesReceptionsDTO, one, true);
        noticesMapper.addReception(one);
        return one;
    }

    @Override
    public NoticesInfo getnoticesInfoByLogId(Integer logId){
        NoticesInfo noticesInfo=noticesMapper.getnoticesInfoByLogId(logId);
        return noticesInfo;
    }

    @Override
    public List<Notices> getnoticesByUserId(Integer userId){
        List<Notices> notices=noticesMapper.getnoticesByUserId(userId);
        return notices;
    }

    @Override
    public List<NoticeReceptions> getreceptionsByUserId(Integer userId){
        List<NoticeReceptions> noticesReceptions=noticesMapper.getreceptionsByUserId(userId);
        return noticesReceptions;
    }
}
