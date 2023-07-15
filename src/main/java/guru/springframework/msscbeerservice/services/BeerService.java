package guru.springframework.msscbeerservice.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import guru.springframework.msscbeerservice.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;

public interface BeerService {

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
	
	BeerDto getById(UUID beerId, Boolean showInventoryOnHand);
	
	BeerDto saveNewBeer(BeerDto beerDto);
	
	BeerDto updateBeer(UUID beerId, BeerDto beerDto);
	
	BeerDto getByUpc(String upc);
}
