package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ActivitiesDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.service.IParticipantApplicationService;
import com.software.eventplanning.service.impl.AddActivitiesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Slf4j
@RestController("ActivitiesController")
@RequestMapping("/api/activities")
@Api(tags = "活动管理")
@ApiSupport(order = 1)
public class ActivitiesController {
    @Resource
    private AddActivitiesServiceImpl       activitiesService;
    @Resource
    private IParticipantApplicationService participantApplicationService;


    /**
     * 活动列表
     *
     * @param pageNum      分页
     * @param pageSize     分页每页数量
     * @param activityName 活动名称
     * @param startDate    开始时间
     * @param endDate      结束时间
     * @param type 2:我加入的,1:我创建的,0:所有活动
     * @return
     */
    @GetMapping(value = "/list")
    public Result<IPage<Activities>> list(HttpServletRequest request,
                                          @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "activityName",defaultValue = "",required = false) String activityName,
                                          @RequestParam(value = "startDate", required = false) String startDate,
                                          @RequestParam(value = "endDate", required = false) String endDate,
                                          @RequestParam(value = "type", required = false, defaultValue = "0") String type) {
        Users users = (Users) request.getAttribute("users");
        return Result.success("查询成功!", activitiesService.showAllActivities(pageNum, pageSize, activityName, type, users.getId()));
    }

    /**
     * 创建活动
     *
     * @return
     */
    @ApiOperation(value = "创建活动", position = 1)
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody ActivitiesDTO activities,HttpServletRequest request) {
        Users users = (Users) request.getAttribute("users");
        Activities act = new Activities();
        act.setActivityName(activities.getActivityName());
        act.setAddress(activities.getAddress());
        act.setDescription(activities.getDescription());
        act.setCreatorId(users.getId());
        act.setCreatedTime(new Date());
        activitiesService.save(act);
        return Result.success("创建活动成功!");
    }

    /**
     * 用户申请加入活动
     *
     * @return
     */
    @GetMapping(value = "/apply/{activityId}")
    public Result<String> apply(@PathVariable("activityId") Integer activityId,HttpServletRequest request) {
        Users users = (Users) request.getAttribute("users");
        Activities activities = activitiesService.getById(activityId);
        if (activities == null) {
            return Result.error(500,"活动不存在!");
        }
        participantApplicationService.apply(activityId, users.getId(), users.getUsername());
        return Result.success("申请活动成功!");
    }

    /**
     * 获取活动详情
     *
     * @return
     */
    @ApiOperation(value = "获取活动详情", position = 2)
    @ApiImplicitParam(name = "id", value = "活动主键", required = true)
    @GetMapping(value = "/detail/{id}")
    public Result<Activities> info(@PathVariable(value = "id", required = true) Integer id) {
        Activities activities = new Activities();
        return Result.success("获取活动详情成功!", activities);
    }

    /**
     * 编辑活动
     *
     * @return
     */
    @ApiOperation(value = "编辑活动", position = 3)
    @PutMapping()
    public Result<Activities> edit(@Validated @RequestBody Activities activities) {
        return Result.success("编辑活动成功!", activities);
    }

    /**
     * 删除活动
     *
     * @return
     */
    @ApiOperation(value = "删除活动", position = 4)
    @DeleteMapping(value = "/{ids}")
    @ApiImplicitParam(name = "ids", value = "活动主键列表", required = true)
    public Result<String> delete(@NotEmpty(message = "主键不能为空") @PathVariable(value = "ids", required = true) Integer[] ids) {
        return Result.success("删除活动成功!");
    }

    /**
     * 获取活动进度
     *
     * @return
     */
    @ApiOperation(value = "获取活动进度", position = 5)
    @GetMapping(value = "/status/{id}")
    @ApiImplicitParam(name = "id", value = "活动主键", required = true)
    public Result<String> status(@NotEmpty(message = "主键不能为空") @PathVariable(value = "id", required = true) Integer id) {
        return Result.success("", "查询成功!");
    }

    /**
     * 分享活动信息
     *
     * @return
     */
    @ApiOperation(value = "分享活动信息", position = 6)
    @PostMapping(value = "/share")
    public Result<String> share(@RequestBody ActivitiesDTO activities) {
        return Result.success("分享活动成功!");
    }

}
