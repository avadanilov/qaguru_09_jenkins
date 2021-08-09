package guru.qa.qaguru_09_jenkins.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import guru.qa.qaguru_09_jenkins.helpers.Attach;
import guru.qa.qaguru_09_jenkins.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.qameta.allure.Allure.step;

public class PracticeFormWithPOTests {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            eMail = faker.internet().emailAddress(),
            gender = "Male",
            number = faker.numerify("8#########"),
            subject = "maths",
            hobby = "Reading",
            address = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi",
            picturePath = "./img/",
            pictureFileName = "1.png";

    String date = "28", //date of birth
            month = "July",
            year = "2005";

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @AfterEach
    void tearDown() {
        Attach.screenshotAs("Last screen");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    void positiveFillTest() {

        step("Open students registration form", () -> registrationPage.openPage());
        step("Fill the form", () -> {
            registrationPage.typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeEmail(eMail)
                    .selectGender(gender)
                    .typeUserNumber(number)
                    .setDateOfBirth(date, month, year)
                    .setSubjects(subject)
                    .setHobby(hobby)
                    .uploadPicture(picturePath + pictureFileName)
                    .setCurrentAddress(address)
                    .setState(state)
                    .setCity(city);
        });
        step("Submit form", () -> registrationPage.submit());
        step("Verify form results", () -> {
            registrationPage.checkStudentName(firstName, lastName)
                    .checkStudentEmail(eMail)
                    .checkMobile(number)
                    .checkDateOfBirth(date, month, year)
                    .checkSubjects(subject)
                    .checkHobbies(hobby)
                    .checkPicture(pictureFileName)
                    .checkAddress(address)
                    .checkStateAndCity(state, city);
        });
    }
}
