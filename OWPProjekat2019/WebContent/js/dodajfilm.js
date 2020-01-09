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
	var nazivInput = $('#nazivInput');
	var reziserInput = $('#reziserInput');
	var glumciInput = $('#glumciInput');
	var zanroviInput = $('#zanroviInput');
	var trajanjeInput = $('#trajanjeInput');
	var distributerInput = $('#distributerInput');
	var zemljaPoreklaInput = $('#zemljaPoreklaInput');
	var godinaProizvodnjeInput = $('#godinaProizvodnjeInput');
	var opisInput = $('#opisInput');
	
	
	
	$('#filmSubmit').on('click', function(event){
		var naziv = nazivInput.val();
		var reziser = reziserInput.val();
		var glumci = glumciInput.val();
		var zanrovi = zanroviInput.val();
		var trajanje = trajanjeInput.val();
		var distributer = distributerInput.val();
		var zemljaPorekla = zemljaPoreklaInput.val();
		var godinaProizvodnje = godinaProizvodnjeInput.val();
		var opis = opisInput.val();
		console.log('naziv: ' + naziv);
		console.log('reziser: ' + reziser);
		console.log('glumci: ' + glumci);
		console.log('zanrovi: ' + zanrovi);
		console.log('trajanje: ' + trajanje);
		console.log('distributer: ' + distributer);
		console.log('zemljaPorekla: ' + zemljaPorekla);
		console.log('godinaProizvodnje: ' + godinaProizvodnje);
		console.log('opis: ' + opis);
		
		params = {
				'action': 'add', 
				'naziv': naziv, 
				'reziser': reziser,
				'glumci': glumci,
				'zanrovi': zanrovi,
				'trajanje': trajanje,
				'distributer': distributer,
				'zemljaPorekla': zemljaPorekla,
				'godinaProizvodnje': godinaProizvodnje,
				'opis': opis 
		};
		$.post('FilmServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('aplikacija.html');
			}
		});
		event.preventDefault();
		return false;
	});
});