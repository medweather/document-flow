
<#-- Настройки текущего пользователя -->
<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        firm = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = firm.getName()
        currentUser = firm.getId()
    >
    <#else >
    <#assign
        name = "unknown"
        currentUser = -1
    >
</#if>