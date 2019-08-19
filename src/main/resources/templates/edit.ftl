<#import "parts/common.ftl" as c>

<@c.page>
    <#--  Страница редактирования документа  -->
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Document editor
    </a>
    <div class="collapse <#if document??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post">
                <div class="form-group">
                    <input type="text" class="form-control"
                           value="<#if document??>${document.title}</#if>" size="100" name="title" placeholder="Название документа"/>
                </div>
                <div class="form-group">
                    <textarea class="form-control" rows="10" cols="45"
                              name="about" placeholder="Содержание документа"><#if document??>${document.about}</#if></textarea>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="hidden" name="id" value="<#if document??>${document.id}</#if>">
                <button type="submit" class="btn btn-primary">Save document</button>
            </form>
        </div>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Title</th>
            <th scope="col">Content</th>
            <th scope="col">Author</th>
            <th scope="col">Second side</th>
        </tr>
        </thead>
        <#if document??>
            <tbody>
                <tr>
                    <th>${document.id}</th>
                    <td>${document.title}</td>
                    <td>${document.about}</td>
                    <td>${document.authorName}</td>
                    <td>${document.otherName}</td>
                </tr>
            </tbody>
        <#else>
            No document
        </#if>
    </table>
</@c.page>