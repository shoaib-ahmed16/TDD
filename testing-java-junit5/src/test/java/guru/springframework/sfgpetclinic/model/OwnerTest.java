package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class OwnerTest implements ModelTests {
    @Test
    void dependentAssertions() {
            Owner owner  = new Owner(1l,"Joe","Buck");
            owner.setCity("Key West");
            owner.setTelephone("1231231234");
            assertAll("Properties Test",
                    ()-> assertAll("Person Properties",
                            ()->assertEquals("Joe",owner.getFirstName(),()->"First Name Failed In Test due to Miss Match"),
                            ()->assertEquals("Buck",owner.getLastName(),()->"Last Name Failed In Test due to Miss Match")),
                    ()-> assertAll("Owner Properties",
                            ()->assertEquals("Key West",owner.getCity(),()->"City Name Failed In Test due to Miss Match"),
                            ()->assertEquals("1231231234",owner.getTelephone(),()->"Telephone Number Failed In Test due to Miss Match"))
                    );
            assertThat(owner.getCity()).isEqualTo("Key West");
    }

    @DisplayName("Value Source Test - ")
    @ParameterizedTest(name ="{displayName} - [{index}] {arguments}")
    @ValueSource(strings ={"Spring","Framework","guru"})
    void testValues(String val){
        System.out.println(val);
    }
    @DisplayName("Enum Source Test - ")
    @ParameterizedTest(name ="{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType){
        System.out.println(ownerType);
    }


    @DisplayName("CSV Input Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({
            "FL,1,1",
            "OH,2,2",
            "NY,3,3"
    })
    void csvInputTest(String stateName, int val1, int val2){
        System.out.println(stateName +" = "+val1 +"::" +val2);
    }

    @DisplayName("CSV From File Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv",numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2){
        System.out.println(stateName +" = "+val1 +"::" +val2);
    }

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int val1, int val2){
        System.out.println(stateName +" = "+val1 +"::" +val2);
    }

    static Stream<Arguments> getArgs(){
        return  Stream.of(Arguments.of("DL",1,1),
                Arguments.of("UK",0,7),
                Arguments.of("KA",5,6)
                );
    }

    @DisplayName("Custom Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomTest(String stateName, int val1, int val2){
        System.out.println(stateName +" = "+val1 +"::" +val2);
    }

}