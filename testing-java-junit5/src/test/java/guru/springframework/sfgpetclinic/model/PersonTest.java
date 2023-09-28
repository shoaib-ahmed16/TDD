package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {
    @Test
    void groupedAssertions() {
        //given
        Person  person  = new Person(2l,"Joe","Buck");

        //then
        assertAll("Test Props Set",
                ()-> assertEquals("Joe",person.getFirstName()),
                ()->assertEquals("Buck",person.getLastName()));
    }

    @Test
    void groupedAssertions2() {
        //given
        Person  person  = new Person(2l,"Joe","Buck");

        //then
        assertAll("Test Props Set",
                ()-> assertEquals("Joe",person.getFirstName(),"First Name Failed"),
                ()->assertEquals("Buck",person.getLastName(),"Last Name Failed"));
    }

}