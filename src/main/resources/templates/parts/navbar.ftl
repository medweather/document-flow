<#import "logout.ftl" as l>
<#include "security.ftl">
    <#-- Шапка сайта -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Document flow</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if firm??>
            <li class="nav-item">
                <a class="nav-link" href="/main">Documents</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/documentFlow">Document management system</a>
            </li>
            </#if>
        </ul>
        <#if firm??>
        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />
        </#if>
    </div>
</nav>