package com.ashakir.dev.rest.webservices.restful_web_services;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.hibernate.sql.ast.tree.predicate.FilterPredicate;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class filteringController {

    @GetMapping("/filtering")
    public SomeBean getBean(){
        return new SomeBean("_1","_2","_3");
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue getDynamicBean(){
        SomeBean bean = new SomeBean("_1","_2","_3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("s1");
        FilterProvider filters = new SimpleFilterProvider().addFilter("filter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
