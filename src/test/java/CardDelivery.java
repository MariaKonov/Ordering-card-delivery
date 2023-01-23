import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDelivery {

    private String Date (int addDays, String patten) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(patten));
    }

    @Test
    void CardDeliveryTest() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        String currentDate = Date(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(currentDate);
        $("[data-test-id=name] input").setValue("Коновалова Мария");
        $("[data-test-id=phone] input").setValue("+79996665544");
        $("[data-test-id=agreement]").click();
        $("notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactOwnText("Встреча успешно" + currentDate));
    }
}
