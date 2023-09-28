package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.Exceptions.ValueNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest implements ControllerTests {

    IndexController indexController;
    @BeforeEach
    void setUp() {
        indexController =new IndexController();
    }

    @DisplayName("Test Proper View  name is  returned  for Index page")
    @Test
    void index() {

        // JUnit Assertion
        assertEquals("index",indexController.index());
        assertEquals("index",indexController.index(),"Wrong View Returned");
        assertEquals("index",indexController.index(),()->"Another Expensive Message "+"Make  me only  if you have to");

        // AssertJ Assertion
        assertThat(indexController.index()).isEqualTo("index");
    }

    @DisplayName("Test  Exception")
    @Test
    void oupsHandler() {
        assertThrows(ValueNotFoundException.class,()->{
            indexController.oupsHandler();
        });
    }

    @Test
    void timeOut(){
        assertTimeout(Duration.ofMillis(100),()->{
            Thread.sleep(50);
            System.out.println("I got  here");
        });
    }
    @Test
    void timeOutPrempt(){
        assertTimeout(Duration.ofMillis(100),()->{
            Thread.sleep(50);
            System.out.println("I got  here 23432152342");
        });
    }

    @Test
    void testAssumptionTrue(){
        // Assumption is faling but test is passing
//        assertTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionTrueAssumptionTrue(){
        assertTrue("GURU".equalsIgnoreCase("GURU"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOs(){

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows(){

    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8(){

    }
    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11(){

    }

    @EnabledIfEnvironmentVariable(named = "Users",matches = "Shoaib Ahmed")
    @Test
    void testIfUserShoaibAhmed(){

    }

    @EnabledIfEnvironmentVariable(named = "USER",matches = "Ahmed Shoaib")
    @Test
    void testIfUserAhmedShoaib(){

    }

}