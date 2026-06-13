function registerUser() {
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (!username || !email || !password) {
        alert("Please fill in all fields.");
        return;
    }

    if (password.length < 6) {
        alert("Password must be at least 6 characters.");
        return;
    }

    const user = {
        username: username,
        email: email,
        password: password,
        role: "USER" // Default role
    };

    fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to register.");
        }
        return response.json();
    })
    .then(data => {
        alert("Registration successful! You can now log in.");
        window.location.href = "login.html";
    })
    .catch(error => {
        alert("Registration failed. Email might already be taken.");
        console.error(error);
    });
}
