<!doctype html>
<#include "../config/top.ftl">
<#include "../config/left.ftl">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>权限列表-权限管理</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
    <@top/>
    <div class="container">
        <div class="row">
            <@role 3/>
            <div class="col-md-10">
                <h2>权限列表</h2>
                <hr>
                <form id="formId" class="form-inline">
                    <div class="form-group">
                        <input type="text" name="findContent" id="findContent"
                               placeholder="输入权限名称" class="form-control" width="300"/>
                        <span>
                            <button type="submit" class="btn btn-primary">查询</button>
                            <button type="button" class="btn btn-success">增加权限</button>
                            <button type="button" class="btn btn-danger">删除</button>
                        </span>
                    </div>
                </form>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th><input type="checkbox"/> </th>
                        <th>权限名称</th>
                        <th>角色类型</th>
                        <th>操作</th>
                    </tr>
                    <#if page.list?exists && page.list?size gt 0>
                        <#list page.list as it>
                            <tr>
                                <td><input type="checkbox" value="${it.id}"/> </td>
                                <td>${it.name}</td>
                                <td>${it.url}</td>
                                <td>
                                    <i class="glyphicon glyphicon-remove"></i>
                                    <a href="#">删除</a>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td class="text-center text-danger" colspan="4">
                                没有相关记录
                            </td>
                        </tr>
                    </#if>
                </table>
                <div class="pagination pull-right">
                    ${page.pageHtml}
                </div>
            </div>
        </div>
    </div>
</body>
</html>