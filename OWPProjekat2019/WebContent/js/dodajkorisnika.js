$(document).ready(function(){
	$('#logoutLink').on('click', function(event){
		$.get('LogoutServlet', function(data){
			console.log(data);
			
			if(data.status == 'unauthenticated'){
				window.location.replace('login.html');
				return;
			}
		});
		event.preventDefault();
		return false;
	});
	var korisnickoImeInput = $('#korisnickoImeInput');
	var lozinkaInput = $('#lozinkaInput');
	var datumRegInput = $('#datumRegInput');
	var ulogaInput = $('#ulogaInput');
	
	
	$('#korisnikSubmit').on('click', function(event){
		var korisnickoIme = korisnickoImeInput.val();
		var lozinka = lozinkaInput.val();
		var datumReg = datumRegInput.val();
		var uloga = ulogaInput.val();
		console.log('korisnickoIme: ' + korisnickoIme);
		console.log('datumReg: ' + datumReg)
		console.log('lozinka: ' + lozinka);
		console.log('uloga: ' + uloga);
		
		params = {
				'action': 'add', 
				'korisnickoIme': korisnickoIme, 
				'lozinka': lozinka,
				'datumReg': datumReg,
				'uloga': uloga
		};
		$.post('KorisnikServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('korisnici.html');
			}
		});
		event.preventDefault();
		return false;
	});
});