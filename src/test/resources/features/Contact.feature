@frontend
Feature: Form tests on the Contact pages

  Scenario: Send correct content in the contact form
    Given I visit "CONTACT_PAGE"
    And I allow all cookies
    And I write "Test" in the "FIRST_NAME"
    And I write "Test" in the "LAST_NAME"
    And I write "test@company.com" in the "EMAIL_ADDRESS"
    And I write "1234567890" in the "PHONE_NUMBER"
    And I write "Test" in the "MESSAGE"
    And I select "Support" in the "SUBJECT"
    When I click on the "SUBMIT_BUTTON"
    And I solve the CAPTCHA
    Then I receive a success message "Your message was validated successfully. (Test only — no request was sent.)" in the "Success area" element on the contact page

  Scenario: Send form submission with required fields missing
    Given I visit "CONTACT_PAGE"
    And I allow all cookies
    When I click on the "SUBMIT_BUTTON"
    Then I receive the required message in the "FIRST_NAME"
    And I receive the validation message "First name is required" in the "FIRST_NAME"

    Given I write "Test" in the "FIRST_NAME"
    When I click on the "SUBMIT_BUTTON"
    Then I receive the required message in the "LAST_NAME"
    And I receive the validation message "Last name is required" in the "LAST_NAME"

    Given I write "Test" in the "LAST_NAME"
    When I click on the "SUBMIT_BUTTON"
    And I receive the required message in the "EMAIL_ADDRESS"
    And I receive the validation message "Email is required" in the "EMAIL_ADDRESS"

    Given I write "test@company.com" in the "EMAIL_ADDRESS"
    When I click on the "SUBMIT_BUTTON"
    And I receive the required message in the "MESSAGE"
    And I receive the validation message "Message is required" in the "MESSAGE"

  Scenario Outline: Send incorrect content in the email field: <content>
    Given I visit "CONTACT_PAGE"
    And I allow all cookies
    And I write <content> in the "EMAIL_ADDRESS"
    When I click on the "SUBMIT_BUTTON"
    Then I receive the validation message "Enter a valid email" in the "EMAIL_ADDRESS"
    Then I receive the invalid message in the "EMAIL_ADDRESS"
    Examples:
      | content           |
      | "test"            |
      | "test@"           |
    # | "test@test"           | # The scenario will not run because we have an issue here
    # | "test@test.test"      | # The scenario will not run because we have an issue here
      | "test@test.test@" |

  Scenario Outline: Send incorrect content in the phone field: <content>
    Given I visit "CONTACT_PAGE"
    And I allow all cookies
    And I write "Test" in the "FIRST_NAME"
    And I write "Test" in the "LAST_NAME"
    And I write "test@company.com" in the "EMAIL_ADDRESS"
    And I write "Test" in the "MESSAGE"
    And I write <content> in the "PHONE_NUMBER"
    When I click on the "SUBMIT_BUTTON"
    Then I receive the validation message "Invalid phone format" in the "PHONE_NUMBER"
    Then I receive the invalid message in the "PHONE_NUMBER"
    Examples:
      | content    |
      | "1"        |
      | "123456"   |
      | "abc"      |
      | "1234567a" |
