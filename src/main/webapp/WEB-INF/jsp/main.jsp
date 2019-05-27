
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sh" uri="http://shiro.apache.org/tags" %>
<html lang="zh-cn">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/pintuer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img src="<%=request.getContextPath()%>/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
    </div>
    <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="javascript:void(0)" id="exit"><span class="icon-power-off"></span>退出登录</a> </div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2><span class="icon-user"></span>基本设置</h2>
    <ul style="display:block">
        <sh:hasRole name="admin">
            <%----%>
        <li><a href="javascript:void(0)" target="right" id="userManage"><span class="icon-caret-right"></span>用户管理</a></li>
            </sh:hasRole>
            <sh:hasRole name="productManager">
        <li><a href="<%=request.getContextPath()%>/curriculum/info" target="right"><span class="icon-caret-right"></span>成绩管理</a></li>
            </sh:hasRole>
<sh:hasRole name="orderManager">
            <li><a href="mygrade" target="right" id="getGrade"><span class="icon-caret-right"></span>我的成绩</a>
            </li>
            </sh:hasRole>
<sh:hasRole name="productManager">
        <li><a href="<%=request.getContextPath()%>/settings/settingsinfo" target="right"><span class="icon-caret-right"></span>课程信息</a></li>
    </sh:hasRole>
</div>
<script type="text/javascript">
    $("#userManage").click(function () {
        window.location="user/info";
    })
    $(function(){
        $(".leftnav h2").click(function(){
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function(){
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });
    $("#exit").click(function(){
        if(confirm("确认推出本系统吗？")){
            window.location="/scoremg/logout.action";
        }
    })
</script>
<ul class="bread">
    <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
    <li><a href="##" id="a_leader_txt">网站信息</a></li>
    <li><b>当前语言：</b><span style="color:#ff22c3;">中文</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="info.html" name="right" width="100%" height="100%"></iframe>
</div>
<%--<div style="text-align:center;">
    <p>来源:<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
</div>--%>
</body>
</html>
