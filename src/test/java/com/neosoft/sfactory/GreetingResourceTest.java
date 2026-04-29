package com.neosoft.sfactory;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@Transactional
@QuarkusTest
class GreetingResourceTest {

@Test
    void testAddUser() {
        // Le payload JSON correspondant à ton record UtilisateurBody
        String jsonInput = """
            {
                "nom": "Dupont",
                "prenom": "Jean"
            }
            """;

        given()
          .contentType(ContentType.JSON)
          .body(jsonInput)
        .when()
          .post("/utilisateurs")
        .then()
          .statusCode(200)
          // On vérifie que la réponse JSON contient les bonnes valeurs
          .body("nom", is("Dupont"))
          .body("prenom", is("Jeanz"));
    }

    @Test
    void testGetAllUsers() {
        given()
          .when().get("/utilisateurs")
          .then()
             .statusCode(200);
    }
}