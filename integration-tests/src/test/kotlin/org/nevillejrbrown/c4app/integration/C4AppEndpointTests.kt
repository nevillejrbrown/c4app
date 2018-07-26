package org.nevillejrbrown.c4app.integration

import cucumber.api.PendingException
import cucumber.api.java.en.And
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.specification.RequestSpecification
import org.assertj.core.api.Assertions.assertThat
import org.nevillejrbrown.c4app.SpringBootKotlinApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import javax.annotation.PostConstruct


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

    private var gameId:Int? = null

    @PostConstruct
    fun setup() {
        requestSpecification = RequestSpecBuilder()
                .setContentType(ContentType.fromContentType(JSON_API_CONTENT_TYPE))
                .setBaseUri("http://localhost:$randomServerPort")
                .build()
    }

    fun getFromURL(url: String) {
        response = RestAssured
                .given()
                .log().all()
                .spec(requestSpecification)
                .`when`().get(url)
                .then().extract()
    }

    fun postJSON(uri: String, requestBody: String) {
        response = RestAssured
                .given()
                .spec(requestSpecification)
                .body(requestBody)
                .`when`().post(uri)
                .then().extract()
    }

    fun extractIdFromResponse() {
        gameId = response?.jsonPath()?.get<Int>("gameId")
    }

    fun responseIsCorrect(jsonSlurper: String, value: String) {
        assertThat(response?.jsonPath()?.get<Int>(jsonSlurper)).isEqualTo(1)
    }

    fun responseStatusIsCorrectInResponse(expectedStatus: Int) {
        assertThat(response?.statusCode())
                .isEqualTo(expectedStatus)
    }

    @When("^I create a game$")
    fun createNewGame() {
        // Write code here that turns the phrase above into concrete actions
        postJSON("/apis/v1/api/games/", "")
        checkAndStoreGameId()
    }

    @Then("^I receive an identifier for a game$")
    fun checkAndStoreGameId() {
        responseStatusIsCorrectInResponse(201)
        extractIdFromResponse()
        assertThat(response?.jsonPath()?.get<Int>("gameId")).isNotNull()
    }


    @Then("^I can retrieve an initialised game with that identifier$")
    fun retrieveGameJustCreated() {
        // Write code here that turns the phrase above into concrete actions
        getFromURL("/apis/v1/api/games/" + gameId)
        assertThat(response?.jsonPath()?.get<Int>("gameId")).isNotNull()

    }


}
