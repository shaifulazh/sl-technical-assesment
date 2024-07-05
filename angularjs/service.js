(function (window, angular) {
    "use strict";

var app = angular.module("demoService", []);

app.config(['$sceDelegateProvider', function($sceDelegateProvider) {
  // We must add the JSONP endpoint that we are using to the trusted list to show that we trust it
  $sceDelegateProvider.trustedResourceUrlList([
    'self',
    'http://localhost:8080'
  ]);
}])


app.factory("apiService", [
  "$http",
  function ($http) {
    var apicall = {};
    const taskUrl = 'http://localhost:8080/api/tasklist'

    apicall.completeTask = function (id,data,callback){
      $http({
        url: `${taskUrl}/${id}`,
        method: "PUT",
        headers: {
            "Content-type" : "application/json"
        },
        data : data,
    }).then(
        function (success) {
          callback(success.data);
        },
        function (error) {
          callback(error.data);
        }
      );
    }
  
    apicall.deleteTask = function (id,callback){
      $http({
        url: `${taskUrl}/${id}`,
        method: "DELETE",
        headers: {
            "Content-type" : "application/json"
        },
        data : null,
    }).then(
        function (success) {
          callback(success.data);
        },
        function (error) {
          callback(error.data);
        }
      );
    }

    apicall.getAllTask = function (callback){
      $http({
        url: `${taskUrl}`,
        method: "GET",
        headers: {
            "Content-type" : "application/json"
        },
        data : null,
    }).then(
        function (success) {
          callback(success.data);
        },
        function (error) {
          callback(error.data);
        }
      );
    }

    apicall.createTaskList = function (data,callback) {
        $http({
            url: taskUrl,
            method: "POST",
            headers: {
                "Content-type" : "application/json"
            },
            data : data,
        }).then(
            function (success) {
              callback(success.data);
            },
            function (error) {
              callback(error.data);
            }
          );
    }

    apicall.getdata = function (path, callback) {
      $http({
        url: path,
        method: "GET",
        headers: {
          "Content-type": "application/x-www-form-urlencoded",
        },
        data: "Email",
      }).then(
        function (success) {
          callback(success.data);
        },
        function (error) {
          callback(error.data);
        }
      );
    };
    return apicall;
  },
]);

})(window, angular);