package guru.springframework.msscbeerservice.services;

import java.io.Console;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService{

	private final BeerRepository beerRepository;
	
	private final BeerMapper beerMapper;
	
	@Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand) {
		System.out.println("It was called");
		BeerPagedList beerPagedList;
		Page<Beer> beerPage = null;
		
		if(beerName != null && beerStyle != null) {
		 if(!beerName.equals("")) {
			//search both 
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		 }
		}else if(beerName != null && beerStyle != null) {
			//search beer service by name
			 if(!beerName.equals("")) {
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
			 }
		}
		else if (beerStyle != null ) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
			beerPage = beerRepository.findAll(pageRequest);
		}	
		
		if(showInventoryOnHand) {
			beerPagedList =new BeerPagedList(beerPage
					.getContent()
					.stream()
					.map(beerMapper::beerToBeerDtoWithInventory)
					.collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(),
							beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		}else {
			beerPagedList =new BeerPagedList(beerPage
					.getContent()
					.stream()
					.map(beerMapper::beerToBeerDto)
					.collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(),
							beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		}
		
		return beerPagedList;
	}
	
	
	 @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
		 System.out.println("It was called");
	if(showInventoryOnHand) {
		return beerMapper.beerToBeerDtoWithInventory(
				beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
	}else
	{
		return beerMapper.beerToBeerDto(
				beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
	}
	
	}


	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		System.out.println("BeerServiceImple -  saveBeer() called  called");
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());
		
		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}


	


	@Override
	public BeerDto getByUpc(String upc) {
		System.out.println("BeerServiceImple -  getByUpc() called");

		return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
	}

	
}
