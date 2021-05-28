package petstore.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

    private static final String PET_STORE_PATH = "https://petstore.swagger.io";

    private static RequestSpecification requestSpecification;
    private static RequestSpecBuilder requestSpecBuilder;

    public static RequestSpecification requestSpec(String path) {

        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(PET_STORE_PATH);
        requestSpecBuilder.setBasePath(path);

        return requestSpecification = requestSpecBuilder.build();
    }
}
