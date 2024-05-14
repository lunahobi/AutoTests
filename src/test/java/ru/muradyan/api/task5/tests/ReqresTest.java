package ru.muradyan.api.task5.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Test;
import ru.muradyan.api.task5.pojos.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class ReqresTest {

    @Test
    @DisplayName("Получить список пользователей со страницы 2")
    public void getUsers() {
        List<UserData> users = given().
                when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserListSchema.json"))
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data", UserData.class);
        assertThat(users).extracting(UserData::getId).isNotNull();
        assertThat(users).extracting(UserData::getFirst_name).contains("Tobias");
        assertThat(users).extracting(UserData::getLast_name).contains("Funke");
    }

    @Test
    @DisplayName("Получить пользователя с id=2")
    public void getUser() {
        UserData user = given().
                when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserSingleSchema.json"))
                .extract().jsonPath().getObject("data", UserData.class);
        assertThat(user).extracting(UserData::getId).isEqualTo(2);
        assertThat(user).extracting(UserData::getEmail).isEqualTo("janet.weaver@reqres.in");
        assertThat(user).extracting(UserData::getFirst_name).isEqualTo("Janet");
        assertThat(user).extracting(UserData::getLast_name).isEqualTo("Weaver");
        assertThat(user).extracting(UserData::getAvatar).isEqualTo("https://reqres.in/img/faces/2-image.jpg");
    }

    @Test
    @DisplayName("Получить пользователя с id=23")
    public void getUserNotFound() {
        given().
                when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
                .body(equalTo("{}"));
    }

    @Test
    @DisplayName("Получить список ресурсов")
    public void getResourses() {
        List<ResourseData> resourses = given().
                when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResourseListSchema.json"))
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data", ResourseData.class);
        assertThat(resourses).extracting(ResourseData::getId).isNotNull();
        assertThat(resourses).extracting(ResourseData::getName).contains("fuchsia rose");
        assertThat(resourses).extracting(ResourseData::getYear).contains(2001);
        assertThat(resourses).extracting(ResourseData::getColor).contains("#C74375");
    }

    @Test
    @DisplayName("Получить ресурс с id=2")
    public void getResourse() {
        ResourseData resourse = given().
                when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResourseSingleSchema.json"))
                .extract().jsonPath().getObject("data", ResourseData.class);
        assertThat(resourse).extracting(ResourseData::getId).isEqualTo(2);
        assertThat(resourse).extracting(ResourseData::getName).isEqualTo("fuchsia rose");
        assertThat(resourse).extracting(ResourseData::getYear).isEqualTo(2001);
        assertThat(resourse).extracting(ResourseData::getColor).isEqualTo("#C74375");
        assertThat(resourse).extracting(ResourseData::getPantone_value).isEqualTo("17-2031");
    }

    @Test
    @DisplayName("Получить ресурс с id=23")
    public void getResourseNotFound() {
        given().
                when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404)
                .body(equalTo("{}"));
    }

    @Test
    @DisplayName("Создать пользователя")
    public void createUser() {
        UserRequest rq =
                UserRequest.builder()
                        .name("morpheus")
                        .job("leader")
                        .build();

        UserResponse rs = given()
                .contentType(ContentType.JSON)
                .body(rq)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CreateUserResponseSchema.json"))
                .extract().as(UserResponse.class);

        assertThat(rs)
                .isNotNull()
                .extracting(UserResponse::getName)
                .isEqualTo(rq.getName());

        assertThat(rs)
                .isNotNull()
                .extracting(UserResponse::getJob)
                .isEqualTo(rq.getJob());
    }

    @Test
    @DisplayName("Обновить пользователя PUT")
    public void updateUserPut() {
        UserRequest rq =
                UserRequest.builder()
                        .name("morpheus")
                        .job("zion resident")
                        .build();

        UserResponse rs = given()
                .contentType(ContentType.JSON)
                .body(rq)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UpdateUserResponseSchema.json"))
                .extract().as(UserResponse.class);

        assertThat(rs)
                .isNotNull()
                .extracting(UserResponse::getName)
                .isEqualTo(rq.getName());

        assertThat(rs)
                .isNotNull()
                .extracting(UserResponse::getJob)
                .isEqualTo(rq.getJob());
    }

    @Test
    @DisplayName("Обновить пользователя PATCH")
    public void updateUserPatch() {
        UserRequest rq =
                UserRequest.builder()
                        .name("morpheus")
                        .job("zion resident")
                        .build();

        UserResponse rs = given()
                .contentType(ContentType.JSON)
                .body(rq)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UpdateUserResponseSchema.json"))
                .extract().as(UserResponse.class);

        assertThat(rs)
                .isNotNull()
                .extracting(UserResponse::getName)
                .isEqualTo(rq.getName());

        assertThat(rs)
                .isNotNull()
                .extracting(UserResponse::getJob)
                .isEqualTo(rq.getJob());
    }

    @Test
    @DisplayName("Удалить пользователя")
    public void deleteUser() {
        given().
                when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .body(equalTo(""));
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registerSuccessful() {
        LoginRegisterRequest rq =
                LoginRegisterRequest.builder()
                        .email("eve.holt@reqres.in")
                        .password("pistol")
                        .build();

        LoginRegisterResponse rs = given()
                .contentType(ContentType.JSON)
                .body(rq)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("RegisterSuccessfulResponseSchema.json"))
                .extract().as(LoginRegisterResponse.class);

        assertThat(rs)
                .isNotNull()
                .extracting(LoginRegisterResponse::getId)
                .isEqualTo(4);

        assertThat(rs)
                .isNotNull()
                .extracting(LoginRegisterResponse::getToken)
                .isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Неуспешная регистрация")
    public void registerUnsuccessful() {
        LoginRegisterRequest rq =
                LoginRegisterRequest.builder()
                        .email("sydney@fife")
                        .build();

        LoginRegisterResponse rs = given()
                .contentType(ContentType.JSON)
                .body(rq)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("RegisterLoginUnsuccessfulResponseSchema.json"))
                .extract().as(LoginRegisterResponse.class);
        assertThat(rs)
                .isNotNull()
                .extracting(LoginRegisterResponse::getError)
                .isEqualTo("Missing password");
    }

    @Test
    @DisplayName("Успешная авторизация")
    public void loginSuccessful() {
        LoginRegisterRequest rq =
                LoginRegisterRequest.builder()
                        .email("eve.holt@reqres.in")
                        .password("cityslicka")
                        .build();

        LoginRegisterResponse rs = given()
                .contentType(ContentType.JSON)
                .body(rq)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("LoginSuccessfulResponseSchema.json"))
                .extract().as(LoginRegisterResponse.class);

        assertThat(rs)
                .isNotNull()
                .extracting(LoginRegisterResponse::getToken)
                .isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Неуспешная авторизация")
    public void loginUnsuccessful() {
        LoginRegisterRequest rq =
                LoginRegisterRequest.builder()
                        .email("peter@klaven")
                        .build();

        LoginRegisterResponse rs = given()
                .contentType(ContentType.JSON)
                .body(rq)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("RegisterLoginUnsuccessfulResponseSchema.json"))
                .extract().as(LoginRegisterResponse.class);

        assertThat(rs)
                .isNotNull()
                .extracting(LoginRegisterResponse::getError)
                .isEqualTo("Missing password");
    }

    @Test
    @DisplayName("Получить список пользователей с задержкой в 3 секунды")
    public void getUsersDelay() {
        List<UserData> users = given().
                when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
                .time(greaterThan(3000L)).and().time(lessThan(6000L))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserListSchema.json"))
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data", UserData.class);
        assertThat(users).extracting(UserData::getId).isNotNull();
        assertThat(users).extracting(UserData::getFirst_name).contains("George");
        assertThat(users).extracting(UserData::getLast_name).contains("Bluth");
    }

}
