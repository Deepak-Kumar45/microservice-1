package com.example.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.ProductResponse;
import com.example.dto.ShopKeeperDto;
import com.example.entity.ShopKeeper;
import com.example.exceptions.ShopKeeperNotFoundException;
import com.example.repository.ShopKeeperRepository;
import com.example.service.ShopkeeperService;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Service
public class ShopkeeperServiceImpl implements ShopkeeperService {

	@Autowired
	private ShopKeeperRepository keeperRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${std-app.url}")
	private String BASE_URL;
	
	@Autowired
	private WebClient webClient;

	@Override
	public ShopKeeper addShopKeeper(ShopKeeperDto dto) {
		ShopKeeper keeper = modelMapper.map(dto, ShopKeeper.class);
		keeper.setShopKeeperId(UUID.randomUUID().toString());
		return keeperRepository.save(keeper);
	}

	@Override
	public ShopKeeper getShopKeeperById(String id) {
		return keeperRepository.findById(id).orElseThrow(() -> new ShopKeeperNotFoundException("ShopKeeper not found"));
	}

	@Override
	public List<ShopKeeper> getAllShopKeeper() {
		return keeperRepository.findAll();
	}

	@Override
	public ShopKeeper getShopKeeperByEmail(String email) {
		return keeperRepository.findByShopKeeperEmail(email)
				.orElseThrow(() -> new ShopKeeperNotFoundException("ShopKeeper not found with " + email));

	}

//	I. REST communication using RestTemplate

//	@Override
//	public ShopKeeperDto getProductsOfShopkeepr(String id) {
//		ShopKeeper shopKeeper = getShopKeeperById(id);
//		ShopKeeperDto dto = modelMapper.map(shopKeeper, ShopKeeperDto.class);
//		String url = BASE_URL+"product/shopkeeper/" + shopKeeper.getShopKeeperId();
//		List<ProductResponse> productResponse = restTemplate.getForObject(url, List.class);
//		dto.setProductResponse(productResponse);
//		return dto;
//	}
	
//	II. REST Communication using WebClient
	@Override
	public ShopKeeperDto getProductsOfShopkeepr(String id) {
		ShopKeeper shopKeeper = getShopKeeperById(id);
		ShopKeeperDto dto = modelMapper.map(shopKeeper, ShopKeeperDto.class);
		String url = BASE_URL+"product/shopkeeper/" + shopKeeper.getShopKeeperId();
		List<ProductResponse> dtos = webClient.get().uri(url).retrieve().bodyToMono(List.class).block();
		dto.setProductResponse(dtos);
		return dto;
	}

}
