var projekcije = []
var sortFilmSmer = 1;
var sortTipProjekcijeSmer = 1;
var sortSalaSmer = 1;
var sortDatumIVremePrikazivanjaSmer = 1;
var sortCenaKarteSmer = 1;
var sortAdminSmer = 1;

$(document).ready(function(){
	var filterFilmInput = $('#filterFilmInput');
	var filterTipProjekcijeInput = $('#filterTipProjekcijeInput');
	var filterSalaInput = $('#filterSalaInput');
	var filterDatumIVremePrikazivanjaInput = $('#filterDatumIVremePrikazivanjaInput');
	var filterCenaKarteInput = $('#filterCenaKarteInput');
	var filterAdminInput = $('#filterAdminInput');


	var projekcijaTable = $('#projekcijaTable');
	
	function getProjekcije(){
		var filterFilm = filterFilmInput.val();
		var filterTipProjekcije = filterTipProjekcijeInput.val();
		var filterSala = filterSalaInput.val();
		var filterDatumIVremePrikazivanja = filterDatumIVremePrikazivanjaInput.val();
		var filterCenaKarte = filterCenaKarteInput.val();
		var filterAdmin = filterAdminInput.val();
		console.log('filterFilm: ' + filterFilm);
		console.log('filterTipProjekcije: ' + filterTipProjekcije);
		console.log('filterSala: ' + filterSala);
		console.log('filterDatumIVremePrikazivanja: ' + filterDatumIVremePrikazivanja);
		console.log('filterCenaKarte: ' + filterCenaKarte);
		console.log('filterAdmin: ' + filterAdmin);
		
		var params = {
			'filterFilm': filterFilm,
			'filterTipProjekcije': filterTipProjekcije,
			'filterSala': filterSala,
			'filterDatumIVremePrikazivanja': filterDatumIVremePrikazivanja,
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
	filterDatumIVremePrikazivanjaInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});
//	filterVremePrikazivanjaInput.on('keyup', function(event){
//		getProjekcije();
//		
//		event.preventDefault();
//		return false;
//	});

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
					'<td><a href="izmenifilm.html?id=' + it.film + '">' + it.film + '</td>' +
					'<td>' + it.tipProjekcije + '</td>' +
					'<td>' + it.sala + '</td>' +
					'<td>' + new Date(it.datumIVremePrikazivanja) + '</td>' +
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
	
	$('#sortDatumIVremePrikazivanja').on('click', function(event){
		alert('Sortiram...');
		sortiraj('datumPrikazivanja');
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
				} else if (sort === 'datumIVremePrikazivanja'){
					if (sortDatumIVremePrikazivanjaSmer == 1){
						if (sortiraneProjekcije[i].datumIVremePrikazivanja > sortiraneProjekcije[j].datumIVremePrikazivanja){
							let temp = sortiraneProjekcije[i];
							sortiraneProjekcije[i] = sortiraneProjekcije[j];
							sortiraneProjekcije[j] = temp;
						}
					} else {
						if (sortiraneProjekcije[i].datumIVremePrikazivanja < sortiraneProjekcije[j].datumIVremePrikazivanja){
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
		}else if (sort === 'datumIVremePrikazivanja'){
			sortDatumIVremePrikazivanjaSmer = -1 * sortDatumIVremePrikazivanjaSmer;
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