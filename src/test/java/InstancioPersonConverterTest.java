import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.Seed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(InstancioExtension.class)
class InstancioPersonConverterTest {

    private final PersonConverter personConverter = new PersonConverter();

    @Test
    void convertPersonTO2Person() {
        // given
        PersonTO personTO = Instancio.create(PersonTO.class);
        // when
        Person person = personConverter.convertPersonTO2Person(personTO);
        // then
        assertThat(person).usingRecursiveComparison().isEqualTo(personTO);
    }

    @Test
    void convertPersonTOList2PersonList() {
        // given
        List<PersonTO> personTOList = Instancio.stream(PersonTO.class).limit(5).collect(Collectors.toList());
        // when
        List<Person> persons = personConverter.convertListOfPersonTOs2ListOfPersons(personTOList);
        // then
        assertThat(persons).usingRecursiveFieldByFieldElementComparator().isEqualTo(personTOList);
    }

    @Test
    void convertPersonTONullableField2Person() {
        // given
        // withNullable --> Instancio randomly chooses between null and non null values
        PersonTO personTO = Instancio.of(PersonTO.class).withNullable(field("phoneNumber")).create();
        // when
        Person person = personConverter.convertPersonTO2Person(personTO);
        // then
        assertThat(person).usingRecursiveComparison().isEqualTo(personTO);
    }

    @Test
    @Seed(1066875797028593003L) // reproduce failed run
    void convertFailPrintSeedTest() {
        // given
        PersonTO personTO = Instancio.create(PersonTO.class);
        // when
        Person person = personConverter.convertPersonTO2Person(personTO);

        // then
        fail("this test should fail");
    }

    // TODO: Impl param test example - see: https://www.instancio.org/user-guide/#arguments-source

}
