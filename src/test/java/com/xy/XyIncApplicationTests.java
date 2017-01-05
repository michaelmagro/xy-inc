package com.xy;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.xy.domain.POI;
import com.xy.resources.POIResources;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class XyIncApplicationTests {
	
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private POIResources poiResources;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);
        assertNotNull("The JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
    	this.mockMvc = MockMvcBuilders
                .standaloneSetup(poiResources).build();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("http://localhost:8080/poi"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    public void create() throws Exception {
    	
    	List<String> pois = new ArrayList<>();
    	
    	// Criação de todos os POI's de exemplo do teste
        String lanchonete = json(new POI("Lanchonete", 27, 12));
        pois.add(lanchonete);
        
        String posto = json(new POI("Posto", 31, 18));
        pois.add(posto);
        
        String joalheira = json(new POI("Joalheria", 15, 12));
        pois.add(joalheira);
        
        String floricultura = json(new POI("Floricultura", 19, 21));
        pois.add(floricultura);
        
        String pub = json(new POI("Pub", 12, 8));
        pois.add(pub);
        
        String supermercado = json(new POI("Supermercado", 23, 6));
        pois.add(supermercado);
        
        String churrascaria = json(new POI("Churrascaria", 28, 2));
        pois.add(churrascaria);
        
        for(String poi : pois) {
        	this.mockMvc.perform(post("http://localhost:8080/poi")
                    .contentType(contentType)
                    .content(poi))
                    .andExpect(status().is2xxSuccessful());
        }
        
    }
    
    @Test
    public void findByProximity() throws Exception {
        mockMvc.perform(get("http://localhost:8080/poi/listByProximity/20/10/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
