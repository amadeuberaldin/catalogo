document.addEventListener("DOMContentLoaded", function () {
    function setCookie(name, value, days) {
        const d = new Date();
        d.setTime(d.getTime() + (days * 24 * 60 * 60 * 1000));
        document.cookie = `${name}=${value}; expires=${d.toUTCString()}; path=/; SameSite=Lax`;
    }

    function getCookie(name) {
        const nameEQ = name + "=";
        const ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i].trim();
            if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length);
        }
        return null;
    }

    const savedTheme = getCookie("theme") || "light"; // tema padrão é "light"
    document.body.classList.remove("light-theme", "dark-theme"); // remove classes padrão

    // Aplica o tema baseado no cookie
    document.body.classList.add(savedTheme === "dark" ? "dark-theme" : "light-theme");

    function toggleTheme() {
        if (document.body.classList.contains("light-theme")) {
            document.body.classList.replace("light-theme", "dark-theme");
            setCookie("theme", "dark", 7);
        } else {
            document.body.classList.replace("dark-theme", "light-theme");
            setCookie("theme", "light", 7);
        }
        updateButtonStyle();
    }

    function updateButtonStyle() {
        const button = document.getElementById("toggle-theme");
        if (document.body.classList.contains("dark-theme")) {
            button.style.backgroundColor = "#fff";
            button.style.color = "#333";
        } else {
            button.style.backgroundColor = "#333";
            button.style.color = "#fff";
        }
    }

    document.getElementById("toggle-theme").addEventListener("click", toggleTheme);
    updateButtonStyle();
});
