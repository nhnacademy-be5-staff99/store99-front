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