$("button[name='idcheck']").click(function (){
    var phone = $('#phone').val();
    var name = $('#name').val();

    var allData = { "phone": phone, "name": name };

   $.ajax({
       url : "/user/findid",
       type : "POST",
       data : allData,
       dataType : "json",
       success : function(data) {
           alert("아이디 : " + data);
           window.location.href='/user/login';
           self.close();
       }
   })
});

$("button[name='pwcheck']").click(function (){
    var id = $('#id').val();

    $.ajax({
        url : "/user/findpw",
        type : "POST",
        data : "id=" + id,
        dataType : "json",
        success : function(data) {
            alert("비밀번호 : " + data);
            window.location.href='/user/login';
            self.close();
        }
    })
});