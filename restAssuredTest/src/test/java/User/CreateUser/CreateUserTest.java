/**
 * 1 Тест. Проверка создания пользователя со всеми полями
 * 1 шаг. Отправить запрос на /user со всеми заполненвми полями.
 * ОР: Проверит, что статус 200, в поле message возвращается id  пользователя.
 *
 * 2 Тест. Проверить, что успешно создасться пользователь только с username и id
 * * 1 шаг. Отправить запрос на /user с username и id.
 * ОР: Проверит, что статус 200, в поле message возвращается id  пользователя.
 * */
package User.CreateUser;

import User.UserBaseTest;
import dto.CreateUser;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends UserBaseTest {
    CreateUser user;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createNewUserWithAllFields() throws IOException {
        CreateUser userPattern = mapper.readValue(new File("src/main/resources/user.json"), CreateUser.class);
        user = CreateUser.builder()
                .userStatus(userPattern.getUserStatus())
                .email(userPattern.getEmail())
                .firstName(userPattern.getFirstName())
                .lastName(userPattern.getLastName())
                .id(userPattern.getId())
                .username(userPattern.getUsername())
                .password(userPattern.getPassword())
                .phone(userPattern.getPhone())
                .build();

        userApi.createUser(user)
                .then()
                .body("code", equalTo(200))
                .body("message", equalTo("12"));
    }

    @Test
    public void createNewUserWithTwoFields(){
        user = CreateUser.builder()
                .id((long) 17)
                .username("tester2")
                .build();

        userApi.createUser(user)
                .then()
                .body("code", equalTo(200))
                .body("message", equalTo("17"));
    }
}
