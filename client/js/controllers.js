var atelierControllers = angular.module('atelierControllers', []);

atelierControllers.controller('atelierListCtrl', function ($scope, $http) {

	$scope.callApi = function(){
		// $http.post('http://jduban.rmorpheus.enseirb.fr/CNRS/rest/atelier', {atelier : 'Hello World!!'}).success(function(data){
		// 	console.log(data)
		// })
		// .error(function(err){
		// 	console.log(err)
		// })

	var datum = {
		"atelier" : {
			"name" : "Hello",
			"id" : "World"
		}
	}
	$http({
       method: 'POST',
       url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/atelier',
       data: datum,
        // headers: {
        //     'Content-Type': 'application/x-www-form-urlencoded'
   // }
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

atelierControllers.controller('atelierFormCtrl', function ($scope, $location) {

	var checkBoxs = function(nm, ck){
		return {name : nm, checked: ck};
	}
	$scope.atelier = {};

	if(location.href.indexOf('?')>-1) {
		$scope.atelier = {
			id:1,
			title:"titre1",
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
			visitors : [checkBoxs('Lycée', true), checkBoxs('Collège', false)],
			horaires : [checkBoxs('Mercredi matin', true), checkBoxs('Jeudi Après-midi', false)]
		};
	}

	$scope.submit = function(){
		console.log($scope.atelier);
	}
})
