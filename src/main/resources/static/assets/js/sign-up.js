var isEmailVerified = false;
var isPasswordChecked = false;
$(document).ready(function () {
    var confirmationCode = ""; // 전역 변수로 인증번호를 저장합니다.

    // 인증번호 발송 버튼 클릭 시
    $("#sendConfirmationCode").click(function () {
        var email = $("#id").val(); // 이메일 입력란의 값 가져오기

        // 서버에 이메일 주소를 전송하여 인증번호를 요청
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/v1/sign-up/mailConfirm",
            data: {
                "email": email
            },
            success: function (data) {
                // 성공적으로 인증번호를 받으면 전역 변수에 저장
                confirmationCode = data;
                console.log("인증코드 : " + confirmationCode);
                alert("해당 이메일로 인증번호가 발송되었습니다.");
            },
            error: function (xhr, textStatus, errorThrown) {
                alert("인증번호 발송에 실패했습니다.");
            }
        });
    });

    // 인증하기 버튼 클릭 시
    $("#verifyEmail").click(function () {
        var enteredCode = $("#emailConfirmationCode").val(); // 입력된 인증번호 가져오기

        // 입력된 인증번호가 전역 변수에 저장된 인증번호와 일치하는지 확인
        if (enteredCode === confirmationCode) {
            alert("인증번호가 일치합니다. 회원가입을 진행하세요.");
            isEmailVerified = true;
            enableSignupButton();
            // 유효성 검사 통과 시 추가 동작 수행 가능
        } else {
            alert("인증번호가 일치하지 않습니다. 다시 확인해주세요.");
        }
    });

    // 비밀번호 중복 확인 버튼 클릭 시
    $("#duplicateCheck").click(function () {
        // 비밀번호 중복 검사 로직
        // 서버에 비밀번호 중복 검사 요청을 보냅니다.
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/v1/sign-up/duplicateCheck",
            success: function (data) {
                // 비밀번호 중복 확인 성공 시
                // isPasswordChecked 값을 true로 설정합니다.
                isPasswordChecked = true;
                // 회원가입 버튼을 활성화하는 함수 호출
                enableSignupButton();
            },
            error: function (xhr, textStatus, errorThrown) {
                // 비밀번호 중복 확인 실패 시
                // 오류 메시지를 출력합니다.
                alert("비밀번호 중복 확인에 실패했습니다.");
            }
        });
    });

    document.getElementById("signup-btn").addEventListener("click", function () {
        if (isEmailVerified == true && isPasswordChecked == true) {
            alert("회원가입이 성공적으로 완료되었습니다.\n로그인 페이지로 돌아갑니다.");
            window.location.href = "/login_form";
        } else {
            alert("이메일 인증과 비밀번호 중복 확인을 먼저 진행해주세요");
        }


    });
});
// 회원가입 버튼 활성화 함수
// function enableSignupButton() {
//     // 이메일 인증 및 비밀번호 중복 확인 모두 성공했을 때 버튼을 활성화합니다.
//     if (isEmailVerified && isPasswordChecked) {
//         $("#signup-btn").prop("disabled", false);
//     } else {
//         $("#signup-btn").prop("disabled", true);
//     }
// }
// var access = false;
//     // 이메일 인증번호
//     $checkEmail.click(function() {
//     $.ajax({
//         type : "POST",
//         url : "sign-up/mailConfirm",
//         data : {
//             "email" : $memail.val()
//         },
//         success : function(data){
//             alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
//             console.log("data : "+data);
//             chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt);
//         }
//     })
// })
//
//     // 이메일 인증번호 체크 함수
//     function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
//     $memailconfirm.on("keyup", function(){
//         if (data != $memailconfirm.val()) { //
//             emconfirmchk = false;
//             $memailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
//             $("#emconfirmchk").css({
//                 "color" : "#FA3E3E",
//                 "font-weight" : "bold",
//                 "font-size" : "10px"
//
//             })
//             //console.log("중복아이디");
//         } else { // 아니면 중복아님
//             emconfirmchk = true;
//             $memailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>")
//
//             $("#emconfirmchk").css({
//                 "color" : "#0D6EFD",
//                 "font-weight" : "bold",
//                 "font-size" : "10px"
//
//             })
//         }
//     })
// }


