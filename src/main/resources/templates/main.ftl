<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="my-3"><h5>Restrictions:</h5></div>

    <#--  Форма ввода ограничения по времени работы в системе документооборота  -->
    <form action="/period" method="post">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="lab1">Start time document flow:</label>
            </div>
            <select class="custom-select-sm-3" name="timeStart" id="lab1">
                <option selected><#if restriction??>${restriction.timeStart}</#if></option>
                <option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option>
                <option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option>
                <option>13</option><option>14</option><option>15</option><option>16</option><option>17</option><option>18</option>
                <option>19</option><option>20</option><option>21</option><option>22</option><option>23</option>
            </select>
            <div class="input-group-append">
                <label class="input-group-text" for="lab2"><strong>:00</strong> &nbsp; &nbsp; &nbsp;Finish time document flow:</label>
            </div>
            <select class="custom-select-sm-3" name="timeFinish" id="lab2">
                <option selected><#if restriction??>${restriction.timeFinish}</#if></option>
                <option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option>
                <option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option>
                <option>13</option><option>14</option><option>15</option><option>16</option><option>17</option><option>18</option>
                <option>19</option><option>20</option><option>21</option><option>22</option><option>23</option>
            </select>
            <div class="input-group-append">
                <label class="input-group-text" for="lab2"><strong>:00</strong></label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Apply</button>
        </div>
    </form>

    <#--  Форма ввода ограничения по количеству документооборотов у одной компании -->
    <form action="/count-flow" method="post">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="lab3">Limit count document flow:</span>
            </div>
            <input type="text" class="form-control-sm-3" name="countDocFlow" style="text-align: center"
                   value="<#if restriction??>${restriction.countDocFlow}</#if>" pattern="^[ 0-9]+$">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Apply</button>
        </div>
    </form>

    <#--  Форма ввода ограничения по количеству созданных документов за определенный промежуток времени -->
    <form action="/count-create-doc" method="post">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="lab4">Limit count create document:</span>
            </div>
            <input type="text" class="form-control-sm-3" name="countCreateDoc" style="text-align: center"
                   value="<#if restriction??>${restriction.countCreateDoc}</#if>" pattern="^[ 0-9]+$">
            <div class="input-group-prepend">
                <span class="input-group-text" id="lab5">Period of create document:</span>
            </div>
            <select class="custom-select-sm-3" name="periodCreateDoc" id="lab2">
                <option selected><#if restriction??>${restriction.periodCreateDoc}</#if></option>
                <option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option>
                <option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option>
                <option>13</option><option>14</option><option>15</option><option>16</option><option>17</option><option>18</option>
                <option>19</option><option>20</option><option>21</option><option>22</option><option>23</option>
            </select>
            <div class="input-group-append">
                <label class="input-group-text" for="lab2"><strong>:00</strong></label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Apply</button>
        </div>
    </form>

    <#--  Форма ввода ограничения по количеству документооборотов между двумя компаниями -->
    <form action="/count-flow-both-sides" method="post">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="lab6">Limit count document flow between both sides:</span>
            </div>
            <input type="text" class="form-control-sm-3" name="countFlowBetBothSides" style="text-align: center"
                   value="<#if restriction??>${restriction.countFlowBetBothSides}</#if>" pattern="^[ 0-9]+$">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Apply</button>
        </div>
    </form>

    <#--  Создание документа  -->
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample"
       role="button" aria-expanded="false" aria-controls="collapseExample">
        Create document
    </a>
    <div class="collapse <#if document??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post">
                <div class="form-group">
                    <input type="text" class="form-control" size="100" name="title" placeholder="Enter the name of the document">
                </div>
                <div class="form-group">
                    <textarea class="form-control" rows="10" cols="45" name="about" placeholder="Content of document"></textarea>
                </div>
                <div class="input-group mb-3">
                    <select class="custom-select" name="otherName">
                        <option selected>ООО "Скворечник"</option>
                        <option>ПАО "Орешник"</option><option>НПАО "Рыбачок"</option><option>ИП Мартынович И.К.</option>
                        <option>ПАО "Окна"</option><option>ООО "Двери"</option><option>НПАО "СтройМатериалы"</option>
                        <option>ИП Петров А.В.</option><option>ПАО "Л-Видео"</option><option>ООО "Фронт"</option>
                        <option>НПАО "Граница"</option><option>ИП Драгуновский В.И.</option>
                    </select>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Create document</button>
            </form>
        </div>
    </div>

    <#--  Список всех документов  -->
    <div class="my-3"><h5>List of documents:</h5></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
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
                    <th scope="col">Adding for signature</th>
                </tr>
            </thead>
            <tbody>
                <#list documents as document>
                    <tr>
                        <th scope="row">${document.id}</th>
                        <td>${document.title}</td>
                        <td>${document.about}</td>
                        <td>${document.authorName}</td>
                        <td>${document.otherName}</td>
                        <td><#if document.isSign()>Подписан<#else>Не подписан</#if></td>
                        <td><#if document.isSignOtherSide()>Подписан<#else>Не подписан</#if></td>
                        <td>
                            <#if document.author.id == currentUser>
                            <a id="<#if (document.isSign() && document.isSignOtherSide()) || document.isSign()>disabledAdd<#else>add</#if>"
                               class="btn btn-info" href="/documentFlow/${document.id}">Add</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
</@c.page>