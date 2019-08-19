<#import "parts/common.ftl" as c>

<@c.page>
    <#--  Система документооборота  -->
    <div class="my-3"><h5>Add document:</h5></div>
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th scope="col">№</th>
                <th scope="col">Title</th>
                <th scope="col">Content</th>
                <th scope="col">Author</th>
                <th scope="col">Second side</th>
                <th scope="col">Author's signature</th>
                <th scope="col">Other company signature</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <#if documentsFlow??>
        <tbody>
            <#list documentsFlow as document>
                <tr>
                    <th>${document.id}</th>
                    <td>${document.title}</td>
                    <td>${document.about}</td>
                    <td>${document.authorName}</td>
                    <td>${document.otherName}</td>
                    <td><#if document.isSign()>Подписан<#else>Не подписан</#if></td>
                    <td><#if document.isSignOtherSide()>Подписан<#else>Не подписан</#if></td>
                    <td>
                        <div class="btn-group" role="group" aria-label="Basic example">
                          <a class="btn btn-light" href="/edit/${document.id}?document=${document.id}">Edit</a>
                          <a class="btn btn-light" href="/sign/${document.id}">Sign</a>
                          <a class="btn btn-light" href="/delete/${document.id}">Delete</a>
                        </div>
                    </td>
                </tr>
            </#list>
        </tbody>
        <#else>
        No document
        </#if>
    </table>
</@c.page>