<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/5/9
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html >

<head>

    <title></title>
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
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>



</body>
<script>
    $("button[type='submit']").click(function(){
        var name=$("#name").val();
        var password=$("#password").val();
        console.log(name+"==========="+password)
        var ids="";
        $("input[type='checkbox']:checked").each(function(){
            ids+=$(this).val()+",";
        });
        if(name!=""&&password!=""){
            $.ajax({
                url: "/user/addUser",
                timeout : 3000,
                data : { name: name, password: password},
                success: function(data){
                  if(data==true){
                      alert("添加用户成功")
                  }
                    window.location="info";
                }
            });
        }else{
            alert("no")
        }
    })


</script>
</html>