var karta = []
var sortProjekcijaSmer = 1;
var sortSedisteSmer = 1;
var sortDatumProdajeSmer = 1;
var sortVremeProdajeSmer = 1;
var sortKorisnikSmer = 1;
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
	
	
	var kartaTable = $('#kartaTable');

	function getKarta(){
		$.post('KorisnikovaKartaServlet', {'action': 'get'},  function(data){
			console.log(data);
			
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}
			if(data.status == 'success') {
				karta = data.karta1;
				
				popuniTabelu(karta);
				
				console.log(karta);
				console.log('ispis...');
			}	
		});
	};
	function popuniTabelu(karteZaTabelu){
		kartaTable.find('tr:gt(1)').remove();

		console.log(karteZaTabelu);
		for(it of karteZaTabelu){
			kartaTable.append(
					'<tr>' + 
					'<td>' + it.projekcija + '</td>' +
					'<td>' + it.sediste + '</td>' +
					'<td><a href="pojedinacnakartakorisnik.html?projekcija' + it.projekcija + '&id=' + it.id + '">' + new Date(it.datumProdaje) + '</td>' + 
					'<td><a href="pojedinacnakartakorisnik.html?projekcija' + it.projekcija + '&id=' + it.id + '">' + it.vremeProdaje + '</td>' +
					'<td><a href="profilkorisnika.html?korisnickoIme' + it.korisnickoIme + '&id=' + it.id + '">' + it.korisnik + '</td>' +
				'</tr>'	
			)
		}
	};
	
	$('#sortProjekcija').on('click', function(event){
		alert('Sortiram...');
		sortiraj('projekcija');
	});
	
	$('#sortSediste').on('click', function(event){
		alert('Sortiram...');
		sortiraj('sediste');
	});
	
	$('#sortDatumProdaje').on('click', function(event){
		alert('Sortiram...');
		sortiraj('datumProdaje');
	});
	$('#sortVremeProdaje').on('click', function(event){
		alert('Sortiram...');
		sortiraj('vremeProdaje');
	});
	
	$('#sortKorisnik').on('click', function(event){
		alert('Sortiram...');
		sortiraj('korisnik');
	});
	
	function sortiraj(sort){
		let sortiraneKarte = karte;

		for(let i = 0; i < sortiraneKarte.length - 1; i++){
			for(let j = i+1; j < sortiraneKarte.length; j++){
				if (sort === 'projekcija'){
					if (sortProjekcijaSmer == 1){
						if (sortiraneKarte[i].projekcija > sortiraneKarte[j].projekcija){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					} else {
						if (sortiraneKarte[i].projekcija < sortiraneKarte[j].projekcija){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					}
				} else if (sort === 'sediste'){
					if (sortSedisteSmer == 1){
						if(sortiraneKarte[i].sediste > sortiraneKarte[j].sediste){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					} else {
						if(sortiraneKarte[i].sediste < sortiraneKarte[j].sediste){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					}
					
				} else if (sort === 'datumProdaje'){
					if (sortDatumProdajeSmer == 1){
						if(sortiraneKarte[i].datumProdaje > sortiraneKarte[j].datumProdaje){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					} else {
						if(sortiraneKarte[i].datumProdaje < sortiraneKarte[j].datumProdaje){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					}
				} else if (sort === 'vremeProdaje'){
					if (sortVremeProdajeSmer == 1){
						if(sortiraneKarte[i].vremeProdaje > sortiraneKarte[j].vremeProdaje){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					} else {
						if(sortiraneKarte[i].vremeProdaje < sortiraneKarte[j].vremeProdaje){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					}
				} else if (sort === 'korisnik'){
					if (sortKorisnikSmer == 1){
						if(sortiraneKarte[i].korisnik > sortiraneKarte[j].korisnik){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					} else {
						if(sortiraneKarte[i].korisnik < sortiraneKarte[j].korisnik){
							let temp = sortiraneKarte[i];
							sortiraneKarte[i] = sortiraneKarte[j];
							sortiraneKarte[j] = temp;
						}
					}
				}
			}
		}
		if (sort === 'projekcija'){
			sortProjekcijaSmer = -1 * sortProjekcijaSmer;
		}else if (sort === 'sediste'){
			sortSedisteSmer = -1 * sortSedisteSmer;
		}else if (sort === 'datumProdaje'){
			sortDatumProdajeSmer = -1 * sortDatumProdajeSmer;
		}else if (sort === 'vremeProdaje'){
			sortVremeProdajeSmer = -1 * sortVremeProdajeSmer;
		}else if (sort === 'korisnik'){
			sortKorisnikSmer = -1 * sortKorisnikSmer;
		}

		popuniTabelu(sortiraneKarte);
	};
	
	getKarta();
});