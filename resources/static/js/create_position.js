document.getElementById('positionForm').addEventListener('submit', function(event) {
    event.preventDefault();

    let form = event.target;
    let formData = new FormData(form);
    let positionName = formData.get('positionName');

    if (!positionName) {
        alert('Position name cannot be empty.');
        return;
    }

    fetch(form.action, {
        method: form.method,
        body: new URLSearchParams(formData),
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
        .then(response => {
            if (response.ok) {
                alert('Position created successfully.');
                window.location.href = '/admin/home';
            } else if (response.status === 409) {
                alert('Position already exists.');
            } else if (response.status === 400) {
                alert('Validation error: Position name cannot be empty.');
            } else {
                alert('An error occurred during position registration.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred during position registration.');
        });
});