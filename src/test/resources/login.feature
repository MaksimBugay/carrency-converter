Feature: the success login

  Scenario: login with valid credentials
    Given user on the login page
    When user logged in using username as "bmv2mail@gmail.com" and password as "dexter"
    Then user should be on the home page