Feature: Create a payment (POST /payments)

Scenario: Header location should return details of the newly created payment
  * call read('classpath:karate/create-payment.feature')

  Given url detailsUrl
  When method get
  Then status 200
  And match response == read('classpath:response-get-payment.json')
  And match response.data == read('classpath:response-payment.json')
  And match response.links.self == detailsUrl

Scenario: List endpoint should include the details of the newly created payment
  * call read('classpath:karate/create-payment.feature')

  Given url paymentsUrl
  When method get
  Then status 200
  And match response.data contains read('classpath:response-payment.json')
  And match response.data[*].id contains id
