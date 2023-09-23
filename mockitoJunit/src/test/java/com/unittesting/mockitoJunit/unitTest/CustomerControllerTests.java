package com.unittesting.mockitoJunit.unitTest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittesting.mockitoJunit.entity.Customer;
import com.unittesting.mockitoJunit.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class CustomerControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CustomerService   customerService;

        @Autowired
        private ObjectMapper objectMapper;



        @Test
        public void giveCustomerObject_whenCreateCustomer_thenReturnSavedCustomer() throws JsonProcessingException, Exception{
            //Given - precondition or setup

            Customer customer= Customer.builder()
            .firstName("Bolaji")
            .lastName("Adesina")
            .email("adesina.bolaji@yahoo.com")
            .build();
            given(customerService.saveCustomer(any(Customer.class)))
            .willAnswer((invocation)-> invocation.getArgument(0));
            
            //when -action or behaviour that we are going test
                ResultActions response = mockMvc.perform(post("/api/customer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)));



             // then - verify the result or output using assert statements
             response.andDo(print()).
             andExpect(status().isCreated())
             .andExpect(jsonPath("$.firstName",
                     is(customer.getFirstName())))
             .andExpect(jsonPath("$.lastName",
                     is(customer.getLastName())))
             .andExpect(jsonPath("$.email",
                     is(customer.getEmail())));
        }
        
        // JUnit test for Get All customer REST API
    @Test
    public void givenListOfCustomers_whenGetAllCustomers_thenReturnCustomersList() throws Exception{
        // given - precondition or setup
        List<Customer> listofCustomers = new ArrayList<>();
        listofCustomers.add(Customer.builder().firstName("Bolaji").lastName("Adesina").email("adesina.bolaji@yahoo.com").build());
        listofCustomers.add(Customer.builder().firstName("Don").lastName("Joe").email("donjoe@gmail.com").build());
        given(customerService.getAllCustomers()).willReturn(listofCustomers);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/customer"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listofCustomers.size())));

    }

    // positive scenario - valid customer id
    // JUnit test for GET customer by id REST API
    @Test
    public void givenCustomerId_whenGetCustomerById_thenReturnCustomerObject() throws Exception{
        // given - precondition or setup
        long customerId = 1L;
        Customer customer = Customer.builder()
                .firstName("Bolaji")
                .lastName("Adesina")
                .email("adesina.bolaji@yahoo.com")
                .build();
        given(customerService.getCustomerById(customerId)).willReturn(Optional.of(customer));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/customer/{id}", customerId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$.email", is(customer.getEmail())));

    }

    // negative scenario - valid customer id
    // JUnit test for GET customer by id REST API
    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        long customerId = 1L;
        Customer employee = Customer.builder()
                 .firstName("Bolaji")
                .lastName("Adesina")
                .email("adesina.bolaji@yahoo.com")
                .build();
        given(customerService.getCustomerById(customerId)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/customer/{id}", customerId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
    // JUnit test for update customer REST API - positive scenario
        @Test
        public void givenUpdatedCustomer_whenUpdateCustomer_thenReturnUpdateCustomerObject() throws Exception{
            // given - precondition or setup
            long customerId = 1L;
            Customer savedCustomer = Customer.builder()
                     .firstName("Bolaji")
                .lastName("Adesina")
                .email("adesina.bolaji@yahoo.com")
                .build();

            Customer updatedEmployee = Customer.builder()
                    .firstName("Doggo")
                    .lastName("Rada")
                    .email("doggorada@gmail.com")
                    .build();
            given(customerService.getCustomerById(customerId)).willReturn(Optional.of(savedCustomer));
            given(customerService.updateCustomer(any(Customer.class)))
                    .willAnswer((invocation)-> invocation.getArgument(0));

            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(put("/api/customer/{id}", customerId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(updatedEmployee)));


            // then - verify the output
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())))
                    .andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
                    .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())));
        }

    // JUnit test for update customer REST API - negative scenario
    @Test
    public void givenUpdatedCustomer_whenUpdateCustomer_thenReturn404() throws Exception{
        // given - precondition or setup
        long customerId = 1L;
        Customer savedCustomer = Customer.builder()
                .firstName("Bolaji")
                .lastName("Adesina")
                .email("adesina.bolaji@yahoo.com")
                .build();

        Customer updatedCustomer = Customer.builder()
                .firstName("Sirius")
                .lastName("Black")
                .email("siriusblack@gmail.com")
                .build();
        given(customerService.getCustomerById(customerId)).willReturn(Optional.empty());
        given(customerService.updateCustomer(any(Customer.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/customer/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCustomer)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

// JUnit test for delete customer REST API
    @Test
    public void givenCustomerId_whenDeleteCustomer_thenReturn200() throws Exception{
        // given - precondition or setup
        long customerId = 1L;
        willDoNothing().given(customerService).deleteCustomer(customerId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/customer/{id}", customerId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }


    
}
