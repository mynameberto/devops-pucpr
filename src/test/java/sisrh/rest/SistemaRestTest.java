package sisrh.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
public class SistemaRestTest {

    @Test
    public void testPingEndpointStatusAndContent() {
        given()
                .baseUri("http://localhost:8080/sisrh/rest")
                .when()
                .get("/sistema/ping")
                .then()
                .statusCode(200)
                .body(containsString("pong"));
    }

    @Test
    public void testPingUuidFormat() {
        String response =
                given()
                        .baseUri("http://localhost:8080/sisrh/rest")
                        .when()
                        .get("/sistema/ping")
                        .then()
                        .extract().body().asString();

        String uuid = response.split(": ")[1];
        assertTrue(uuid.matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
    }

    @Test
    public void testDataHoraFormat() {
        given()
                .baseUri("http://localhost:8080/sisrh/rest")
                .when()
                .get("/sistema/datahora")
                .then()
                .statusCode(200)
                .body(matchesPattern("\\d{2}/\\d{2}/\\d{4} - \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    public void testEndpointInexistente() {
        given()
                .baseUri("http://localhost:8080/sisrh/rest")
                .when()
                .get("/sistema/naoexiste")
                .then()
                .statusCode(404);
    }

    @Test
    public void testContentType() {
        given()
                .baseUri("http://localhost:8080/sisrh/rest")
                .when()
                .get("/sistema/datahora")
                .then()
                .contentType(ContentType.TEXT);
    }
}