<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/5/8
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <div class="padding border-bottom">
        <a class="button border-yellow" href="add"><span class="icon-plus-square-o"></span > 添加内容</a>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">ID</th>
            <th>用户名</th>
            <th>密码</th>
            <th width="250">操作</th>
        </tr>
        <c:forEach items="${users}" var="user"  >
        <tr>
            <td >${user.id}</td>
            <td>${user.name}</td>
            <td aria-readonly="true" >**********</td>

            <td>
                <div class="button-group">
                    <a type="button" id="modify" class="button border-main" href="javascript:void(0)" onclick="return modify(${user.id})"><span class="icon-edit"></span>修改</a>
                    <a class="button border-red" href="javascript:void(0)" onclick="return del(${user.id})"><span class="icon-trash-o"></span> 删除</a>
                </div>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
<div id="modifyDiv"class="panel admin-panel"  hidden="hidden">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改用户信息</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="">
            <div id="userID" hidden="hidden"></div>
            <div class="form-group">
                <div class="label">
                    <label>姓名</label>
                </div>
                <div class="field">
                    <input type="text" id="name" class="input w50" value="" name="title" data-validate="required:请输入姓名" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>密码</label>
                </div>
                <div class="field">
                    <input type="password" id="password" class="input w50" value="" name="title" data-validate="required:密码" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>确认密码</label>
                </div>
                <div class="field" id="warn">
                    <input type="password" id="password2" class="input w50" value="" name="title" data-validate="required:密码" />
                    <div class="tips"></div>
                </div>
            </div>
            <if condition="$iscid eq 1">

                <div class="form-group">
                    <div class="label">
                        <label>用户身份</label>
                    </div>
                    <div id="roleItem" class="field" style="padding-top:8px;">
                    </div>
                </div>
            </if>

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
    function del(id){
    if(confirm("确认删除吗？")){
        window.location="del?id="+id;
    }
    }

    function modify(id){
        $.ajax({
            url: "findById",
            data: {id:id},
            success: function(msg){
                $("#info").hide()
                $("#modifyDiv").show()
                $("#userID").val(msg.id);
                $("input[id='name']").val(msg.name);
                $("input[id='password']").val(msg.password);
            }
        });
    }
    $("button[type='submit']").click(function(){
        var id=$("#userID").val();
        var name=$("input[id='name']").val();
        var pwd=$("input[id='password']").val();
        var repwd=$("input[id='password2']").val();
        if(pwd!=repwd){
            $("#warn").append("<b style='color:#ff1215;'>两次密码不一致</b>");
           return false;

         }else{
            $.ajax({
                url: "/scoremg/user/modify",
                data: { id:id,
                        name:name,
                        password:pwd
                },
                success: function(msg){
                    alert( msg);
                }
            });
        }

    })


</script>

</body>
</html>
