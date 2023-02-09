let index={
    init:function(){
        $("#btn-save").on("click", ()=>{ //function (){} , ()=> {} this를 바인딩 하기 위해서!!
            this.save();
        });
        // $("#btn-login").on("click", ()=>{ //function (){} , ()=> {} this를 바인딩 하기 위해서!!
        //     this.login();
        // });
    },
    save:function (){
        let data ={
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };
        // console.log(data);
        // alert("user의 save함수 호출됨");

        //ajax호출시 default가 비동기 호출
        $.ajax({
            //회원가입 수행 요청
            type:"POST",
            url:"/auth/joinProc",
            data:JSON.stringify(data), // http body 데이터
            contentType:"application/json;charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            dataType:"json" //요청을 서버로 해서 응답이 왔을때 기본적으로 모든 것이 문자열(생긴게 json이라면 )=> javascript object 로 반환 한다.
        }).done(function(resp) {
            alert("회원가입이 완료되었습니다.");
            console.log(resp);
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error))
        }); //통신을 이용해서 3개의 paramete데이터를 Json으로 변경하여 insert요청!!

    },
    // login:function (){
    //     let data ={
    //         username:$("#username").val(),
    //         password:$("#password").val(),
    //     };
    //     // console.log(data);
    //     // alert("user의 save함수 호출됨");
    //
    //     //ajax호출시 default가 비동기 호출
    //     $.ajax({
    //         //회원가입 수행 요청
    //         type:"POST",
    //         url:"/api/user/login",
    //         data:JSON.stringify(data), // http body 데이터
    //         contentType:"application/json;charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
    //         dataType:"json" //요청을 서버로 해서 응답이 왔을때 기본적으로 모든 것이 문자열(생긴게 json이라면 )=> javascript object 로 반환 한다.
    //     }).done(function(resp) {
    //         alert("로그인이 완료되었습니다.");
    //         console.log(resp);
    //         location.href="/";
    //     }).fail(function(error){
    //         alert(JSON.stringify(error))
    //     }); //통신을 이용해서 3개의 paramete데이터를 Json으로 변경하여 insert요청!!
    //
    // }
}
index.init();