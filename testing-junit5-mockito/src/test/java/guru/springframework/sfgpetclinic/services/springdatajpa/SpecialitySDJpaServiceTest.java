package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.lenient;

@DisplayName("SpecialitySDJpaService Test written using Mockito ")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SpecialitySDJpaServiceTest {

    @Mock(serializable = true)
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void deleteById() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository,times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeastOnce() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository,atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMostOnce() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository,atMost(5)).deleteById(1l);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository,atLeastOnce()).deleteById(1l);
        verify(specialtyRepository,never()).deleteById(5l);
    }
    @Test
    void delete() {
        service.delete(new Speciality());
    }

    @Test
    void testDeleteByObject(){
        Speciality speciality = new Speciality();
        service.delete(speciality);
        verify(specialtyRepository).delete(any(Speciality.class));

    }

    @Test
    void findById() {
        Speciality speciality  =new Speciality();
        when(specialtyRepository.findById(1l)).thenReturn(Optional.of(speciality));
        Speciality foundSpeciality =service.findById(1l);
        assertThat(foundSpeciality).isNotNull();
        verify(specialtyRepository).findById(1l);
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void testDThrow(){
        doThrow(new RuntimeException("Crash!!!")).when(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class,()->specialtyRepository.delete(new Speciality()));
        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByThrows(){
        given(specialtyRepository.findById(1l)).willThrow(new RuntimeException("Not Found"));

        assertThrows(RuntimeException.class,()->service.findById(1l));

        then(specialtyRepository).should().findById(1l);
    }

    @Test
    void testThrowBDD(){
        willThrow(new RuntimeException("Thrown Exception!!!")).given(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class,()->specialtyRepository.delete(new Speciality()));

        then(specialtyRepository).should().delete(any());
    }

    @Test
    void testSaveLambda(){
        //Given
        final String MATCH_ME ="MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1l);

        //need mock to only return on match MATCH_ME string
        given( specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        //When
        Speciality returnedSpeciality= service.save(speciality);

        //Then
        assertThat(returnedSpeciality.getId()).isEqualTo(1l);
    }
    @Test
    void testSaveLambdaNoMatch(){
        //Given
        final String MATCH_ME ="MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("Not a Match");

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1l);

        //need mock to only return on match MATCH_ME string
        given( specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        //When
        Speciality returnedSpeciality= service.save(speciality);

        //Then
        assertNull(returnedSpeciality);

    }


}