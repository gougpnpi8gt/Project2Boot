package ru.vadim.Project2Boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.vadim.Project2Boot.entity.Person;
import ru.vadim.Project2Boot.service.PeopleService;

@Component
public class PersonValidator implements org.springframework.validation.Validator {
    private final PeopleService peopleService;
    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (peopleService.findByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName", "", "This fullName is already taken");
        }
    }
}
