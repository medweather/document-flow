<#import "parts/common.ftl" as c>

<@c.page>
    <#--  Страница авторизации  -->
    <h4>Login page</h4>
    <form action="/login" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Login: </label>
            <div class="col-sm-3">
                <input class="form-control"
                       type="text" name="username" placeholder="Enter login"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-3">
                <input class="form-control"
                       type="password" name="password" placeholder="Enter password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Sign in</button>
    </form><br><br>
    <div class="container">
        <div class="row">
            <div class="col">
                <p class="text-justify"><strong>ПАО "Орешник":</strong> <br>login: oreshnik <br>password: 12345678
                    <br><br><strong>ООО "Скворечник":</strong> <br>login: skvorechnik <br>password: 87654321
                    <br><br><strong>НПАО "Рыбачок":</strong> <br>login: ribachok <br>password: 10293847
                    <br><br><strong>ИП Мартынович И.К.:</strong> <br>login: martinovich <br>password: 56473829</p>
            </div>
            <div class="col">
                <p class="text-justify"><strong>ПАО "Окна":</strong> <br>login: okna <br>password: qwerty
                    <br><br><strong>ООО "Двери":</strong> <br>login: dveri <br>password: ytrewq
                    <br><br><strong>НПАО "СтройМатериалы":</strong> <br>login: stroy <br>password: asdfgh
                    <br><br><strong>ИП Петров А.В.:</strong> <br>login: petrov <br>password: hgfdsa</p>
            </div>
            <div class="col">
                <p class="text-justify"><strong>ПАО "Л-Видео":</strong> <br>login: lvideo <br>password: qwer1234
                    <br><br><strong>ООО "Фронт":</strong> <br>login: front <br>password: 4321rewq
                    <br><br><strong>НПАО "Граница":</strong> <br>login: granica <br>password: asdf5678
                    <br><br><strong>ИП Драгуновский В.И.:</strong> <br>login: dragunovski <br>password: 8765fdsa</p>
            </div>
        </div>
    </div>
</@c.page>