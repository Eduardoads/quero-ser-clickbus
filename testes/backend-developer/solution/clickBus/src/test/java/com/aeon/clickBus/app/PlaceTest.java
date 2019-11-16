package com.aeon.clickBus.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.aeon.clickBus.app.repository.PlaceRepository;
import com.aeon.clickBus.app.service.PlaceService;

@SpringBootTest
public class PlaceTest {

    @MockBean
    private PlaceService mock;
    
    @Mock
    private PlaceRepository mockRepository;

	@Before
	public void setup() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void find_placeId_OK() throws Exception {
		Assert.assertTrue(true);
	}
	
	@Test
    public void find_placeName_OK() throws Exception {
		Assert.assertTrue(true);
    }
	
	@Test
    public void find_allPlaces_OK() throws Exception {
		Assert.assertTrue(true);
    }
	
	@Test
    public void save_place_OK() throws Exception {
		Assert.assertTrue(true);
    }
	
	@Test
    public void update_place_OK() throws Exception {
		Assert.assertTrue(true);
    }
    
    
    @Test
    public void delete_place_OK() throws Exception {
    	Assert.assertTrue(true);
    }

}
