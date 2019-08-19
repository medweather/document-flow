<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<#--  Страница приветствия  -->
<div>
    <h5>Welcome to the electronic document management system!</h5>
    <div>This is a simple example of document management</div><br><br>
    <#if !firm??>
    <a href="/login">Authorization</a>
    </#if>
</div>
</@c.page>
