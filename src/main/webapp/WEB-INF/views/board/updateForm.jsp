<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form>
        <input type="hidden" id="id" value="${board.id}" />
        <div class="form-group">
            <input type="text"  name="title" class="form-control " placeholder="Enter title" id="title" value="${board.title}">
        </div>
        <div class="form-group">
            <textarea name="content" rows="5" class="form-control summernote" id="content" >${board.content}</textarea>
        </div>

    </form>
        <button id="btn-update" class="btn btn-primary">글수정완료</button>
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