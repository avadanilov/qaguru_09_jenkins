package guru.qa.qaguru_09_jenkins.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.qaguru_09_jenkins.components.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class RegistrationPage {

    private static final String FORM_TITLE = "Student Registration Form";
    private static final String RESULTS_TITLE = "Thanks for submitting the form";

    private final SelenideElement modal = $("[role=dialog]");
    private final Calendar calendar = new Calendar();

    public RegistrationPage
    openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
        return this;
    }

    public RegistrationPage typeFirstName(String firstName) {
        $("#firstName").val(firstName);
        return this;
    }

    public RegistrationPage typeLastName(String lastName) {
        $("#lastName").val(lastName);
        return this;
    }

    public void checkResultsTitle() {
        modal.$(".modal-title").shouldHave(text(RESULTS_TITLE));
    }

    public void checkResultsValue() {
        modal.$(".table-responsive").shouldHave(text("FirstName LastName"));
    }

    public RegistrationPage typeEmail(String eMail) {
        $("input#userEmail").val(eMail);
        return this;
    }

    public RegistrationPage selectGender(String gender) {

        $(format("[name=gender][value=%s]", gender)).parent().click();
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage typeUserNumber(String number) {
        $("input#userNumber").val(number);
        return this;
    }

    public RegistrationPage setSubjects(String subject) {
        $("#subjectsInput").val(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        $("#hobbiesWrapper").$(byText(hobby)).scrollIntoView(true).click();
        return this;
    }

    public RegistrationPage uploadPicture(String pictureFileName) {
        $("#uploadPicture").uploadFromClasspath(pictureFileName);
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        $("#currentAddress").val(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    public void submit() {
        $("#submit").click();
    }

    public RegistrationPage checkStudentName(String firstName, String lastName) {
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        return this;
    }

    public RegistrationPage checkStudentEmail(String eMail) {
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(eMail));
        return this;
    }

    public RegistrationPage checkGender(String gender) {
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        return this;
    }

    public RegistrationPage checkMobile(String mobile) {
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(mobile));
        return this;
    }

    public RegistrationPage checkDateOfBirth(String date, String month, String year) {
        $(".table-responsive").$(byText("Date of Birth"))
                .parent().shouldHave(text(date + " " + month + "," + year));
        return this;
    }

    public RegistrationPage checkSubjects(String subject) {
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        return this;
    }

    public RegistrationPage checkHobbies(String hobby) {
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobby));
        return this;
    }

    public RegistrationPage checkPicture(String fileName) {
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(fileName));
        return this;
    }

    public RegistrationPage checkAddress(String address) {
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(address));
        return this;
    }

    public RegistrationPage checkStateAndCity(String state, String city) {
        $(".table-responsive").$(byText("State and City"))
                .parent().shouldHave(text(state + " " + city));
        return this;
    }
}
