package guru.qa.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class GitHubPage {

    @Step("Открываем главную страницу")
    public GitHubPage openMainPage() {
        open("/");

        return this;
    }

    @Step("Ищем репозиторий {repositoryName}")
    public GitHubPage searchForRepository(String repositoryName) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repositoryName);
        $("#query-builder-test").submit();

        return this;
    }

    @Step("Кликаем по ссылке репозитория {repositoryName}")
    public void clickOnRepositoryLink(String repositoryName) {
        $(linkText(repositoryName)).click();
    }

    @Step("Открываем таб Issues")
    public GitHubPage openIssuesTab() {
        $("#issues-tab").click();

        return this;
    }

    @Step("Проверяем Issue с названием \"{issueName}\"")
    public void verifySeeIssueWithName(String issueName) {
        $(withText(issueName)).should(Condition.exist);
    }
}
