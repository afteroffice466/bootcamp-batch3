Feature: Employee API

  Background:
    Given The base url in this feature is "https://whitesmokehouse.com/webhook"

  Scenario:
    When Send a http "POST" request to "/employee/add" with body:
      """
      {
        "email": "test92@test.com",
        "password": "test",
        "full_name": "test name",
        "department": "IT",
        "title": "QA"
      }
      """
    Then The response status must be 200
    And The response schema should be match with schema "add_employee_schema.json"

  Scenario:
    When Send a http "POST" request to "/employee/login" with body:
      """
      {
        "email": "test92@test.com",
        "password": "test"
      }
      """
    Then The response status must be 200
    And Save the token from the response to local storage

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "PUT" request to "/employee/update" with body:
      """
      {
        "email": "test92@test.com",
        "password": "test",
        "full_name": "Ini nama yg udh diupdate ya",
        "department": "Tech",
        "title": "Backend Engineer"
      }
      """
    Then The response status must be 200
    And Full name in the response must be "Ini nama yg udh diupdate ya"
    And Department in the response must be "Tech"
    And Title in the response must be "Backend Engineer"

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "GET" request to "/employee/get" with body:
      """
      {}
      """
    Then The response status must be 200
    And Full name in the response must be "Ini nama yg udh diupdate ya"
    And Department in the response must be "Tech"
    And Title in the response must be "Backend Engineer"

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "DELETE" request to "/employee/delete" with body:
      """
      {}
      """
    Then The response status must be 200
