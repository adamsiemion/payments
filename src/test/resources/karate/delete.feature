Feature: Delete a payment (DELETE /payments/{id})

Background:
  * call read('classpath:karate/create-payment.feature')

  Given url detailsUrl
  When method delete
  Then status 200

Scenario: Get to the same url should return 404
  Given url detailsUrl
  When method get
  Then status 404

Scenario: List should not include the deleted record
  Given url paymentsUrl
  When method get
  Then match response.data[*].id !contains id