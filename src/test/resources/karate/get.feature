Feature: Get a payment (GET /payments/{id})

Scenario: Get the details of the newly created payment
  * call read('classpath:karate/create-payment.feature')

  Given url detailsUrl
  When method get
  Then status 200
  And match response == read('classpath:response-get-payment.json')
  And match response.data == read('classpath:response-payment.json')
  And match response.links.self == detailsUrl

Scenario: Return 404 for a non existing payment
  Given url paymentsUrl + '/no-such-id'
  When method get
  Then status 404

