<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/5/12
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/pintuer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/pintuer.js"></script>
    <script src="<%=request.getContextPath()%>/js/pintuer.js"></script>
</head>
<body>
<div id="defaultPage">
按科目查询 <select id="subject">
    <option></option>
</select>
按学生名查询<input id="username">
按学生成绩查询<input id="stuGrade">
<button id="search">查询</button>
<button id="download">一键导出表格</button>
<script type="text/javascript">
    function check() {
        var excel_file = $("#excel_file").val();
        if (excel_file == "" || excel_file.length == 0) {
            alert("请选择文件路径！");
            return false;
        } else {
            return true;
        }
    }

</script>
<form action="upLoadExcel" method="post" enctype="multipart/form-data" onsubmit="return check();">
    <div style="margin: 30px;">
        <input id="excel_file" type="file" name="filename" accept="xlsx" size="80" />
        <input id="excel_button" type="submit" value="导入Excel" />
    </div>
</form>
<table id="two" class="table table-hover text-center" hidden="hidden">
    <tr>
        <th width="10%">学生名称</th>
        <th width="15%">课程名</th>
        <th width="20%">授课老师</th>
        <th width="15%">学生成绩</th>
        <th width="15%">操作</th>
    </tr>
    <tr id="ajaxData">

    </tr>


</table>
</div >
<div>
    <a href="javascript:void(0)" id="firstPage" >首页</a><a href="javascript:void(0)" id="lastPage">上一页</a>
    <a href="javascript:void(0)" id="curPage"></a><a href="javascript:void(0)" id="nextPage">下一页</a>
    <a href="javascript:void(0)" id="endPage">尾页</a>
    <p id="countPage" hidden="hidden"></p>
    <p id="cur" hidden="hidden"></p>
</div>
<script>




    (
        $.ajax({
        url: "criteriaQuery",
        success: function (data) {
            $("#one").hide()
            $("#two").show()
            $.each(data.pageData,function (i,value) {
                $("#two").append("<tr calss='hello'><td>" + value.name + "</td><td>" + value.cname + "</td><td>" + value.tname + "</td><td>" + value.score + "</td>" + "<td><div class='button-group'>" +
                    "<a class='button border-main' href='javascript:void(0)' onclick='return modify("+value.id+","+value.cid+")'><span class='icon-edit'></span> 修改</a>" +
                    "<a class='button border-red' href='javascript:void(0)' onclick='return del("+value.id+","+value.cid+")'><span class='icon-trash-o'></span> 删除</a></div></td></tr>" + "<br>")

            })
            $("#curPage").text("当前为第"+(data.curPage)+"页")
            $("#cur").val(data.curPage)
            $("#countPage").val(data.countPage)
        }

    })
    )
    $("#lastPage").click(function () {
        var subject = $("#subject").val();
        var username = $("#username").val();
        var stuGrade = $("#stuGrade").val();
        if($("#cur").val()==1){
            alert("当前已经为第一页了！")
            return;
        }
        var curPage=$("#cur").val()
        curPage--;
        $.ajax({
            url:"criteriaQuery",
            data:{
                subject: subject,
                username: username,
                stuGrade: stuGrade,
                curPage:curPage
            },
            success:function (data) {
                $("#two").empty()
                $.each(data.pageData,function (i,value) {

                    $("#two").append("<tr calss='hello'><td>" + value.name + "</td><td>" + value.cname + "</td><td>" + value.tname + "</td><td>" + value.score + "</td>" + "<td><div class='button-group'>" +
                        "<a class='button border-main' href='javascript:void(0)' onclick='return modify("+value.id+","+value.cid+")'><span class='icon-edit'></span> 修改</a>" +
                        "<a class='button border-red' href='javascript:void(0)' onclick='return del("+value.id+","+value.cid+")'><span class='icon-trash-o'></span> 删除</a></div></td></tr>" + "<br>")

                })
                $("#curPage").text("当前为第"+(data.curPage)+"页")
                $("#cur").val(data.curPage)
                $("#countPage").val(data.countPage)
            }
        })
    })

    $("#nextPage").click(function(){
        var subject = $("#subject").val();
        var username = $("#username").val();
        var stuGrade = $("#stuGrade").val();
        if($("#cur").val()==$("#countPage").val()){
            alert("当前已经为最后一页了！")
            return;
        }
        var curPage=$("#cur").val()
        curPage++;
        //执行下一页操作
        $.ajax({
            url:"criteriaQuery",
            data:{
                subject: subject,
                username: username,
                stuGrade: stuGrade,
                curPage:curPage
            },
            success:function (data) {
                $("#two").empty()
                $.each(data.pageData,function (i,value) {

                    $("#two").append("<tr calss='hello'><td>" + value.name + "</td><td>" + value.cname + "</td><td>" + value.tname + "</td><td>" + value.score + "</td>" + "<td><div class='button-group'>" +
                        "<a class='button border-main' href='javascript:void(0)' onclick='return modify("+value.id+","+value.cid+")'><span class='icon-edit'></span> 修改</a>" +
                        "<a class='button border-red' href='javascript:void(0)' onclick='return del("+value.id+","+value.cid+")'><span class='icon-trash-o'></span> 删除</a></div></td></tr>" + "<br>")

                })
                $("#curPage").text("当前为第"+(data.curPage)+"页")
                $("#cur").val(data.curPage)
                $("#countPage").val(data.countPage)
            }
        })

    })





    $("#endPage").click(function(){
        var countPage=$("#countPage").val();
        var subject = $("#subject").val();
        var username = $("#username").val();
        var stuGrade = $("#stuGrade").val();
        //执行下一页操作
        $.ajax({
            url:"criteriaQuery",
            data:{
                subject: subject,
                username: username,
                stuGrade: stuGrade,
                curPage:countPage
            },
            success:function (data) {
                $("#two").empty()
                $.each(data.pageData,function (i,value) {

                    $("#two").append("<tr calss='hello'><td>" + value.name + "</td><td>" + value.cname + "</td><td>" + value.tname + "</td><td>" + value.score + "</td>" + "<td><div class='button-group'>" +
                        "<a class='button border-main' href='javascript:void(0)' onclick='return modify("+value.id+","+value.cid+")'><span class='icon-edit'></span> 修改</a>" +
                        "<a class='button border-red' href='javascript:void(0)' onclick='return del("+value.id+","+value.cid+")'><span class='icon-trash-o'></span> 删除</a></div></td></tr>" + "<br>")

                })
                $("#curPage").text("当前为第"+(data.curPage)+"页")
                $("#cur").val(data.curPage)
                $("#countPage").val(data.countPage)
            }
        })

    })
    $("#firstPage").click(function(){
        var subject = $("#subject").val();
        var username = $("#username").val();
        var stuGrade = $("#stuGrade").val();
        //执行第一页操作
        $.ajax({
            url:"criteriaQuery",
            data:{
                subject: subject,
                username: username,
                stuGrade: stuGrade,
                curPage:1
            },
            success:function (data) {
                $("#two").empty()
                $.each(data.pageData,function (i,value) {

                    $("#two").append("<tr calss='hello'><td>" + value.name + "</td><td>" + value.cname + "</td><td>" + value.tname + "</td><td>" + value.score + "</td>" + "<td><div class='button-group'>" +
                        "<a class='button border-main' href='javascript:void(0)' onclick='return modify("+value.id+","+value.cid+")'><span class='icon-edit'></span> 修改</a>" +
                        "<a class='button border-red' href='javascript:void(0)' onclick='return del("+value.id+","+value.cid+")'><span class='icon-trash-o'></span> 删除</a></div></td></tr>" + "<br>")

                })
                $("#curPage").text("当前为第"+(data.curPage)+"页")
                $("#cur").val(data.curPage)
                $("#countPage").val(data.countPage)
            }
        })

    })


    $("#subject").click($.ajax({
            url: "curriculs",
            success: function (data) {
                $(data).each(function (i, value) {
                    $("#subject").append("<option>" + value.name + "</option>");

                });
            }
        }))
    $("#download").click(function () {
        if (confirm("确定下载吗?")) {
            window.location = "downloadExcel"
        }
    })


    $("#search").click(function () {

        $("#two").empty();
        $("#two").append("<tr> <th width='10%'>学生名称</th><th width='15%'>课程名</th>" +
            "<th width='20%'>授课老师</th><th width='15%'>学生成绩</th>" +
            "<th width='15%'>操作</th></tr>")


        //进行动态查询的方法
        var subject = $("#subject").val();
        var username = $("#username").val();
        var stuGrade = $("#stuGrade").val();
        var curPage = $("#cur").val();
        $.ajax({
            url: "criteriaQuery",
            data: {
                "subject": subject,
                "username": username,
                "stuGrade": stuGrade,
                "curPage":curPage
            },
            success: function (data) {
                $("#one").hide()
                $("#two").show()
                $.each(data.pageData,function (i,value) {
                    $("#two").append("<tr calss='hello'><td>" + value.name + "</td><td>" + value.cname + "</td><td>" + value.tname + "</td><td>" + value.score + "</td>" + "<td><div class='button-group'>" +
                        "<a class='button border-main' href='javascript:void(0)' onclick='return modify("+value.id+","+value.cid+")'><span class='icon-edit'></span> 修改</a>" +
                        "<a class='button border-red' href='javascript:void(0)' onclick='return del("+value.id+","+value.cid+")'><span class='icon-trash-o'></span> 删除</a></div></td></tr>" + "<br>")

                })
                $("#curPage").val(data.curPage)


            }

        })
    })

    function del(id,cid){
        if(confirm("确认删除吗？")){
            $.ajax({
                url:"del",
                data:{
                    id:id,
                    cid:cid
                },
                success: function (msg) {
                    alert(msg)
                    window.location.reload()
                }
            })
        }
    }

    function modify(id,cid){

        window.location="modifyPage?id="+id+"&cid="+cid;

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
                url: "modify",
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
