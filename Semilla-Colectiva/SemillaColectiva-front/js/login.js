const formularioLogin = document.querySelector('.formulario'); // clase del <form>
const inputCorreo = formularioLogin.querySelector('input[type="text"]');
const inputContrasena = formularioLogin.querySelector('input[type="password"]');

window.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('.formulario');
    const image = document.querySelector('.image-container');
    // Retrasa un poco para suavizar el efecto
    setTimeout(() => {
        form.classList.remove('hidden');
        form.classList.add('animate-in');
         image.classList.add('animate-in');
    }, 300);
});


formularioLogin.addEventListener('submit', function (e) {
  e.preventDefault();

  const correo = inputCorreo.value.trim();
  const contrasena = inputContrasena.value;

  let mensaje = document.getElementById('mensajeLogin');

  if (!mensaje) {
    mensaje = document.createElement('div');
    mensaje.id = 'mensajeLogin';
    formularioLogin.appendChild(mensaje); 
  }

  const usuario = JSON.parse(localStorage.getItem(`usuario_${correo}`));

  if (!usuario || usuario.contrasena !== contrasena) {
    mensaje.textContent = "Correo o contraseña incorrectos.";
    mensaje.className = "form-text text-danger text-center mb-2";
    
    alert("Contraseña incorrecta.");

    checkboxOlvido.disabled = false;
    checkboxOlvido.checked = true;

    return;
  }

  mensaje.textContent = "Inicio de sesión exitoso.";
  mensaje.className = "form-text text-success text-center mb-2";

  localStorage.setItem("usuarioActual", correo);

  setTimeout(() => {
    window.location.href = "trazabilidad.html";
  }, 1000);
});

function enviarRecuperacion() {
  const correo = document.getElementById('correoRecuperar').value.trim();

  if (!correo) {
    alert("Por favor, ingresa tu correo.");
    return;
  }

  // validar si existe en localStorage:
  const usuario = localStorage.getItem(`usuario_${correo}`);
  if (!usuario) {
    alert("Correo no registrado.");
    return;
  }

  // Simular envío
  alert(`Se han enviado instrucciones de recuperación a: ${correo}`);
  
  // Cierra el modal
  const modal = bootstrap.Modal.getInstance(document.getElementById('modalRecuperar'));
  modal.hide();
}
