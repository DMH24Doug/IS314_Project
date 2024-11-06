document.addEventListener('DOMContentLoaded', () => {
    const phoneInputs = document.querySelectorAll('#phone, #emergencyPhone');
    const prefix = '(679) ';
    const maxImageSize = 2 * 1024 * 1024; // 2MB in bytes

    phoneInputs.forEach(input => {
        // Ensure the input starts with the "(679) " prefix
        if (!input.value.startsWith(prefix)) {
            input.value = prefix;
        }

        input.addEventListener('focus', () => {
            // Move cursor after the prefix on focus
            if (input.value === prefix) {
                input.setSelectionRange(prefix.length, prefix.length);
            }
        });

        input.addEventListener('keydown', (e) => {
            // Prevent modifying the prefix and ensure valid keypresses
            if (input.selectionStart < prefix.length && e.key !== 'Tab' && e.key !== 'Shift') {
                e.preventDefault();
            }
        });

        input.addEventListener('input', () => {
            // Allow only numbers after the prefix
            let remainingInput = input.value.slice(prefix.length).replace(/\D/g, '');

            if (remainingInput.length > 7) {
                remainingInput = remainingInput.slice(0, 7); // Limit to 7 digits
            }

            input.value = prefix + remainingInput;
        });

        input.addEventListener('click', (e) => {
            // Keep cursor after the prefix on click
            if (input.selectionStart < prefix.length) {
                input.setSelectionRange(prefix.length, prefix.length);
            }
        });
    });

    // Form validation logic
    function validateForm(event) {
        let isValid = true;

        // Helper function for validating inputs
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

        // Validate Image Upload
        const imageInput = document.getElementById('imageFile');
        const imageError = document.getElementById('imageFile-error');
        const file = imageInput.files[0];

        if (!file) {
            imageError.textContent = 'Please upload an image.';
            imageError.style.display = 'block';
            isValid = false;
        } else if (!['image/jpeg', 'image/jpg'].includes(file.type)) {
            imageError.textContent = 'Only JPEG images are allowed.';
            imageError.style.display = 'block';
            isValid = false;
        } else if (file.size > maxImageSize) {
            imageError.textContent = 'Image size must be under 2MB.';
            imageError.style.display = 'block';
            isValid = false;
        } else {
            imageError.style.display = 'none';
        }

        if (!isValid) {
            event.preventDefault(); // Prevent form submission if invalid
        }
    }

    // Add submit event listener for validation
    const form = document.querySelector('form');
    form.addEventListener('submit', validateForm);

    // Reset form and errors
    form.addEventListener('reset', () => {
        document.querySelectorAll('input, textarea').forEach(input => {
            input.classList.remove('error', 'valid');
        });

        document.querySelectorAll('.error-message').forEach(error => {
            error.style.display = 'none';
        });

        // Reset phone inputs to prefix
        phoneInputs.forEach(input => {
            input.value = prefix;
        });

        // Clear image input
        const imageInput = document.getElementById('imageFile');
        imageInput.value = '';
    });
});
