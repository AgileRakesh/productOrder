package com.product.controller.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.product.controller.OrderItemController;
import com.product.controller.DTO.OrderItemDTO;
import com.product.manager.OrderItemManager;
import static org.mockito.BDDMockito.*;


@WebMvcTest(controllers = OrderItemController.class)
@ActiveProfiles("test")
public class OrderItemControllerTest {
	@Autowired                           
    private MockMvc mockMvc;  
                                                 
    @MockBean                           
    private OrderItemManager orderItemManager; 
                                               
    private List<OrderItemDTO> orderItemLIst;   
    
    private OrderItemDTO orderItemDTO ;
    
    private static String ORDER_JSON_STRING="[ { \"productCode\": 25, \"productName\": \"pump\", \"quantity\": 3 }, { \"productCode\": 789, \"productName\": \"mobile\", \"quantity\": 1 } ]";

    @BeforeEach                           
    void setUp() {                               
       this.orderItemLIst = new ArrayList<>();                                
       OrderItemDTO order=new OrderItemDTO();
       order.setOrderItemId(35);
       this.orderItemDTO=order;
       orderItemLIst.add(order);
 
    }
    
    @Test
    public void test_getOrderItems() throws Exception {
    	given(orderItemManager.getOrderItems()).willReturn(orderItemLIst);
    	this.mockMvc.perform(get("/orderItem/getOrderItemDetails")).andExpect(status().isOk()).andExpect(jsonPath("$.size()",is(orderItemLIst.size())));
    	
    }
    
    @Test
    public void test_getOrderItem() throws Exception {
    	given(orderItemManager.getOrderItem(35)).willReturn(orderItemDTO);
    	this.mockMvc.perform(get("/orderItem/getOrderItem").param("itemId", "35")).andExpect(status().isOk())
    	.andExpect(jsonPath("$.orderItemId", is(orderItemDTO.getOrderItemId())));
    	
    }
    
    @Test
    public void test_createOrderItems() throws Exception {
    	
    	given(orderItemManager.saveOrderItemData(orderItemLIst)).willReturn(true);
    	this.mockMvc.perform(post("/orderItem/createOrderItem").contentType("application/json")
    			.content(ORDER_JSON_STRING))
    			.andExpect(status().isOk());
    	
    }
    @Test
    public void test_createOrderItems_UnsupportedMediaType() throws Exception {
    	
    	given(orderItemManager.saveOrderItemData(orderItemLIst)).willReturn(true);
    	this.mockMvc.perform(post("/orderItem/createOrderItem").contentType("application/xml")
    			.content(ORDER_JSON_STRING))
    			.andExpect(status().isUnsupportedMediaType());
    	
    }
    
    @Test
    public void test_getOrderItem_Badrequest() throws Exception {
    	given(orderItemManager.getOrderItem(35)).willReturn(orderItemDTO);
    	this.mockMvc.perform(get("/orderItem/getOrderItem")).andExpect(status().isBadRequest());
    	
    }
      
    
}
