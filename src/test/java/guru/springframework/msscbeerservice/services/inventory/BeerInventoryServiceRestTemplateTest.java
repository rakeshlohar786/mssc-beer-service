package guru.springframework.msscbeerservice.services.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;

@Disabled
@SpringBootTest
public class BeerInventoryServiceRestTemplateTest {

	@Autowired
	BeerInventoryService beerInventoryService;
	
	@BeforeEach
	void setUp() {
		
	}
	
	@Test
	void getOnHandInventory() {
		System.out.println("Test RFamn");
		Integer qoh = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);
		System.out.print("Qty "+qoh);
		System.out.println(qoh);
		
	}
	
}
