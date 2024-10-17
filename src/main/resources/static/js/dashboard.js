function showDiv(divId) {
    // Hide all contentDivs
    const contentDivs = document.querySelectorAll('.contentDiv');
    contentDivs.forEach(div => {
        div.style.display = 'none';
    });

    // Show the selected div
    const selectedDiv = document.getElementById(divId);
    selectedDiv.style.display = 'block';
}

// Load the default dashboardDiv when the page loads
window.onload = function() {
    showDiv('dashboardDiv');
};