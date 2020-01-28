$(document).ready(function(){
	var urlParams = new URLSearchParams(window.location.search);
	$('#idInput').val(urlParams.get('id'));
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
	 var idInput = $('#idInput');
	 var filmInput = $('#filmInput');
	 var tipProjekcijeInput = $('#tipProjekcijeInput');
     var salaInput = $('#salaInput');
     var datumPrikazivanjaInput = $('#datumPrikazivanjaInput');
     var vremePrikazivanjaInput = $('#vremePrikazivanjaInput');
     var cenaKarteInput = $('#cenaKarteInput');
     var adminInput = $('#adminInput');	
 	
 	function getProjekcija() {
 		$.get('ProjekcijaServlet', {'action': 'get'}, function(data) {
 			console.log(data);

 			if (data.status == 'unauthenticated') {
 				window.location.replace('login.html');
 				return;
 			}
 			
 			if (data.status == 'success') {
 				var projekcija1 = data.projekcija1;
 				console.log(projekcija1);
 				if (data.ulogovanKorisnikUloga == 'korisnik') {
 					console.log('ispis...');
 					$('#korisnikForm').show();
 					$('#adminForm').hide();
 					
 					
 					$('#filmCell').text(projekcija1.film);
 					$('#tipProjekcijeCell').text(projekcija1.tipProjekcije);
 					$('#salaCell').text(projekcija1.sala);
 					$('#datumPrikazivanjaCell').text(projekcija1.datumPrikazivanja);
 					$('#vremePrikazivanjaCell').text(projekcija1.vremePrikazivanja);
 					$('#cenaKarteCell').text(projekcija1.cenaKarte);
 					$('#adminCell').text(projekcija1.admin);
 				} else if (data.ulogovanKorisnikUloga == 'admin') {
 					console.log('ispis...')
 					console.log(data);
 					$('#korisnikForm').hide()
 					$('#adminForm').show();

 					$('#updateSubmit').on('click', function(event) {
 						var id = idInput.val();
 						var film = filmInput.val();
 						var tipProjekcije = tipProjekcijeInput.val();
 						var sala = salaInput.val();
 						var datumPrikazivanja = datumPrikazivanjaInput.val();
 						var vremePrikazivanja = vremePrikazivanjaInput.val();
 						var cenaKarte = cenaKarteInput.val();
 						var admin = adminInput.val();
 						console.log('id: ' + id);
 						console.log('film: ' + film);
 						console.log('tipProjekcije: ' + tipProjekcije);
 						console.log('sala: ' + sala);
 						console.log('datumPrikazivanja: ' + datumPrikazivanja);
 						console.log('vremePrikazivanja: ' + vremePrikazivanja);
 						console.log('cenaKarte: ' + cenaKarte);
 						console.log('admin: ' + admin);

 						params = {
 							'action': 'update',
 							'id': id, 
 							'film': film,
 							'tipProjekcije': tipProjekcije,
 							'sala': sala,
 							'datumPrikazivanja': datumPrikazivanja,
 							'vremePrikazivanja': vremePrikazivanja,
 							'cenaKarte': cenaKarte,
 							'admin': admin
 						};
 						console.log(params);
 						$.post('ProjekcijaServlet', params, function(data) {
 							if (data.status == 'unauthenticated') {
 								window.location.replace('login.html');
 								return;
 							}

 							if (data.status == 'success') {
 								window.location.replace('projekcijeadmin.html');
 								return;
 							}
 						});

 						event.preventDefault();
 						return false;
 					});

 				}
 			}
 		});
 	}
 	
 	getProjekcija();
});