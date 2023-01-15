@rest
Feature: REST API

  @rest1
  Scenario: Booker REST API CRUD Operations
    Given I login POST via REST API as "username_password"
    When I create POST via REST API "bookingdata" order
    Then I verify GET via REST API new order is in the list
    When I update PUT via REST API new "bookingdata_update" order
    Then I verify GET via REST API new "bookingdata_update" order is updated
    When I delete DELETE via REST API new order
    Then I verify GET via REST API new order is deleted


