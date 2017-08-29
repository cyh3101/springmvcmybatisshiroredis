<!doctype html>
<#include "../config/top.ftl"/>
<#include "../config/left.ftl"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户角色分配-权限管理</title>
    <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/layui/lay/dest/layui.all.js"></script>
</head>
<body>
    <@top/>
    <div class="container">
        <div class="row">
            <@role 2/>
            <div class="col-md-10">
                <h2>用户角色分配</h2>
                <hr>
                <form id="formId" action="" class="form-inline">
                    <div class="form-group">
                        <input type="text" id="findContent" name="findContent"
                               placeholder="输入用户昵称/用户账号" class="form-control">
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" class="btn btn-danger">清空用户角色</button>
                    </div>
                    <hr>
                    <table class="table table-bordered">
                        <tr>
                            <th><input type="checkbox" id="checkAll"/> </th>
                            <th>用户昵称</th>
                            <th>Email/账号</th>
                            <th>状态</th>
                            <th>拥有的角色</th>
                            <th>操作</th>
                        </tr>
                        <#if page?exists && page.list?size gt 0>
                            <#list page.list as it>
                                <tr>
                                    <td><input type="checkbox"  value="${it.id}"/> </td>
                                    <td>${it.nickname}</td>
                                    <td>${it.email}</td>
                                    <td>${(it.status==1)?string('有效','无效')}</td>
                                    <td><#if it.roleNames?exists>
                                        ${it.roleNames}
                                    <#else>
                                        -
                                    </#if></td>
                                    <td><span class="glyphicon"/><a href="#">选择角色</a> </td>
                                </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td colspan="6" class="text-center text-danger"></td>
                            </tr>
                        </#if>
                    </table>
                </form>
            </div>

        </div>
    </div>
</body>
</html>