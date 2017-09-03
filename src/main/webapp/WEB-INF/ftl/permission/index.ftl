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
    <script src="${basePath}/js/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/layui/lay/dest/layui.all.js"></script>
    <script src="${basePath}/js/init.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        so.init(function () {
            so.checkBoxInit('#selectAll','[check=box]');
            so.id('deleteAll').on('click',function () {
                deleteById();
            });
        });
        function deleteById() {
            var checked = $('[check=box]:checked');
            var ids = [];
            $.each(checked,function () {
               ids.push(this.id);
            });
            _delete(ids.join(','));
        }
        function _delete(ids) {
            var index = layer.confirm("确定删除?",function () {
                var load = layer.load();
                $.post(
                       "/permission/deleteById",
                       {ids:ids},
                       function (result) {
                           layer.close(load);
                           if(result && result.status != 200){
                               return layer.msg("删除失败", so.default()),!1;
                           } else {
                               layer.msg("删除成功");
                           }
                           setTimeout(function () {
                               $('#formId').submit();
                           });
                       }
               );
                layer.close(load);
            });
        }
        function addPermission() {
            var name = $('#pname').val();
            var url = $('#purl').val();
            if(!name || $.trim(name) ==''){
                return layer.msg("权限名称不能为空",so.default()),!1;
            }
            if(!url || $.trim(url) == ''){
                return layer.msg("权限URL地址不能为空",so.default()),!1;
            }
            var load = layer.load();
            $.post(
                    "${basePath}/permission/addPermission",
                    {name:name,url:url},
                    function (result) {
                        layer.close(load);
                        if(result && result.status != 200){
                            return layer.msg("添加失败"),!1;
                        }else {
                            layer.msg("添加成功");
                        }
                        setTimeout(function () {
                            $('#formId').submit();
                        },1000);
                    },
                    'json'
            );
        }
    </script>
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
                            <button type="button" class="btn btn-success" onclick="$('#addPermissionModal').modal();">增加权限</button>
                            <button type="button" id="deleteAll" class="btn btn-danger">删除</button>
                        </span>
                    </div>
                </form>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th><input type="checkbox" id="selectAll"/> </th>
                        <th>权限名称</th>
                        <th>角色类型</th>
                        <th>操作</th>
                    </tr>
                    <#if page.list?exists && page.list?size gt 0>
                        <#list page.list as it>
                            <tr>
                                <td><input type="checkbox" check="box" id="${it.id}"/> </td>
                                <td>${it.name}</td>
                                <td>${it.url}</td>
                                <td>
                                    <i class="glyphicon glyphicon-remove"></i>
                                    <a href="javascript:_delete(${it.id});">删除</a>
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
        <div class="modal fade" id="addPermissionModal" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加权限</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="control-label">权限名称:</label>
                            <input type="text" name="pname" id="pname" class="form-control" placeholder="请输入权限名称"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">权限URL地址:</label>
                            <input type="text" name="purl" id="purl" class="form-control" placeholder="请输入URL地址"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="addPermission()">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>