@City1
Feature: City Module API automation

  Scenario: Verify User Get CityList through api
    Given User add header for citylist
    When User should add request body stateId for to get citylist
    And User Send "POST" request for citylist endpoint
    Then User verify the status code in 200
    Then User verify the CityList response message matches "Yercaud" and saved city_id
