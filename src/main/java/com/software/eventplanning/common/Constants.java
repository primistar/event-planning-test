package com.software.eventplanning.common;

public interface Constants {


    Integer CODE_200 = 200;
    Integer CODE_500 = 500;

    Integer CODE_501=501;//应该前端自动填充的信息为空，系统异常（eg: UserId等）;

    //登陆阶段错误
    Integer CODE_400 = 400; //登录用户名或密码为空

    //创建活动时的错误
    Integer CODE_401 = 401; //创建活动时缺少必要的信息
    Integer CODE_402 = 402 ; //创建活动时活动名重复
    Integer CODE_403=403; //填入活动模板信息时缺少活动ID或模板ID
    Integer CODE_405=405; //填入活动模板时缺少模板信息
    Integer CODE_406=406; //创建活动模板时重复

    //处理场地申请以及分配场地时的错误
    Integer CODE_503=503; //场地申请表单中有空项
    Integer CODE_504=504; //申请与分配表中记录冲突，申请驳回
    Integer CODE_505=505;//申请的开始时间大于结束时间
    Integer CODE_506=506;//没有空闲且没有被申请的场地，无法分配
    Integer CODE_507=507;//申请场地的开始时间早于目前的时间
    Integer CODE_508=508;//预约场地的时间必须大于1个小时
    Integer CODE_509=509;//预约时间不能跨天预约
    Integer CODE_510=510;//预约时间只能在早上8点到夜晚10点
    //此后为后台管理的错误
    Integer CODE_530=530;//后台管理员输入的用户ID不合法
    Integer CODE_531=531;//要操作的用户不存在
    Integer CODE_532=532;//修改权限操作失败
    Integer CODE_533=533;//管理员识别错误
    Integer CODE_534=534;//修改权限不足
    Integer CODE_535=535;//管理员无法修改自身权限
    Integer CODE_600 = 600;
    Integer CODE_601 = 601;
    Integer CODE_602 = 602;
    Integer CODE_301 = 301; // 报销信息有空项
    Integer CODE_302 = 302;

}
