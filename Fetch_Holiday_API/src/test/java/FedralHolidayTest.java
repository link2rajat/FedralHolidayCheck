import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class FedralHolidayTest {

    @Test
    public void testCheckIfTodayIsHoliday() {
        try {
            String today = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            boolean isHoliday = FedralHolidayChecker.checkIfTodayIsHoliday(today);
            assertFalse(isHoliday);
        } catch (IOException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testCheckIfIndependenceDayIsHoliday() {
        try {
            String testHoliday = LocalDate.of(2024, 7, 4).format(DateTimeFormatter.ISO_DATE);
            boolean isHoliday = FedralHolidayChecker.checkIfTodayIsHoliday(testHoliday);
            assertTrue(isHoliday);
        } catch (IOException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}