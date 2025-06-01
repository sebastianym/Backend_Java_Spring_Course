package com.giweb.supply.business.primertallergiweb.service;

import com.giweb.supply.business.primertallergiweb.dto.CartDto;
import com.giweb.supply.business.primertallergiweb.dto.ProductDto;
import com.giweb.supply.business.primertallergiweb.dto.UserDto;
import com.giweb.supply.business.primertallergiweb.dto.UserLoginRequestDto;
import com.giweb.supply.business.primertallergiweb.dto.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeStoreApiService {
    private static final String BASE_URL = "https://fakestoreapi.com";
    private final RestTemplate restTemplate;

    public String login(UserLoginRequestDto loginRequest) {
        String url = BASE_URL + "/auth/login";
        try {
            ResponseEntity<UserLoginResponseDto> response = restTemplate.postForEntity(url, loginRequest, UserLoginResponseDto.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().getToken();
            }
        } catch (HttpClientErrorException e) {
            return null;
        }
        return null;
    }

    public List<ProductDto> getAllProducts() {
        String url = BASE_URL + "/products";
        ProductDto[] products = restTemplate.getForObject(url, ProductDto[].class);
        return Arrays.asList(products);
    }

    public List<CartDto> getCartsByUserId(Long userId) {
        String url = BASE_URL + "/carts/user/" + userId;
        CartDto[] carts = restTemplate.getForObject(url, CartDto[].class);
        return Arrays.asList(carts);
    }

    public List<CartDto> getCartsByUserIdWithProducts(Long userId) {
        List<CartDto> carts = getCartsByUserId(userId);
        if (carts == null) return null;
        for (CartDto cart : carts) {
            double total = 0.0;
            if (cart.getProducts() != null) {
                for (CartDto.CartProductDto cartProduct : cart.getProducts()) {
                    ProductDto product = getProductById(cartProduct.getProductId());
                    cartProduct.setProduct(product);
                    if (product != null && cartProduct.getQuantity() != null) {
                        total += product.getPrice() * cartProduct.getQuantity();
                    }
                }
            }
            cart.setTotal(total);
        }
        return carts;
    }

    public ProductDto getProductById(Long id) {
        String url = BASE_URL + "/products/" + id;
        return restTemplate.getForObject(url, ProductDto.class);
    }

    public UserDto getUserById(Long id) {
        String url = BASE_URL + "/users/" + id;
        return restTemplate.getForObject(url, UserDto.class);
    }

    public Long getUserIdByUsername(String username) {
        String url = BASE_URL + "/users";
        UserDto[] users = restTemplate.getForObject(url, UserDto[].class);
        if (users != null) {
            for (UserDto user : users) {
                if (user.getUsername().equals(username)) {
                    return user.getId();
                }
            }
        }
        return null;
    }
}
