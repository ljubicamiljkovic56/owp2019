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
	 var projekcijaCell = $('#projekcijaCell');
	 var sedisteCell = $('#sedisteCell');
     var datumProdajeCell = $('#datumProdajeCell');
     var vremeProdajeCell = $('#vremeProdajeCell');
     var korisnikCell = $('#korisnikCell');
 	
 	function getKarta() {
 	 	$.post('KorisnikovaKartaServlet', {'action': 'get', 'id' : idCell.val() }, function(data) {
 			console.log(data);

 			if (data.status == 'unauthenticated') {
 				window.location.replace('login.html');
 				return;
 			}
 			
 			if (data.status == 'success') {
 				var karta1 = data.karta1;
 				console.log(karta1);
 				console.log('ispis...');
 				$('#korisnikForm').show();
 			
 				
 				$('#idCell').val(karta1.id);
 				$('#projekcijaCell').val(karta1.projekcija);
 				$('#sedisteCell').val(karta1.sediste);
 				$('#datumProdajeCell').val(karta1.datumProdaje);
 				$('#vremeProdajeCell').val(karta1.vremeProdaje);
 				$('#korisnikCell').val(karta1.korisnik);
 			}
 		});
 	}
 	
 	getKarta();
});