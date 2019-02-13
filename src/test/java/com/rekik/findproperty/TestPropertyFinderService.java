package com.rekik.findproperty;

import com.rekik.findproperty.controller.PropertyController;
import com.rekik.findproperty.service.PropertyFinderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
public class TestPropertyFinderService {
    private MockMvc mockMvc;

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    PropertyFinderService propertyFinderService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(propertyController)
                .build();
    }

    @Test
    public void testhighestValuedPropertyOwnerinVA() throws Exception {
        //language=JSON ["home_482","home_d50","home_1c9","home_1cd", "home_7d1","home_554","home_c8d","home_f5e","home_ae4","home_c42"]
        String json = "[\"home_482\",\"home_d50\",\"home_1c9\",\"home_1cd\", \"home_7d1\",\"home_554\",\"home_c8d\",\"home_f5e\",\"home_ae4\",\"home_c42\"]";
        //  List<String> propertyids = new ArrayList<String>(Arrays.asList(json.split(",")));

        String[] strarr =  {
                "home_482",
                "home_d50",
                "home_1c9",
                "home_1cd",
                "home_7d1",
                "home_554",
                "home_c8d",
                "home_f5e",
                "home_ae4",
                "home_c42"
        };

        /*      JSONArray ids = new JSONArray();
        for(String str : strarr){
            ids.put(str);
        }*/
        List<String> propertyids = new ArrayList<String>();
        for(String str : strarr){
            propertyids.add(str);
        }


        Mockito.when(propertyFinderService.highestValuedPropertyFinder(propertyids)).thenReturn("Michele Marshall");
        mockMvc.perform(MockMvcRequestBuilders.post("/owner")
               .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
       Mockito.verify(propertyFinderService).highestValuedPropertyFinder(propertyids);

    }
}
