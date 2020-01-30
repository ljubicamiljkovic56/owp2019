$(document).ready(function(){
	var urlParams = new URLSearchParams(window.location.search);
	$('#idCell').val(urlParams.get('id'));
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
	 var idCell = $('#idCell');
	 var nazivCell = $('#nazivCell');
	 var reziserCell = $('#reziserCell');
     var glumciCell = $('#glumciCell');
     var zanroviCell = $('#zanroviCell');
     var trajanjeCell = $('#trajanjeCell');
     var distributerCell = $('#distributerCell');
     var zemljaPoreklaCell = $('#zemljaPoreklaCell');
     var godinaProizvodnjeCell = $('#godinaProizvodnjeCell');
     var opisCell = $('#opisCell');
	
	
 	
 	function getFilm() {
 	 	$.post('FilmZaKorisnikaServlet', {'action': 'get', 'id' : idCell.val() }, function(data) {
 			console.log(data);

 			if (data.status == 'unauthenticated') {
 				window.location.replace('login.html');
 				return;
 			}
 			
 			if (data.status == 'success') {
 				var film1 = data.film1;
 				console.log(film1);
 				console.log('ispis...');
 				$('#korisnikForm').show();
 			
 				
 				$('#idCell').val(film1.id);
 				$('#nazivCell').val(film1.naziv);
 				$('#reziserCell').val(film1.reziser);
 				$('#glumciCell').val(film1.glumci);
 				$('#zanroviCell').val(film1.zanrovi);
 				$('#trajanjeCell').val(film1.trajanje);
 				$('#distributerCell').val(film1.distributer);
 				$('#zemljaPoreklaCell').val(film1.zemljaPorekla);
 				$('#godinaProizvodnjeCell').val(film1.godinaProizvodnje);
 				$('#opisCell').val(film1.opis);
 			}
 		});
 	}
 	
 	getFilm();
});