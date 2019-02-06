package com.rekik.findproperty.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rekik.findproperty.entity.Property;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class PropertyController {

    public static final String BASE_URI = "http://webservice-takehome-1.spookle.xyz/property?property_id=";
    private static final String NOT_FOUND = "Property not found";

    @GetMapping("/")
    public @ResponseBody String getThisOwner(@RequestParam(value = "id") String id) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(BASE_URI+id).build();
        URI resourceURI = uriComponents.toUri();

        Property property =  restTemplate.getForObject
                (resourceURI, Property.class);
       // return resourceURI.toString();
        return property.getOwner();

    }

    /**
     *
     * @param propertyids
     * @return
     */
    //@GetMapping("/owner")
   // @ResponseBody
  /* public String findHighestValuedPropertyOwner
    (@RequestParam(value = "ids") List<String> propertyids)
            throws JsonParseException, JsonMappingException, IOException {*/

    @PostMapping("/owner")
    @ResponseBody
    public String highestValuedPropertyOwnerinVA(@RequestBody List<String> propertyids ){

    RestTemplate restTemplate = new RestTemplate();
        List<Property> properties = new ArrayList<>();

        for(String id: propertyids){
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(BASE_URI+id).build();
            URI resourceURI = uriComponents.toUri();

            Property property =  restTemplate.getForObject(resourceURI,Property.class);

            properties.add(property);

        }

        properties.sort(Comparator.comparing(Property::getValue));

        int count = 0;
        for(String id: propertyids){
            System.out.println(properties.get(count).getValue()+" "+properties.get(count).getOwner()+" "+propertyids.size());
            count++;
        }

        return properties.get(propertyids.size()-1).getOwner();
    }

}
