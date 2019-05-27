<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/23
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/pintuer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/pintuer.js"></script>
</head>
<body>

<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加用户</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="">
            <div class="form-group">
                <div class="label">
                    <label>学生名</label>
                </div>
                <div class="field">
                    <input type="text" id="name" readonly="readonly" class="input w50" value="" name="title" data-validate="required:请输入姓名" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程名</label>
                </div>
                <div class="field">
                    <input type="text"  id="cname" readonly="readonly" class="input w50" value="" name="title"  />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>授课老师</label>
                </div>
                <div class="field">
                    <input type="text" id="tname" readonly="readonly" class="input w50" value="" name="title"  />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>成绩</label>
                </div>
                <div class="field">
                    <input type="text" id="grade" class="input w50" value="" name="title" />
                    <div class="tips"></div>
                </div>
            </div>
                <p id="uid" hidden="hidden"></p>
            <p id="cid" hidden="hidden"></p>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    (
        $.ajax({
            url:"getmodifyInfo",
            success:function (data) {
                $("#name").val(data.name);
                $("#cname").val(data.cname);
                $("#tname").val(data.tname);
                $("#grade").val(data.score);
                $("#uid").val(data.id);
                $("#cid").val(data.cid);
            }
        })
    )

    $("button[type='submit']").click(function(){

        $.ajax({
            url:"modifyScore",
            data:{
                   id:$("#uid").val(),
                cid:$("#cid").val(),
                score:$("#grade").val()
            },
            success:function (data) {
                alert("修改成功！！！")
            }
        })
    })


</script>

</body>
</html>
