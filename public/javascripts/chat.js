var app = angular.module('chatApp', ['ngMaterial']);
app.controller('chatcontroller', function ($scope) {
    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'hello'
          },
        {
            'sender': 'BOT',
            'text': 'hi what can i do for you'
          },
        {
            'sender': 'USER',
            'text': 'hello babe'
          },
        {
            'sender': 'BOT',
            'text': 'helllllo'
          }
       ];
});