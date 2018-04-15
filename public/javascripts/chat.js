var app = angular.module('chatApp', ['ngMaterial']);



app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('purple')
        .accentPalette('red');
});
app.controller('chatcontroller', function ($scope) {
    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'hey wassup'
          },
        {
            'sender': 'BOT',
            'text': 'hey cool'

          },
        {
            'sender': 'USER',
            'text': 'how was your day??????'
          },
        {
            'sender': 'BOT',
            'text': 'fine!!!!!'
          }
       ];
});