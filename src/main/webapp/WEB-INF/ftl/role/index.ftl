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
        <script src="${basePath}/js/layui/lay/dest/layui.all.js"></script>
        <script>
            function addRole() {
                var name = $('#name').val();
                var type = $('#type').val();
                if($.trim(name) == ''){
                    return layer.msg('角色名字不能为空'),!1;
                }
                if(!/^[a-z0-9A-Z]{6}$/.test(type)){
                    return layer.msg('角色类型为6位数字字母'),!1;
                }
                var load = layer.load();
                $.post(
                        '${basePath}/role/addRole',
                        {name:name,type:type},
                        function (result) {
                            layer.close(load);
                            if(result && result.status != 200){
                                return layer.msg('添加角色失败'),!1;
                            } else {
                                layer.msg(result.message);
                                setTimeout(function () {
                                    $('#formId').submit();
                                },1000);
                            }
                        },
                        'json'
                );
            }
            function deleteById(ids) {
                var index = layer.confirm("确定删除" + ids.length + "个元素?",function () {
                    var load = layer.load();
                    $.post(
                            '${basePath}/role/deleteRoleById',
                           {ids:ids.join(',')},
                           function (result) {
                                layer.close(load);
                                if(result && result.status != 200){
                                    return layer.msg("删除失败，请重试"),!1;
                                } else {
                                    layer.msg(result.message);
                                    setTimeout(function () {
                                        $('#formId').submit();
                                    },1000);
                                }
                           },
                           'json'
                   );
                    layer.close(index);
                });
            }
        </script>
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
                            <a href="javascript:void(0);" class="btn btn-success" onclick="$('#addRole').modal();">增加角色</a>
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
                                                <a href="javascript:deleteById([${it.id}])">删除</a>
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
            <div class="modal fade" id="addRole" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" aria-label="关闭"
                                    data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">添加角色</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="control-label">角色名称</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="请输入角色名称"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">角色类型</label>
                                <input type="text" class="form-control" id="type" name="type" placeholder="请输入角色类型 [字母 + 数字] 6位"/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="addRole();">增加角色</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>