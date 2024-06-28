document.getElementById('userForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const form = event.target;
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    data.employee = {
        firstname: formData.get('employee.firstname'),
        lastname: formData.get('employee.lastname'),
        email: formData.get('employee.email'),
        phoneNumber: formData.get('employee.phoneNumber'),
        salary: parseFloat(formData.get('employee.salary')),
        departmentId: parseInt(formData.get('employee.departmentId')),
        positionId: parseInt(formData.get('employee.positionId'))
    };

    try {
        const response = await fetch(form.action, {
            method: form.method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('User created successfully!');
        } else {
            const errorData = await response.json();
            alert('Error: ' + (errorData.message || response.statusText));
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred. Please try again.');
    }
});
