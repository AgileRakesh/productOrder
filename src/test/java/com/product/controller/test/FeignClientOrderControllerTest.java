package com.product.controller.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.product.controller.FeignClientOrderController;
import com.product.controller.DTO.OrderDTO;
import com.product.manager.FeignOrderManager;

@WebMvcTest(controllers = FeignClientOrderController.class)
@ActiveProfiles("test")
public class FeignClientOrderControllerTest {

	@Autowired                           
    private MockMvc mockMvc;  
                                                 
    @MockBean                           
    private FeignOrderManager feignOrderManager; 
                                               
    private List<OrderDTO> orderLIst;   
    
    private OrderDTO orderDTO ;
    
    private static String ORDER_JSON_STRING="[ { \"customerName\":\"ram\", \"shippingAddress\":\"chengal, hyderabad, Telangal-10\", \"total\":2563, \"orderItemIds\":23 } ]";

    @BeforeEach                           
    void setUp() {                               
       this.orderLIst = new ArrayList<>();                                
       OrderDTO order=new OrderDTO();
       order.setOrderId(5);
       this.orderDTO=order;
       orderLIst.add(order);
 
    }
    
    @Test
    public void test_get_All_Orders() throws Exception {
    	given(feignOrderManager.getOrders()).willReturn(orderLIst);
    	this.mockMvc.perform(get("/ordersFeignClient/getOrderDetails")).andExpect(status().isOk()).andExpect(jsonPath("$.size()",is(orderLIst.size())));
    	
    }
    
    @Test
    public void test_getOrderItem() throws Exception {
    	given(feignOrderManager.getOrder(5)).willReturn(orderDTO);
    	this.mockMvc.perform(get("/ordersFeignClient/getOrder").param("orderId", "5")).andExpect(status().isOk())
    	.andExpect(jsonPath("$.orderId", is(orderDTO.getOrderId())));
    	
    }
    
    @Test
    public void test_createOrderItems() throws Exception {
    	
    	given(feignOrderManager.saveOrderData(orderLIst)).willReturn(true);
    	this.mockMvc.perform(post("/ordersFeignClient/createOrder").contentType("application/json")
    			.content(ORDER_JSON_STRING))
    			.andExpect(status().isOk());
    	
    }
    @Test
    public void test_createOrderItems_UnsupportedMediaType() throws Exception {
    	
    	given(feignOrderManager.saveOrderData(orderLIst)).willReturn(true);
    	this.mockMvc.perform(post("/ordersFeignClient/createOrder").contentType("application/xml")
    			.content(ORDER_JSON_STRING))
    			.andExpect(status().isUnsupportedMediaType());
    	
    }
    
    @Test
    public void test_getOrderItem_BadRequest() throws Exception {
    	given(feignOrderManager.getOrder(5)).willReturn(orderDTO);
    	this.mockMvc.perform(get("/ordersFeignClient/getOrder")).andExpect(status().isBadRequest());
    	
    }
    
    


}
