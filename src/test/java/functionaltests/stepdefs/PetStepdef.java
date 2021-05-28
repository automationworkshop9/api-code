package functionaltests.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import petstore.data.PetDSO;
import petstore.data.PetEntity;
import petstore.data.PetStatus;
import petstore.proxy.PetStoreProxy;
import petstore.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Cucumber.class)
public class PetStepdef {

    private final PetStoreProxy petStoreProxy = new PetStoreProxy();
    private final Storage storage = new Storage();
    Logger logger = Logger.getLogger(PetStepdef.class.getName());

    @Given("^[Uu]ser add(?:s|ed) pet (.*) to the pet store$")
    public void addPet(final String petName) {
        petStoreProxy.addPetToStore(petName);
        logger.log(Level.INFO, "Pet " + petName + " added to the store.");

    }

    @When("^[Pp]et status is set to (available|pending|sold)$")
    public void setPetStatus(final String petStatus) {
        final PetEntity pet = storage.getLastPet();
        final PetStatus status = PetStatus.valueOf(petStatus.toUpperCase());
        petStoreProxy.updatePetStatus(pet, status);
        logger.log(Level.INFO, "Pet status is updated.");
    }

    @Then("^[Ii]t (?:will be|is)? possible to sell it$")
    public void validatePossibleToSell() {
        final PetEntity expectedPet = storage.getLastPet();
        final PetDSO actualPet = petStoreProxy.getPet(expectedPet);
        assertThat(actualPet.getStatus()).as("Pet is not available!").isEqualTo(expectedPet.getPetStatus().toString());
        logger.log(Level.INFO,"It is possible to sell the Pet.");
    }

    @When("^[Pp]et is removed from the pet store$")
    public void removePet() {
        final PetEntity pet = storage.getLastPet();
        final boolean isPetDeleted = petStoreProxy.removePet(pet);
        assertThat(isPetDeleted).as("Pet is not deleted!").isTrue();
        logger.log(Level.INFO,"Pet is removed from pet store.");
        pet.setDeleted(true);

    }

    @Then("^[Pp]et (?:will not be|is)? present in the pet store$")
    public void validatePetRemovedFromStore() {
        final PetEntity pet = storage.getLastPet();
        String responseBody = petStoreProxy.getDeletedPet(pet);
        assertThat(responseBody).contains("Pet not found");
    }
}
