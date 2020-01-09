var korisnici = []
var sortKorisnickoImeSmer = 1;
var sortUlogaSmer = 1;
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
	
	
	var korisnikInput = $('#korisnikInput');
	
	var putniciTable = $('#korisnikTable');

	function getKorisnici(){
		var korisnik = korisnikInput.val();
		
		console.log('korisnik: ' + korisnik);

		var params = {
			'korisnik' : korisnik

		};
		$.get('KorisnikServlet', params, function(data){
			console.log(data);
			
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}
			if(data.status == 'success') {
				korisnici = data.korisnik;
				
				popuniTabelu(korisnici);
			}	
		});
	};
	korisnikInput.on('keyup', function(event){
		getKorisnici();
		
		event.preventDefault();
		return false;
	});
	korisnikTable.on('click', 'input.deleteSubmit', function(event){
		alert('Brisem...');
		var korisnikID = $(this).attr('korisnikID');
		console.log('korisnikID: ' + korisnikID);
		
		params = {
				'action': 'delete',
				'id': korisnikID	
		};
		console.log(params);
		$.post('KorisnikServlet', params, function(data){
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('korisnici.html');
				return;
			}
		});
		event.preventDefault();
		return false;
	});
	
	function popuniTabelu(korisniciZaTabelu){
		putniciTable.find('tr:gt(1)').remove();

		console.log(korisniciZaTabelu);
		for(it of korisniciZaTabelu){
			putniciTable.append(
					'<tr>' + 
					'<td><a href="korisnici.html?id=' + it.id + '">' + it.id + '</a></td>' +
					'<td><a href="korisnik.html?id=' + it.korisnickoIme + '">' + it.korisnickoIme + '</td>' +
					'<td>' + new Date(it.datumReg) + '</td>' + 
					'<td>' + it.uloga + '</td>' +
					'<td>' + 
						'<form>' + 
							'<input type="submit" value="Obrisi" class="deleteSubmit" korisnikID="' + it.id + '">' + 
						'</form>' +
					'</td>' + 
				'</tr>'	
			)
		}
	};
	
	$('#sortKorisnickoIme').on('click', function(event){
		alert('Sortiram...');
		sortiraj('korisnickoIme');
	});
	
	$('#sortUloga').on('click', function(event){
		alert('Sortiram...');
		sortiraj('uloga');
	});
	
	function sortiraj(sort){
		let sortiraniKorisnici = korisnici;

		for(let i = 0; i < sortiraniKorisnici.length - 1; i++){
			for(let j = i+1; j < sortiraniKorisnici.length; j++){
				if (sort === 'korisnickoIme'){
					if (sortKorisnickoImeSmer == 1){
						if (sortiraniKorisnici[i].korisnickoIme > sortiraniKorisnici[j].korisnickoIme){
							let temp = sortiraniKorisnici[i];
							sortiraniKorisnici[i] = sortiraniKorisnici[j];
							sortiraniKorisnici[j] = temp;
						}
					} else {
						if (sortiraniKorisnici[i].korisnickoIme < sortiraniKorisnici[j].korisnickoIme){
							let temp = sortiraniKorisnici[i];
							sortiraniKorisnici[i] = sortiraniKorisnici[j];
							sortiraniKorisnici[j] = temp;
						}
					}
				} else if (sort === 'uloga'){
					if (sortUlogaSmer == 1){
						if(sortiraniKorisnici[i].uloga > sortiraniKorisnici[j].uloga){
							let temp = sortiraniKorisnici[i];
							sortiraniKorisnici[i] = sortiraniKorisnici[j];
							sortiraniKorisnici[j] = temp;
						}
					} else {
						if(sortiraniKorisnici[i].uloga < sortiraniKorisnici[j].uloga){
							let temp = sortiraniKorisnici[i];
							sortiraniKorisnici[i] = sortiraniKorisnici[j];
							sortiraniKorisnici[j] = temp;
						}
					}
				}
			}
		}
		if (sort === 'korisnickoIme'){
			sortKorisnickoImeSmer = -1 * sortKorisnickoImeSmer;
		}else if (sort === 'uloga'){
			sortUlogaSmer = -1 * sortUlogaSmer;
		}

		popuniTabelu(sortiraniKorisnici);
	};
	
	getKorisnici();
});