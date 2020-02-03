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
	 var filmCell = $('#filmCell');
	 var tipProjekcijeCell = $('#tipProjekcijeCell');
     var salaCell = $('#salaCell');
     var datumPrikazivanjaCell = $('#datumPrikazivanjaCell');
     var vremePrikazivanjaCell = $('#vremePrikazivanjaCell');
     var cenaKarteCell = $('#cenaKarteCell');
	
	
 	
 	function getProjekcija() {
 	 	$.post('ProjekcijaZaKorisnikaServlet', {'action': 'get', 'id' : idCell.val() }, function(data) {
 			console.log(data);

 			if (data.status == 'unauthenticated') {
 				window.location.replace('login.html');
 				return;
 			}
 			
 			if (data.status == 'success') {
 				var projekcija1 = data.projekcija1;
 				console.log(projekcija1);
 				console.log('ispis...');
 				$('#korisnikForm').show();
 			
 				
 				$('#idCell').val(projekcija1.id);
 				$('#filmCell').val(projekcija1.film);
 				$('#tipProjekcijeCell').val(projekcija1.tipProjekcije);
 				$('#salaCell').val(projekcija1.sala);
 				$('#datumPrikazivanjaCell').val(projekcija1.datumPrikazivanja);
 				$('#vremePrikazivanjaCell').val(projekcija1.vremePrikazivanja);
 				$('#cenaKarteCell').val(projekcija1.cenaKarte);
 			}
 		});
 	}
 	
 	getProjekcija();
});