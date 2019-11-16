package com.aeon.clickBus.app;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aeon.clickBus.app.model.Countries;
import com.aeon.clickBus.app.model.Place;
import com.aeon.clickBus.app.model.State;
import com.aeon.clickBus.app.service.PlaceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlaceControllerTest {
	private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaceService mockRepository;
    
    @Before
    public void init() {
        Place place = new Place(1L, "House", "teste", "Salvador", State.BA, Countries.Brasil, new Date(), null);
        when(mockRepository.findById(1L)).thenReturn(Optional.of(place));
    }
    
    @Test
    public void find_placeId_OK() throws Exception {

        mockMvc.perform(get("/searchId/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("House")))
                .andExpect(jsonPath("$.slug", is("teste")))
                .andExpect(jsonPath("$.city", is("Salvador")))
                .andExpect(jsonPath("$.state", is(State.BA)))
                .andExpect(jsonPath("$.countries", is(Countries.Brasil)))
                .andExpect(jsonPath("$.createdAt", is(new Date())));

        verify(mockRepository, times(1)).findById(1L);
    }
    
    @Test
    public void find_placeName_OK() throws Exception {

        mockMvc.perform(get("/places/searchName/H"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("House")))
                .andExpect(jsonPath("$.slug", is("teste")))
                .andExpect(jsonPath("$.city", is("Salvador")))
                .andExpect(jsonPath("$.state", is(State.BA)))
                .andExpect(jsonPath("$.countries", is(Countries.Brasil)))
                .andExpect(jsonPath("$.createdAt", is(new Date())));

        verify(mockRepository, times(1)).findByName("H");
    }
    
    @Test
    public void find_allPlaces_OK() throws Exception {

        List<Place> places = Arrays.asList(
        		new Place(1L, "House", "teste", "Salvador", State.BA, Countries.Brasil, new Date(), null),
        		new Place(2L, "Work", "teste", "São Paulo", State.SP, Countries.Brasil, new Date(), null));

        when(mockRepository.findAll()).thenReturn(places);
        
        mockMvc.perform(get("/places"))
        	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$", is(2)))
    		.andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].name", is("House")))
	        .andExpect(jsonPath("$[0].slug", is("teste")))
	        .andExpect(jsonPath("$[0].city", is("Salvador")))
	        .andExpect(jsonPath("$[0].state", is(State.BA)))
	        .andExpect(jsonPath("$[0].countries", is(Countries.Brasil)))
	        .andExpect(jsonPath("$[0].createdAt", is(new Date())))
	        .andExpect(jsonPath("$[0].id", is(2)))
	        .andExpect(jsonPath("$[0].name", is("Work")))
	        .andExpect(jsonPath("$[0].slug", is("teste")))
	        .andExpect(jsonPath("$[0].city", is("São Paulo")))
	        .andExpect(jsonPath("$[0].state", is(State.SP)))
	        .andExpect(jsonPath("$[0].countries", is(Countries.Brasil)))
	        .andExpect(jsonPath("$[0].createdAt", is(new Date())));

        verify(mockRepository, times(1)).findAll();
    }
    
    @Test
    public void find_placeIdNotFound_404() throws Exception {
        mockMvc.perform(get("/places/searchName/Trip")).andExpect(status().isNotFound());
    }
    
    @Test
    public void save_place_OK() throws Exception {

        Place newPlace = new Place(1L, "House", "teste", "Salvador", State.BA, Countries.Brasil, new Date(), null);
        when(mockRepository.save(newPlace));

        mockMvc.perform(post("/places")
                .content(om.writeValueAsString(newPlace))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("House")))
                .andExpect(jsonPath("$.slug", is("teste")))
                .andExpect(jsonPath("$.city", is("Salvador")))
                .andExpect(jsonPath("$.state", is(State.BA)))
                .andExpect(jsonPath("$.countries", is(Countries.Brasil)))
                .andExpect(jsonPath("$.createdAt", is(new Date())));

        verify(mockRepository, times(1)).save(newPlace);

    }

    @Test
    public void update_place_OK() throws Exception {

    	Place updatePlace = new Place(1L, "House", "teste", "Salvador", State.BA, Countries.Brasil, new Date(), null);
    	when(mockRepository.save(updatePlace));

        mockMvc.perform(put("/places")
        	.content(om.writeValueAsString(updatePlace))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("House")))
            .andExpect(jsonPath("$.slug", is("teste")))
            .andExpect(jsonPath("$.city", is("Lauro de Freitas")))
            .andExpect(jsonPath("$.state", is(State.BA)))
            .andExpect(jsonPath("$.countries", is(Countries.Brasil)))
            .andExpect(jsonPath("$.createdAt", is(new Date())))
            .andExpect(jsonPath("$.updatedAtAt", is(new Date())));
    }
    
    @Test
    public void delete_place_OK() throws Exception {

        doNothing().when(mockRepository).delete(1L);

        mockMvc.perform(delete("/places/delete/1"))
             .andExpect(status().isOk());

        verify(mockRepository, times(1)).delete(1L);
    }
    
    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
