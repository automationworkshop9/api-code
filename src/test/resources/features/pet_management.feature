Feature: Pets

  User is able to add new pet to the store. Once added, pet status is set to AVAILABLE.
  Pet can be sold if the status of the pet is PENDING.

  Scenario: User is able to remove pet from the Pet Store
    Given User added pet Pomeranian to the pet store
    When Pet is removed from the pet store
    Then Pet will not be present in the pet store