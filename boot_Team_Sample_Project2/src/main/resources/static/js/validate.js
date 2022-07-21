//////CONTACT FORM VALIDATION
jQuery(document).ready(function ($) {
	
	//if submit button is clicked
	$('#submit').click(function () {		
		
		//Get the data from all the fields
		var name = $('input[name=name]');
		var id = $('input[name=id]');
		var pw = $('input[name=pw]');
		var pw2 = $('input[name=pw2]');
		var email = $('input[name=email]');
		var phone = $('input[name=phone]');
		var regx = /^([a-z0-9_\-\.])+\@([a-z0-9_\-\.])+\.([a-z]{2,4})$/i;
		var comment = $('textarea[name=comment]');
		var returnError = false;
		
		//Simple validation to make sure user entered something
		//Add your own error checking here with JS, but also do some error checking with PHP.
		//If error found, add hightlight class to the text field
		if (name.val()=='') {
			name.addClass('error');
			returnError = true;
		} else name.removeClass('error');

		if (id.val()=='') {
			id.addClass('error');
			returnError = true;
		} else id.removeClass('error');

		if (pw.val()=='') {
			pw.addClass('error');
			returnError = true;
		} else pw.removeClass('error');

		if (pw2.val()=='') {
			pw2.addClass('error');
			returnError = true;
		} else pw2.removeClass('error');

		if (email.val()=='') {
			email.addClass('error');
			returnError = true;
		} else email.removeClass('error');
		
		if (phone.val()=='') {
			phone.addClass('error');
			returnError = true;
		} else phone.removeClass('error');
		
		if(!regx.test(email.val())){
          email.addClass('error');
          returnError = true;
		} else email.removeClass('error');
		
		
		// Highlight all error fields, then quit.
		if(returnError == true){
			return false;	
		}
		
		//organize the data
		
		var data = 'name=' + name.val() + '&id=' + id.val() + '&pw=' + pw.val() + '&pw2=' + pw2.val() + '&email=' + email.val() + '&phone=' + phone.val();
		// + '&comment='  + encodeURIComponent(comment.val());

		//disabled all the text fields
		$('.text').attr('disabled','true');
		
		//show the loading sign
		$('.loading').show();
		
		//start the ajax
		$.ajax({
			//this is the php file that processes the data and sends email
			url: "contact.php",	
			
			//GET method is used
			type: "GET",

			//pass the data			
			data: data,		
			
			//Do not cache the page
			cache: false,
			
			//success
			success: function (html) {				
				//if contact.php returned 1/true (send mail success)
				if (html==1) {
				
					//show the success message
					$('.done').fadeIn('slow');
					
					$(".form").find('input[type=text], textarea').val("");
					
				//if contact.php returned 0/false (send mail failed)
				} else alert('Sorry, unexpected error. Please try again later.');				
			}		
		});
		
		//cancel the submit button default behaviours
		return false;
	});	
});	

