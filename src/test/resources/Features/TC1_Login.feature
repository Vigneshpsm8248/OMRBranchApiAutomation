@Login1
Feature: Login Module API automation

  Scenario: Get User logtoken from login endpoint
    Given User add header
    When User add basic authentication for login
    And User send "POST" request for login endpoint
    Then User verify the status code in 200
    Then User verify the login response body firstname present as "Vignesh" and get the logtoken saved
