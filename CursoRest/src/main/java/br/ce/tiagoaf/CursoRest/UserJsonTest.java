package br.ce.tiagoaf.CursoRest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*
 * API JSON
 * {
 * "id": 1,
 * "name": "João da Silva",
 * "age": "30",
 * "salary": "1234.5678
 * },
 * 
 * {
 * "id": 2,
 * "name": "Maria Joaquina",
 * "endereco": {
 * 		"rua": "Rua dos bobos,
 * 		"numero": "0"
 *  * },
 * "age": "25",
 * "salary": "5678.1234"
 * },
 */

public class UserJsonTest {

	@Test
	public void deveVerificarPrimeiroNivel() {
		given()
		.when().get("https://restapi.wcaquino.me/users/1")
		.then()
		.statusCode(200)
		.body("id", is(1) )
		.body("name", containsString("Silva"))
		.body("age", greaterThan(18));
		
	}
	@Test 
	public void deveVerificarPrimeiroNivelOutrasFormas() {
		
		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");
		
		//path
		Assert.assertEquals(new Integer (1), response.path("id"));
		//parametro do tipo string
		Assert.assertEquals(new Integer (1), response.path("%s","id"));
		
		//Jsonpath
		JsonPath jpath = new JsonPath(response.asString());
		Assert.assertEquals(1, jpath.getInt("id"));
		
		//from
		int id = JsonPath.from(response.asString()).getInt("id");
		Assert.assertEquals(1,  id);
		
	}
	
	@Test
	public void deveVerificarSegundoNivel() {
		//verificar body json aninhado
		given()
		.when()
		.get("https://restapi.wcaquino.me/users/2")
		.then()
		.statusCode(200)
		.body("name", containsString("Joaquina"))
		.body("endereco.rua", is("Rua dos bobos"));
	}
	
}
