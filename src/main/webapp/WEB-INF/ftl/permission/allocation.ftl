<!doctype html>
<#include "../config/top.ftl"/>
<#include "../config/left.ftl"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>权限分配—权限管理</title>
    <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/layui/lay/dest/layui.all.js"></script>
    <script>
        so.init(function () {
            so.checkBoxInit('#checkAll',[checked])
        });
        function selectPermissionById(id) {
            var load = layer.load();
            $.post(
                    "/permission/selectPermissionById",
                    {id:id},
                    function (result) {
                        layer.close(load);
                        var html = [];
                        if(result && result.length){
                            $.each(result,function () {
                                html.push("<div class='checkbox'>");
                                html.push("<label>");
                                html.push("<input type='checkbox");
                                html.push(" id='");
                                html.push(this.id);
                                html.push("'");
                                if(this.check){
                                    html.push(" checked='checked'");
                                }
                                html.push(" name='");
                                html.push(this.name);
                                html.push("'>");
                                html.push(this.name);
                                html.push("</label></div>");
                            });
                            $('#selectPermissionForm').html(html.join('')),$('#selectRoleId').val(id),$('#selectPermissionModal').modal();

                        }else {
                            return layer.msg(result.message,so.default()),!1;
                        }
                    },
                    'json'
            );
        }
        function selectPermission() {

        }
    </script>
</head>
<body>
    <@top/>
    <div class="container">
        <div class="row">
            <@role 4/>
            <div class="col-md-10">
                <h2>权限分配</h2>
                <hr>
                <form id="formId" action="" class="form-inline">
                    <div class="form-group">
                        <input type="text" id="findContent" name="findContent" class="form-control" placeholder="输入角色名称/角色类型"/>
                        <button type="submit" class="btn btn-primary">查询</button>
                    </div>
                    <hr>
                    <table class="table table-bordered">
                        <input type="hidden" id="selectRoleId"/>
                        <tr>
                            <th><input type="checkbox" id="checkAll"/> </th>
                            <th>角色名称</th>
                            <th>角色类型</th>
                            <th>拥有的权限</th>
                            <th>操作</th>
                        </tr>
                    <#if page.list?exists && page.list?size gt 0>
                        <#list page.list as it>
                            <tr>
                                <td><input type="checkbox" id="${it.id}"/> </td>
                                <td>${it.name}</td>
                                <td>${it.type}</td>
                                <td>${it.permissionNames?default('-')}</td>
                                <td><span class="glyphicon glyphicon-share-alt"/><a href="javascript:selectPermissionById(${it.id})">选择权限</a> </td>
                            </tr>
                        </#list>
                    </#if>
                    </table>
                </form>
                <div class="pagination pull-right">
                    ${page.pageHtml}
                </div>
            </div>
        </div>
        <div class="modal modal-content" id="selectPermissionModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <label>添加权限</label>
                    </div>
                    <form class="modal-body" id="selectPermissionForm">
                        加载中...
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="selectPermission();">保存</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>
</html>