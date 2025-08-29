Feature: Login Functionality
  Scenario: Successfully login with valid credentials
    Given user is on the login page
    When user enter valid username and password
    And clicks the login button
    Then user should be redirect to the dashboard

    Scenario: Unsuccessful login with invalid credentials
      Given user is on the login page
      When user enters invalid username and password
      And clicks the login button
      Then an error message is displayed

      #    Examples:
#    |username|password|
#    |"admin" |"username"|
#    |"123"   |"password"|