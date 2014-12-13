var atelierControllers = angular.module('atelierControllers', []);

atelierControllers.controller('atelierListCtrl', function ($scope, $http) {

	// POST DATA 

	$scope.postData = function(){

		var datum = {
			id:9,
			title:"titre9",
			lab:"LaBRI",
			theme:"Theme1",
			location:"CNRS, TALENCE",
			type: "type9",
			duration:"1h30",
			capacity:"120",
			summary:"Some stuff happening somewhere",
			anim:"Marc Fgrijzd",
			partners:"LaBRI",
			content:"IT",
			visitors : [{id : 2, name : "6ieme5ieme"}, {id : 7, name : "Prepa"}],
			horaires : [{id : 1, name : "Lundi matin"}, {id : 4, name : "Mardi après-midi"}]
		}

		$http({
			method: 'POST',
			url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers',
			data: datum,
		}).success(function(data){
			console.log(data)
		});

		/*$http({
			method: 'GET',
			url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers',
			data: datum,
		}).success(function(data){
			console.log(data)
		});*/


	}
/*		$http({
			method: 'POST',
			url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers',
			data: datum,
		}).success(function(data){
			console.log(data)
		});*/

    // DELETE DATA

    $scope.deleteData = function(){

    	var datum = {
    		id:2
    	}

    	$http({
    		method: 'DELETE',
    		url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers',
    		data: datum,
    	}).success(function(data){
    		console.log(data)
    	});
    }

    // UPDATE DATA

    $scope.updateData = function(){

    	var datum = {
    		id:7,
    		title:"titre modifié",
    		lab:"LaBRI",
    		theme:"Theme1",
    		location:"CNRS, TALENCE",
    		type: "type1",
    		duration:"1h30",
    		capacity:"120",
    		summary:"Some stuff happening somewhere",
    		anim:"Marc Fgrijzd",
    		partners:"LaBRI",
    		content:"IT",
    		visitors : [{id : 3, name : "4ieme3ieme"}, {id : 7, name : "Prepa"}, {id : 8, name : "Universite"}],
    		horaires : [{id : 6, name : "Mercredi après-midi"}, {id : 7, name : "Jeudi matin"}, {id : 9, name : "Vendredi matin"}]

    	}

    	$http({
    		method: 'POST',
    		url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers/update',
    		data: datum,
    	}).success(function(data){
    		console.log(data)
    	});
    }
    

	// Dummy data to be replaced by real data extracted from server
	$scope.ateliers = [];

	var atelier1 = {id:1, title:"titre1", lab:"LaBRI", theme:"Theme1", location:"CNRS, TALENCE"};
	var atelier2 = {id:2, title:"titre2", lab:"IMS", theme:"Theme2", location:"ENSEIRB-MATMECA, TALENCE"};
	var atelier3 = {id:3, title:"titre3", lab:"LaBRI", theme:"Theme3", location:"CNRS, TALENCE"};

	$scope.ateliers.push(atelier1);
	$scope.ateliers.push(atelier3);
	$scope.ateliers.push(atelier2);

	$scope.edit = function(val){
		location.href = location.href.slice(0, location.href.lastIndexOf('/')) + '/' + 'ateliers.html' + '?id=' + val;
	}

})

atelierControllers.controller('atelierFormCtrl', function ($scope, $location, $http) {

	

	var checkBoxs = function(nm, ck){
		return {name : nm, checked: ck};
	}
	$scope.atelier = {};

	// if(location.href.indexOf('?')>-1) {
	// 	$scope.atelier = {
	// 		id:1,
	// 		title:"titre1",
	// 		lab:"LaBRI",
	// 		theme:"Theme1",
	// 		location:"CNRS, TALENCE",
	// 		type: "type1",
	// 		duration:"1h30",
	// 		capacity:"120",
	// 		summary:"Some stuff happening somewhere",
	// 		anim:"Marc Fgrijzd",
	// 		partners:"LaBRI",
	// 		content:"IT",
	// 		visitors : [checkBoxs('Lycée', true), checkBoxs('Collège', false)],
	// 		horaires : [checkBoxs('Mercredi matin', true), checkBoxs('Jeudi Après-midi', false)]
	// 	};
	// }

	$scope.submit = function(){
		if (isFilled($scope.atelier, $scope.$$listeners.$destroy.length)){
			$scope.atelier.id = 13;
			$http({
				method: 'POST',
				url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers',
				data: $scope.atelier,
			}).success(function(data){
				console.log(data)
			});

		} else {
			console.log("not yet")
		}
		
	}
})

function isFilled(atelier, numberOfFields){
	var hasAllKeys = Object.keys(atelier).length == numberOfFields;
	var allKeysFilled = true;

	if (hasAllKeys){
		Object.keys(atelier).forEach(function(d){
			allKeysFilled = atelier[d].length > 0;
		})
	}
	return hasAllKeys ? allKeysFilled : hasAllKeys;
}

