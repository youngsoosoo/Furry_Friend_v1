//휴대폰번호 인증번호 보내기 버튼 클릭 이벤트
$('#phoneCheck').click(function(){

    var phone = $('input[name="phone"]').val();
    $.ajax({
        url : "/phoneCheck",
        type : "POST",
        data : "phone=" + phone,
        dataType : "json",
        success : function(data) {
            const checkNum = data;
            alert('checkNum:'+ checkNum);

            //인증하기 버튼 클릭 이벤트
            $('#certifyCheck').click(function(){
                const userNum = $('input[name="phoneCertify"]').val();
                if(checkNum == userNum){
                    alert('인증 성공하였습니다.');
                }else {
                    alert('인증 실패하였습니다. 다시 입력해주세요.');
                }
            });

        },
        error : function() {
            alert("에러")
        }
    });
});


//아이디 중복 확인
$("#id").blur(function(){
    var id = $("#id").val();
    if(id == "" || id.length < 2){
        $(".successidChk").text("※ 영문자, 소문자 입력가능, 최소 2자이상 최대 16자 까지 입력");
        $(".successidChk").css("color", "red");
        $("#idDoubleChk").val("false");
    }else{
        $.ajax({
            url : '/idCheck?id='+ id,
            type : 'post',
            cache : false,
            success : function(data) {
                if (data == 0) {
                    $(".successidChk").text("사용가능한 아이디입니다.");
                    $(".successidChk").css("color", "green");
                    $("#idDoubleChk").val("true");
                } else {
                    $(".successidChk").text("사용중인 아이디입니다");
                    $(".successidChk").css("color", "red");
                    $("#idDoubleChk").val("false");
                }
            }, error: function (request, status, error) {
                console.log("code: " + request.status)
                console.log("message: " + request.responseText)
                console.log("error: " + error);
            }
        });
    }
});

//비밀번호 확인
$("#pwchk").blur(function(){
    if($("#pwchk").val() == $("#pw").val()){
        $(".successPwChk").text("비밀번호가 일치합니다.");
        $(".successPwChk").css("color", "green");
        $("#pwDoubleChk").val("true");
    }else{
        $(".successPwChk").text("비밀번호가 일치하지 않습니다.");
        $(".successPwChk").css("color", "red");
        $("#pwDoubleChk").val("false");
    }
});
