package br.ce.tiagoaf.CursoRest;

import static io.restassured.RestAssured.*;



import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test 
	public void testOlaMundo() {
	    
		Response response = request(Method.GET,"http://restapi.wcaquino.me:80/ola");
	    
	    Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
	    Assert.assertTrue(response.statusCode() == 200);
	    Assert.assertTrue("O status code deveria ser 200", response.statusCode()== 200);
	    Assert.assertEquals(200, response.statusCode());
	    
	    
	    ValidatableResponse validacao = response.then();
	    validacao.statusCode(200);
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		
		Response response = request(Method.GET,"http://restapi.wcaquino.me/ola");
	    
	    ValidatableResponse validacao = response.then();
	    validacao.statusCode(200);
	    
	    get("http://restapi.wcaquino.me/ola").then().statusCode(200);
	    
        RestAssured.given().when().get("http://restapi.wcaquino.me/ola").then()
	    .statusCode(200);
		
	}



}
