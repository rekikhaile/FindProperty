package com.rekik.findproperty.controller;


import com.rekik.findproperty.entity.Property;
import com.rekik.findproperty.exceptionhandling.InvalidInputException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@RestController
public class PropertyController {

    public static final String BASE_URI = "http://webservice-takehome-1.spookle.xyz/property?property_id=";

    @PostMapping(value = "/owner")
    public String highestValuedPropertyOwnerinVA(@RequestBody List<String> propertyids) {

        if(propertyids.isEmpty()){
            throw new InvalidInputException("Property not found");

        }

        else {
            RestTemplate restTemplate = new RestTemplate();
            List<Property> properties = new ArrayList<>();
            List<Property> VAproperties = new ArrayList<>();
            List<String> propertyidsVA = new ArrayList<>();

            for (String id : propertyids) {
                UriComponents uriComponents = UriComponentsBuilder.fromUriString(BASE_URI + id).build();
                URI resourceURI = uriComponents.toUri();
                Property property = restTemplate.getForObject(resourceURI, Property.class);
                properties.add(property);
                if(property.getAddress().getState().equals("VA"))
                    VAproperties.add(property);

            }

            VAproperties.sort(Comparator.comparing(Property::getValue));
            Property maxVAproperty = Collections.max(VAproperties,Comparator.comparing(v -> v.getValue()));

            System.out.println("Test output if the Maximum property value in VA "+ maxVAproperty.getValue());

            int count = 0;
            int countMax = 0;
            System.out.println("Test output of all properties in VA :");
            for (Property prop : VAproperties) {
                if(maxVAproperty.getValue() == prop.getValue())
                    countMax++;

                System.out.println(VAproperties.get(count).getValue() + " " + VAproperties.get(count).getOwner() +
                        " " + VAproperties.get(count).getAddress().getState() + " "+ VAproperties.size());
                count++;
            }

            if(countMax >  1){
                return "There are more than one property owners who own highest valued properties with similar values ";
            }
            return maxVAproperty.getOwner();
        }
    }


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

}
