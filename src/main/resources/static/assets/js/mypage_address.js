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
$(document).ready(function() {
    $('#addAddress').click(function() {
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
            success: function() {
                // 성공적으로 요청이 완료되면 모달을 닫고 페이지를 새로 고침합니다.
                $('#addressModal').modal('hide');
                location.reload();
            },
            error: function() {
                // 요청이 실패하면 오류 메시지를 표시합니다.
                window.alert('주소 추가에 실패했습니다.');
            }
        });
    });
});