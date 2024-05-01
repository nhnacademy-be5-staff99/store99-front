// 주소 삭제
$(document).ready(function () {
    $('.address-delete-btn').click(function () {
        var addressId = $(this).attr('id');
        $.ajax({
            url: '/address/' + addressId,
            type: 'DELETE',
            success: function (result) {
                // 페이지를 새로 고침하여 변경 사항을 반영
                location.reload();
            }
        });
    });
});

// 기본 주소로 변경
$(document).ready(function () {
    $('.address-change-default-btn').click(function () {
        console.log($(this).attr('id'));
        var addressId = $(this).attr('id');
        $.ajax({
            url: '/address/change_default',
            type: 'PATCH',
            data: JSON.stringify({addressId: addressId}),
            contentType: 'application/json',
            success: function (result) {
                // 페이지를 새로 고침하여 변경 사항을 반영
                location.reload();
            }
        });
    });
});

// 주소 추가
$(document).ready(function () {
    $('#addAddress').click(function () {
        var addressGeneral = $('#generalAddressInput').val();
        var addressDetail = $('#detailAddressInput').val();
        var addressAlias = $('#aliasInput').val();
        var addressCode = $('#postCodeInput').val();

        $.ajax({
            url: '/address/add',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                addressGeneral: addressGeneral,
                addressDetail: addressDetail,
                addressAlias: addressAlias,
                addressCode: addressCode
            }),
            success: function () {
                // 성공적으로 요청이 완료되면 모달을 닫고 페이지를 새로 고침합니다.
                $('#addressModal').modal('hide');
                location.reload();
            },
            error: function () {
                // 요청이 실패하면 오류 메시지를 표시합니다.
                window.alert('주소 추가에 실패했습니다.');
            }
        });
    });
});

// 주소 수정 모달 열기
$(document).ready(function () {
    // 수정 버튼에 클릭 이벤트 리스너 추가
    $('.address-update-btn').click(function () {
        var addressId = $(this).attr('id');

        // 주소 정보 가져오기
        $.ajax({
            url: '/address?addressId=' + addressId,
            type: 'GET',
            success: function (addressInfo) {
                console.log(addressInfo);
                // 모달의 입력 필드에 주소 정보 채우기
                $('#aliasInput').val(addressInfo.addressAlias);
                $('#postCodeInput').val(addressInfo.addressCode);
                $('#generalAddressInput').val(addressInfo.addressGeneral);
                $('#detailAddressInput').val(addressInfo.addressDetail);

                // 모달 header 의 제목 변경
                $('#addressModalLabel').text('주소 수정');

                // 모달의 footer 에 '추가하기' 버튼 삭제
                $('#addAddress').remove();

                // 모달의 footer에 '수정하기' 버튼 추가
                $('.modal-footer').append('<button type="button" class="btn btn-primary" id="updateAddress">수정하기</button>');

                // '수정하기' 버튼에 클릭 이벤트 리스너 추가
                $('#updateAddress').click(function () {
                    // 수정된 주소 정보 가져오기
                    var updatedAddressInfo = {
                        addressId: addressId,
                        addressGeneral: $('#generalAddressInput').val(),
                        addressDetail: $('#detailAddressInput').val(),
                        addressAlias: $('#aliasInput').val(),
                        addressCode: $('#postCodeInput').val()
                    };

                    // AJAX 요청을 통해 서버에 수정된 주소 정보 전송
                    $.ajax({
                        url: '/address/update',
                        type: 'PATCH',
                        data: JSON.stringify(updatedAddressInfo),
                        contentType: 'application/json; charset=utf-8',
                        success: function () {
                            // 요청이 성공적으로 완료되면 모달을 닫고 페이지를 새로 고침합니다.
                            $('#addressModal').modal('hide');
                            location.reload();
                        },
                        error: function () {
                            // 요청이 실패하면 오류 메시지를 표시합니다.
                            window.alert('주소 수정에 실패했습니다.');
                        }
                    });
                });

                // 모달 창 열기
                $('#addressModal').modal('show');
            },
            error: function () {
                // 요청이 실패하면 오류 메시지를 표시합니다.
                window.alert('주소 정보를 가져오는데 실패했습니다.');
            }
        });
    });
});