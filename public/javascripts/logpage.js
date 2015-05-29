$(document).ready(function() {
	// Login Form Submission
	$('#login').click(function(event){
		// Remove Previous Errors If Any
	    $('.form-group').removeClass('has-error'); 	// remove the error class
	    $('.help-block').remove(); 					// remove the error text

	    // Pick up Data from
	    var formData = {
	        "email"	            : $('#l-email').val(),
	        "pass"              : $('#l-pass').val()
	    };

		// Validations

		// Email
        if (!formData.email) {
            $('#l-email-group').addClass('has-error'); // add the error class to show red input
            $('#l-email-group').append('<div class="help-block"><p>Required</p></div>'); // add the actual error message under our input
        }

        // Password
        if (!formData.pass) {
            $('#l-pass-group').addClass('has-error'); // add the error class to show red input
            $('#l-pass-group').append('<div class="help-block"><p>Required</p></div>'); // add the actual error message under our input
         }

		// The Ajax Call
	    $.ajax({
	        type        : 'POST', 	// define the type of HTTP verb we want to use (POST for our form)
	        url         : 'http://localhost:9000/loginSeller', 		// the url where we want to POST
	        data        : JSON.stringify(formData), // our data object
	        dataType    : 'json', 	// what type of data do we expect back from the server
	        contentType : "application/json"
	    })
	    // using the done promise callback
	    .done(function(data) {

	    });

    // stop the form from submitting the normal way and refreshing the page
    event.preventDefault();
	});

	// Login Form Submission
	$('#register').click(function(event){
		// Remove Previous Errors If Any
	    $('.form-group').removeClass('has-error'); 	// remove the error class
	    $('.help-block').remove(); 					// remove the error text

	    // Pick up Data from
	    var formData = {
	    	"fname"				: $('#fname').val(),
	    	"lname"				: $('#lname').val(),
	        "email"	            : $('#email').val(),
	        "pass"              : $('#pass').val(),
	        "rpass"				: $('#rpass').val(),
	        "zip"               : $('#zip').val(),
	        "cus"               : $('#cus').val(),
	    };

		// Validations

		// First Name
        if (!formData.fname) {
            $('#fname-group').addClass('has-error'); // add the error class to show red input
            $('#fname-group').append('<div class="help-block"> <p>Required</p> </div>'); // add the actual error message under our input
        }

		// Last Name
        if (!formData.lname) {
            $('#lname-group').addClass('has-error'); // add the error class to show red input
            $('#lname-group').append('<div class="help-block"> <p>Required</p> </div>'); // add the actual error message under our input
        }

		// Email
        if (!formData.email) {
            $('#email-group').addClass('has-error'); // add the error class to show red input
            $('#email-group').append('<div class="help-block"> <p>Required</p> </div>'); // add the actual error message under our input
        }

        // Password
        if (!formData.pass) {
            $('#pass-group').addClass('has-error'); // add the error class to show red input
            $('#pass-group').append('<div class="help-block"><p>Required</p></div>'); // add the actual error message under our input
         }else{
         	// Check	 for Password Compatibility
         	// Empty Field
         	if(!formData.rpass){
         		$('#rpass-group').addClass('has-error'); // add the error class to show red input
            	$('#rpass-group').append('<div class="help-block"><p>Required</p></div>'); // add the actual error message under our input
         	}else{
         		// Passwords Don't Match
         		if (formData.pass != formData.rpass){
         			$('#pass-group').addClass('has-error'); // add the error class to show red input
            		$('#pass-group').append('<div class="help-block"><p>Passwords Don\'t Match</p></div>'); // add the actual error message under our input
         		}
         	}
         }

        // Zip Code
        if (!formData.zip) {
            $('#zip-group').addClass('has-error'); // add the error class to show red input
            $('#zip-group').append('<div class="help-block"><p>Required</p></div>'); // add the actual error message under our input
         }

        // Cuisine
        if (!formData.cus) {
            $('#cus-group').addClass('has-error'); // add the error class to show red input
            $('#cus-group').append('<div class="help-block"><p>Required</p></div>'); // add the actual error message under our input
         }

		// The Ajax Call
	    $.ajax({
	        type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
	        url         : 'http://localhost:9000/addNewSeller', // the url where we want to POST
	        data        : JSON.stringify(formData), // our data object
	        dataType    : 'json', // what type of data do we expect back from the server
	        contentType : "application/json"
	    })
	    // using the done promise callback
	    .done(function(data) {

	    });

    // stop the form from submitting the normal way and refreshing the page
    event.preventDefault();
	});

});