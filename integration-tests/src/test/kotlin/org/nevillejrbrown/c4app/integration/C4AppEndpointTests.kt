package org.nevillejrbrown.c4app.integration

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers.contains
import org.nevillejrbrown.c4app.SpringBootKotlinApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import javax.annotation.PostConstruct
import org.assertj.core.api.Assertions.assertThat


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [SpringBootKotlinApplication::class])
@ContextConfiguration(classes = [SpringBootKotlinApplication::class])
class C4AppEndpointTests {
    private val JSON_API_CONTENT_TYPE = "application/vnd.api+json"

    @LocalServerPort
    lateinit var randomServerPort: Integer
    lateinit var requestSpecification: RequestSpecification

    private var response: ExtractableResponse<*>? = null

    @PostConstruct
    fun setup() {
        requestSpecification = RequestSpecBuilder()
                .setContentType(ContentType.fromContentType(JSON_API_CONTENT_TYPE))
                .setBaseUri("http://localhost:$randomServerPort")
                .build()
    }

    @When("^a GET request is made to the URI: \"([^\"]*)\"$")
    fun getFromURL(url: String) {
        response = RestAssured
                .given()
                .log().all()
                .spec(requestSpecification)
                .`when`().get(url)
                .then().extract()
    }

    @Given("I POST to endpoint \"([^\"]*)\"")
    fun postJSON(uri: String, requestBody: String) {
        response = RestAssured
                .given()
                .spec(requestSpecification)
                .body(requestBody)
                .`when`().post(uri)
                .then().extract()
    }



    @And("^response for JsonSlurper \"([^\"]*)\" returns \"([^\"]*)\"$")
    fun responseIsCorrect(jsonSlurper: String, value: String) {
        println("Got ID:::")
        print("Body:")
        println(response?.asString())
        print(response?.jsonPath()?.get<Int>(jsonSlurper))
        //response.jsonPath().
        assertThat(response?.jsonPath()?.get<Int>(jsonSlurper)).isEqualTo(1)
    }

    @And("^response has game id \"([^\"]*)\"$")
    fun gameWithIdReturned(gameId:Int) {
        assertThat(response?.jsonPath()?.get<Int>("gameId")).isEqualTo(gameId)
    }

    @And("^the api returns a (\\d+) code$")
    fun responseStatusIsCorrect(status: Int) {
        print("Body:")
        println(response?.asString())

        assertThat(response?.statusCode())
                .isEqualTo(status)
    }
}
