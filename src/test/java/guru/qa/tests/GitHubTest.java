package guru.qa.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class GitHubTest extends BaseTest {

    private final String repository = "eroshenkoam/allure-example";
    private final String issueName = "Hello World";

    @Test
    @DisplayName("Тест на чистом Selenide (с Listener)")
    void pureGitHubIssueTest() {
        open("/");
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(linkText("eroshenkoam/allure-example")).click();

        $("#issues-tab").click();

        $(withText("Hello World")).should(Condition.exist);
    }

    @Test
    @DisplayName("Тест с лямбда шагами")
    void lambdaStepsGitHubIssueTest() {
        step("Открываем главную страницу", () -> {
            open("/");
        });
        step("Ищем репозиторий " + repository, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(repository);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория " + repository, () -> {
            $(linkText(repository)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем Issue с названием " + issueName, () -> {
            $(withText(issueName)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Тест с шагами с аннотацией @Step (и Page Object)")
    void annotatedStepTest() {
        gitHubPage.openMainPage();

        gitHubPage.searchForRepository(repository)
                .clickOnRepositoryLink(repository);

        gitHubPage.openIssuesTab();

        gitHubPage.verifySeeIssueWithName(issueName);
    }
}
