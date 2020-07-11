package com.product.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.controller.DTO.OrderItemDTO;



@FeignClient(url="http://localhost:8080/orderItem",name="orderFeign")
public interface OrderItemsUsingFeign {
	
	@GetMapping(value="/getOrderItem")
	public OrderItemDTO getOrderItem(@RequestParam(value="itemId") Integer id);

}
