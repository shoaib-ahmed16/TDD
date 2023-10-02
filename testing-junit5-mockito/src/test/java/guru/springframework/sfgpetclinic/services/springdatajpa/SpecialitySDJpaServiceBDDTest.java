package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


@DisplayName("SpecialitySDJpaService Test written using Mockito BDD : -->  ")
@ExtendWith(MockitoExtension.class)
public class SpecialitySDJpaServiceBDDTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void deleteById() {
        //Given - none

        //When
        service.deleteById(1l);
        service.deleteById(1l);

        //Then
        then(specialtyRepository).should(times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeastOnce() {
        //Given - none

        //When
        service.deleteById(1l);
        service.deleteById(1l);
        //Then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMostOnce() {
        //Given - none

        //When
        service.deleteById(1l);
        service.deleteById(1l);

        //Then
        then(specialtyRepository).should(atMost(5)).deleteById(1l);
    }

    @Test
    void deleteByIdNever() {
        //Given - none

        //When
        service.deleteById(1l);
        service.deleteById(1l);

        //Then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);
        then(specialtyRepository).should(never()).deleteById(5l);

    }
    @Test
    void delete() {
        //Given - none

        //When
        service.delete(new Speciality());

        //Then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void testDeleteByObject(){
        //Given
        Speciality speciality = new Speciality();
        //When
        service.delete(speciality);
        //Then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findByIdBddTest(){

        //Given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1l)).willReturn(Optional.of(speciality));

        //When
        Speciality foundSpeciality =service.findById(1l);

        //Then
        assertThat(foundSpeciality).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }



    @Test
    void findAll() {
    }

    @Test
    void save() {
    }


}
