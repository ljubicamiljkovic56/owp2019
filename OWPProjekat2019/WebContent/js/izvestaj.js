var izvestaj = []
var sortNazivFilmaSmer = 1;
var sortBrojProjekcijaSmer = 1;
var sortBrojKarataSmer = 1;
var sortUkupnaCenaKarataSmer = 1;
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
	
	
	var filterNazivFilmaInput = $('#filterNazivFilmaInput');
	var filterBrojProjekcijaInput = $('#filterBrojProjekcijaInput');
	var filterBrojKarataInput = $('#filterBrojKarataInput');
	var filterUkupnaCenaKarataInput = $('#filterUkupnaCenaKarataInput');
	
	var izvestajTable = $('#izvestajTable');

	function getIzvestaj(){
		var filterNazivFilma = filterNazivFilmaInput.val();
		var filterBrojProjekcija = filterBrojProjekcijaInput.val();
		var filterBrojKarata = filterBrojKarataInput.val();
		var filterUkupnaCenaKarata = filterUkupnaCenaKarataInput.val();
		console.log('filterNazivFilma: ' + filterNazivFilma);
		console.log('filterBrojProjekcija:' + filterBrojProjekcija);
		console.log('filterBrojKarata: ' + filterBrojKarata);
		console.log('filterUkupnaCenaKarata: ' + filterUkupnaCenaKarata);

		var params = {
			'filterNazivFilma ' : filterNazivFilma,
			'filterBrojProjekcija ' : filterBrojProjekcija,
			'filterBrojKarata ' : filterBrojKarata,
			'filterUkupnaCenaKarata ' : filterUkupnaCenaKarata

		};
		$.get('IzvestajServlet', params, function(data){
			console.log(data);
			
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}
			if(data.status == 'success') {
				izvestaj = data.filterIzvestaj;
				
				popuniTabelu(izvestaj);
			}	
		});
	};
	filterNazivFilmaInput.on('keyup', function(event){
		getIzvestaj();
		
		event.preventDefault();
		return false;
	});
	filterBrojProjekcijaInput.on('keyup', function(event){
		getIzvestaj();
		
		event.preventDefault();
		return false;
	});
	filterBrojKarataInput.on('keyup', function(event){
		getIzvestaj();
		
		event.preventDefault();
		return false;
	});
	filterUkupnaCenaKarataInput.on('keyup', function(event){
		getIzvestaj();
		
		event.preventDefault();
		return false;
	});
	
	function popuniTabelu(izvestajiZaTabelu){
		izvestajTable.find('tr:gt(1)').remove();

		console.log(izvestajiZaTabelu);
		for(it of izvestajiZaTabelu){
			izvestajTable.append(
					'<tr>' + 
					'<td>' + it.nazivFilma  + '</td>' +
					'<td>' + it.brojProjekcija + '</td>' +
					'<td>' + it.brojKarata + '</td>' + 
					'<td>' + it.ukupnaCenaKarata + '</td>' +
					'<td>' + 
					'</td>' + 
				'</tr>'	
			)
		}
	};
	
	$('#sortNazivFilma').on('click', function(event){
		alert('Sortiram...');
		sortiraj('nazivFilma');
	});
	
	$('#sortBrojProjekcija').on('click', function(event){
		alert('Sortiram...');
		sortiraj('brojProjekcija');
	});
	
	$('#sortBrojKarata').on('click', function(event){
		alert('Sortiram...');
		sortiraj('brojKarata');
	});
	$('#sortUkupnaCenaKarata').on('click', function(event){
		alert('Sortiram...');
		sortiraj('ukupnaCenaKarata');
	});
	
	
	function sortiraj(sort){
		let sortiraniIzvestaj = izvestaj;

		for(let i = 0; i < sortiraniIzvestaj.length - 1; i++){
			for(let j = i+1; j < sortiraniIzvestaj.length; j++){
				if (sort === 'nazivFilma'){
					if (sortNazivFilmaSmer == 1){
						if (sortiraniIzvestaj[i].nazivFilma > sortiraniIzvestaj[j].nazivFilma){
							let temp = sortiraniIzvestaj[i];
							sortiraniIzvestaj[i] = sortiraniIzvestaj[j];
							sortiraniIzvestaj[j] = temp;
						}
					} else {
						if (sortiraniIzvestaj[i].nazivFilma < sortiraniIzvestaj[j].nazivFilma){
							let temp = sortiraniIzvestaj[i];
							sortiraniIzvestaj[i] = sortiraniIzvestaj[j];
							sortiraniIzvestaj[j] = temp;
						}
					}
				} else if (sort === 'brojProjekcija'){
					if (sortBrojProjekcijaSmer == 1){
						if(sortiraniIzvestaj[i].brojProjekcija > sortiraniIzvestaj[j].brojProjekcija){
							let temp = sortiraniIzvestaj[i];
							sortiraniIzvestaj[i] = sortiraniIzvestaj[j];
							sortiraniIzvestaj[j] = temp;
						}
					} else {
						if(sortiraniIzvestaj[i].brojProjekcija < sortiraniIzvestaj[j].brojProjekcija){
							let temp = sortiraniIzvestaj[i];
							sortiraniIzvestaj[i] = sortiraniIzvestaj[j];
							sortiraniIzvestaj[j] = temp;
						}
					}
					
				} else if (sort === 'brojKarata'){
					if (sortBrojKarataSmer == 1){
						if(sortiraniIzvestaj[i].brojKarata > sortiraniIzvestaj[j].brojKarata){
							let temp = sortiraniIzvestaj[i];
							sortiraniIzvestaj[i] = sortiraniIzvestaj[j];
							sortiraniIzvestaj[j] = temp;
						}
					} else {
						if(sortiraniIzvestaj[i].brojKarata < sortiraniIzvestaj[j].brojKarata){
							let temp = sortiraniIzvestaj[i];
							sortiraniIzvestaj[i] = sortiraniIzvestaj[j];
							sortiraniIzvestaj[j] = temp;
						}
					}
				} else if (sort === 'ukupnaCenaKarata'){
					if (sortUkupnaCenaKarataSmer == 1){
						if(sortiraniIzvestaj[i].ukupnaCenaKarata > sortiraniIzvestaj[j].ukupnaCenaKarata){
							let temp = sortiraniIzvestaj[i];
							sortiraniIzvestaj[i] = sortiraniIzvestaj[j];
							sortiraniIzvestaj[j] = temp;
						}
					} else {
						if(sortiraniIzvestaj[i].ukupnaCenaKarata < sortiraniIzvestaj[j].ukupnaCenaKarata){
							let temp = sortiraniIzvestaj[i];
							sortiraniIzvestaj[i] = sortiraniIzvestaj[j];
							sortiraniIzvestaj[j] = temp;
						}
					}
				}
			}
		}
		if (sort === 'nazivFilma'){
			sortNazivFilmaSmer = -1 * sortNazivFilmaSmer;
		}else if (sort === 'brojProjekcija'){
			sortBrojProjekcijaSmer = -1 * sortBrojProjekcijaSmer;
		}else if (sort === 'karte'){
			sortBrojKarataSmer = -1 * sortBrojKarataSmer;
		}else if (sort === 'ukupnaCenaKarata'){
			sortUkupnaCenaKarataSmer = -1 * sortUkupnaCenaKarataSmer;
		}

		popuniTabelu(sortiraniIzvestaj);
	};
	
	getIzvestaj();
});