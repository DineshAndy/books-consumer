package com.qaautomated;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaautomated.controller.LibraryController;
import com.qaautomated.controller.ProductsPrices;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
public class FirstPactTest {
	
	@Autowired
	private LibraryController libraryC;
	
	//Mock Pact Server created for the below Test
	@Pact(consumer="BooksDetails2", provider = "toysDetails2")
	public RequestResponsePact pactAllToysDetails(PactDslWithProvider builder) {
		
		 return builder.given("Toys Details")
		.uponReceiving("Getting all toys details")
		.path("/allToysDetails")
		.willRespondWith()
		.status(200)
		.body(PactDslJsonArray.arrayMinLike(2)
				.stringType("toys_name")
				.stringType("id")
				.integerType("price",100)
				.stringType("category").closeObject()).toPact();
	}
	
	@Test
	@PactTestFor(pactMethod="pactAllToysDetails", port = "9191")
	public void testPrice(MockServer mockServer) throws JsonMappingException, JsonProcessingException {
		
		String expectedjson = "{\"booksPrice\":100,\"toysPrice\":200}";
		//creating the Pact Mockservice URL instead of getting it from actual Provider
		libraryC.setBaseUrl(mockServer.getUrl());
		ProductsPrices pp = libraryC.getProductPrices();
		ObjectMapper obj = new ObjectMapper();
		String actualjson = obj.writeValueAsString(pp);
		Assertions.assertEquals(expectedjson, actualjson);
		
	}


}
