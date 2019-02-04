Feature: Update a payment (PUT /payments/{id})

Background:
  * call read('classpath:karate/create-payment.feature')

  Given url detailsUrl
  And request read('classpath:request-update-payment.json')
  When method put
  Then status 200

Scenario: Get should return the details
  Given url detailsUrl
  When method get
  Then status 200
  And match response.data == read('classpath:response-payment-updated.json')
  And match response.data.id == id

Scenario: Get should return version 1
  Given url detailsUrl
  When method get
  Then status 200
  And match response.data.version == 1

Scenario: List should include
  Given url paymentsUrl
  When method get
  Then status 200
  And match response.data contains read('classpath:response-payment-updated.json')
  And match response.links.self == paymentsUrl

Scenario: ANother update with the same version should fail
  Given url detailsUrl
  And request read('classpath:request-update-payment.json')
  When method put
  Then status 409 # conflict