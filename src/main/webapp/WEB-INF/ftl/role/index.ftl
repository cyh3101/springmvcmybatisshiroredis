<!DOCTYPE html>
<#include "../config/top.ftl"/>
<#include "../config/left.ftl"/>
<html>
    <head>
        <meta charset="utf-8">
        <title>角色列表-权限管理</title>
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
                <@role 1/>
                <div class="col-md-10">
                    <h2>角色列表</h2>
                    <hr>
                    <form id="formId" class="form-inline">
                        <div class="form-group">
                            <input type="text" class="form-control" width="300" id="findContent" name="findContent"
                                   placeholder="请输入角色名称/角色类型"/>
                        </div>
                        <span>
                            <button type="submit" class="btn btn-primary">查询</button>
                            <button type="button" class="btn btn-success">增加角色</button>
                            <button type="submit" class="btn btn-danger">删除</button>
                        </span>
                        <hr>
                        <table class="table table-bordered">
                            <tr>
                                <th><input type="checkbox" > </th>
                                <th>角色名称</th>
                                <th>角色类型</th>
                                <th>操作</th>
                            </tr>
                            <#if page.list?exists && page.list?size gt 0>
                                <#list page.list as it>
                                    <tr>
                                        <td><input type="checkbox" value="${it.id}" id="checkAll"/> </td>
                                        <td>${it.name}</td>
                                        <td>${it.type}</td>
                                        <td>
                                            <#if it.type == '888888'>
                                                -
                                            <#else>
                                                <i class="glyphicon glyphicon-remove"></i>
                                                <a href="#">删除</a>
                                            </#if>
                                        </td>
                                    </tr>
                                </#list>
                            <#else>
                                <tr>
                                    <td colspan="4" class="text-center text-danger">
                                        没有找到相关记录
                                    </td>
                                </tr>
                            </#if>
                        </table>
                    </form>
                    <div class="pagination pull-right">
                        ${page.pageHtml}
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>