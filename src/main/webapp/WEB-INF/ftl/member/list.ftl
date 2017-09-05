<!DOCTYPE html>
<#include "../config/top.ftl"/>
<#include "../config/left.ftl"/>
<html>
    <head>
        <title>用户列表-用户中心</title>
        <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
        <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
        <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${basePath}/js/layui/css/layui.css"/>
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="${basePath}/js/jquery-3.2.1.js"></script>

        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="${basePath}/js/layui/lay/dest/layui.all.js"></script>
        <script src="${basePath}/js/init.js"></script>
        <script>
            so.init(function () {
                so.checkBoxInit('#selectAll', '[check=box]');
                $('#deleteAll').on('click',function () {
                    var checkeds = $('[check=box]:checked');
                    var ids = [];
                    $.each(checkeds,function () {
                        ids.push(this.id);
                    });
                    return deleteUserById(ids.join(","));
                });
            });
            function forbidUserById(status,id) {
                var text = status==0?"禁止":"允许";
                var index = layer.confirm("确定"+text+"这个用户",function () {
                    var load = layer.load();
                    $.post('${basePath}/member/forbidUserById',
                            {status:status,id:id},
                            function (result) {
                                layer.close(load);
                                if(result && result.status != 200){
                                    return layer.msg(result.message),!0;
                                } else {
                                    layer.msg(text + '成功');
                                    setTimeout(function () {
                                        $('#formId').submit();
                                    },1000);
                                }
                            }

                    );
                    layer.close(index);
                });
            }

            function deleteUserById(id) {
                var index = layer.confirm('确定删除这个用户',function () {
                   var load = layer.load();
                   $.post('${basePath}/member/deleteUserById',
                           {id:id},
                           function (result) {
                                layer.close(load);
                               if(result && result.status != 200){
                                   layer.msg("删除失败请重试");
                               }else{
                                   layer.msg(result.message);
                                   setTimeout(function () {
                                       $('#formId').submit();
                                   },1000);
                               }
                           }
                   );
                   layer.close(index);
                });
            }
        </script>
    </head>
    <body data-target="#one" data-spy="scroll">
        <@shiro.hasPermission name="888888">
            aaaaa
        </@shiro.hasPermission>
        <@top/>
        <div class="container" style="min-height: 300px;margin-top: 60px;">
            <div class="row">
                <@memeber 1/>
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
                            <button type="button" id="deleteAll" class="btn btn-danger">删除</button>
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
                                        <td><input type="checkbox" check="box" id="${it.id}"/> </td>
                                        <td>${it.nickname}</td>
                                        <td>${it.email}</td>
                                        <td>${(it.status==1)?string('有效','无效')}</td>
                                        <td>${it.createTime?string('yyyy-MM-dd HH:mm')}</td>
                                        <td>${it.lastLoginTime?string('yyyy-MM-dd HH:mm')}</td>
                                        <td>${(it.status==1)?string('<i class="glyphicon glyphicon-eye-close"></i>&nbsp;','<i class="glyphicon glyphicon-eye-open"></i>')}
                                            <a href="javascript:forbidUserById(${(it.status==1)?string('0','1')}, ${it.id});">${(it.status==1)?string('禁止登录','允许登录')}</a>
                                            <a href="javascript:deleteUserById(${it.id});">删除</a>
                                        </td>
                                    </tr>
                                </#list></a>
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