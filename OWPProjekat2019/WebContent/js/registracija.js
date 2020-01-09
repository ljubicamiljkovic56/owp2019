$(document).ready(function(){
	var korisnickoImeInput = $('#korisnickoImeInput');
	var lozinkaInput = $('#lozinkaInput');
	var ponovljenaLozinkaInput = $('#ponovljenaLozinkaInput');

	var porukaParagraph = $('#porukaParagraph');
	
	$('#registracijaSubmit').on('click', function(event){
		var korisnickoIme = korisnickoImeInput.val();
		var lozinka = lozinkaInput.val();
		var ponovljenaLozinka = ponovljenaLozinkaInput.val();
		console.log('korisnickoIme: ' + korisnickoIme);
		console.log('lozinka: ' + lozinka);
		console.log('ponovljenaLozinka: ' + ponovljenaLozinka);
		
		if(lozinka != ponovljenaLozinka) {
			porukaParagraph.text('Lozinke nisu iste!');
			
			event.preventDefault();
			return false;
		}
		var params = {
			'korisnickoIme': korisnickoIme, 
			'lozinka': lozinka
		}
		$.post('RegistracijaServlet', params, function(data){
			console.log(data);
			
			if(data.status == 'failure'){
				porukaParagraph.text(data.message);
				return;
			}
			if(data.status == 'success'){
				window.location.replace('login.html');
			}
		});
		event.preventDefault();
		return false;
	});
});