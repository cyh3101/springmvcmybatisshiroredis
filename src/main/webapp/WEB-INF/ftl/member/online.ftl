<!DOCTYPE html>
<#include "../config/top.ftl">
<#include "../config/left.ftl">
<html>
    <head>
        <meta charset="UTF-8">
        <title>在线用户-用户中心</title>
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
        <div class="container" style="min-height: 300px;margin-top: 60px;">
            <div class="row">
                <@memeber 2/>
                <div class="col-md-10">
                    <h2>当前在线用户</h2>
                    <table class="table table-bordered">
                        <tr>
                            <th>SessionID</th>
                            <th>昵称</th>
                            <th>Email/账号</th>
                            <th>创建会话</th>
                            <th>会话最后活动</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <#if list?exists && list?size gt 0>
                            <#list list as it>
                                <tr>
                                    <td>${it.sessionId}</td>
                                    <td>${it.nickname}</td>
                                    <td>${it.email}</td>
                                    <td>${it.createTime?string('yyyy-MM-dd HH:mm')}</td>
                                    <td>${it.lastAccessTime?string('yyyy-MM-dd HH:mm')}</td>
                                    <td>${(it.sessionStatus)?string('有效','已踢出')}</td>
                                    <td>
                                        <a href="${basePath}/member/onlineDetail">详情</a>
                                        <a href="javascript:void(0);">激活</a>
                                    </td>
                                </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td class="text-center text-danger" colspan="7">没有用户</td>
                            </tr>
                        </#if>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>