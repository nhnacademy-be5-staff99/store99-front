document.querySelectorAll('.delete-button').forEach(button => {
    button.addEventListener('click', function (event) {
        event.preventDefault();
        fetch(button.dataset.url, {method: 'DELETE'})
            .then(response => location.reload())
            .catch(error => {
                console.error(error);
                location.reload();
            });
    });
});

document.querySelectorAll('.restore-button').forEach(button => {
    button.addEventListener('click', function (event) {
        event.preventDefault();
        fetch(button.dataset.url, {method: 'PUT'})
            .then(response => location.reload())
            .catch(error => {
                console.error(error);
                location.reload();
            })
    });
});