package guru.springframework.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner
{
	
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    
    private final BeerRepository beerRepository;
	
	@Override
	public void run(String... args) throws Exception {
	
		if(beerRepository.count() == 0) {
			loadBeerObjects();
		}
	}
	
	
	public void loadBeerObjects() {
		
		Beer b1 = Beer.builder()
				  .beerName("Mango Bobs")
				  .beerStyle(BeerStyleEnum.IPA.name())
				  .minOnHand(12)
				  .quantityToBrew(200)
				  .price(new BigDecimal("12.95"))
				  .upc(BEER_1_UPC)
				  .build();
		
		Beer b2 = Beer.builder()
				  .beerName("Pinball Porter")
				  .beerStyle(BeerStyleEnum.PALE_ALE.name())
				  .minOnHand(12)
				  .quantityToBrew(200)
				  .price(new BigDecimal("12.95"))
				  .upc(BEER_2_UPC)
				  .build();
		
		Beer b3 = Beer.builder()
				  .beerName("Pinbal Lovers")
				  .beerStyle(BeerStyleEnum.PALE_ALE.name())
				  .minOnHand(12)
				  .quantityToBrew(200)
				  .price(new BigDecimal("12.95"))
				  .upc(BEER_3_UPC)
				  .build();
		
		
		
		beerRepository.save(b1);
		beerRepository.save(b2);
		beerRepository.save(b3);
		
		System.out.println(b1.getId());
		System.out.println(b2.getId());
		System.out.println(b3.getId());
		
		System.out.println("Load Beers "+beerRepository.count() 
		+" \n Created Date: "+b1.getCreatedDate()
		+"\n Updated Date "+b1.getUpdatedDate()
				);
		
		
	}

	
}
