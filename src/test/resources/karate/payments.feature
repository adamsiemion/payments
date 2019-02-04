Feature: Testing Payments REST API

Background:
* def baseUrl = 'http://localhost:' + (karate.properties['port'] || 8080) + '/v1/payments'
* def listUrl = baseUrl
* def createUrl = baseUrl

@Ignore
Scenario: As a REST API user I want to create a new payment
  Given url createUrl
  And request read('classpath:request-create-payment.json')
  When method post
  Then status 201
  And match responseHeaders['Location'] == '#notnull'

  Given def detailsUrl = responseHeaders['Location'][0]
  And url detailsUrl
  When method get
  Then status 200
  And match response == read('classpath:response-get-payment.json')
  And match response.data == read('classpath:response-payment.json')
  And match response.links.self == detailsUrl

  Given url listUrl
  When method get
  Then status 200
  And match response == read('classpath:response-list-payment.json')
  And match response.data contains read('classpath:response-payment.json')
  And match response.links.self == listUrl

@Ignore
Scenario: Create a payment, update it and verify its details are correctly returned
  Given url createUrl
  And request read('classpath:request-create-payment.json')
  When method post
  Then status 201
  And match responseHeaders['Location'] == '#notnull'

  Given def detailsUrl = responseHeaders['Location'][0]
  And def id = detailsUrl.substring(detailsUrl.lastIndexOf('/') + 1)
  And url detailsUrl
  And request read('classpath:request-update-payment.json')
  When method put
  Then status 200
  And match response == read('classpath:response-get-payment.json')
  And match response.data == read('classpath:response-payment-updated.json')
  And match response.data.id == id
  And match response.links.self == detailsUrl

  Given url detailsUrl
  When method get
  Then status 200
  And match response == read('classpath:response-get-payment.json')
  And match response.data == read('classpath:response-payment-updated.json')
  And match response.data.id == id
  And match response.links.self == detailsUrl

  Given url listUrl
  When method get
  Then status 200
  And match response == read('classpath:response-list-payment.json')
  And match response.data contains read('classpath:response-payment-updated.json')
  And match response.links.self == listUrl

  Given url detailsUrl
  And request read('classpath:request-update-payment.json')
  When method put
  Then status 409 # conflict

@Ignore
Scenario: Create a payment, delete it and verify its details are correctly returned
  Given url createUrl
  And request read('classpath:request-create-payment.json')
  When method post
  Then status 201
  And match responseHeaders['Location'] == '#notnull'

  Given def detailsUrl = responseHeaders['Location'][0]
  And def id = detailsUrl.substring(detailsUrl.lastIndexOf('/') + 1)
  And url detailsUrl
  When method delete
  Then status 200

  Given url detailsUrl
  When method get
  Then status 404

  Given url listUrl
  When method get
  Then match response.data[*].id !contains id