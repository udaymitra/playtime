function signInButtonClick(loginForm) {
    var loginFormData = {
        'email' : loginForm.elements.inputEmail.value,
        'pass' : loginForm.elements.inputPassword.value
    };
    var loginFormJson = JSON.stringify(loginFormData);

    ajaxPostCall(loginFormJson, 'http://localhost:9000/loginSeller')
        .done(loginSuccessCallback)
        .fail(loginFailureCallback);
}

function loginSuccessCallback(data) {
    if(data.Message == "success"){
        alert("Login Successful");
    } else {
        alert("Invalid email/password");
    }
}

function loginFailureCallback(data) {
    alert("AJAX call failure!!!" + data);
}

function registerUserButtonClick(registerUserForm) {


}
