const images = [
    'url("https://www.who.int/images/default-source/wpro/health-topic/hospitals/f8-11102016-my-6042.tmb-1920v.jpg?Culture=en&sfvrsn=57e1f33d_4%201920w")',
    'url("https://thumbs.dreamstime.com/b/medical-science-scientific-research-abstract-backdrop-62195483.jpg")',
    '/images/slideshow/image4.jpeg'
];

let currentIndex = 0;

function changeBackground() {
    const container = document.querySelector('.imageContainer');
    container.style.backgroundImage = images[currentIndex];

    currentIndex = (currentIndex + 1) % images.length; // Loop back to the first image
}

// Change image every 5 seconds
setInterval(changeBackground, 5000);

// Initial call to set the first background image
changeBackground();


//https://www.kcl.ac.uk/newimages/folsm/main-article/scps/hospital-patients-drug-related-deaths.xe7d76a07.png?f=webp
//https://st4.depositphotos.com/13349494/20129/i/450/depositphotos_201294084-stock-photo-top-view-stethoscope-surrounded-different.jpg