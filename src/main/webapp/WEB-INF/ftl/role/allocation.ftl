<!doctype html>
<#include "../config/top.ftl"/>
<#include "../config/left.ftl"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户角色分配-权限管理</title>
    <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/layui/lay/dest/layui.all.js"></script>
    <script src="${basePath}/js/init.js"></script>
    <script>
        so.init(function () {
            so.checkBoxInit('#checkAll','[check=box]');
            so.id('deleteAll').on('click',function () {
                var checks = $('[check=box]:checked');
                if(!checks.length){
                    return layer.msg('请选择要清除的用户',so.default),!1
                }
                var array = [];
                checks.each(function () {
                    array.push(this.value);
                });
                return deleteById(array);
            });
        });
        function deleteById(ids) {
            var index = layer.confirm("确定清除?",function () {
                var load = layer.load();
                $.post(
                        "${basePath}/role/clearRoleByUserIds",
                        {ids:ids.join(',')},
                        function (result) {
                            if(result && result.status != 200){
                                return layer.msg("删除失败",so.default()),!1;
                            }
                            layer.msg("删除成功");
                            setTimeout(function () {
                                $('#formId').submit();
                            },1000);
                        },
                        'json'

                );
            })
        }
        selectRoleById
        function selectRoleById(id){
            var load = layer.load();
            $.post(
                    "${basePath}/role/getRoleById",
                    {id:id},
                    function (result) {
                        layer.close(load);
                        if(result && result.length){
                            var html = [];
                            $.each(result,function () {
                                html.push("<div class='checkbox'><label>");
                                html.push("<input type='checkbox' id='");
                                html.push(this.id);
                                html.push("'");
                                if(this.checked){
                                    html.push(" checked='checked'");
                                }
                                html.push(" name='");
                                html.push(this.name);
                                html.push("'/>");
                                html.push(this.name);
                                html.push("</label></div>");
                            });
                            return so.id('selectRoleForm').html(html.join('')) & $('#selectRole').modal(),$('#selectUserId').val(id),!1;
                        } else {
                            return layer.msg('没有找到相关信息',so.default);
                        }
                    },
                    'json'
            );
        }
        function selectRole(){
            var checked = $("#selectRoleForm :checked");
            var ids=[], names=[];
            $.each(checked,function () {
                ids.push(this.id);
                names.push($.trim($(this).attr('name')));
            });

            var index = layer.confirm("确定提交?",function () {
                var load = layer.load();
                $.post(
                        "${basePath}/role/addRole2User",
                        {userId:$('#selectUserId').val(),ids:ids.join(',')},
                        function (result) {
                            layer.close(load);
                            if(result && result.status !=200){
                                return layer.msg(result.message),!1;
                            }else{
                                layer.msg("设置成功");
                            }
                            setTimeout(function () {
                                $('#formId').submit();
                            },1000);
                        },
                        'json'
                );
            });
        }
    </script>
</head>
<body>
    <@top/>
    <div class="container">
        <div class="row">
            <@role 2/>
            <div class="col-md-10">
                <h2>用户角色分配</h2>
                <hr>
                <form id="formId" action="" class="form-inline">
                    <div class="form-group">
                        <input type="text" id="findContent" name="findContent"
                               placeholder="输入用户昵称/用户账号" class="form-control">
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" id="deleteAll" class="btn btn-danger">清空用户角色</button>
                    </div>
                    <hr>
                    <table class="table table-bordered">
                        <input type="hidden" id="selectUserId"/>
                        <tr>
                            <th><input type="checkbox" id="checkAll"/> </th>
                            <th>用户昵称</th>
                            <th>Email/账号</th>
                            <th>状态</th>
                            <th>拥有的角色</th>
                            <th>操作</th>
                        </tr>
                        <#if page?exists && page.list?size gt 0>
                            <#list page.list as it>
                                <tr>
                                    <td><input type="checkbox" check="box"  value="${it.id}"/> </td>
                                    <td>${it.nickname}</td>
                                    <td>${it.email}</td>
                                    <td>${(it.status==1)?string('有效','无效')}</td>
                                    <td roleIds="${it.roleIds?default('')}">${it.roleNames?default('-')}</td>
                                    <td><span class="glyphicon glyphicon-share-alt"/><a href="javascript:selectRoleById(${it.id});">选择角色</a> </td>
                                </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td colspan="6" class="text-center text-danger"></td>
                            </tr>
                        </#if>
                    </table>
                </form>
            </div>
        </div>
        <div class="modal fade bs-example-modal-sm" id="selectRole" role="dialog">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close"  data-dismiss="modal"><span>&times;</span> </button>
                        <h4 class="modal-title" id="selectRoleLabel">添加角色</h4>
                    </div>
                    <form id="selectRoleForm" class="modal-body">
                        加载中...
                    </form>

                    <div class="modal-footer">
                        <button type="button" id="close" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" id="save" onclick="selectRole();" class="btn btn-primary">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>