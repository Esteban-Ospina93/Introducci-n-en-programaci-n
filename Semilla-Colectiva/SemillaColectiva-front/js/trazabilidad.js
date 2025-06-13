// VARIABLES GLOBALES
const d = document;
const links = d.querySelectorAll(".nav-link");
const imagenInput = d.getElementById("imagen");
const previewImage = d.getElementById("previewImage");


d.addEventListener("DOMContentLoaded", () => {

    // ANIMAR HIPERVÍNCULOS AL CARGAR

    links.forEach((link, i) => {
        setTimeout(() => {
            link.classList.add("visible");
        }, 300 + i * 150);
    });
});

d.addEventListener('DOMContentLoaded', () => {
    const form = d.getElementById('formTrazabilidad');
    const btnEnviar = d.getElementById('btnEnviar');
    const inputs = form.querySelectorAll('input, select');

    // Habilita el botón solo si todos los campos tienen contenido
    inputs.forEach(input => {
        input.addEventListener('input', () => {
            const formCompleto = Array.from(inputs).every(el => el.value.trim() !== '');
            btnEnviar.disabled = !formCompleto;
        });
    });

    imagenInput.addEventListener("change", function () {
        const file = this.files[0];
        if (file && file.type.startsWith("image/")) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewImage.src = e.target.result;
                previewImage.style.display = "block";
            };
            reader.readAsDataURL(file);
        } else {
            previewImage.src = "#";
            previewImage.style.display = "none";
        }
    });

    // Validación Bootstrap personalizada
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        event.stopPropagation();

        form.classList.add('was-validated');

        if (form.checkValidity()) {
            // Crear objeto con datos del formulario
            const trazabilidad = {
                nombre: form.nombre.value,
                especie: form.especie.value,
                ciclo: form.ciclo.value,
                estado: form.estado.value,
                imagen: previewImage.src // base64
            };

            // Obtener registros anteriores o crear nuevo array
            const registros = JSON.parse(localStorage.getItem("trazabilidades")) || [];

            // Agregar nuevo registro
            registros.push(trazabilidad);

            // Guardar en LocalStorage
            localStorage.setItem("trazabilidades", JSON.stringify(registros));

            alert("Formulario enviado con éxito");

            // Limpiar el formulario
            form.reset();
            btnEnviar.disabled = true;
            previewImage.style.display = "none";

            // Cerrar el modal
            const modal = bootstrap.Modal.getInstance(document.getElementById('trazabilidadModal'));
            modal.hide();
        }
    });
});


imagenInput.addEventListener("change", function () {
    const file = this.files[0];
    if (file && file.type.startsWith("image/")) {
        const reader = new FileReader();
        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = "block";
        };
        reader.readAsDataURL(file);
    } else {
        previewImage.src = "#";
        previewImage.style.display = "none";
    }
});

// Al guardar un nuevo registro
function actualizarEstiloHistorialSiHayRegistros() {
  const historialBtn = d.querySelector('#verHistorial');
  const registros = JSON.parse(localStorage.getItem('registrosTrazabilidad')) || [];

  if (registros.length > 0) {
    historialBtn.classList.add('activo'); // Clase que activa el color igual a "Crear trazabilidad"
  } else {
    historialBtn.classList.remove('activo'); // Para mantenerlo blanco si aún no hay nada
  }
}

function guardarTrazabilidad(nuevoRegistro) {
  const registros = JSON.parse(localStorage.getItem('registrosTrazabilidad')) || [];
  registros.push(nuevoRegistro);
  localStorage.setItem('registrosTrazabilidad', JSON.stringify(registros));
  actualizarEstiloHistorialSiHayRegistros(); // <- aquí
}

// Funcion para ver registros en el historial

document.addEventListener('DOMContentLoaded', function () {
    const verHistorial = document.getElementById('verHistorial');
    const historialContainer = document.getElementById('historialContainer');
    const historialList = document.getElementById('historialList');

    verHistorial.addEventListener('click', function () {
        const registros = JSON.parse(localStorage.getItem("trazabilidades")) || [];

        historialList.innerHTML = ""; // Limpia contenido previo

        if (registros.length === 0) {
            historialList.innerHTML = "<p>No hay registros disponibles.</p>";
        } else {
            registros.forEach((item, index) => {
                const card = document.createElement("div");
                card.classList.add("col");
                card.innerHTML = `
                    <div class="card h-100 shadow-sm">
                        <img src="${item.imagen}" class="card-img-top" alt="Imagen árbol ${item.nombre}">
                        <div class="card-body">
                            <h5 class="card-title">${item.nombre}</h5>
                            <p class="card-text"><strong>Especie:</strong> ${item.especie}</p>
                            <p class="card-text"><strong>Ciclo:</strong> ${item.ciclo}</p>
                            <p class="card-text"><strong>Estado:</strong> ${item.estado}</p>
                        </div>
                    </div>
                `;
                historialList.appendChild(card);
            });
        }

        historialContainer.style.display = "block";

        // Opcional: Scroll hacia el historial
        historialContainer.scrollIntoView({ behavior: "smooth" });
    });
});