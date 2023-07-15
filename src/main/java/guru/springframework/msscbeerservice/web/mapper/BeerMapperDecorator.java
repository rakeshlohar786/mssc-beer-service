package guru.springframework.msscbeerservice.web.mapper;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.springframework.beans.factory.annotation.Autowired;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;

public abstract class BeerMapperDecorator implements BeerMapper  {

	private BeerInventoryService beerInventoryService;
	private BeerMapper beerMapper;
	
	@Autowired
	public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
		this.beerInventoryService = beerInventoryService;
	}
	
	@Autowired
	public void setBeerMapper(BeerMapper beerMapper) {
		this.beerMapper = beerMapper;
	}

	@Override
	public BeerDto beerToBeerDto(Beer beer) {
			return beerMapper.beerToBeerDto(beer);
	}

	
	@Override
	public Beer beerDtoToBeer(BeerDto dto) {
		return beerMapper.beerDtoToBeer(dto);
	}

	@Override
	public BeerDto beerToBeerDtoWithInventory(Beer beer) 
	{
		
		System.out.println("Called");
		BeerDto dto= beerMapper.beerToBeerDto(beer);
		dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
		return dto;

	}
	
	

	
	
}