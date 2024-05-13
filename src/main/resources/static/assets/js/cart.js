// 각 도서의 가격과 수량을 가져와서 총 금액을 계산
function calculateTotal() {
    let total = 0;
    document.querySelectorAll('input[name="select"]:checked').forEach(function (checkbox) {
        let row = checkbox.closest('tr');
        let price = parseInt(row.querySelector('.order-per-price').innerText);
        total += price;
    });
    document.getElementById('order-total-price').innerText = total + '원';
}

// 체크박스가 변경될 때마다 총 금액을 다시 계산
document.querySelectorAll('input[name="select"]').forEach(function (checkbox) {
    checkbox.addEventListener('change', calculateTotal);
});

// 페이지 로드 시 총 금액 계산
calculateTotal();

// 주문하기 버튼을 클릭했을 때
document.getElementById('order-button').addEventListener('click', function (event) {
    // 체크된 모든 도서의 아이디와 수량을 가져옵니다.
    let orderBookRequests = Array.from(document.querySelectorAll('input.order-check:checked')).map(function (checkbox) {
        let row = checkbox.closest('tr');
        let bookId = row.querySelector('input[name="bookId"]').value;
        let quantity = row.querySelector('input[name="quantity"]').value;
        return {bookId: bookId, quantity: quantity};
    });

    // 주문할 도서가 없으면 경고창을 띄웁니다.
    if (orderBookRequests.length === 0) {
        alert('주문할 도서를 선택하세요.');
        event.preventDefault();
        return;
    }

    // 주문 도서 정보를 form에 추가합니다.
    let form = document.getElementById('order-form');
    orderBookRequests.forEach(function (orderBookRequest, index) {
        let bookIdInput = document.createElement('input');
        bookIdInput.type = 'hidden';
        bookIdInput.name = 'orderBookRequestList[' + index + '].bookId';
        bookIdInput.value = orderBookRequest.bookId;
        form.appendChild(bookIdInput);

        let quantityInput = document.createElement('input');
        quantityInput.type = 'hidden';
        quantityInput.name = 'orderBookRequestList[' + index + '].quantity';
        quantityInput.value = orderBookRequest.quantity;
        form.appendChild(quantityInput);
    });

    // form을 제출합니다.
    form.submit();
});