function deleteUser(userId, userName) {
    console.log("Attempting to delete user with ID:", userId);
    if (confirm('Are you sure you want to delete ' + userName + '?')) {
        fetch('/admin/users/delete/' + userId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    showModal(userName + ' has been deleted successfully.');
                    document.querySelector(`button[onclick="deleteUser(${userId}, '${userName}')"]`).closest('tr').remove();
                } else {
                    response.json().then(data => {
                        showModal('Failed to delete ' + userName + ': ' + data.message);
                    }).catch(() => {
                        showModal('Failed to delete ' + userName + '.');
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                showModal('An error occurred while trying to delete ' + userName + '.');
            });
    }
}

function showModal(message) {
    const modal = document.getElementById("myModal");
    const modalMessage = document.getElementById("modal-message");
    const span = document.getElementsByClassName("close")[0];

    modalMessage.textContent = message;
    modal.style.display = "block";

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}
