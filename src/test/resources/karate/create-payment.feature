Feature: Create a single payment

Scenario: Create a single payment
  Given url paymentsUrl
  And request read('classpath:request-create-payment.json')
  When method post
  Then status 201
  And match responseHeaders['Location'] == '#notnull'
  And def detailsUrl = responseHeaders['Location'][0]
  And def id = response.data.id