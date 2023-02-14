<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <button id="btn-update" class="btn btn-primary">회원정보수정</button>
    <form>
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="form-group">
            <label for="username">UserName:</label>
            <input type="text" class="form-control" readonly placeholder="Enter username" id="username" value="${principal.user.username}">
        </div>
        <c:if test="${empty principal.user.oauth}">
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        </c:if>
        <div class="form-group">
            <label for="email">Email :</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}" readonly>
        </div>
    </form>
</div>
<br />
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>