// VARIABLES GLOBALES
const d = document;
const links = d.querySelectorAll(".nav-link");
const animatedText = d.querySelector('.animated-text');


d.addEventListener("DOMContentLoaded", () => {

    // ANIMAR HIPERVÍNCULOS AL CARGAR

    links.forEach((link, i) => {
        setTimeout(() => {
            link.classList.add("visible");
        }, 300 + i * 150);
    });

    function showWhenInView() {
        const rect = animatedText.getBoundingClientRect();
        const windowHeight = window.innerHeight;

        if (rect.top <= windowHeight - 100) {
            animatedText.classList.add('visible');
        }
    }

    showWhenInView();
    window.addEventListener('scroll', showWhenInView);
});
