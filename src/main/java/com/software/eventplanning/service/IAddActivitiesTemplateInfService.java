package com.software.eventplanning.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.ActivitiesTemplateDTO;
import com.software.eventplanning.entity.Template;

public interface IAddActivitiesTemplateInfService extends IService<Template> {
    Template AddActivitiesTemplateInf(ActivitiesTemplateDTO activitiesTemplateDTO);

    IPage<Template> list(Integer pageNum, Integer pageSize, Integer template, String key);
}
