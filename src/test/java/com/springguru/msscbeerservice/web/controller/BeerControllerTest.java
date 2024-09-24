package com.springguru.msscbeerservice.web.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springguru.msscbeerservice.web.model.BeerDto;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testGetBeerById() throws Exception {

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()))
            .andExpect(status().isOk());
    }

    @Test
    void testSaveNewBeer() throws Exception{
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
            .andExpect(status().isCreated());

    }

    @Test
    void testUpdateBeerById() throws Exception{
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
            .content(beerDtoJson)
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNoContent());
    }
}
