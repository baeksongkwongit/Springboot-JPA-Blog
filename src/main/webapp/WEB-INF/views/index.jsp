<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="layout/header.jsp"%>
    <div class="container">
    <c:forEach var="board" items="${boards.content}">
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                    <%--        <p class="card-text">내용 적는 부분</p>--%>
                <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>
    <ul class="pagination justify-content-center"> <!--시작 justify-content-start 끝 justify-content-end-->
        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number -1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number -1}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number +1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number +1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    </div>
<br/>
<%@ include file="layout/footer.jsp"%>