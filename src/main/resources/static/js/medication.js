function autoFillMedication() {
    const selectedMedication = document.getElementById("medicationName").value;
    if (selectedMedication) {
        fetch(`/api/medications/details?name=${selectedMedication}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Medication not found");
                }
            })
            .then(data => {
                document.getElementById("dosage").value = data.dosage;
                document.getElementById("frequency").value = data.frequency;
                document.getElementById("remarks").value = data.remarks;
            })
            .catch(error => {
                console.error(error);
                document.getElementById("dosage").value = "";
                document.getElementById("frequency").value = "";
                document.getElementById("remarks").value = "";
            });
    }
}
