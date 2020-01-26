var filmovi = []
var sortNazivSmer = 1;
var sortReziserSmer = 1;
var sortGlumciSmer = 1
var sortZanroviSmer = 1;
var sortTrajanjeSmer = 1;
var sortDistributerSmer = 1;
var sortZemljaPoreklaSmer = 1;
var sortGodinaProizvodnjeSmer = 1;
var sortOpisSmer = 1;

$(document).ready(function(){
	var filterNazivInput = $('#filterNazivInput');
	var filterReziserInput = $('#filterReziserInput');
	var filterGlumciInput = $('#filterGlumciInput');
	var filterZanroviInput = $('#filterZanroviInput');
	var filterTrajanjeInput = $('#filterTrajanjeInput');
	var filterDistributerInput = $('#filterDistributerInput');
	var filterZemljaPoreklaInput = $('#filterZemljaPoreklaInput');
	var filterGodinaProizvodnjeInput = $('#filterGodinaProizvodnjeInput');
	var filterOpisInput = $('#filterOpisInput');

	var filmTable = $('#filmTable');
	
	var adminParagraph = $('#adminParagraph');
	
	function getFilmovi(){
		var filterNaziv = filterNazivInput.val();
		var filterReziser = filterReziserInput.val();
		var filterGlumci = filterGlumciInput.val();
		var filterZanrovi = filterZanroviInput.val();
		var filterTrajanje = filterTrajanjeInput.val();
		var filterDistributer = filterDistributerInput.val();
		var filterZemljaPorekla = filterZemljaPoreklaInput.val();
		var filterGodinaProizvodnje = filterGodinaProizvodnjeInput.val();
		var filterOpis = filterOpisInput.val();
		console.log('filterNaziv: ' + filterNaziv);
		console.log('filterReziser: ' + filterReziser);
		console.log('filterGlumci: ' + filterGlumci)
		console.log('filterZanrovi: ' + filterZanrovi);
		console.log('filterTrajanje: ' + filterTrajanje);
		console.log('filterDistributer: ' + filterDistributer);
		console.log('filterZemljaPorekla: ' + filterZemljaPorekla);
		console.log('filterGodinaProizvodnje: ' + filterGodinaProizvodnje);
		console.log('filterOpis: ' + filterOpis);
		
		var params = {
			'filterNaziv': filterNaziv,
			'filterReziser': filterReziser,
			'filterGlumci': filterGlumci,
			'filterZanrovi': filterZanrovi,
			'filterTrajanje': filterTrajanje,
			'filterDistributer': filterDistributer,
			'filterZemljaPorekla': filterZemljaPorekla,
		    'filterGodinaProizvodnje': filterGodinaProizvodnje,
			'filterOpis': filterOpis
		};
		
		$.get('AplikacijaServlet', params, function(data){
			console.log(data);
			
			if(data.status == 'success') {
				filmovi = data.filterFilmovi;
				
				popuniTabelu(filmovi);
				
			}	

		});
	}
	function getAdminInterface(){
		$.get('KorisnikServlet', {'action': 'ulogovanKorisnikUloga'}, function(data) {
			console.log(data);
			
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}
			adminParagraph.empty();
			if(data.status == 'success') {
				adminParagraph.empty();
				if(data.ulogovanKorisnikUloga == 'admin'){
					$('#adminParagraph').append('<a href="dodajfilm.html">Dodavanje filma</a>');
					$('#adminParagraph').append('<p></p>');
					$('#adminParagraph').append('<a href="dodajprojekciju.html">Dodavanje projekcije</a>');
					$('#adminParagraph').append('<p></p>');
					$('#adminParagraph').append('<a href="korisnici.html">Pregled korisnika</a>');
					$('#adminParagraph').append('<p></p>');
					$('#adminParagraph').append('<a href="projekcijeadmin.html">Pregled projekcija - admin</a>');
					$('#adminParagraph').append('<p></p>');
					$('#adminParagraph').append('<a href="karte.html">Pregled karata</a>');
					
				}
			}
		});
	}
	
	
	filterNazivInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterReziserInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterGlumciInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	
	filterZanroviInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterTrajanjeInput.on('keyup', function(event){
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
	filterGodinaProizvodnjeInput.on('keyup', function(event){
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	filterOpisInput.on('keyup', function(event){
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
					'<td><a href="izmenifilm.html?naziv=' + it.naziv + '&id=' + it.id +  '">' + it.naziv + '</td>' +
					'<td>' + it.reziser + '</td>' +
					'<td>' + it.glumci + '</td>' +
					'<td>' + it.zanrovi + '</td>' +
					'<td>' + it.trajanje + '</td>' +
					'<td>' + it.distributer + '</td>' +
					'<td>' + it.zemljaPorekla + '</td>' +
					'<td>' + it.godinaProizvodnje + '</td>' +
					'<td>' + it.opis + '</td>' + 
					'<td>' + 
					'<form>' + '<input type="submit" value="Obrisi" class="deleteSubmit" filmID="' + it.id + '">' + 
				'</form>' + '</td>' + 
					'<td>' + 
					'</td>' + 
				'</tr>'
			)
		}
	};
	
	filmTable.on('click', 'input.deleteSubmit', function(event){
		alert('Brisem...');
		var filmID = $(this).attr('filmID');
		console.log('filmID: ' + filmID);
		
		params = {
				'action': 'delete',
				'id': filmID
		};
		console.log(params);
		$.post('FilmServlet', params, function(data){
			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('aplikacija.html');
				return;
			}
		});
		event.preventDefault();
		return false;
	});
	
	$('#sortNaziv').on('click', function(event){
		alert('Sortiram...');
		sortiraj('naziv');
	});
	$('#sortReziser').on('click', function(event){
		alert('Sortiram...');
		sortiraj('reziser');
	});
	$('#sortGlumci').on('click', function(event){
		alert('Sortiram...');
		sortiraj('glumci');
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
	$('#sortOpis').on('click', function(event){
		alert('Sortiram...');
		sortiraj('opis');
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
				} else if (sort === 'opis'){
					if (sortOpisSmer == 1){
						if(sortiraniFilmovi[i].opis > sortiraniFilmovi[j].opis){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if(sortiraniFilmovi[i].opis < sortiraniFilmovi[j].opis){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					}
				} else if (sort === 'reziser'){
					if (sortReziserSmer == 1){
						if(sortiraniFilmovi[i].reziser > sortiraniFilmovi[j].reziser){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if(sortiraniFilmovi[i].reziser < sortiraniFilmovi[j].reziser){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					}
				} else if (sort === 'glumci'){
					if (sortGlumciSmer == 1){
						if(sortiraniFilmovi[i].glumci > sortiraniFilmovi[j].glumci){
							let temp = sortiraniFilmovi[i];
							sortiraniFilmovi[i] = sortiraniFilmovi[j];
							sortiraniFilmovi[j] = temp;
						}
					} else {
						if(sortiraniFilmovi[i].glumci < sortiraniFilmovi[j].glumci){
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
		}else if (sort === 'reziser'){
			sortReziserSmer = -1 * sortReziserSmer;
		}else if (sort === 'glumci'){
			sortGlumciSmer = -1 * sortGlumciSmer;
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
		}else if (sort === 'opis'){
			sortOpisSmer = -1 * sortOpisSmer;
		}

		popuniTabelu(sortiraniFilmovi);
	};
	getFilmovi();
	getAdminInterface();
	console.log('dobavljeni filmovi?');
});