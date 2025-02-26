package com.example.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.clients.ProductClient;
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
	
	@Autowired
	private ProductClient productClient;

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

	@Override
	public ShopKeeperDto getProductsOfShopkeepr(String id) {
		ShopKeeper shopKeeper = getShopKeeperById(id);
		ShopKeeperDto dto = modelMapper.map(shopKeeper, ShopKeeperDto.class);
		
//		List<ProductResponse> dtos = getResponseByRestTemplate(id);
		
//		List<ProductResponse> dtos = getResponseByWebClient(id);
		
		List<ProductResponse> dtos = getResponseByFeignClient(id);
		
		dto.setProductResponse(dtos);
		return dto;
	}

//	I. REST communication using RestTemplate
	List<ProductResponse> getResponseByRestTemplate(String id){
		System.err.println("I. REST communication using RestTemplate");
		String url = BASE_URL+"/product/shopkeeper/" + id;
		List<ProductResponse> productResponse = restTemplate.getForObject(url, List.class);
		return productResponse;
	}
	
//	II. REST Communication using WebClient	
	public List<ProductResponse> getResponseByWebClient(String id){
		System.err.println("II. REST communication using WebClient");
		String url = BASE_URL+"/product/shopkeeper/" + id;
		return  (List<ProductResponse>)webClient.get().uri(url).retrieve().bodyToMono(List.class).block();
	}
	
//	III. REST communication using FeignClient
	public List<ProductResponse> getResponseByFeignClient(String id){
		System.err.println("III. REST communication using FeignClient");
		return  (List<ProductResponse>) productClient.getProductByShopkeeperId(id).getBody();
	}
	

}