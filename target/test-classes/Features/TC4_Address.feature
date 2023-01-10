@Address1
Feature: Address Module API automation

  Scenario Outline: Verify add user address to the application through api
    Given User add header and bearer authentication for accessing AddUseraddress endpoints
    When User add request body for Add user new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for addUserAddress endpoint
    Then User verify the status code in 200
    Then User verify the addUserAddress response message matches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment   | state | city | country | zipcode | address          | address_type |
      | Vignesh    | Vicky     | 7397194873 | doorno7/256 |    35 | 4440 |     101 |  636007 | 7/256 anna nagar | home         |

  Scenario Outline: Verify update user address to the application through api
    Given User add header and bearer authentication for accessing UpdateUseraddress endpoints
    When User add request body for Update user address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for updateUserAddress endpoint
    Then User verify the status code in 200
    Then User verify the updateUserAddress response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment   | state | city | country | zipcode | address          | address_type |
      | Vignesh    | Vicky     | 7397194873 | doorno7/256 |    35 | 4440 |     101 |  636007 | 7/256 anna nagar | home         |

  Scenario: Verify get user address to the application through api
    Given User add header and bearer authentication for accessing GetUseraddress endpoints
    When User send "GET" request for getUserAddress endpoint
    Then User verify the status code in 200
    Then User verify the getUserAddress response message matches "OK"

  Scenario: Verify delete user address to the application through api
    Given User add header and bearer authentication for accessing DeleteUseraddress endpoints
    When User add request body for delete address
    And User send "DELETE" request for deleteUserAddress endpoint
    Then User verify the status code in 200
    Then User verify the deleteUserAddress response message matches "Address deleted successfully"
