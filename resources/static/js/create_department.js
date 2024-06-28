document.getElementById('departmentForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var form = event.target;
    var formData = new FormData(form);
    var departmentName = formData.get('departmentName');

    if (!departmentName) {
        alert('Department name cannot be empty.');
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
                alert('Department created successfully.');
                window.location.href = '/admin/home';
            } else if (response.status === 409) {
                alert('Department already exists.');
            } else if (response.status === 400) {
                alert('Validation error: Department name cannot be empty.');
            } else {
                alert('An error occurred during department registration.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred during department registration.');
        });
});