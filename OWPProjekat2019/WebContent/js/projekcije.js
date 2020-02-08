var projekcije = []
var sortFilmSmer = 1;
var sortTipProjekcijeSmer = 1;
var sortSalaSmer = 1;
var sortDatumPrikazivanjaSmer = 1;
var sortVremePrikazivanjaSmer = 1;
var sortCenaKarteSmer = 1;

$(document).ready(function(){
	var filterFilmInput = $('#filterFilmInput');
	var filterTipProjekcijeInput = $('#filterTipProjekcijeInput');
	var filterSalaInput = $('#filterSalaInput');
	var filterDatumPrikazivanjaInput = $('#filterDatumPrikazivanjaInput');
//	var filterDatumPrikazivanjaNInput = $('#filterDatumPrikazivanjaNInput');
	var filterVremePrikazivanjaInput = $('#filterVremePrikazivanjaInput');
//	var filterVremePrikazivanjaNInput = $('#filterVremePrikazivanjaNInput');
	var filterCenaKarteVInput = $('#filterCenaKarteVInput');
	var filterCenaKarteNInput = $('#filterCenaKarteNInput');


	var projekcijaTable = $('#projekcijaTable');
	
	function getProjekcije(){
		var filterFilm = filterFilmInput.val();
		var filterTipProjekcije = filterTipProjekcijeInput.val();
		var filterSala = filterSalaInput.val();
		var filterDatumPrikazivanja = filterDatumPrikazivanjaInput.val();
		//var filterDatumPrikazivanjaN = filterDatumPrikazivanjaNInput.val();
		var filterVremePrikazivanja = filterVremePrikazivanjaInput.val();
		//var filterVremePrikazivanjaN = filterVremePrikazivanjaNInput.val();
		var filterCenaKarteV = filterCenaKarteVInput.val();
		var filterCenaKarteN = filterCenaKarteNInput.val();
		console.log('filterFilm: ' + filterFilm);
		console.log('filterTipProjekcije: ' + filterTipProjekcije);
		console.log('filterSala: ' + filterSala);
		console.log('filterDatumPrikazivanja: ' + filterDatumPrikazivanja);
	//	console.log('filterDatumPrikazivanjaN: ' + filterDatumPrikazivanjaN);
		console.log('filterVremePrikazivanja: ' + filterVremePrikazivanja);
		//console.log('filterVremePrikazivanjaN: ' + filterVremePrikazivanjaN);
		console.log('filterCenaKarteV: ' + filterCenaKarteV);
		console.log('filterCenaKarteN: ' + filterCenaKarteN);
		
		var params = {
			'filterFilm': filterFilm,
			'filterTipProjekcije': filterTipProjekcije,
			'filterSala': filterSala,
			'filterDatumPrikazivanja': filterDatumPrikazivanja,
			//'filterDatumPrikazivanjaN': filterDatumPrikazivanjaN,
			'filterVremePrikazivanja': filterVremePrikazivanja,
			//'filterVremePrikazivanjaN': filterVremePrikazivanjaN,
		    'filterCenaKarteV': filterCenaKarteV,
		    'filterCenaKarteN': filterCenaKarteN

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
//	filterDatumPrikazivanjaNInput.on('keyup', function(event){
//		getProjekcije();
//		
//		event.preventDefault();
//		return false;
//	});
	filterVremePrikazivanjaInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});
//	filterVremePrikazivanjaNInput.on('keyup', function(event){
//		getProjekcije();
//		
//		event.preventDefault();
//		return false;
//	});

	filterCenaKarteVInput.on('keyup', function(event){
		getProjekcije();
		
		event.preventDefault();
		return false;
	});
	
	filterCenaKarteNInput.on('keyup', function(event){
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
					'<td><a href="projekcijafilmzakorisnika.html?film' + it.film + '&id=' + it.id + '">' + it.film + '</td>' +
					'<td>' + it.tipProjekcije + '</td>' +
					'<td>' + it.sala + '</td>' +
					'<td><a href="projekcijazakorisnika.html?film' + it.film + '&id=' + it.id + '">' + new Date(it.datumPrikazivanja) + '</td>' +
					'<td><a href="projekcijazakorisnika.html?film' + it.film + '&id=' + it.id + '">' + it.vremePrikazivanja + '</td>' +
					'<td>' + it.cenaKarte + '</td>' +
					'<td>' + 
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
				}else if (sort === 'vremePrikazivanja'){
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
		}

		popuniTabelu(sortiraneProjekcije);
	};
	getProjekcije();
	console.log('dobavljene projekcije?');
});