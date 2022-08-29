//휴대폰번호 인증번호 보내기 버튼 클릭 이벤트
var code2 = "";
$('#phoneChk').click(function(){
    alert("인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인을 해주십시오.");
    var phone = $("#phone").val();
    $.ajax({
        url : "/phoneCheck",
        type : "POST",
        data : "phone=" + phone,
        dataType : "json",
        success : function(data) {
            const checkNum = data;
            alert('checkNum:' + checkNum);

            //인증하기 버튼 클릭 이벤트
            if (data == "error") {
                alert("휴대폰 번호가 올바르지 않습니다.")
                $(".successPhoneChk").text("유효한 번호를 입력해주세요.");
                $(".successPhoneChk").css("color", "red");
                $("#phone").attr("autofocus", true);
            } else {
                $("#phone2").attr("disabled", false);
                $("#phoneChk2").css("display", "inline-block");
                $(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
                $(".successPhoneChk").css("color", "green");
                $("#phone").attr("readonly", true);
                code2 = data;
            }
        }
    });

});

//휴대폰 인증번호 대조
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

//체크박스 체크여부
$("input:checkbox").click(checkedChange);
function checkedChange() {
    if($(this).prop("checked")){
        $("label[for="+this.id+"]").text("동의되었습니다.");
        $("label[for="+this.id+"]").css("color","blue");
    }else{
        $("label[for="+this.id+"]").text("동의 해주시기 바랍니다.");
        $("label[for="+this.id+"]").css("color","red");
    }
}

//최종 틍록하기 버튼 클릭시 각 부분별 입력값 맞는지 확인
$("#button1").click(function(){
    if($("#agreement1").prop("checked") && $("#agreement2").prop("checked")  && $("#idDoubleChk").val() == "true" && $("#pwDoubleChk").val() == "true"  && $("#phoneDoubleChk").val() == "true"){
        alert($('#id').val()+"님 환영합니다. 선택해주셔서 감사합니다 :)");
    }else{
        alert("회원가입을 완료할 수 없습니다. 다시한번 확인해주십시오.");
        if(!$("#agreement1").prop("checked")){
            $("label[for=agreement1]").text("동의 해주시기 바랍니다.");
            $("label[for=agreement1]").css("color","red");
        }
        if(!$("#agreement2").prop("checked")) {
            $("label[for=agreement2]").text("동의 해주시기 바랍니다.");
            $("label[for=agreement2]").css("color","red");
        }
        if($("#idDoubleChk").val() != "true"){
            $(".successidChk").text("아이디를 입력해주세요 :)");
            $(".successidChk").css("color", "red");
        }
        if($("#pwDoubleChk").val() != "true"){
            $(".successPwChk").text("비밀번호가 일치하지 않습니다 :)");
            $(".successPwChk").css("color", "red");
        }
        if($("#phoneDoubleChk").val() != "true"){
            $(".successPhoneChk").text("휴대폰 인증을 완료해주세요 :)");
            $(".successPhoneChk").css("color", "red");
        }
        return false
    }
});

$("#button2").click(function(){
    $("#idDoubleChk").val("false");
    $("#pwDoubleChk").val("false");
    $("#phoneDoubleChk").val("false");
    $("#phone").attr("readonly",false);
});