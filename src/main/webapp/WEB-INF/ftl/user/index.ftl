<!DOCTYPE html>
<#assign base=request.contextPath/>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <h2>个人资料</h2>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>昵称</th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>Email/账号</th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>创建时间</th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>最后登录时间</th>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <script src="${basePath}/js/jquery-1.8.2.min.js" type="text/javascript"/>
    <script src="${basePath}/js/layui/layui.js" type="text/javascript"/>
</body>
</html>