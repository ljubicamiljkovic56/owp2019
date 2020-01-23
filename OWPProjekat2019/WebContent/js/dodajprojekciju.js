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
	var filmInput = $('#filmInput');
	var tipProjekcijeInput = $('#tipProjekcijeInput');
	var salaInput = $('#salaInput');
	var datumIVremePrikazivanjaInput = $('#datumIVremePrikazivanjaInput');
	var cenaKarteInput = $('#cenaKarteInput');
	var adminInput = $('#adminInput');
	
	$('#projekcijaSubmit').on('click', function(event){
		var film = filmInput.val();
		var tipProjekcije = tipProjekcijeInput.val();
		var sala = salaInput.val();
		var datumIVremePrikazivanja = datumIVremePrikazivanjaInput.val();
		var cenaKarte = cenaKarteInput.val();
		var admin = adminInput.val();
		console.log('film: ' + film);
		console.log('tipProjekcije: ' + tipProjekcije);
		console.log('sala: ' + sala);
		console.log('datumIVremePrikazivanja: ' + datumIVremePrikazivanja);
		console.log('cenaKarte: ' + cenaKarte);
		console.log('admin: ' + admin);
		
		params = {
				'action': 'add', 
				'film': film, 
				'tipProjekcije': tipProjekcije,
				'sala': sala,
				'datumIVremePrikazivanja': datumIVremePrikazivanja,
				'cenaKarte': cenaKarte,
				'admin': admin
		};
		$.post('ProjekcijaServlet', params, function(data) {
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