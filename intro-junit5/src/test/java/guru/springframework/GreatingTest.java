package guru.springframework;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GreatingTest {

    private Greating greating;

    @BeforeAll
    public static void callOnceBeforeAllTest(){
        System.out.println("before all test... call once !!!");
    }
    @BeforeEach
    void setUp(){
        System.out.println("In before each test...!");
        greating= new Greating();
    }
    @Test
    void helloWorld() {
        System.out.println(greating.helloWorld());
    }

    @Test
    void testHelloWorld() {
        System.out.println(greating.helloWorld("Shoaib"));
    }

    @AfterEach
    void afterEachMethod(){
        System.out.println("In after each method..!");
    }

    @AfterAll
    public static void callOnceAfterAllTest(){
        System.out.println("after all test... call once !!!");
    }
}