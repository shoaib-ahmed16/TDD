package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("VisitSDJpaService Test written using Mockito: --> ")
@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test fo Finding ALl Visit : --> ")
    @Test
    void findAll() {
        Visit  visit =new Visit();
        Set<Visit> visits =new HashSet<>();
        visits.add(visit);
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits= service.findAll();
        verify(visitRepository).findAll();
        assertThat(foundVisits).hasSize(1);
    }

    @DisplayName("Test for finding Visit By Id: --> ")
    @Test
    void findById() {
        Visit visit  =new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit foundVisit =service.findById(1l);
        verify(visitRepository).findById(anyLong());
        assertThat(foundVisit).isNotNull();
    }

    @DisplayName("Test for saving Visit: --> ")
    @Test
    void save() {
        Visit visit  =new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(new Visit());
        verify(visitRepository).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @DisplayName("Test for deleting Visit: --> ")
    @Test
    void delete() {
        Visit visit  =new Visit();
        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
    }

    @DisplayName("Test for deleting Visit By Id: --> ")
    @Test
    void deleteById() {
        service.deleteById(1l);
        verify(visitRepository).deleteById(anyLong());
    }
}