package com.product.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.controller.DTO.OrderDTO;
import com.product.manager.FeignOrderManager;



@RestController
@RequestMapping("/ordersFeignClient")
public class FeignClientOrderController {
	@Autowired
	FeignOrderManager feignOrderManager;
	
	
	//creates new order in DB if it valid list of OrderDTO
	@PostMapping(value="/createOrder")
	public boolean createOrder(@RequestBody @Valid List<OrderDTO> data ) {
		feignOrderManager.saveOrderData(data);
		
		return true;
		
	}
		
	//Retrives all order deatils from H2 DB , uses feign client to fetch order item details from /orderItem service
	@GetMapping(value="/getOrderDetails")
	public List<OrderDTO> getOrders(){
		return feignOrderManager.getOrders();
	}
	
	@GetMapping(value="/getOrder")
	public OrderDTO getOrder(@RequestParam("orderId") @NotBlank Integer orderId) {
		return feignOrderManager.getOrder(orderId);
		
	}
}
