package org.nevillejrbrown.c4app.integration
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/features"],
        glue = ["org.nevillejrbrown.c4app.integration"],
        plugin = ["json:build/output/cucumber/cucumber.json"]
        )
class CucumberTest
