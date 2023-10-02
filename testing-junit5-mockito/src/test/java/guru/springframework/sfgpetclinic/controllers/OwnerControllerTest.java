package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp(){
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invoation ->{
            List<Owner> owners = new ArrayList<>();
            String name = invoation.getArgument(0);
                if(name.equals("%Buck%")){
                    owners.add(new Owner(1l,"Joe","Buck"));
                    return owners;
                }else if(name.equals("%DonotFindMe%")){
                    return  owners;
                }if(name.equals("%FindMe%")){
                        owners.add(new Owner(1l,"Joe","Buck"));
                        owners.add(new Owner(2l,"Joe2","Buck2"));
                        return  owners;
                    }
            throw new RuntimeException("Invalid Argument");
        });
    }

    @Test
    void processFindFormWildCardFound(){
        //given
        Owner owner  =new Owner(1l,"Jim","FindMe");
        InOrder inOrder =inOrder(ownerService,model);

        //When
        String viewName = controller.processFindForm(owner,bindingResult, model);

        //Then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

        //inOrder asserts
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(),anyList());
    }
    @Test
    void processFindFormWildCardStringAnnotation(){
        //given
        Owner owner  =new Owner(1l,"Jim","Buck");

        //When
        String viewName = controller.processFindForm(owner,bindingResult,null);

        //Then
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
    }
    @Test
    void processFindFormWildCardNotFound(){
        //given
        Owner owner  =new Owner(1l,"Jim","DonotFindMe");

        //When
        String viewName = controller.processFindForm(owner,bindingResult,null);

        //Then
        assertThat("%DonotFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processCreationFormHasError() {
        //given
        Owner owner  =new Owner(1l,"Jim","Bob");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName=controller.processCreationForm(owner,bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    }
    @Test
    void processCreationFormNoError() {
        //given
        Owner owner  =new Owner(5l,"Jim","Bob");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);
        //when
        String viewName=controller.processCreationForm(owner,bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);

    }
}