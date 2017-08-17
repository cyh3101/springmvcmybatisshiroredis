<!DOCTYPE html>
<#assign base=request.contextPath/>
<#include "../config/top.ftl">
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
</head>
<body>
    <@top 1/>
    <div class="container" style="min-height:300px;margin-top: 60px;">

        <div class="row">
            <div class="col-md-10">
                <h2>个人资料</h2>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>昵称</th>
                        <td>${token.nickname?default('未设置')}</td>
                    </tr>
                    <tr>
                        <th>Email/账号</th>
                        <td>${token.email}</td>
                    </tr>
                    <tr>
                        <th>创建时间</th>
                        <td>${token.createTime?string('yyyy-MM-dd HH:mm')}</td>
                    </tr>
                    <tr>
                        <th>最后登录时间</th>
                        <td>${token.lastLoginTime?string('yyyy-MM-dd HH:mm')}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <script src="${basePath}/js/jquery-1.8.2.min.js" type="text/javascript"/>
    <script src="${basePath}/js/layui/layui.js" type="text/javascript"/>
</body>
</html>