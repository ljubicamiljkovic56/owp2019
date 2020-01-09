$(document).ready(function(){
	var korisnickoImeInput = $('#korisnickoImeInput');
	var lozinkaInput = $('#lozinkaInput');
	
	$('#loginSubmit').on('click', function(event){
		var korisnickoIme = korisnickoImeInput.val();
		var lozinka = lozinkaInput.val();
		console.log('korisnickoIme: ' + korisnickoIme);
		console.log('lozinka: ' + lozinka);
		
		var params = {
			'korisnickoIme': korisnickoIme, 
			'lozinka': lozinka
		}
		$.post('LoginServlet', params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			if (data.status == 'failure') {
				korisnickoImeInput.val('');
				lozinkaInput.val('');
				
				return;
			}
			if (data.status == 'success') {
				window.location.replace('aplikacija.html');
			}
		});
		console.log('slanje poruke');
		
		event.preventDefault();
		return false;
	});
});