document.getElementById('registrationForm').addEventListener('submit', function (e) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('re_password').value;

    if (password !== confirmPassword) {
        e.preventDefault();  // Prevent form submission
        const errorSpan = document.getElementById('re-password-error');
        errorSpan.style.display = 'inline';  // Show password mismatch error
    }
});
