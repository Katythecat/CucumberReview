Feature: test the login functionality

  @login
  Scenario: validate admin login
    #Given the user navigates to the url
    When user enters a valid email and password
    And clicks on Login Button
    Then the user is logged in

  Scenario: validate invalid admin login when username is incorrect
    Given the user navigates to the url
    When user enters a username "XYZA" and password "Hum@nhrm123"
    And clicks on Login Button
    Then the user is not logged in

  Scenario: validate invalid admin login when password is incorrect
    Given the user navigates to the url
    When user enters a username "Admin" and password "abracadbra"
    And clicks on Login Button
    Then the user is not logged in

  Scenario: validate invalid admin login when password is empty and user name is empty
    Given the user navigates to the url
    When user enters a username "" and password ""
    And clicks on Login Button
    Then the user is not logged in


  Scenario: validate invalid admin login when password is empty and user name is ADMIN
    Given the user navigates to the url
    When user enters a username "Admin" and password ""
    And clicks on Login Button
    Then the user is not logged in


  @outline
  Scenario Outline:validate the wrong credentials error message
    When user enters the "<username>" ane "<password>"
    And clicks on Login Button
    Then user see a message "<expectedErrorMsg>"
    Examples:
      | username | password    | expectedErrorMsg                 |
      | admin    | ssdad       | Invalid credentials      |
      |          |             | Username cannot be empty |
      |          | Hum@nhrm123 | Username cannot be empty |


