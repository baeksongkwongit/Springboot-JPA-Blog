<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form action="/board/saveProc" method="POST">

        <div class="form-group">
            <label for="title">title:</label>
            <input type="text"  name="title" class="form-control " placeholder="Enter title" id="title">
        </div>
        <div class="form-group">
            <label for="content">content:</label>
            <textarea name="content" rows="5" class="form-control summernote" id="content" ></textarea>
        </div>

    </form>
        <button id="btn-save" class="btn btn-primary">글쓰기</button>
</div>
<script>
    $('.summernote').summernote({
        //placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>