import java.util.ArrayList;
import java.util.List;

public class PersonConverter {


    public Person convertPersonTO2Person(PersonTO personTO){
        Person person = new Person();
        person.setName(personTO.getName());
        person.setPrename(personTO.getPrename());
        person.setPhoneNumber(personTO.getPhoneNumber());

        return person;
    }

    public List<Person> convertListOfPersonTOs2ListOfPersons(List<PersonTO> personTOs){
        List<Person> persons = new ArrayList<>();
        personTOs.forEach(personTO -> persons.add(convertPersonTO2Person(personTO)));
        return persons;
    }


}
