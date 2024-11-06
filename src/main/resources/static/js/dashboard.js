function showDiv(divId) {
    // Ensure all contentDivs are hidden
    const contentDivs = document.querySelectorAll('.contentDiv');
    contentDivs.forEach(div => {
        div.style.display = 'none';
        div.classList.remove('true'); // Remove any active class if needed
    });

    // Show the selected div
    const selectedDiv = document.getElementById(divId);
    if (selectedDiv) {
        selectedDiv.style.display = 'block';
        selectedDiv.classList.add('true'); // Optional: Add active class for styling
    } else {
        console.warn(`Div with ID ${divId} not found.`);
    }
}

// Set default content on page load
window.onload = function () {
    showDiv('dashboardDiv'); // Ensure default content is loaded
};

function toggleDropdown() {
    const dropdown = document.getElementById("dropdownMenu");
    dropdown.classList.toggle("show");
}

// Close the dropdown if clicked outside
window.onclick = function (event) {
    if (!event.target.matches('.accountButton, .accountButton img')) {
        const dropdowns = document.getElementsByClassName("dropdown-content");
        for (let i = 0; i < dropdowns.length; i++) {
            const openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
};


//doctor page
// Function to show patient details in the patient details div
function showPatientDetails(patientId) {
    // Fetch the patient data (You could use an AJAX request here if needed)
    const patient = patients.find(p => p.id === patientId);

    if (patient) {
        // Set the patient info in the patientDetailsDiv
        document.getElementById('patientId').textContent = patient.id;
        document.getElementById('patientName').textContent = `${patient.fname} ${patient.lname}`;
        document.getElementById('patientAddress').textContent = patient.address;

        // Hide the patient list and show the details div
        document.getElementById('patientDiv').style.display = 'none';
        document.getElementById('patientDetailsDiv').style.display = 'block';
    }
}

// Function to go back to the patient list
function backToPatientList() {
    document.getElementById('patientDetailsDiv').style.display = 'none';
    document.getElementById('patientDiv').style.display = 'block';
}