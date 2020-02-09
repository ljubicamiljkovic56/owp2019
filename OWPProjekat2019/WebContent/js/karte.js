var karte = []
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
	
	var filterProjekcijaInput = $('#filterProjekcijaInput');
	var filterSedisteVInput = $('#filterSedisteVInput');
	var filterSedisteNInput = $('#filterSedisteNInput');
	var filterDatumProdajeInput = $('#filterDatumProdajeInput');
	var filterVremeProdajeInput = $('#filterVremeProdajeInput');
	var filterKorisnikInput = $('#filterKorisnikInput');
	
	var karteTable = $('#karteTable');

	function getKarte(){
		var filterProjekcija = filterProjekcijaInput.val();
		var filterSedisteV = filterSedisteVInput.val();
		var filterSedisteN = filterSedisteNInput.val();
		var filterDatumProdaje = filterDatumProdajeInput.val();
		var filterVremeProdaje = filterVremeProdajeInput.val();
		var filterKorisnik = filterKorisnikInput.val();
		console.log('filterProjekcija: ' + filterProjekcija);
		console.log('filterSedisteV:' + filterSedisteV);
		console.log('filterSedisteN:' + filterSedisteN);
		console.log('filterDatumProdaje: ' + filterDatumProdaje);
		console.log('filterVremeProdaje: ' + filterVremeProdaje);
		console.log('filterKorisnik: ' + filterKorisnik);

		var params = {
			'filterProjekcija ' : filterProjekcija,
			'filterSedsiteV': filterSedisteV,
			'filterSedisteN': filterSedisteN,
			'filterDatumProdaje ' : filterDatumProdaje,
			'filterVremeProdaje' : filterVremeProdaje,
			'filterKorisnik ' : filterKorisnik

		};
		$.get('KartaServlet', params, function(data){
			console.log(data);
			
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}
			if(data.status == 'success') {
				karte = data.filterKarte;
				
				popuniTabelu(karte);
			}	
		});
	};
	filterProjekcijaInput.on('keyup', function(event){
		getKarte();
		
		event.preventDefault();
		return false;
	});
	filterSedisteVInput.on('keyup', function(event){
		getKarte();
		
		event.preventDefault();
		return false;
	});
	filterSedisteNInput.on('keyup', function(event){
		getKarte();
		
		event.preventDefault();
		return false;
	});
	filterDatumProdajeInput.on('keyup', function(event){
		getKarte();
		
		event.preventDefault();
		return false;
	});
	filterVremeProdajeInput.on('keyup', function(event){
		getKarte();
		
		event.preventDefault();
		return false;
	});
	
	filterKorisnikInput.on('keyup', function(event){
		getKarte();
		
		event.preventDefault();
		return false;
	});
	
	karteTable.on('click', 'input.deleteSubmit', function(event){
		alert('Brisem...');
		var kartaID = $(this).attr('kartaID');
		console.log('kartaID: ' + kartaID);
		
		params = {
				'action': 'delete',
				'id': kartaID	
		};
		console.log(params);
		$.post('KartaServlet', params, function(data){
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('karte.html');
				return;
			}
		});
		event.preventDefault();
		return false;
	});
	
	function popuniTabelu(karteZaTabelu){
		karteTable.find('tr:gt(1)').remove();

		console.log(karteZaTabelu);
		for(it of karteZaTabelu){
			karteTable.append(
					'<tr>' + 
					'<td><a href="projekcijafilmzakorisnika.html?film=' + it.film + '&id=' + it.id +  '">' + it.projekcija + '</td>' +
					'<td>' + it.sediste + '</td>' +
					'<td><a href="pojedinacnakarta.html?projekcija' + it.projekcija + '&id=' + it.id + '">' + new Date(it.datumProdaje) + '</td>' + 
					'<td><a href="pojedinacnakarta.html?projekcija' + it.projekcija + '&id=' + it.id + '">' + it.vremeProdaje + '</td>' +
					'<td><a href="profilkorisnikaadmin.html?korisnickoIme' + it.korisnickoIme + '&id=' + it.id + '">' + it.korisnik + '</td>' +
					'<td>' + 
						'<form>' + 
							'<input type="submit" value="Obrisi" class="deleteSubmit" kartaID="' + it.id + '">' + 
						'</form>' +
					'</td>' + 
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
	
	getKarte();
});