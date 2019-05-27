<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/5/21
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>网站信息</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/pintuer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/pintuer.js"></script>
</head>
<body>
<div id="info" class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>

    <table class="table table-hover text-center" id="grade">
        <tr>
            <th width="5%">姓名</th>
            <th>课程名</th>
            <th>授课老师</th>
            <th width="250">成绩</th>
        </tr>


    </table>
</div>


</body>
<script>
    (
        $.ajax({
            url: "/scoremg/stu/mygrade",
            success: function (msg) {
                $.each(msg,function (i, value) {
                    $("#grade").append("<tr class='hello'><td>" + value.name + "</td><td>" + value.cname + "</td><td>" + value.tname + "</td><td>" + value.score + "</td>" + "<td><div class='button-group'>" +
                        "</tr>" + "<br>")

                })
            }
        })
    )

</script>
</html>
