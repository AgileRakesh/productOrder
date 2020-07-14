package com.product.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.controller.DTO.OrderDTO;
import com.product.manager.FeignOrderManager;



@RestController
@RequestMapping("/ordersFeignClient")
public class FeignClientOrderController {
	
	@Autowired
	FeignOrderManager feignOrderManager;
	
	/**
	 * this method creates new order in Database
	 * @param data type- OrderDTO
	 * @return true if data successfully stored in database otherwise false
	 */
	@RequestMapping(value="/createOrder",method = RequestMethod.POST)
	public boolean createOrder(@RequestBody @Valid List<OrderDTO> data ) {
		feignOrderManager.saveOrderData(data);
		return true;	
	}
		
	
	/**
	 * Retrieves all order details from H2 Database , uses feign client to fetch order item details from /orderItem service
	 * @return list of orders
	 */
	@RequestMapping(value="/getOrderDetails",method = RequestMethod.GET)
	public List<OrderDTO> getOrders(){
		return feignOrderManager.getOrders();
	}
	
	
	/**
	 * Fetches order details for given  order id
	 * @param orderId
	 * @return order details in OrderDTO type
	 */
	@RequestMapping(value="/getOrder",method = RequestMethod.GET)
	public OrderDTO getOrder(@RequestParam("orderId") @NotBlank Integer orderId) {
		return feignOrderManager.getOrder(orderId);
		
	}
}
