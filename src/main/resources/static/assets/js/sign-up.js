var isEmailVerified = false;
var isPasswordChecked = false;
var isPhoneNumberValid = false;
$(document).ready(function () {
    var confirmationCode;
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
                if(response === "duplicateEmail"){
                    alert("이미 가입된 이메일입니다. 다른 이메일로 시도해주세요")
                }
                else{
                confirmationCode = response;
                alert("인증번호가 발송되었습니다. 이메일을 확인해주세요.");}
            },
            error: function (xhr, textStatus, errorThrown) {
                console.error("오류: " + xhr.responseText);
                alert("존재하지 않는 이메일입니다. 다시 시도해주세요.");
            }
        });
    });

    $("#checkEmail").click(function () {
        var enteredCode = $("#emailConfirmationCode").val();
        if (enteredCode === confirmationCode) {
            alert("인증번호가 일치합니다. 회원가입을 진행하세요.");
            isEmailVerified = true;
            document.getElementById('id').disabled = true;
            document.getElementById('sendConfirmationCode').disabled = true;
            document.getElementById('emailConfirmationCode').disabled = true;
            document.getElementById('password').disabled = false;
            document.getElementById('validateCheck').disabled = false;
        } else {
            alert("인증번호가 일치하지 않습니다. 다시 확인해주세요.");
            isEmailVerified = false;
        }
    });

    $("#validateCheck").click(function () {
        var password = $("#password").val();
        if(isEmailVerified == true){
            var passwordReg = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$/;
            if (!passwordReg.test(password)) {
                alert("비밀번호는 8~16자리의 영문과 숫자를 포함해야합니다.");
                isPasswordChecked = false;
            } else {
                alert("사용 가능한 비밀번호입니다.");
                isPasswordChecked = true;
                document.getElementById('password').disabled = true;
                document.getElementById('validateCheck').disabled = true;
                document.getElementById('checkEmail').disabled = true;
                document.getElementById('username').disabled = false;
                document.getElementById('address_detail').disabled = false;
                document.getElementById('address_alias').disabled = false;
                document.getElementById('addressCodeSearch').disabled = false;
                document.getElementById('phoneNumber').disabled = false;
                document.getElementById('birthday').disabled = false;
            }
        }
        else {
            alert("이메일 인증을 먼저 진행해주세요.");
        }
    });

    $("#addressCodeSearch").click(function() {
        new daum.Postcode({
            oncomplete: function(data) {

                var fullRoadAddr = data.roadAddress;
                var extraRoadAddr = '';

                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }

                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }

                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                $("#address_code").val(data.zonecode);
                $("#address_general").val(fullRoadAddr);
            }
        }).open();
    });

    function validateInput(input) {
        if (input.length !== 11) {
            return false;
        }
        for (let i = 0; i < input.length; i++) {
            if (isNaN(parseInt(input[i])) || parseInt(input[i]) < 0 || parseInt(input[i]) > 9) {
                return false;
            }
        }
        return true;
    }

    document.getElementById('signUp').addEventListener('submit', function(event) {
        isPhoneNumberValid = validateInput(document.getElementById('phoneNumber').value);
        event.preventDefault();
        const formData = {
            email: document.getElementById('id').value,
            password: document.getElementById('password').value,
            name: document.getElementById('username').value,
            phoneNumber: document.getElementById('phoneNumber').value,
            userBirthDate: document.getElementById('birthday').value,
            address: {
                addressGeneral: document.getElementById('address_general').value,
                addressDetail: document.getElementById('address_detail').value,
                addressAlias: document.getElementById('address_alias').value,
                addressCode: parseInt(document.getElementById('address_code').value),
                isDefaultAddress: true
            }
        };
        if (isEmailVerified == true && isPasswordChecked == true) {
            if(isPhoneNumberValid == false){
                alert("휴대폰 번호 형식이 올바르지 않습니다.");
            }
            else{
            fetch('/sign-up', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            })
                .then(response => {
                    if (!response.ok) {
                        return response.json().then(data => {
                            alert(`회원가입 실패: ${data.header.resultMessage}`);
                            throw new Error(data.header.resultMessage);
                        });
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    alert("회원가입이 성공적으로 완료되었습니다.\n로그인 페이지로 돌아갑니다.");
                    window.location = "login_form"
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        }} else {
            alert("이메일 인증과 비밀번호 중복 확인을 먼저 진행해주세요");
        }
    });
});


