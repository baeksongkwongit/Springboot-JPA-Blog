let index={
    init:function(){
        $("#btn-save").on("click", ()=>{ //function (){} , ()=> {} this를 바인딩 하기 위해서!!
            this.save();
        });
        $("#btn-delete").on("click", ()=>{ //function (){} , ()=> {} this를 바인딩 하기 위해서!!
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{ //function (){} , ()=> {} this를 바인딩 하기 위해서!!
            this.update();
        });
    },
    save:function (){
        let data ={
            title:$("#title").val(),
            content:$("#content").val(),
        };
        $.ajax({
            //회원가입 수행 요청
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data), // http body 데이터
            contentType:"application/json;charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            dataType:"json"
        }).done(function(resp) {
            alert("글쓰기가 완료되었습니다.");
            console.log(resp);
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error))
        }); //통신을 이용해서 3개의 paramete데이터를 Json으로 변경하여 insert요청!!

    },
    deleteById:function (){
        let id =$("#id").text();
        $.ajax({
            //회원가입 수행 요청
            type:"DELETE",
            url:"/api/board/" + id,
            dataType:"json"
        }).done(function(resp) {
            alert("삭제가 완료되었습니다.");
           //console.log(resp);
           location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error))
        }); //통신을 이용해서 3개의 paramete데이터를 Json으로 변경하여 insert요청!!

    },
    update:function (){
        let id = $("#id").val();
        let data ={
            title:$("#title").val(),
            content:$("#content").val(),

        };
        $.ajax({
            //회원가입 수행 요청
            type:"PUT",
            url:"/api/board/"+id,
            data:JSON.stringify(data), // http body 데이터
            contentType:"application/json;charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            dataType:"json"
        }).done(function(resp) {
            alert("글수정이 완료되었습니다.");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error))
        }); //통신을 이용해서 3개의 paramete데이터를 Json으로 변경하여 insert요청!!

    }
}
index.init();