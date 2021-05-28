package petstore.storage;

import petstore.data.PetEntity;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final List<PetEntity> pets = new ArrayList<>();

    public static List<PetEntity> getPets() {
        return pets;
    }

    /**
     * Get the last pet from storage
     */
    public PetEntity getLastPet() {
        return pets.stream().reduce((first, last) -> last).orElseThrow(() -> new RuntimeException("Last Pet not found!"));
    }
}
