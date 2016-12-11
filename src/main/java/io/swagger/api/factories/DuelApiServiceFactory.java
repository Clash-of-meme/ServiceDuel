package io.swagger.api.factories;

import io.swagger.api.DuelApiService;
import io.swagger.api.impl.DuelApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-11T15:09:34.277Z")
public class DuelApiServiceFactory {
    private final static DuelApiService service = new DuelApiServiceImpl();

    public static DuelApiService getDuelApi() {
        return service;
    }
}
