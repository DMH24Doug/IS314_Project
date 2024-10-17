function validateForm(event) {
    let isValid = true;

    // Helper function to validate input and show errors
    function validateInput(id, pattern = null, errorMessage = '') {
        const input = document.getElementById(id);
        const error = document.getElementById(`${id}-error`);
        const value = input.value.trim();

        if (!value || (pattern && !pattern.test(value))) {
            input.classList.add('error');
            input.classList.remove('valid');
            error.textContent = errorMessage;
            error.style.display = 'block';
            isValid = false;
        } else {
            input.classList.remove('error');
            input.classList.add('valid');
            error.style.display = 'none';
        }
    }

    // Phone pattern: (679) 1234567
    const phonePattern = /^\(679\) \d{7}$/;

    // Validate required fields
    validateInput('fname', null, 'First name is required.');
    validateInput('lname', null, 'Last name is required.');
    validateInput('email', /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/, 'Invalid email format.');
    validateInput('phone', phonePattern, 'Invalid phone format.');
    validateInput('dob', null, 'Please select a valid date.');
    validateInput('address', null, 'Address is required.');
    validateInput('emergencyName', null, 'Emergency contact name is required.');
    validateInput('emergencyPhone', phonePattern, 'Invalid phone format.');
    validateInput('emergencyEmail', /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/, 'Invalid email format.');

    if (!isValid) {
        event.preventDefault(); // Prevent form submission if invalid
    }
}

// Add submit listener to trigger validation on form submission
document.querySelector('form').addEventListener('submit', validateForm);

// Add reset listener to clear errors and input fields
document.querySelector('form').addEventListener('reset', () => {
    document.querySelectorAll('input, textarea').forEach(input => {
        input.classList.remove('error', 'valid');
    });
    document.querySelectorAll('.error-message').forEach(error => {
        error.style.display = 'none';
    });
});

// Lock the (679) prefix and allow only 7 digits after it
document.querySelectorAll('#phone, #emergencyPhone').forEach(input => {
    // Set cursor position after prefix when focused
    input.addEventListener('focus', () => {
        if (input.value === '(679) ') {
            input.setSelectionRange(6, 6);
        }
    });

    // Prevent any modification of the prefix "(679) "
    input.addEventListener('keydown', (e) => {
        if (input.selectionStart < 6) {
            e.preventDefault(); // Block input before the prefix
        }
    });

    // Allow only numbers after the prefix
    input.addEventListener('input', () => {
        const prefix = '(679) ';
        let remainingInput = input.value.slice(6).replace(/\D/g, ''); // Remove non-digits

        if (remainingInput.length > 7) {
            remainingInput = remainingInput.slice(0, 7); // Limit to 7 digits
        }

        input.value = prefix + remainingInput;
    });

    // Ensure cursor position stays after prefix on click
    input.addEventListener('click', (e) => {
        if (input.selectionStart < 6) {
            input.setSelectionRange(6, 6);
        }
    });
});
