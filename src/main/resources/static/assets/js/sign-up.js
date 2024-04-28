var isEmailVerified = false;
var isPasswordChecked = false;
$(document).ready(function () {
    var confirmationCode = "";
    $("#sendConfirmationCode").click(function () {
        var email = $("#id").val();

        $.ajax({
            type: "POST",
            url: "/mailConfirm",
            contentType: "application/json",
            data: JSON.stringify({
                "email": email
            }),
            success: function (response) {
                confirmationCode = response;
                alert("인증번호가 발송되었습니다. 이메일을 확인해주세요.");
                console.log("인증번호: " + confirmationCode);
            },
            error: function (xhr, textStatus, errorThrown) {
                console.error("오류: " + xhr.responseText);
                alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
            }
        });
    });

    $("#checkEmail").click(function () {
        var enteredCode = $("#emailConfirmationCode").val();
        if (enteredCode === confirmationCode) {
            alert("인증번호가 일치합니다. 회원가입을 진행하세요.");
            isEmailVerified = true;
        } else {
            alert("인증번호가 일치하지 않습니다. 다시 확인해주세요.");
        }
    });

    $("#duplicateCheck").click(function () {
        $.ajax({
            type: "POST",
            url: "/duplicateCheck",
            data: JSON.stringify({
                "email": email
            }),
            success: function (response) {
                // 비밀번호 중복 확인 성공 시
                // isPasswordChecked 값을 true로 설정합니다.
                isPasswordChecked = true;
                // 회원가입 버튼을 활성화하는 함수 호출
                // enableSignupButton();
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


