package com.rekik.findproperty.controller;


import com.rekik.findproperty.service.PropertyFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PropertyController {

    @Autowired
    PropertyFinderService propertyFinderService;

    /**
     *
     * @param propertyids
     * @return property owner who owns the highest valued property in the state of Virginia
     */

    @PostMapping(value = "/owner", consumes = MediaType.APPLICATION_JSON_VALUE )
    public String highestValuedPropertyOwnerinVA(@RequestBody List<String> propertyids) {
        return propertyFinderService.highestValuedPropertyFinder(propertyids);
    }

}
