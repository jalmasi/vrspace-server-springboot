# vrspace-server-springboot
Reuse VRSpace server and extend, disable, replace components.

This example project shows how to:
- use VRSpace maven artifact from maven central (pom.xml)
- use VRSpace server in your own spring boot application (ExampleServerApplication.java)
- prevent the server from distributing content (application.properties)
- customise error page (error.html)
- implement custom user authentication and authorisation

This custom user authentication and authorisation (MySecurityConfig.java, ExampleServerApplication.java, index.html, login.html ) 
is based on spring boot login page examples freely available on the web, namely Baeldung and Stackoverflow, with everything non-essential stripped off.
VRSpace part of it is in MyClientFactory.java, that
- extends ClientFactory interface
- does not implement createGuestClient, thus disables anonymous access
- implements findClient that requires user to be logged in
and application.properties, that
- enables use of MyClientFactory
- disables use of built-in Oauth2 authentication

A real-life scenario - I want only registered users allowed.

# how to use

- import the maven project into your IDE
- start ExampleServerApplication as spring boot app
- access http://localhost:8080/ with TWO browsers
- log in as user1/pass1 in first, and user2/pass2 in second browser