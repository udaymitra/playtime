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

function registerUserButtonClick(registerUserForm) {
    var formElements = registerUserForm.elements;

    var inputPassword = formElements.inputPassword.value;
    var reEnteredPassword = formElements.reEnteredPassword.value;
    if (inputPassword !== reEnteredPassword) {
        alert("Both the passwords dont match!");
        return;
    }

    var registerFormData = {
        'emailPassword': {
            'email' : formElements.inputEmail.value,
            'pass' : formElements.inputPassword.value
        },
        'fname': formElements.firstName.value,
        'lname': formElements.lastName.value,
        'zip': formElements.zipCode.value,
        'cuisine': formElements.cuisine.value
    };
    var registerFormJson= JSON.stringify(registerFormData);

    ajaxPostCall(registerFormJson, 'http://localhost:9000/addNewSeller')
        .done(registerSuccessCallback)
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

function registerSuccessCallback(data) {
    if(data.Message == "success"){
        alert("Successfully registerd");
    } else {
        alert("Couldnt register. User email address is probably already registered");
    }
}

function validateEmail(email){
    // Make an AJAX Call to determine the Validity of the email
    var emailJson = JSON.stringify(email.value);
    ajaxPostCall(emailJson, 'http://localhost:9000/validateEmail').done(validateSuccessCallback).fail(loginFailureCallback);
}

function validateSuccessCallback(data){
    if(data.Message == "success"){
        alert("Mail Sent");
    } else {
        alert("Email Already Signed Up");
    }
}
