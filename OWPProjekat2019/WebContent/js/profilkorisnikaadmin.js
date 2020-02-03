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
	 var korisnickoImeCell = $('#nazivCell');
	 var lozinkaCell = $('#reziserCell');
     var datumRegCell = $('#glumciCell');
     var ulogaCell = $('#zanroviCell');
	
 	
 	function getKorisnik() {
 	 	$.post('ProfilKorisnikaAdminServlet', {'action': 'get', 'id' : idCell.val() }, function(data) {
 			console.log(data);

 			if (data.status == 'unauthenticated') {
 				window.location.replace('login.html');
 				return;
 			}
 			
 			if (data.status == 'success') {
 				var korisnik1 = data.korisnik1;
 				console.log(korisnik1);
 				console.log('ispis...');
 				$('#korisnikForm').show();
 			
 				
 				$('#idCell').val(korisnik1.id);
 				$('#korisnickoImeCell').val(korisnik1.korisnickoIme);
 				$('#lozinkaCell').val(korisnik1.lozinka);
 				$('#datumRegCell').val(korisnik1.datumReg);
 				$('#ulogaCell').val(korisnik1.uloga);
 			}
 		});
 	}
 	
 	getKorisnik();
});