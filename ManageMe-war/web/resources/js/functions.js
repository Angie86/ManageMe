/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log("Name: " + profile.getName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());
        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
        alert("Hola");
        passToJSFManagedBean ([ {
                name: 'email',
                value : profile.getEmail()
            },
            {
                name: 'photo',
                value : profile.getImageUrl()
            },
            {
                name: 'name',
                value : profile.getName()
                
            }
       
        ]);
            window.location.replace("http://localhost:8080/ManageMe-war/faces/pages/chatPage.xhtml");
      };
      
      
  function signOut() {
    passToSignOut([
                        ]);
    var auth2 = gapi.auth2.getAuthInstance();
   
    auth2.signOut().then(function () {
      console.log('User signed out.');
      
    });
   
  }