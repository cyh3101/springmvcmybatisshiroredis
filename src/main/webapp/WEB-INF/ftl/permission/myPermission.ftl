<!doctype html>
<#include "../config/top.ftl">
<#include "../config/left.ftl">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的权限-个人中心</title>
    <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/layui/lay/dest/layui.all.js"></script>
    <script src="${basePath}/js/bootstrap-treeview.js"></script>
</head>
<body>
<@top/>
<div class="container">
    <div class="row">
    <@user 2/>
        <div class="col-md-10">
            <h2>我的权限</h2>
            <hr>
            <div id="myPermissionTree">loading...</div>
        </div>
    </div>
    <script>
        $(function () {
            //加载permission tree data
            var load = layer.load();
            $.post(
                '${basePath}/role/getMyPermissionTree',
                {},
                function (result) {
                layer.close(load);
                if(result && !result.length){
                    return $('#myPermissionTree').html('<code>您没有任何权限</code>'),!1;
                }else {
                    $('#myPermissionTree').treeview({
                        levels:1,
                        color:"#428bca",
                        nodeIcon:"glyphicon glyphicon-user",
                        showTags: true,
                        data:result
                    });
                }
            },
            'json'
            );
        });
    </script>
</div>
</body>
</html>