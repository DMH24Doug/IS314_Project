
//dropdown function
function toggleDropdown() {
    var dropdown = document.getElementById("dropdownMenu");
    dropdown.classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('#myAccButton a')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
};


//dynamic nav bar
// Get the current page's file name
let currentPage = window.location.pathname.split("/").pop();

// Get all the <a> tags inside the <nav>
let navLinks = document.querySelectorAll("nav ul li a");

// Loop through each <a> tag
navLinks.forEach(link => {
    // If the href matches the current page, add 'active' class
    if (link.getAttribute("href") === currentPage) {
        link.classList.add("active");
    }
});

//dynamic body resize to fit scroller

function adjustPadding() {
    const body = document.body;
    if (body.scrollHeight > window.innerHeight) {
        body.style.paddingRight = '0'; // Adjust based on your scrollbar width
    } else {
        body.style.paddingRight = '0';
    }
}

// Run the function on page load and window resize
window.addEventListener('resize', adjustPadding);
window.addEventListener('load', adjustPadding);


const messageInput = document.getElementById('messageInput');
const wordCountDisplay = document.getElementById('wordCountDisplay');
const submitButton = document.getElementById('submitButton');
const maxChars = 300; // Set character limit

messageInput.placeholder = `Message (max ${maxChars} characters)`;

// Add 'input' event listener to enforce character limit
messageInput.addEventListener('input', function (e) {
    if (messageInput.value.length > maxChars) {
        // Prevent adding more characters by slicing to max length
        messageInput.value = messageInput.value.slice(0, maxChars);
    }

    // Update word count display
    wordCountDisplay.textContent = `${messageInput.value.length} / ${maxChars} characters`;

    // Adjust text color based on the limit
    if (messageInput.value.length >= maxChars) {
        wordCountDisplay.style.color = 'red'; // Red color when limit is reached
    } else {
        wordCountDisplay.style.color = 'black'; // Default color otherwise
    }
});


//email contact validation
document.getElementById('contactForm').addEventListener('submit', function(event) {
    var emailInput = document.getElementById('emailInput');
    var emailError = document.getElementById('emailError');

    //email regex validation pattern
    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

    if (!emailPattern.test(emailInput.value)) {
        event.preventDefault(); // Prevent form submission
        emailError.style.display = 'block'; // Show the error message
        emailInput.style.border = '2px solid red'; // Highlight the input with red border
    } else {
        emailError.style.display = 'none'; // Hide the error message
        emailInput.style.border = ''; // Reset input border
    }
});

//dynamic booking service details







