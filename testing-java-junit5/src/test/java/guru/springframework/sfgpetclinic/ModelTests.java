package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.*;

@Tag("model")
public interface ModelTests {

    @BeforeEach
    default  void beforeEachConsoleOutputer(TestInfo testInfo){
        System.out.println("Running Test - "+testInfo.getDisplayName()) ;
    }

}
