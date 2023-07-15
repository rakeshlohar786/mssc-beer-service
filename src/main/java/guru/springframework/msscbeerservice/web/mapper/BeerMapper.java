package guru.springframework.msscbeerservice.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.springframework.stereotype.Component;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;

//@Component
@Mapper( uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);
	
	BeerDto beerToBeerDtoWithInventory(Beer beer);
	
	Beer beerDtoToBeer(BeerDto dti);
	
	
}
