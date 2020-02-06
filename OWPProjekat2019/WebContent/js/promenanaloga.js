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
	 var korisnickoImeInput = $('#korisnickoImeInput');
	 var lozinkaInput = $('#lozinkaInput');
     var datumRegInput = $('#datumRegInput');
     var ulogaInput = $('#ulogaInput');
	
 	
 	function getKorisnik() {
 		$.get('PromenaNalogaServlet', {'action': 'update'}, function(data) {
 			console.log(data);

 			if (data.status == 'unauthenticated') {
 				window.location.replace('login.html');
 				return;
 			}
 			
 			if (data.status == 'success') {
 				var korisnik1 = data.korisnik1;
 				console.log(korisnik1);
 				console.log('success');

 				$('#updateSubmit').on('click', function(event) {
 					var id = idInput.val();
 					var korisnickoIme = korisnickoImeInput.val();
 					var lozinka = lozinkaInput.val();
 					var datumReg = datumRegInput.val();
 					var uloga = ulogaInput.val();
 					console.log('id: ' + id);
 					console.log('korisnickoIme: ' + korisnickoIme);
 					console.log('lozinka: ' + lozinka);
 					console.log('datumReg: ' + datumReg);
 					console.log('uloga: ' + uloga);

 						
 					params = {
 						'action': 'update',
 						'id': id, 
 						'korisnickoIme': korisnickoIme,
 						'lozinka': lozinka,
 						'datumReg': datumReg,
 						'uloga': uloga
 						
 					};
 					console.log(params);
 					$.post('PromenaNalogaServlet', params, function(data) {
 						if (data.status == 'unauthenticated') {
 							window.location.replace('login.html');
 							return;
 						}

 						if (data.status == 'success') {
 							window.location.replace('profilkorisnika.html');
 							return;
 						}
 					});

 					event.preventDefault();
 					return false;
 				});
 		}
 	});
 }
 	
 getKorisnik();
});