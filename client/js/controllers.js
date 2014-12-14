var atelierControllers = angular.module('atelierControllers', []);

atelierControllers.controller('apiCtrl', function ($scope, $http) {

	$scope.lefties = [];
	$scope.righties = [];

	$http({
		method: 'GET',
		url: 'scripts/api.json'
	}).success(function(data){
		data.forEach(function(d, i){
			d.class = getClassByMethod(d.method);
			if (!(i % 2)) $scope.lefties.push(d);
			else $scope.righties.push(d);
		})
		$scope.methods = data;
		$scope.methods.forEach(function(d){
			d.testClass = 'disabled';
		})
		startTest();
	});

	// Create Two ateliers
	function startTest(){
		var atelier = {
			title:'AtelierTest"1', 
			lab:"lab", 
			theme:"theme",
			location:"loc",
			type: "type",
			duration:"1h''''30",
			capacity:"cap",
			summary:"summary",
			anim:"anim",
			partners:"partners",
			content:"content",
			visitors : [{id : 1, name : "horaire1"}],
			horaires : [{id : 1, name : "horaire2"}]
		}

		$http({
			method: $scope.methods[5].method,
			url: $scope.methods[5].link,
		}).success(function(data){
			$scope.methods[5].result = data.visitors.length>0 && data.horaires.length>0;
			if($scope.methods[5].result) {
				console.log('test0 : ' + $scope.methods[5].description)
				recheckClass(5)
			}
			else { 
				console.log('test0 : err')
			}
		})



	//LEVEL 1
	$http({
		method: $scope.methods[0].method,
		url: $scope.methods[0].link,
		data : atelier
	}).success(function(data){
		$scope.methods[0].result = Boolean(data);
		if (!data) console.error('test1 : err')
			else {
				console.log('test1 : ' + $scope.methods[0].description)
				recheckClass(0)
				// LEVEL 2
				$http({
					method: $scope.methods[2].method,
					url: $scope.methods[2].link,
					data : atelier
				}).success(function(data){
					$scope.methods[2].result = data.length > 0;
					// LEVEL 3
					if (data.length == 0) console.error('test2 : err')
						else {
							console.log('test2 : ' + $scope.methods[2].description)
							recheckClass(2)
							$http({
								method: $scope.methods[3].method,
								url: $scope.methods[3].link.replace('{id}',data[0].id),
								data : atelier
							}).success(function(data){
								$scope.methods[3].result = data.length == 1;
								//LEVEL 4
								if (data.length != 1) console.error('test3 : err')
									else {
										console.log('test3 : ' + $scope.methods[3].description)
										recheckClass(3)
										atelier.id = data[0].id;
										$http({
											method: $scope.methods[1].method,
											url: $scope.methods[1].link,
											data : atelier
										}).success(function(data){
											$scope.methods[1].result = Boolean(data);
											//LEVEL 5
											if (!data) console.error('test4 : err')
												else {
													console.log('test4 : ' + $scope.methods[1].description)
													recheckClass(1)
													$http({
														method: $scope.methods[4].method,
														url: $scope.methods[4].link,
														data : {id : atelier.id}
													}).success(function(data){
														$scope.methods[4].result = Boolean(data);
														if (!data) console.error('test5 : err')
															else {
																console.log('test5 : ' + $scope.methods[4].description)
																recheckClass(4)
															}
														})
												}
											})
									}
								})
}
});
}

});

}

function recheckClass(k){
	$scope.methods[k].testClass = $scope.methods[k].result ? 'list-group-item-success' : 'list-group-item-warning';
}



	// Get list of ateliers

	// Get a specific atelier

	// Edit an atelier from this list

	// Delete the first atelier

	// Get list of visitors

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
				location.href = location.href.slice(0, location.href.lastIndexOf('/')) + '/' + 'index.html';
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


