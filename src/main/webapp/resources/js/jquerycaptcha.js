/* JS/JQUERY Written by the Coding Magician 2010 */

$(document).ready(function() {
	//alert("js is working");
	//CAPTCHA
	
	//show captcha when the terms box is checked
	if ($('input[name=termsckb]').attr('checked') != true) {
    	$('#captcha-wrap').hide();
    }
    
	//hide when unchecked
    $('#termsckb').change(function() {
    	 if ($('input[name=termsckb]').attr('checked') == true) {
			 $('#captcha-wrap').show();
			 $("#signupbutton").attr("value", "I am human!");
		 }
		 else {
			 $('#captcha-wrap').hide();
			 $("#signupbutton").attr("value", "Submit");
		 }
	});
	
});

//Validate the Recaptcha' Before continuing with POST ACTION
function validateCaptcha()
{
	challengeField = $("input#recaptcha_challenge_field").val();
	responseField = $("input#recaptcha_response_field").val();
	var html = $.ajax({
		type: "POST",
		url: "validateform.php",
		data: "form=signup&recaptcha_challenge_field=" + challengeField + "&recaptcha_response_field=" + responseField,
		async: false
		}).responseText;
	if(html == "success") {
		//Add the Action to the Form
		$("form").attr("action", "processform.php"); //<-- your script to process the form
		$("#submit").attr("value", "Submit");
		//Indicate a Successful Captcha
		$("#captcha-status").html("<p class=\"green bold\">Success! Thanks you may now proceed.</p>");
	} else {
		$("#captcha-status").html("<p class=\"red bold\">The security code you entered did not match. Please try again.</p>");
		Recaptcha.reload();
	}
}	
