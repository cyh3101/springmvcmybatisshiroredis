<#macro user index>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix">
            <li class="${(index == 1)?string('active','')}">
                <a href="${basePath}/u/index" >
                    <i class="glyphicon glyphicon-chevron-right"></i>个人资料
                </a>
                <ul class="dropdown-menu" >
                    <li><a href="${basePath}/u/updateSelf">资料修改</a> </li>
                    <li><a href="${basePath}/u/updatePswd">密码修改</a> </li>
                </ul>
            </li>
            <li class="${(index==2)?string('active','')}dropdown">
                <a href="${basePath}/role/myPermission">
                    <i class="glyphicon glyphicon-chevron-right"></i>我的权限
                </a>
            </li>
        </ul>
    </div>
</#macro>
<#macro memeber index>
    <@shiro.hasAnyRoles name='888888,100002'>
        <div class="col-md-2">
            <ul class="nav nav-list nav-stacked nav-tabs dropdown">
                <li class="${(index==1)?string('active','')}"><a href="${basePath}/member/list">
                    <i class="glyphicon glyphicon-chevron-right"></i> 用户列表</a> </li>
                <li class="${(index==2)?string('active','')}"><a href="${basePath}/member/online">
                    <i class="glyphicon glyphicon-chevron-right"></i> 在线用户</a> </li>
            </ul>
        </div>
    </@shiro.hasAnyRoles>
</#macro>
<#macro role index>
    <@shiro.hasAnyRoles name='888888,100003'>
    <div class="col-md-2">
        <ul class="nav nav-list nav-stacked nav-tabs dropdown">
                <@shiro.hasPermission name="role/index">
                    <li class="${(index==1)?string('active','')}"><a href="${basePath}/role/index">
                        <i class="glyphicon glyphicon-chevron-right"></i>角色列表</a> </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="role/allocation">
                    <li class="${(index==2)?string('active','')}"><a href="${basePath}/role/allocation">
                        <i class="glyphicon glyphicon-chevron-right"></i>角色分配</a> </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="permission/index">
                    <li class="${(index==3)?string('active','')}"><a href="${basePath}/permission/index">
                        <i class="glyphicon glyphicon-chevron-right"></i>权限列表</a> </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="permission/allocation">
                    <li class="${(index==4)?string('active','')}"><a href="${basePath}/permission/allocation">
                        <i class="glyphicon glyphicon-chevron-right"></i>权限分配</a> </li>
                </@shiro.hasPermission>
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>

