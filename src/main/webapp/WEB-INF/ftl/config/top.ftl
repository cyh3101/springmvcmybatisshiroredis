<#macro top>
<div class="navbar navbar-inverse " style="height: 41px;">
    <div class="container">
        <nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
            <ul class="nav navbar-nav" id="topMenu">
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    个人中心<span class="caret"></span> </a>
                    <ul class="dropdown-menu">
                        <li><a href="${basePath}/u/index">个人资料</a> </li>
                        <li><a href="${basePath}/u/updateSelf">资料修改</a> </li>
                        <li><a href="${basePath}/u/updatePswd">密码修改</a> </li>
                        <li><a href="${basePath}/role/myPermission">我的权限</a> </li>
                    </ul>
                </li>

                    <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        用户中心<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                                <li><a href="${basePath}/member/list">用户列表</a> </li>
                                <li><a href="${basePath}/member/online">在线用户</a> </li>
                        </ul>
                    </li>
                    <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        权限管理<span class="caret"></span> </a>
                        <ul class="dropdown-menu">
                                <li><a href="${basePath}/role/index">角色列表</a> </li>
                                <li><a href="${basePath}/role/allocation">角色分配</a> </li>
                                <li><a href="${basePath}/permission/index">权限列表</a> </li>
                                <li><a href="${basePath}/permission/allocation">权限分配</a> </li>
                        </ul>
                    </li>

                <li><a href="#" class="dropdown-toggle" target="_blank">相关博客</a> </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">管理员
                    <span class="caret"></span> </a>
                    <ul class="dropdown-menu">
                        <li><a href="${basePath}/u/index">个人资料</a> </li>
                        <li><a href="${basePath}/role/myPermission">我的权限</a> </li>
                        <li><a href="#">退出登录</a> </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>

</#macro>