package guru.springframework.msscbeerservice.web.controller;


import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.txw2.Document;

import guru.springframework.msscbeerservice.domain.Beer;

import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;

import org.apache.catalina.connector.ResponseFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

//import static org.mockito.Mockito.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; - commented for documentation
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "guru.springframework.msscbeerservice.web.mapper")
public class BeerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerRepository beerRepository;

	@Test
	void getBeerById() throws Exception {
		
		given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));
	
	/** Without documentation code code 
		mockMvc.perform(get("/api/v1/beer/"+UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	__**/
		
	 //modified for documentation
	
		mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
		.param("isCold", "yes")		
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(document("v1/beer",pathParameters(
				parameterWithName("beerId").description("UUID for desired beer to get.")
				),
				 requestParameters(
						parameterWithName("isCold").description("Is Beer Cold Param")
					),
				 
				 responseFields(
						 fieldWithPath("id").description("Id of Beer"),
						 fieldWithPath("version").description("Version Number"),
						 fieldWithPath("createdDate").description("Date Created"),
						 fieldWithPath("lastModifiedDate").description("Date Updated"),
						 fieldWithPath("beerName").description("Beer Name"),
						 fieldWithPath("beerStyle").description("Beer Style"),
						 fieldWithPath("upc").description("UPC of Beer"),
						 fieldWithPath("price").description("Price"),
						 fieldWithPath("quantityOnHand").description("Quantity On Hand")
						 )));
		

		
	
	}
	
	@Test
	void saveNewBear() throws Exception {
		
		BeerDto beerDto = getValidBeerDto();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		/**code before API documentation
		mockMvc.perform(post("/api/v1/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.contentType(beerDtoJson))
				.andExpect(status().isCreated());
		**/	
		
		//code after API documentation
		mockMvc.perform(post("/api/v1/beer/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isCreated())
				.andDo(document("v1/beer", 
						requestFields(
							fieldWithPath("id").ignored(),
							fieldWithPath("version").ignored(),
							fieldWithPath("createdDate").ignored(),
							fieldWithPath("lastModifiedDate").ignored(),
							fieldWithPath("beerName").description("Name of the beer"),
							fieldWithPath("beerStyle").description("Style of Beer").attributes(),
							 fieldWithPath("upc").description("UPC of Beer"),
							fieldWithPath("price").description("Beer Price"),
							fieldWithPath("quantityOnHand").ignored()
								)
						));
		
		
		
	}
	
	@Test
	void updateBeerById()throws Exception {
		
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(put("/api/v1/beer"+UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isNoContent());
				
	
	}
	
	 BeerDto getValidBeerDto(){
		 
	        return BeerDto.builder()
	                .beerName("Nice Ale")
	                .beerStyle(BeerStyleEnum.ALE)
	                .price(new BigDecimal("9.99"))
	                .upc(123123123123L)
	                .build();

	    }
	

	
}