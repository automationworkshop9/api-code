package petstore.proxy;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import petstore.client.BaseClass;
import petstore.data.PetDSO;
import petstore.data.PetEntity;
import petstore.data.PetStatus;
import io.restassured.response.Response;
import petstore.storage.Storage;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

public class PetStoreProxy {

    public static final String REST_PET = "/v2/pet";

    /**
     * Add new pet to the Pet Store.
     */
    public void addPetToStore(final String petName) {

        // create an object which will be used as a payload
        final PetDSO petBody = new PetDSO.Builder()
                .id(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE))
                .name(petName)
                .status(PetStatus.AVAILABLE.toString())
                .build();

        // make a call to api with created object in the body
        final Response response = given()
                .spec(BaseClass.requestSpec(REST_PET))
                .contentType(ContentType.JSON)
                .body(petBody)
                .when()
                .post()
                .then()
                .extract().response();

        // check if status code is as expected, if not throw an exception
        if (response.statusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Pet can not be added. Expected status: " + HttpStatus.SC_OK + " Actual status: " + response.statusCode());
        }

        // create an object which will be user for future assertions
        final PetEntity pet = new PetEntity.Builder()
                .id(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE))
                .name(petName)
                .petStatus(PetStatus.PENDING)
                .build();

        // store the object for the future assertion
        Storage.getPets().add(pet);
    }

    /**
     * Update status of the Pet.
     */
    public void updatePetStatus(final PetEntity pet, final PetStatus petStatus) {

        final PetDSO petBody = new PetDSO.Builder()
                .id(pet.getId())
                .name(pet.getName())
                .category(pet.getCategory())
                .photoUrls(pet.getPhotoUrls())
                .tags(pet.getTags())
                .status(PetStatus.PENDING.toString())
                .build();

        final Response response = given()
                .spec(BaseClass.requestSpec(REST_PET))
                .contentType("application/json")
                .body(petBody)
                .when()
                .put()
                .then()
                .extract().response();

        if (response.statusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Pet can not be updated. Expected status: " + HttpStatus.SC_OK + " Actual status: " + response.statusCode());
        }

        pet.setPetStatus(petStatus);
    }

    /**
     * Remove pet from the Pet Store.
     */
    public boolean removePet(final PetEntity pet) {

        final Response response = given()
                .spec(BaseClass.requestSpec(REST_PET))
                .when()
                .delete("/" + pet.getId())
                .then()
                .extract().response();

        return response.statusCode() == HttpStatus.SC_OK;
    }

    /**
     * Get pet from the Pet Store.
     */
    public PetDSO getPet(final PetEntity pet) {

        final Response response = given()
                .spec(BaseClass.requestSpec(REST_PET))
                .when()
                .get(pet.getId().toString())
                .then()
                .extract().response();

        if (response.statusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Pet can not be fetched. Expected status: " + HttpStatus.SC_OK + " Actual status: " + response.statusCode());
        }
        return response.as(PetDSO.class);
    }

    public String getDeletedPet(final PetEntity pet) {

        final Response response = given()
                .spec(BaseClass.requestSpec(REST_PET))
                .when()
                .get(pet.getId().toString())
                .then()
                .extract().response();

        if (response.statusCode() == HttpStatus.SC_OK) {
            throw new RuntimeException("Pet is found in the store! Expected status: " + HttpStatus.SC_NOT_FOUND + " Actual status: " + response.statusCode());
        }
        return response.andReturn().body().asString();
    }

}
