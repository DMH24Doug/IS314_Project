const today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();

const availableTimes = ["08:00", "09:00", "10:00", "11:00", "12:00",
    "13:00", "14:00", "15:00", "16:00", "17:00"];

function generateCalendar(month, year) {
    const calendar = document.getElementById('calendar');
    calendar.innerHTML = '';
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    const firstDay = new Date(year, month, 1).getDay();

    document.getElementById('monthYear').innerText =
        `${new Date(year, month).toLocaleString('default', { month: 'long' })} ${year}`;

    for (let i = 0; i < firstDay; i++) {
        calendar.appendChild(document.createElement('div'));
    }

    for (let day = 1; day <= daysInMonth; day++) {
        const dayElement = document.createElement('div');
        dayElement.classList.add('calendar-day');
        dayElement.innerText = day;

        dayElement.addEventListener('click', () => {
            document.querySelectorAll('.calendar-day').forEach(el => el.classList.remove('selected-day'));
            dayElement.classList.add('selected-day');
            document.getElementById('summaryDate').innerText = `Date: ${year}-${month + 1}-${day}`;
        });

        calendar.appendChild(dayElement);
    }

    document.getElementById('prevButton').disabled =
        year === today.getFullYear() && month === today.getMonth();
}

function generateTimeSlots() {
    const timeSlotContainer = document.getElementById('timeSlot');
    timeSlotContainer.innerHTML = '';

    availableTimes.forEach(time => {
        const timeButton = document.createElement('button');
        timeButton.innerText = time;

        const isPast = new Date(`${currentYear}-${currentMonth + 1}-${today.getDate()}T${time}`) < new Date();
        timeButton.disabled = isPast;

        timeButton.addEventListener('click', () => {
            document.querySelectorAll('.timeSlot button').forEach(btn => btn.classList.remove('selected'));
            timeButton.classList.add('selected');
            document.getElementById('summaryTime').innerText = `Time: ${time}`;
        });

        timeSlotContainer.appendChild(timeButton);
    });
}

function changeMonth(step) {
    currentMonth += step;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    } else if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    generateCalendar(currentMonth, currentYear);
}

function confirmSelection() {
    const selectedTime = document.querySelector('.timeSlot button.selected')?.innerText;
    if (!selectedTime) {
        alert('Please select a time.');
        return;
    }
    alert(`You selected ${selectedTime}`);
}

window.onload = () => {
    generateCalendar(currentMonth, currentYear);
    generateTimeSlots();
};