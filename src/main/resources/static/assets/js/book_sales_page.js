/**
 * 카트에 넣기
 * @author seunggyu-kim
 */
document.querySelectorAll('.add-to-cart-button').forEach(function (button) {
    button.addEventListener('click', function () {
        var bookId = this.dataset.bookId;

        fetch('/cart/books', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "bookId": bookId,
                "quantity": 1
            }),
        }).catch((error) => {
            console.error('Error:', error);
        });


    });
});