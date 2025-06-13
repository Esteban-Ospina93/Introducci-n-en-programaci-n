let d = document;

d.getElementById("formRegistro").addEventListener("submit", function (e) {
  e.preventDefault();

  const nombre = d.getElementById("nombre").value.trim();
  const correo = d.getElementById("correo").value.trim();
  const confirmarCorreo = d.getElementById("confirmarCorreo").value.trim();
  const contrasena = d.getElementById("contrasena").value;
  const confirmarContrasena = d.getElementById("confirmarContrasena").value;

  const mensaje = d.getElementById("mensaje");

  if (correo !== confirmarCorreo) {
    mensaje.textContent = "Los correos no coinciden.";
    mensaje.classList.add("text-danger");
    mensaje.classList.remove("text-success");
    return;
  }

  if (contrasena !== confirmarContrasena) {
    mensaje.textContent = "Las contraseñas no coinciden.";
    mensaje.classList.add("text-danger");
    mensaje.classList.remove("text-success");
    return;
  }

  const usuario = {
    nombre,
    correo,
    contrasena,
  };

  localStorage.setItem(`usuario_${correo}`, JSON.stringify(usuario));

  mensaje.textContent = "¡Cuenta registrada exitosamente!";
  mensaje.classList.remove("text-danger");
  mensaje.classList.add("text-success");

  this.reset();

  // Redirige al login después de 2 segundos
  setTimeout(() => {
    window.location.href = "login.html";
  }, 1000);
});