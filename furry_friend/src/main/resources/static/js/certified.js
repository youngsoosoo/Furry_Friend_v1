var code2 = "";
$("#phoneChk").click(function(){
    alert("인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인을 해주십시오.");
    var phone = $("#phone").val();
    $.ajax({
        type:"GET",
        url:"/phoneCheck?phone=" + phone,
        cache : false,
        success:function(data){
            if(data == "error"){
                alert("휴대폰 번호가 올바르지 않습니다.")
                $(".successPhoneChk").text("유효한 번호를 입력해주세요.");
                $(".successPhoneChk").css("color","red");
                $("#phone").attr("autofocus",true);
            }else{
                $("#phone2").attr("disabled",false);
                $("#phoneChk2").css("display","inline-block");
                $(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
                $(".successPhoneChk").css("color","green");
                $("#phone").attr("readonly",true);
                code2 = data;
            }
        }
    });
});

$("#phoneChk2").click(function(){
    if($("#phone2").val() == code2){
        $(".successPhoneChk").text("인증번호가 일치합니다.");
        $(".successPhoneChk").css("color","green");
        $("#phoneDoubleChk").val("true");
        $("#phone2").attr("disabled",true);
    }else{
        $(".successPhoneChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
        $(".successPhoneChk").css("color","red");
        $("#phoneDoubleChk").val("false");
        $(this).attr("autofocus",true);
    }
});


//실험
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
