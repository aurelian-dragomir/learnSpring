Feature: test hello controller

  Scenario: we want to check if /hello works correctly
    Given we make a get request to /hello
    When i check the response
    Then the status code should be 200
    And the body should be "hello"