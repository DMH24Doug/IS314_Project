document.getElementById('registrationForm').addEventListener('submit', function (e) {
    if (!validateForm()) {
        e.preventDefault(); // Stop submission if validation fails
    }
});

function validateForm() {
    let isValid = true;

    // Username Validation
    const username = document.getElementById('userName').value;
    const usernameError = document.getElementById('userName-error');
    const usernamePattern = /^[a-zA-Z0-9]{3,15}$/;

    if (!usernamePattern.test(username)) {
        usernameError.textContent = 'Username must be 3-15 characters and alphanumeric.';
        usernameError.style.display = 'block';
        isValid = false;
    } else {
        usernameError.style.display = 'none';
    }

    // Password Validation
    const password = document.getElementById('password').value;
    const passwordError = document.getElementById('password-error');
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    if (!passwordPattern.test(password)) {
        passwordError.textContent = 'Password must contain at least 8 characters, with uppercase, lowercase, number, and special character.';
        passwordError.style.display = 'block';
        isValid = false;
    } else {
        passwordError.style.display = 'none';
    }

    // Confirm Password Validation
    const rePassword = document.getElementById('re_password').value;
    const rePasswordError = document.getElementById('re-password-error');

    if (password !== rePassword) {
        rePasswordError.textContent = 'Passwords do not match.';
        rePasswordError.style.display = 'block';
        isValid = false;
    } else {
        rePasswordError.style.display = 'none';
    }

    return isValid;
}
