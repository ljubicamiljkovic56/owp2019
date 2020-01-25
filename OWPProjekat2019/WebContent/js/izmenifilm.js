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
	 var nazivInput = $('#nazivInput');
	 var reziserInput = $('#reziserInput');
     var glumciInput = $('#glumciInput');
     var zanroviInput = $('#zanroviInput');
     var trajanjeInput = $('#trajanjeInput');
     var distributerInput = $('#distributerInput');
     var zemljaPoreklaInput = $('#zemljaPoreklaInput');
     var godinaProizvodnjeInput = $('#godinaProizvodnjeInput');
     var opisInput = $('#opisInput');
	
	
 	
 	function getFilm() {
 		$.get('FilmServlet', {'action': 'get'}, function(data) {
 			console.log(data);

 			if (data.status == 'unauthenticated') {
 				window.location.replace('login.html');
 				return;
 			}
 			
 			if (data.status == 'success') {
 				var film1 = data.film1;
 				console.log(film1);
 				if (data.ulogovanKorisnikUloga == 'korisnik') {
 					console.log('ispis...');
 					$('#korisnikForm').show();
 					$('#adminForm').hide();
 					
 					
 					$('#nazivCell').text(film1.naziv);
 					$('#reziserCell').text(film1.reziser);
 					$('#glumciCell').text(film1.glumci);
 					$('#zanroviCell').text(film1.zanrovi);
 					$('#trajanjeCell').text(film1.trajanje);
 					$('#distributerCell').text(film1.distributer);
 					$('#zemljaPoreklaCell').text(film1.zemljaPorekla);
 					$('#godinaProizvodnjeCell').text(film1.godinaProizvodnje);
 					$('#opisCell').text(film1.opis);
 				} else if (data.ulogovanKorisnikUloga == 'admin') {
 					console.log('ispis...')
 					console.log(data);
 					$('#korisnikForm').hide()
 					$('#adminForm').show();

 					$('#updateSubmit').on('click', function(event) {
 						var id = idInput.val();
 						var naziv = nazivInput.val();
 						var reziser = reziserInput.val();
 						var glumci = glumciInput.val();
 						var zanrovi = zanroviInput.val();
 						var trajanje = trajanjeInput.val();
 						var distributer = distributerInput.val();
 						var zemljaPorekla = zemljaPoreklaInput.val();
 						var godinaProizvodnje = godinaProizvodnjeInput.val();
 						var opis = opisInput.val();
 						console.log('id: ' + id);
 						console.log('naziv: ' + naziv);
 						console.log('reziser: ' + reziser);
 						console.log('glumci: ' + glumci);
 						console.log('zanrovi: ' + zanrovi);
 						console.log('trajanje: ' + trajanje);
 						console.log('distributer: ' + distributer);
 						console.log('zemljaPorekla: ' + zemljaPorekla);
 						console.log('godinaProizvodnje: ' + godinaProizvodnje);
 						console.log('opis: ' + opis);

 						params = {
 							'action': 'update',
 							'id': id, 
 							'naziv': naziv,
 							'reziser': reziser,
 							'glumci': glumci,
 							'zanrovi': zanrovi,
 							'trajanje': trajanje,
 							'distributer': distributer,
 							'zemljaPorekla': zemljaPorekla,
 							'godinaProizvodnje': godinaProizvodnje,
 							'opis': opis
 						};
 						console.log(params);
 						$.post('FilmServlet', params, function(data) {
 							if (data.status == 'unauthenticated') {
 								window.location.replace('login.html');
 								return;
 							}

 							if (data.status == 'success') {
 								window.location.replace('aplikacija.html');
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
 	
 	getFilm();
});