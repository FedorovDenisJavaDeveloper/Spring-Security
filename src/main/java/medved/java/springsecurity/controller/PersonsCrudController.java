package medved.java.springsecurity.controller;

import medved.java.springsecurity.entity.BasePerson;
import medved.java.springsecurity.entity.Persons;
import medved.java.springsecurity.repository.PersonsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/")
public class PersonsCrudController {
    private final PersonsRepository personsRepository;

    public PersonsCrudController(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    @GetMapping("/read-all")
    @Secured("ROLE_READ")
    public List<Persons> readAll() {
        return personsRepository.findAll();
    }

    @PostMapping("/save")
    @RolesAllowed("ROLE_WRITE")
    public ResponseEntity<Persons> savePersons(@RequestBody Persons person) {
        return ResponseEntity.ok(personsRepository.save(person));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_WRITE', 'ROLE_DELETE')")
    public ResponseEntity<BasePerson> deletePersons(@RequestBody BasePerson basePerson) {
        personsRepository.deleteById(basePerson);
        return ResponseEntity.ok(basePerson);
    }

    @GetMapping("/user/{user}")
    @PreAuthorize("#username==authentication.principal.username")
    public String checkUser(@PathVariable("user") String username) {
        return "Hello from authorized user " + username;
    }
}
