<!DOCTYPE html>
<#include "../config/top.ftl"/>
<#include "../config/left.ftl"/>
<html>
    <head>
        <title>用户列表-用户中心</title>
        <link rel="icon" href="${basePath}/images/cai.ico" type="image/x-icon"/>
        <link rel="shortcut icon" href="${basePath}/images/cai.ico"/>
        <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${basePath}/css/layui.css"/>
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <@top/>
        <div class="container" style="min-height: 300px;margin-top: 60px;">
            <div class="row">
                <@memeber/>
                <div class="col-md-10">
                    <h2>用户列表</h2>
                    <hr>
                    <form action="" id="formId" class="form-inline">
                        <div class="form-group">
                            <input type="text" class="form-control" style="width: 300px;"
                                   value="${findContent?default('')}" name="findContent" id="findContent"
                                   placeholder="输入昵称/账号"/>
                        </div>
                        <span>
                            <button type="submit" class="btn btn-primary">查询</button>
                            <button type="button" class="btn btn-danger">删除</button>
                        </span>
                        <hr>
                        <table class="table table-bordered">
                            <tr>
                                <th><input type="checkbox" id="selectAll"/> </th>
                                <th>昵称</th>
                                <th>Email/账号</th>
                                <th>登录状态</th>
                                <th>创建时间</th>
                                <th>最后登录时间</th>
                                <th>操作</th>
                            </tr>
                            <#if page?exists && page.list?size gt 0>
                                <#list page.list as it>
                                    <tr>
                                        <td><input type="checkbox" id="${it.id}"/> </td>
                                        <td>${it.nickname}</td>
                                        <td>${it.email}</td>
                                        <td>${(it.status==1)?string('有效','无效')}</td>
                                        <td>${it.createTime?string('yyyy-MM-dd HH:mm')}</td>
                                        <td>${it.lastLoginTime?string('yyyy-MM-dd HH:mm')}</td>
                                        <td>${(it.status==1)?string('<i class="glyphicon glyphicon-eye-close"></i>&nbsp;','<i class="glyphicon glyphicon-eye-close"></i>')}
                                            <a href="javascript:void(0);">${(it.status==1)?string('禁止登录','允许登录')}</a>
                                            <a href="javascript:void(0);">删除</a>
                                        </td>
                                    </tr>
                                </#list>
                            <#else>
                                <tr>
                                    <td class="text-center text-danger" colspan="7">没有找到相关记录</td>
                                </tr>
                            </#if>
                        </table>
                        <div class="pagination pull-right">
                            ${page.pageHtml}
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </body>
</html>