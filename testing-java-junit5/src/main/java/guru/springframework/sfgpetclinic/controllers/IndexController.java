package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.Exceptions.ValueNotFoundException;

public class IndexController {

    public String index(){

        return "index";
    }

    public String oupsHandler(){
        throw new ValueNotFoundException("No Value Found");
//        return "notimplemented";
    }
}
