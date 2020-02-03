var projekcije = []
var sortFilmSmer = 1;
var sortTipProjekcijeSmer = 1;
var sortSalaSmer = 1;
var sortDatumPrikazivanjaSmer = 1;
var sortVremePrikazivanjaSmer = 1;
var sortCenaKarteSmer = 1;
var sortAdminSmer = 1;

$(document).ready(function(){
	var filterFilmInput = $('#filterFilmInput');
	var filterTipProjekcijeInput = $('#filterTipProjekcijeInput');
	var filterSalaInput = $('#filterSalaInput');
	var filterDatumPrikazivanjaInput = $('#filterDatumPrikazivanjaInput');
	var filterVremePrikazivanjaInput = $('#filterVremePrikazivanjaInput');
	var filterCenaKarteInput = $('#filterCenaKarteInput');
	var filterAdminInput = $('#filterAdminInput');


	var projekcijaTable = $('#projekcijaTable');
	
	function getProjekcije(){
		var filterFilm = filterFilmInput.val();
		var filterTipProjekcije = filterTipProjekcijeInput.val();
		var filterSala = filterSalaInput.val();
		var filterDatumPrikazivanja = filterDatumPrikazivanjaInput.val();
		var filterVremePrikazivanja = filterVremePrikazivanjaInput.val();
		var filterCenaKarte = filterCenaKarteInput.val();
		var filterAdmin = filterAdminInput.val();
		console.log('filterFilm: ' + filterFilm);
		console.log('filterTipProjekcije: ' + filterTipProjekcije);
		console.log('filterSala: ' + filterSala);
		console.log('filterDatumPrikazivanja: ' + filterDatumPrikazivanja);
		console.log('filterVremePrikazivanja: ' + filterVremePrikazivanja);
		console.log('filterCenaKarte: ' + filterCenaKarte);
		console.log('filterAdmin: ' + filterAdmin);
		
		var params = {
			'filterFilm': filterFilm,
			'filterTipProjekcije': filterTipProjekcije,
			'filterSala': filterSala,
			'filterDatumPrikazivanja': filterDatumPrikazivanja,
			'filterVremePrikazivanja': filterVremePrikazivanja,
		    'filterCenaKarte': filterCenaKarte,
		    'filterAdmin': filterAdmin

		};
		
		$.get('ProjekcijeServlet', params, function(data){
			console.log(data);
			
			if(data.status == 'success') {
				projekcije = data.filterProjekcije;
				
				popuniTabelu(projekcije);
				
			}	

		});
	}
	filterFilmInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});
	filterTipProjekcijeInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});
	filterSalaInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});
	filterDatumPrikazivanjaInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});
	filterVremePrikazivanjaInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});

	filterCenaKarteInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});
	filterAdminInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});

	function popuniTabelu(projekcijeZaTabelu){
		projekcijaTable.find('tr:gt(1)').remove();
		
		console.log('tabela?')
		
		console.log(projekcijeZaTabelu);
		for(it of projekcijeZaTabelu){
			projekcijaTable.append(
				'<tr>' + 
					'<td><a href="izmeniprojekciju.html?film=' + it.film + '&id=' + it.id +  '">' + it.film + '</td>' +
					'<td>' + it.tipProjekcije + '</td>' +
					'<td>' + it.sala + '</td>' +
					'<td><a href="projekcijazakorisnika.html?film' + it.film + '&id=' + it.id + '">' + new Date(it.datumPrikazivanja) + '</td>' +
					'<td><a href="projekcijazakorisnika.html?film' + it.film + '&id=' + it.id + '">' + it.vremePrikazivanja + '</td>' +
					'<td>' + it.cenaKarte + '</td>' +
					'<td>' + it.admin + '</td>' +
					'<td>' + 
					'<form>' + 
						'<input type="submit" value="Obrisi" class="deleteSubmit" projekcijaID="' + it.id + '">' + 
					'</form>' +
					'</td>' + 
				'</tr>'
			)
		}
	};
	
	projekcijaTable.on('click', 'input.deleteSubmit', function(event){
		alert('Brisem...');
		var projekcijaID = $(this).attr('projekcijaID');
		console.log('projekcijaID: ' + projekcijaID);
		
		params = {
				'action': 'delete',
				'id': projekcijaID
		};
		console.log(params);
		$.post('ProjekcijaServlet', params, function(data){
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('projekcijeadmin.html');
				return;
			}
		});
		event.preventDefault();
		return false;
	});
	
	$('#sortFilm').on('click', function(event){
		alert('Sortiram...');
		sortiraj('film');
	});
	
	$('#sortTipProjekcije').on('click', function(event){
		alert('Sortiram...');
		sortiraj('tipProjekcije');
	});
	$('#sortSala').on('click', function(event){
		alert('Sortiram...');
		sortiraj('sala');
	});
	
	$('#sortDatumPrikazivanja').on('click', function(event){
		alert('Sortiram...');
		sortiraj('datumPrikazivanja');
	});
	$('#sortVremePrikazivanja').on('click', function(event){
		alert('Sortiram...');
		sortiraj('vremePrikazivanja');
	});
	$('#sortCenaKarte').on('click', function(event){
		alert('Sortiram...');
		sortiraj('cenaKarte');
	});
	$('#sortAdmin').on('click', function(event){
		alert('Sortiram...');
		sortiraj('admin');
	});
	
	function sortiraj(sort){
		let sortiraneProjekcije = projekcije;

		for(let i = 0; i < sortiraneProjekcije.length - 1; i++){
			for(let j = i+1; j < sortiraneProjekcije.length; j++){
				if (sort === 'film'){
					if (sortFilmSmer == 1){
						if (sortiraneProjekcije[i].film > sortiraneProjekcije[j].film){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					} else {
						if (sortiraneProjekcije[i].film < sortiraneProjekcije[j].film){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					}
					
				} else if (sort === 'tipProjekcije'){
					if (sortTipProjekcijeSmer == 1){
						if(sortiraneProjekcije[i].tipProjekcije > sortiraneProjekcije[j].tipProjekcije){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraniProjekcije[j] = temp;
						}
					} else {
						if(sortiraneProjekcije[i].tipProjekcije < sortiraneProjekcije[j].tipProjekcije){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					}
				} else if (sort === 'sala'){
					if (sortSalaSmer == 1){
						if(sortiraneProjekcije[i].sala > sortiraneProjekcije[j].sala){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					} else {
						if(sortiraneProjekcije[i].sala < sortiraneProjekcije[j].sala){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					}
				} else if (sort === 'datumPrikazivanja'){
					if (sortDatumPrikazivanjaSmer == 1){
						if (sortiraneProjekcije[i].datumPrikazivanja > sortiraneProjekcije[j].datumPrikazivanja){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					} else {
						if (sortiraneProjekcije[i].datumPrikazivanja < sortiraneProjekcije[j].datumPrikazivanja){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					}

				} else if (sort === 'vremePrikazivanja'){
					if (sortVremePrikazivanjaSmer == 1){
						if (sortiraneProjekcije[i].vremePrikazivanja > sortiraneProjekcije[j].vremePrikazivanja){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					} else {
						if (sortiraneProjekcije[i].vremePrikazivanja < sortiraneProjekcije[j].vremePrikazivanja){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					}

				} else if (sort === 'cenaKarte'){
					if (sortCenaKarteSmer == 1){
						if(sortiraneProjekcije[i].cenaKarte > sortiraneProjekcije[j].cenaKarte){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					} else {
						if(sortiraneProjekcije[i].cenaKarte < sortiraneProjekcije[j].cenaKarte){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					}
				}
				else if (sort === 'admin'){
					if (sortAdminSmer == 1){
						if(sortiraneProjekcije[i].admin > sortiraneProjekcije[j].admin){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					} else {
						if(sortiraneProjekcije[i].admin < sortiraneProjekcije[j].admin){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					}
				}
			}
		}
		if (sort === 'film'){
			sortFilmSmer = -1 * sortFilmSmer;
		}else if (sort === 'tipProjekcije'){
			sortTipProjekcijeSmer = -1 * sortTipProjekcijeSmer;
		}else if (sort === 'sala'){
			sortSalaSmer = -1 * sortSalaSmer;
		}else if (sort === 'datumPrikazivanja'){
			sortDatumPrikazivanjaSmer = -1 * sortDatumPrikazivanjaSmer;
		}else if (sort === 'vremePrikazivanja'){
			sortVremePrikazivanjaSmer = -1 * sortVremePrikazivanjaSmer;
		}else if (sort === 'cenaKarte'){
			sortCenaKarteSmer = -1 * sortCenaKarteSmer;
		}else if (sort === 'admin'){
		sortAdminSmer = -1 * sortAdminSmer;
	}

		popuniTabelu(sortiraneProjekcije);
	};
	getProjekcije();
	console.log('dobavljene projekcije?');
});