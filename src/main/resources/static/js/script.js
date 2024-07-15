console.log("script loaded");

let currentTheme = getTheme();
console.log(currentTheme);

changeTheme(currentTheme);

function changeTheme() {
    document.querySelector('html').classList.add(currentTheme);
    const themeButton = document.getElementById('theme_change_button')
    themeButton.addEventListener('click', (event) => {
        const oldTheme = currentTheme;
        console.log("button clicked")
        if (currentTheme === "dark") {
            currentTheme = "light";
        }else {
            currentTheme = "dark";
        }
        //change theme in local storage
        setTheme(currentTheme);
        document.querySelector('html').classList.remove(oldTheme);

        document.querySelector('html').classList.add(currentTheme);

        // change button text
        themeButton.querySelector("span").textContent =
        currentTheme == "dark" ? "light" : "dark";
    });
}

// set theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// get theme for local storage 
function getTheme() {
    let theme = localStorage.getItem("theme");
    if (theme) {
        return theme;
    }
    return "light";
}