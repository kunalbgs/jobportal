document.addEventListener('DOMContentLoaded', () => {
    validateLoginForm();
    validateRegisterForm();
});

function validateLoginForm() {
    const loginForm = document.querySelector('form[action="/auth/login"]') ||
        document.querySelector('form[th\\:action="@{/auth/login}"]');

    if (!loginForm) return;

    loginForm.addEventListener('submit', (event) => {
        const username = getValue('username');
        const password = getValue('password');
        const role = getValue('role');

        if (!username || !password || !role) {
            alert("⚠️ All fields are required: Username, Password, and Role.");
            event.preventDefault();
            return;
        }

        if (password.length < 6) {
            alert("🔒 Password must be at least 6 characters.");
            event.preventDefault();
        }
    });
}

function validateRegisterForm() {
    const registerForm = document.querySelector('form[action="/auth/register"]') ||
        document.querySelector('form[th\\:action="@{/auth/register}"]');

    if (!registerForm) return;

    registerForm.addEventListener('submit', (event) => {
        const username = getValue('username');
        const email = getValue('email');
        const password = getValue('password');
        const role = getValue('role');

        if (!username || !email || !password || !role) {
            alert("⚠️ Please fill in all required fields.");
            event.preventDefault();
            return;
        }

        if (!validateEmail(email)) {
            alert("📧 Invalid email format.");
            event.preventDefault();
            return;
        }

        if (password.length < 6) {
            alert("🔒 Password must be at least 6 characters.");
            event.preventDefault();
        }
    });
}

function getValue(id) {
    const field = document.getElementById(id);
    return field ? field.value.trim() : '';
}

function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}