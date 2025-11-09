// ✅ Slide Panel Open
function openEmailPanel() {
    const panel = document.getElementById("emailPanel");
    panel.setAttribute("aria-hidden", "false");
}

// ✅ Slide Panel Close
function closeEmailPanel() {
    const panel = document.getElementById("emailPanel");
    panel.setAttribute("aria-hidden", "true");
}

// ✅ Toggle Password Visibility
function togglePassword() {
    const pass = document.getElementById("password");
    pass.type = pass.type === "password" ? "text" : "password";
}
//
// // ✅ Google Click
// function googleLogin() {
//     alert("✅ Google Login Coming Soon (Backend Required)");
// }
//
// // ✅ LinkedIn Click
// function linkedinLogin() {
//     alert("✅ LinkedIn Login Coming Soon (Backend Required)");
// }


// ✅ Login Form Validation
function validateLoginForm() {
    const form = document.getElementById("loginForm");
    if (!form) return;

    form.addEventListener("submit", (e) => {
        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();
        const role = document.getElementById("role").value.trim();

        if (!username || !password || !role) {
            alert("⚠️ All fields are required.");
            e.preventDefault();
            return;
        }

        if (password.length < 6) {
            alert("🔒 Password must be at least 6 characters.");
            e.preventDefault();
        }
    });
}

document.addEventListener("DOMContentLoaded", () => {
    validateLoginForm();
});
