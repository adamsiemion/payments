function() {
    return {
        paymentsUrl: 'http://localhost:' + (karate.properties['port'] || 8080) + '/v1/payments'
    };
}