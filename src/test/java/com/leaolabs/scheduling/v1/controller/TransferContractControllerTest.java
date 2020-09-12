package com.leaolabs.scheduling.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaolabs.scheduling.v1.dtos.CustomerDto;
import com.leaolabs.scheduling.v1.dtos.SchedulingDto;
import com.leaolabs.scheduling.v1.dtos.TransferContractDto;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferContractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    static final String URI = "/v1/scheduling/transfer";
    static final MediaType JSON = MediaType.APPLICATION_JSON;
    static final String BIG_STRING = "aaaaaaaaaaaaa bbbbbbbbbbbbbbb cccccccccccc dddddddddd eeeeeeeeee fffffffffffff ggggggggggggg hhhhhhhhhh";


    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndCustomerCpfHasTooLesserDescription()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(CustomerDto.builder().name("Leonardo Nascimento Cintra").cpf("111").build())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Invalid body parameter customer.cpf - it must be filled with a value greater or equals than 11")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Invalid field customer.cpf - it must be filled with a value greater or equals than 11")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndCustomerCpfHasTooLongDescription()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(CustomerDto.builder().name("Leonardo Nascimento Cintra").cpf(BIG_STRING).build())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Invalid body parameter customer.cpf - it must be filled with a value lesser or equals than 11")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Invalid field customer.cpf - it must be filled with a value lesser or equals than 11")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndCustomerNameHasTooLongDescription()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(CustomerDto.builder().name(BIG_STRING).cpf("11122233345").build())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Invalid body parameter customer.name - it must be filled with a value lesser or equals than 100")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Invalid field customer.name - it must be filled with a value lesser or equals than 100")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndCustomerCpfMissing()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(CustomerDto.builder().name("Test Name").build())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter customer.cpf")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field customer.cpf is required and can not be empty")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndCustomerNameIsEmptyString()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(CustomerDto.builder().name("").cpf("11122233387").build())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter customer.name")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field customer.name is required and can not be empty")))
                .andReturn()
                .getResponse();
    }
    
    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndCustomerNameMissing()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(CustomerDto.builder().build())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter customer.name")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field customer.name is required and can not be empty")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndCustomerMissing()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter customer")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field customer is required and can not be empty")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAmountHasLessThanOne()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.valueOf(0.90))
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Invalid body parameter amount - it must be filled with a value greater than 0,9")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Invalid field amount - it must be filled with a value greater than 0,9")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAmountHasZero()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.ZERO)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Invalid body parameter amount - it must be filled with a value greater than 0")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Invalid field amount - it must be filled with a value greater than 0")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAmountHasMissing()  {

        var dto = TransferContractDto.builder()
                .accountTarget("323232")
                .accountOrigin("898989")
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter amount")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field amount is required and can not be empty")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAccountTargetHasEmptyString()  {

        var dto = TransferContractDto.builder()
                .accountTarget("")
                .accountOrigin("898989")
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter accountTarget")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field accountTarget is required and can not be empty")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAccountTargetHasMissing()  {

        var dto = TransferContractDto.builder()
                .accountOrigin("3898")
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter accountTarget")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field accountTarget is required and can not be empty")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAccountTargetHasTooLongDescription()  {

        var dto = TransferContractDto.builder()
                .accountOrigin("3232")
                .accountTarget(BIG_STRING)
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Invalid body parameter accountTarget - it must be filled with a value lesser or equals than 100")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Invalid field accountTarget - it must be filled with a value lesser or equals than 100")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAccountOriginHasEmptyString()  {

        var dto = TransferContractDto.builder()
                .accountTarget("11111")
                .accountOrigin("")
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter accountOrigin")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field accountOrigin is required and can not be empty")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAccountOriginHasMissing()  {

        var dto = TransferContractDto.builder()
                .accountTarget("11111")
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Missing body parameter accountOrigin")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Field accountOrigin is required and can not be empty")))
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    public void whenCreateTransferContractAndAccountOriginHasTooLongDescription()  {

        var dto = TransferContractDto.builder()
                .accountOrigin(BIG_STRING)
                .accountTarget("11111")
                .customer(getFakeCustomerDto())
                .scheduling(getFakeSchedulingDto())
                .amount(BigDecimal.TEN)
                .build();

        this.mockMvc.perform(post(URI)
                .contentType(JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].developerMessage", Matchers.is("Invalid body parameter accountOrigin - it must be filled with a value lesser or equals than 100")))
                .andExpect(jsonPath("$.[0].userMessage", Matchers.is("Invalid field accountOrigin - it must be filled with a value lesser or equals than 100")))
                .andReturn()
                .getResponse();
    }

    private SchedulingDto getFakeSchedulingDto() {
        return SchedulingDto.builder().build();
    }

    private CustomerDto getFakeCustomerDto() {
        return CustomerDto.builder()
                .name("Leonardo Nascimento Cintra")
                .cpf("11122233345")
                .build();
    }

}
