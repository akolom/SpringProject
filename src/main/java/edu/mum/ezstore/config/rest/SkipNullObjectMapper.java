package edu.mum.ezstore.config.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sosandstrom
 */
public class SkipNullObjectMapper extends ObjectMapper {

    @SuppressWarnings("deprecation")
	public void init() {
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //ignore unknown properties
	    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
