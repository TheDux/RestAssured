package br.ce.tiagoaf.CursoRest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;


//GET na API e printando o body

public class App 
{
    public static void main( String[] args )
    {
    	
    //cria o objeto emcima da request do restAssured
    Response response = RestAssured.request(Method.GET, 
    		"http://restapi.wcaquino.me/ola");
    //printa o body no console
    
    //DA O GET E PRINTA O STATUS
    System.out.println(response.getBody().asString());
    System.out.println(response.statusCode());
    
    //VERIFICA O BODY E STATUS CODE
    System.out.println(response.getBody().asString().equals("Ola Mundo!"));
    System.out.println(response.statusCode() == 200);
    

    }
}
