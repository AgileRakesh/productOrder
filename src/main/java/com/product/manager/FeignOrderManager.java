package com.product.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.controller.DTO.OrderDTO;
import com.product.controller.DTO.OrderItemDTO;
import com.product.dao.OrderDetailsDAO;
import com.product.feign.proxy.OrderItemsUsingFeign;



@Component
public class FeignOrderManager {
	@Autowired
	OrderDetailsDAO orderDetails;
	
	@Autowired
	private OrderItemsUsingFeign orderItemsUsingFeign;
	
	public boolean saveOrderData(List<OrderDTO> list) {
		orderDetails.insertOrderData(list);
		return true;
	}
	
	public List<OrderDTO> getOrders(){
		
		List<OrderDTO> orderList= orderDetails.getAllOrders();
		//iterate for every order and get order items from /getOrderItem service by passing item id
		orderList.forEach(order->{
			OrderItemDTO orderItem=getOrderItem(order.getOrderItemIds());
			List<OrderItemDTO> itemList=new ArrayList<>();
			itemList.add(orderItem);
			order.setOrderItems(itemList);
		});
		
		return orderList;
	}
	
	public OrderItemDTO getOrderItem(Integer id) {
		OrderItemDTO ob1=orderItemsUsingFeign.getOrderItem(id);
		return ob1;
		
	}
	
	public OrderDTO getOrder(Integer id) {
		List<OrderItemDTO> itemList=new ArrayList<>();
		
		OrderDTO order= orderDetails.getOrder(id);
		itemList.add(getOrderItem(order.getOrderItemIds()));
		order.setOrderItems(itemList);
		return order;
		
	}

}
