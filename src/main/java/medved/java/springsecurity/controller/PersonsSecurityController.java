package medved.java.springsecurity.controller;

import medved.java.springsecurity.entity.BasePerson;
import medved.java.springsecurity.entity.Persons;
import medved.java.springsecurity.repository.PersonsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonsSecurityController {

    private final PersonsRepository repository;

    public PersonsSecurityController(PersonsRepository repository) {
        this.repository = repository;
    }
//
//    @GetMapping("/first")
//    public String firstEndpoint() {
//        return "Only for first!";
//    }
//
//    @GetMapping("/third")
//    public String thirdEndpoint() {
//        return "For first & second. Third!";
//    }
//
//    @GetMapping("/all")
//    public String forAllEndpoint() {
//        return "For all users without authorization!";
//    }
//
//    @GetMapping("/second")
//    public String secondEndpoint() {
//        return "Only for second!";
//    }
//
//    @GetMapping("/fourth")
//    public String fourthEndpoint() {
//        return "For all authorized users! Fourth!";
//    }
//
//

    @GetMapping("/by-city")
    public List<Persons> getPersonsByCity(@RequestParam("city_of_living") String city) {
        return repository.findPersonsByCityOfLiving(city);
    }

    @GetMapping("/by-age")
    public List<Persons> getPersonsByAge(@RequestParam("age") int age) {
        return repository.findPersonsByAgeAsc(age);
    }

    @GetMapping("/baseperson/by-age")
    public List<BasePerson> getBasePersonsByAge(@RequestParam("age") int age) {
        return repository.findBasePersonByAge(age);
    }

    @GetMapping("/phone/by-age")
    public List<String> getPhoneByAge(@RequestParam("age") int age) {
        return repository.findPhoneNumberByAge(age);
    }

    @GetMapping("/by-fio")
    public List<Optional<Persons>> getPersonsByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return repository.findPersonsByFio(name, surname);
    }
}