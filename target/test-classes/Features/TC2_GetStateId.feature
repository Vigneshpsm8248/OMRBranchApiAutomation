@State1
Feature: State Module API automation

  Scenario: Verify User Get StateList through api
    Given User add header for statelist
    When User Send "GET" request for statelist endpoint
    Then User verify the status code in 200
    Then User verify the StateList response message matches "Tamil Nadu" and saved state_id
