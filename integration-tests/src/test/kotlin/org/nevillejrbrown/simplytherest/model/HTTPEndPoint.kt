package org.nevillejrbrown.simplytherest.model

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.specification.RequestSpecification
import org.springframework.boot.web.server.LocalServerPort
import javax.annotation.PostConstruct

class HTTPEndPoint(val baseURIString:String, val serverPort: Int) {
    private val JSON_API_CONTENT_TYPE = "application/vnd.api+json"

    lateinit var requestSpecification: RequestSpecification
    private var response: ExtractableResponse<*>? = null


    // TODO work out how to get this run
    @PostConstruct
    fun setup() {
        requestSpecification = RequestSpecBuilder()
                .setContentType(ContentType.fromContentType(JSON_API_CONTENT_TYPE))
                .setBaseUri("$baseURIString:$serverPort")
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

    fun patchJSON(uri: String, requestBody: String) {
        response = RestAssured
                .given()
                .spec(requestSpecification)
                .body(requestBody)
                .`when`().patch(uri)
                .then().extract()
    }



}