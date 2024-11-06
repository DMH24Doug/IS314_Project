function submitForms() {
    const examForm = new FormData(document.getElementById('examForm'));
    const prescForm = new FormData(document.getElementById('prescForm'));

    // Merge both form data objects
    const combinedData = {};
    examForm.forEach((value, key) => (combinedData[key] = value));
    prescForm.forEach((value, key) => (combinedData[key] = value));

    fetch('/examination/create', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(combinedData),
    })
        .then(response => response.json())
        .then(data => alert('Successfully submitted!'))
        .catch(error => console.error('Error:', error));
}
