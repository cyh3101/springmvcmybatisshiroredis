<#macro user>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix">
            <li>
                <a href="${basePath}/u/index" >
                    <i class="glyphicon glyphicon-chevron-right"></i>个人资料
                </a>
                <ul class="dropdown-menu" >
                    <li><a href="${basePath}/u/updateSelf">资料修改</a> </li>
                    <li><a href="${basePath}/u/updatePswd">密码修改</a> </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="${basePath}/role/myPermission">
                    <i class="glyphicon glyphicon-chevron-right"></i>我的权限
                </a>
            </li>
        </ul>
    </div>
</#macro>
<#macro memeber>
    <div class="col-md-2">
        <ul class="nav nav-list nav-stacked nav-tabs dropdown">
            <li><a href="${basePath}/member/list">
                <i class="glyphicon glyphicon-chevron-right"></i> 用户列表</a> </li>
            <li><a href="${basePath}/memeber/online">
                <i class="glyphicon glyphicon-chevron-right"></i> 在线用户</a> </li>
        </ul>
    </div>
</#macro>

