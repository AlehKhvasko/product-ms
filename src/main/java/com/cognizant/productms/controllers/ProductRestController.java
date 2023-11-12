package com.cognizant.productms.controllers;

import com.cognizant.productms.dto.Coupon;
import com.cognizant.productms.model.Product;
import com.cognizant.productms.repository.ProductRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/productapi")
public class ProductRestController {
    private RestTemplate restTemplate;

    @Value("${couponService.url}")
    private String couponServiceUrl;
    private ProductRepoInterface productRepoInterface;

    public ProductRestController(RestTemplate restTemplate, ProductRepoInterface productRepoInterface) {
        this.restTemplate = restTemplate;
        this.productRepoInterface = productRepoInterface;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        Coupon coupon = restTemplate.getForObject(couponServiceUrl + product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return  productRepoInterface.save(product);
    }


}
