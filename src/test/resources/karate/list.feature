Feature: Get payments (GET /payments)

Scenario: List endpoint should contain format
  Given url paymentsUrl
  When method get
  Then status 200
  And match response == read('classpath:response-list-payment.json')
  And match response.links.self == paymentsUrl
