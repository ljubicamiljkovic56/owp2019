var filmovi = []
var sortNazivSmer = 1;
var sortZanroviSmer = 1;
var sortTrajanjeSmer = 1;
var sortDistributerSmer = 1;
var sortZemljaPoreklaSmer = 1;
var sortGodinaProizvodnjeSmer = 1;

$(document).ready(function(){
	var filterNazivInput = $('#filterNazivInput');
	var filterZanroviInput = $('#filterZanroviInput');
	var filterTrajanjeVInput = $('#filterTrajanjeVInput');
	var filterTrajanjeNInput = $('#filterTrajanjeNInput');
	var filterDistributerInput = $('#filterDistributerInput');
	var filterZemljaPoreklaInput = $('#filterZemljaPoreklaInput');
	var filterGodinaProizvodnjeVInput = $('#filterGodinaProizvodnjeVInput');
	var filterGodinaProizvodnjeNInput = $('#filterGodinaProizvodnjeNInput');

	var filmTable = $('#filmTable');
	
	function getFilmovi(){
		var filterNaziv = filterNazivInput.val();
		var filterZanrovi = filterZanroviInput.val();
		var filterTrajanjeV = filterTrajanjeVInput.val();
		var filterTrajanjeN = filterTrajanjeNInput.val();
		var filterDistributer = filterDistributerInput.val();
		var filterZemljaPorekla = filterZemljaPoreklaInput.val();
		var filterGodinaProizvodnjeV = filterGodinaProizvodnjeVInput.val();
		var filterGodinaProizvodnjeN = filterGodinaProizvodnjeNInput.val();
		console.log('filterNaziv: ' + filterNaziv);
		console.log('filterZanrovi: ' + filterZanrovi);
		console.log('filterTrajanjeV: ' + filterTrajanjeV);
		console.log('filterTrajanjeN: ' + filterTrajanjeN)
		console.log('filterDistributer: ' + filterDistributer);
		console.log('filterZemljaPorekla: ' + filterZemljaPorekla);
		console.log('filterGodinaProizvodnjeV: ' + filterGodinaProizvodnjeV);
		console.log('filterGodinaProizvodnjeN: ' + filterGodinaProizvodnjeN);

		
		var params = {
			'filterNaziv': filterNaziv,
			'filterZanrovi': filterZanrovi,
			'filterTrajanjeV': filterTrajanjeV,
			'filterTrajanjeN': filterTrajanjeN,
			'filterDistributer': filterDistributer,
			'filterZemljaPorekla': filterZemljaPorekla,
		    'filterGodinaProizvodnjeV': filterGodinaProizvodnjeV,
		    'filterGodinaProizvodnjeN': filterGodinaProizvodnjeN
		};
		
		$.get('IndexServlet', params, function(data){
			console.log(data);
			
			if(data.status == 'success') {
				filmovi = data.filterFilmovi;
				
				popuniTabelu(filmovi);
				
			}	

		});
	}
	filterNazivInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterZanroviInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterTrajanjeVInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterTrajanjeNInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterDistributerInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterZemljaPoreklaInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterGodinaProizvodnjeVInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterGodinaProizvodnjeNInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});

	function popuniTabelu(filmoviZaTabelu){
		filmTable.find('tr:gt(1)').remove();
		
		console.log('tabela?')
		
		console.log(filmoviZaTabelu);
		for(it of filmoviZaTabelu){
			filmTable.append(
				'<tr>' + 
					'<td>' + it.naziv + '</td>' +
					'<td>' + it.zanrovi + '</td>' +
					'<td>' + it.trajanje + '</td>' +
					'<td>' + it.distributer + '</td>' +
					'<td>' + it.zemljaPorekla + '</td>' +
					'<td>' + it.godinaProizvodnje + '</td>' +
					'<td>' + 
					'</td>' + 
				'</tr>'
			)
		}
	};
	$('#sortNaziv').on('click', function(event){
		alert('Sortiram...');
		sortiraj('naziv');
	});
	
	$('#sortZanrovi').on('click', function(event){
		alert('Sortiram...');
		sortiraj('zanrovi');
	});
	$('#sortTrajanje').on('click', function(event){
		alert('Sortiram...');
		sortiraj('trajanje');
	});
	$('#sortDistributer').on('click', function(event){
		alert('Sortiram...');
		sortiraj('distributer');
	});
	$('#sortZemljaPorekla').on('click', function(event){
		alert('Sortiram...');
		sortiraj('zemljaPorekla');
	});
	$('#sortGodinaProizvodnje').on('click', function(event){
		alert('Sortiram...');
		sortiraj('godinaProizvodnje');
	});
	
	function sortiraj(sort){
		let sortiraniFilmovi = filmovi;

		for(let i = 0; i < sortiraniFilmovi.length - 1; i++){
			for(let j = i+1; j < sortiraniFilmovi.length; j++){
				if (sort === 'naziv'){
					if (sortNazivSmer == 1){
						if (sortiraniFilmovi[i].naziv > sortiraniFilmovi[j].naziv){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if (sortiraniFilmovi[i].naziv < sortiraniFilmovi[j].naziv){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					}
				} else if (sort === 'zanrovi'){
					if (sortZanroviSmer == 1){
						if (sortiraniFilmovi[i].zanrovi > sortiraniFilmovi[j].zanrovi){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if (sortiraniFilmovi[i].zanrovi < sortiraniFilmovi[j].zanrovi){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					}
				} else if (sort === 'trajanje'){
					if (sortTrajanjeSmer == 1){
						if(sortiraniFilmovi[i].trajanje > sortiraniFilmovi[j].trajanje){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if(sortiraniFilmovi[i].trajanje < sortiraniFilmovi[j].trajanje){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					}
				} else if (sort === 'distributer'){
					if (sortDistributerSmer == 1){
						if(sortiraniFilmovi[i].distributer > sortiraniFilmovi[j].distributer){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if(sortiraniFilmovi[i].distributer < sortiraniFilmovi[j].distributer){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					}
				} else if (sort === 'zemljaPorekla'){
					if (sortZemljaPoreklaSmer == 1){
						if(sortiraniFilmovi[i].zemljaPorekla > sortiraniFilmovi[j].zemljaPorekla){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if(sortiraniFilmovi[i].zemljaPorekla < sortiraniFilmovi[j].zemljaPorekla){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					}
				} else if (sort === 'godinaProizvodnje'){
					if (sortGodinaProizvodnjeSmer == 1){
						if(sortiraniFilmovi[i].godinaProizvodnje > sortiraniFilmovi[j].godinaProizvodnje){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if(sortiraniFilmovi[i].godinaProizvodnje < sortiraniFilmovi[j].godinaProizvodnje){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					}
				} 
			}
		}
		if (sort === 'naziv'){
			sortNazivSmer = -1 * sortNazivSmer;
		}else if (sort === 'zanrovi'){
			sortZanroviSmer = -1 * sortZanroviSmer;
		}else if (sort === 'trajanje'){
			sortTrajanjeSmer = -1 * sortTrajanjeSmer;
		}else if (sort === 'distributer'){
			sortDistributerSmer = -1 * sortDistributerSmer;
		}else if (sort === 'zemljaPorekla'){
			sortZemljaPoreklaSmer = -1 * sortZemljaPoreklaSmer;
		}else if (sort === 'godinaProizvodnje'){
			sortGodinaProizvodnjeSmer = -1 * sortGodinaProizvodnjeSmer;
		}

		popuniTabelu(sortiraniFilmovi);
	};
	getFilmovi();
	console.log('dobavljeni filmovi?');
});