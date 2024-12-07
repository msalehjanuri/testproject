document.addEventListener('DOMContentLoaded', function() {
            // Get the form elements
            const loginForm = document.getElementById('login-form');
            const registerForm = document.getElementById('register-form');
            const toRegisterBtn = document.getElementById('to-register');
            const toLoginBtn = document.getElementById('to-login');

            // Switch to Register Form
            toRegisterBtn.addEventListener('click', function() {
                loginForm.style.display = 'none';
                registerForm.style.display = 'block';
            });

            // Switch to Login Form
            toLoginBtn.addEventListener('click', function() {
                registerForm.style.display = 'none';
                loginForm.style.display = 'block';
            });
});