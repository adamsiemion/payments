package com.siemion.payments;

import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentsEnd2EndTestIT {

    @LocalServerPort
    Integer port;

    @RunWith(Karate.class)
    @CucumberOptions(features = "classpath:karate", tags = "~@Ignore")
    public static class KarateTest {

    }

    @Test
    public void test() {
        if (port != null) {
            System.setProperty("port", String.valueOf(port));
        }
        assert(JUnitCore.runClasses(KarateTest.class).wasSuccessful());
    }

}