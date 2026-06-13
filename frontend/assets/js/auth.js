const loggedInUser =
JSON.parse(
    localStorage.getItem("loggedInUser")
);
if(!loggedInUser){
    window.location.href =
    "/assets/modules/users/login.html";
}
