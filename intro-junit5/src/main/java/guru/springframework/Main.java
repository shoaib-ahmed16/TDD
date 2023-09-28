package guru.springframework;

public class Main {
    static {
        System.out.println("Inside static  block...!");
    }


    public static void main(String[] args) {
        System.out.println("Inside Main method...!");
        Main main =new Main();
        Main.helloStatic();
        main.helloDynamic();
    }

    {
        System.out.println("Inside dynamic block method...!");
    }
    public Main(){
        System.out.println("Inside Main Constructor...!");
    }
    public  void helloDynamic(){
        System.out.println("Inside helloDynamic  method...!");
    }
    public  static void helloStatic(){
        System.out.println("Inside helloStatic method...!");
    }

}
