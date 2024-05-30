import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class FedralHolidayTest {

    @Test
    public void testCheckIfTodayIsHoliday() {
        try {

            boolean isHoliday = FedralHolidayChecker.checkIfTodayIsHoliday(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

            assertFalse(isHoliday);
        } catch (IOException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testCheckIfIndependenceDayIsHoliday() {
        try {
            boolean isHoliday = FedralHolidayChecker.checkIfTodayIsHoliday(LocalDate.of(2024, 7, 4).format(DateTimeFormatter.ISO_DATE));

            assertTrue(isHoliday);
        } catch (IOException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}