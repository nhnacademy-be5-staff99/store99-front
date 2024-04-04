console.log("js start.");

function submitBookClick() {
    const url = "/books/admin";
    const data = {
        bookIsbn13: document.getElementById("bookIsbn13").value,
        bookIsbn10: document.getElementById("bookIsbn10").value,
        bookTitle: document.getElementById("bookTitle").value,
        bookContents: document.getElementById("bookContents").value,
        bookDescription: document.getElementById("bookDescription").value,
        bookPublisher: document.getElementById("bookPublisher").value,
        bookDateTime: document.getElementById("bookDateTime").value,
        bookPrice: document.getElementById("bookPrice").value,
        bookSalePrice: document.getElementById("bookSalePrice").value,
        bookIsPacked: document.getElementById("bookIsPacked").checked,
        bookThumbnailUrl: document.getElementById("bookThumbnailUrl").value,
        bookStock: document.getElementById("bookStock").value
    };
    console.log(data)
    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Response:', data);
        })
        .catch(error => {
            console.error('There was an error!', error);
        });
}

const submitButton = document.getElementById("submitButton");

submitButton.addEventListener("click", submitBookClick)
