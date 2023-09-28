package guru.springframework.sfgpetclinic.Exceptions;

public class ValueNotFoundException extends RuntimeException{
    public ValueNotFoundException(){

    }
    public ValueNotFoundException(String message){
        super(message);
    }
}
