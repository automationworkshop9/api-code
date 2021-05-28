Feature: Pets

  Once pet is added to the store user can fetch the information about the pet,
  change pet information or delete the pet from the store.

  Scenario: User is able to sell a pet in status pending
    Given User added pet Beagle to the pet store
    When Pet status is set to pending
    Then it will be possible to sell it