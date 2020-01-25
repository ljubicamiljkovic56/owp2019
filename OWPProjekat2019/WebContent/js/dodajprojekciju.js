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
	var datumPrikazivanjaInput = $('#datumPrikazivanjaInput');
	var vremePrikazivanjaInput = $('#vremePrikazivanjaInput');
	var cenaKarteInput = $('#cenaKarteInput');
	var adminInput = $('#adminInput');
	
	$('button').on('click', function(event){
		var film = filmInput.val();
		var tipProjekcije = tipProjekcijeInput.val();
		var sala = salaInput.val();
		var datumPrikazivanja = datumPrikazivanjaInput.val();
		var vremePrikazivanja = vremePrikazivanjaInput.val();
		var cenaKarte = cenaKarteInput.val();
		var admin = adminInput.val();
		console.log('film: ' + film);
		console.log('tipProjekcije: ' + tipProjekcije);
		console.log('sala: ' + sala);
		console.log('datumPrikazivanja: ' + datumPrikazivanja);
		console.log('vremePrikazivanja: ' + vremePrikazivanja);
		console.log('cenaKarte: ' + cenaKarte);
		console.log('admin: ' + admin);
		
		params = {
				'action': 'add', 
				'film': film, 
				'tipProjekcije': tipProjekcije,
				'sala': sala,
				'datumPrikazivanja': datumPrikazivanja,
				'vremePrikazivanja': vremePrikazivanja,
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
				window.location.replace('projekcijeadmin.html');
			}
		});
		event.preventDefault();
		return false;
	});
});