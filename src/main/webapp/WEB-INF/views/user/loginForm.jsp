<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form action="/auth/loginProc" method="POST">

        <div class="form-group">
            <label for="username">UserName:</label>
            <input type="text"  name="username" class="form-control" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" name="remember" type="checkbox"> Remember me
            </label>
        </div>
        <button id="btn-login" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=8b05792a24c287d91fb63a6ead4b48a3&redirect_uri=http://localhost:8081/auth/kakao/callback&response_type=code
"><img height="38" src="/image/kakao_login.png"/></a>
    </form>
</div>
<br />
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>