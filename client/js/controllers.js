var atelierControllers = angular.module('atelierControllers', []);

atelierControllers.controller('apiCtrl', function ($scope, $http) {

	$scope.lefties = [];
	$scope.righties = [];

	$http({
		method: 'GET',
		url: 'scripts/api.json'
	}).success(function(data){
		data.forEach(function(d, i){
			d.tested = 0;
			d.class = getClassByMethod(d.method);
			if (!(i % 2)) $scope.lefties.push(d);
			else $scope.righties.push(d);
		})
		testSet($scope.lefties)
	});
	function getClassByMethod(method){
		switch(method){
			case 'POST':
			return 'primary';
			break;
			case 'GET' :
			return 'success';
			break;
			case 'DELETE':
			return 'danger';
			break;
			default:
			return 'default';
			break;
		}
	}

});

atelierControllers.controller('atelierListCtrl', function ($scope, $http) {


	$http({
		method: 'GET',
		url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers',
	}).success(function(data){
		if (typeof(data) != 'string') $scope.ateliers = data;
	})

	$scope.edit = function(val){
		location.href = location.href.slice(0, location.href.lastIndexOf('/')) + '/' + 'ateliers.html' + '?id=' + val;		
	}

	$scope.remove = function(val){

		$http({
			method: 'DELETE',
			url: 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers',
			data : {id : val}
		}).success(function(data){
			console.log(data)
			$scope.ateliers.forEach(function(d){
				if (d.id == val) $scope.ateliers.splice($scope.ateliers.indexOf(d), 1);
			})
		})


	}


})

atelierControllers.controller('atelierFormCtrl', function ($scope, $location, $http) {
	var edit, id;
	if(location.href.indexOf('?') > -1){
		edit = true;
		id  = parseInt(location.href.split('=')[location.href.split('=').length-1])
	} else { edit = false;}

	var checkBoxs = function(nm, ck){
		return {name : nm, checked: ck};
	}
	$scope.atelier = {};

	$http.get('http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers/listVisitorsHoraires/')
	.success(function(data){
		$scope.allVisitors = data.visitors;
		$scope.allHoraires = data.horaires;

		if (edit){
			$http.get('http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers/'+ id)
			.success(function(data){
				$scope.atelier = data[0];
				console.log($scope.atelier)

				$scope.atelier.visitors.forEach(function(d, i){
					$scope.allVisitors.forEach(function(dd){
						if (dd.id == d.id) dd.checked = true;
					})
				})
				$scope.atelier.horaires.forEach(function(d, i){
					$scope.allHoraires.forEach(function(dd){
						if (dd.id == d.id) dd.checked = true;
					})
				})
			})
		}
	})

	$scope.submit = function(){
		var link = edit ? 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers/update' : 'http://jduban.rmorpheus.enseirb.fr/CNRS/rest/ateliers';
		$scope.atelier = filterCheckboxs($scope.atelier, $scope.allHoraires, $scope.allVisitors)
		//$scope.atelier.id = id;
		
		var isValid = [ 
		Object.keys($scope.atelier).length > 12,
		$scope.atelier.visitors.length > 0,
		$scope.atelier.horaires.length > 0 ];

		if (isValid[0] && isValid [1] && isValid[2]){
			if ($scope.atelier.id == null) $scope.atelier.id = 0;
			$http({
				method: 'POST',
				url: link,
				data: $scope.atelier,
			}).success(function(data){
				console.log(data)
			});
		} else if (!isValid[0]) {
			console.log('missing field')
		} else if(!isValid[1] || isValid[2]){
			console.log('missing checkbox')
		}
	}
})

function filterCheckboxs(atelier, allHoraires, allVisitors){
	atelier.visitors = [];
	atelier.horaires = [];
	allVisitors.forEach(function(d){
		if (d.checked) atelier.visitors.push(d);
	})
	allHoraires.forEach(function(d){
		if (d.checked) atelier.horaires.push(d);
	})

	return atelier;
}


