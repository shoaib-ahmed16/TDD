package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Owner Map  Service Test")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;
    @BeforeEach
    void setUp() {
        petTypeService =new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService =new OwnerMapService(petTypeService,petService);
        System.out.println("First Before each");
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownerAreZero(){
        int ownerCount =ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type -")
    @Nested
    class TestCreatePetTypes {
        @BeforeEach
        void setUp(){
            PetType petType = new PetType(1l,"Dog");
            PetType petType2 =new PetType(2l,"Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);
            System.out.println("Nested Before each");
        }

        @Test
        void testPetCount(){
            int petTypeCount = petTypeService.findAll().size();
            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }
        @DisplayName("Save Owners Tests -")
        @Nested
        class SaveOwnerTests{
            @BeforeEach
            void setUp(){
                ownerMapService.save(new Owner(1l,"Before","Each"));
                System.out.println("Saved  Owners  Before each");
            }
            @Test
            void saveOwner(){
                Owner owner = new Owner(2l,"Joe","Buck");
                Owner savedOwner  =ownerMapService.save(owner);
                assertThat(savedOwner).isNotNull();
            }
            @DisplayName("Save Owners Tests - ")
            @Nested
            class FindOwnersTests {
                @DisplayName("Find Owner")
                @Test
                void findOwner(){
                    Owner  foundOwner =ownerMapService.findById(1l);
                    assertThat(foundOwner).isNotNull();
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound(){
                    Owner foundOwner = ownerMapService.findById(2l);
                    assertThat(foundOwner).isNull();
                }
            }
        }
    }
    @DisplayName("Verify Still Zero Owners")
    @Test
    void ownersAreStillZero(){
        int ownerCount =ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }
}