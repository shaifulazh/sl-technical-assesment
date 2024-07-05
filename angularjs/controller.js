(function (window, angular) {
  "use strict";

  var app = angular.module("demoController", ["demoService"]);

  app.controller("mainController", [
    "$scope",
    "apiService",
    function ($scope, apiService) {
      $scope.init = function () {
        apiService.getAllTask(function (response) {
          if (response.length > 0) {
            $scope.lists = response;
          }else {
              $scope.lists = []
          }
          console.log(response);
        });
      };

      $scope.create = function (task) {
        apiService.createTaskList(task, function (response) {
          $scope.result = response;
          console.log(response);
          $scope.init();
        });
      };

      $scope.onDelete = function (id) {
        apiService.deleteTask(id, function (response) {
          if (response.length > 0) {
            console.log("success")
          }
          console.log(response);
          $scope.init();
        });
      };

      $scope.onComplete = function (id) {
        let data = {completed : true}
        apiService.completeTask(id ,data, function (response){
            if (response.length>0) {
                console.log("success")
            }
            $scope.init()
        })
      }
    },
  ]);

})(window, angular);
