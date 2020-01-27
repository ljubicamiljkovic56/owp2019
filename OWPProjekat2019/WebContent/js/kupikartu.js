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
	var projekcijaInput = $('#projekcijaInput');
	var sedisteInput = $('#sedisteInput');
	var datumProdajeInput = $('#datumProdajeInput');
	var vremeProdajeInput = $('#vremeProdajeInput');
	var korisnikInput = $('#korisnikInput');
	
	$('#kartaSubmit').on('click', function(event){
		var projekcija = projekcijaInput.val();
		var sediste = sedisteInput.val();
		var datumProdaje = datumProdajeInput.val();
		var vremeProdaje = vremeProdajeInput.val();
		var korisnik = korisnikInput.val();
		console.log('projekcija: ' + projekcija);
		console.log('sediste: ' + sediste)
		console.log('datumProdaje: ' + datumProdaje);
		console.log('vremeProdaje: ' + vremeProdaje);
		console.log('korisnik: ' + korisnik);
		
		params = {
				'action': 'add', 
				'projekcija': projekcija, 
				'sediste': sediste,
				'datumProdaje': datumProdaje,
				'vremeProdaje': vremeProdaje,
				'korisnik ': korisnik
		};
		$.post('KartaServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('aplikacija.html');
			}
		});
		event.preventDefault();
		return false;
	});
});