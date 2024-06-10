import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class FedralHolidayChecker {

    private static final  String url = "https://www.federalreserve.gov/aboutthefed/k8.htm";

    public static void main(String[] args) {
        try {
            String today = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            //String testHoliday = LocalDate.of(2024, 7, 4).format(DateTimeFormatter.ISO_DATE);
            boolean isHoliday = checkIfTodayIsHoliday(today);
            System.out.println("Is today a federal holiday? " + isHoliday);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkIfTodayIsHoliday(String date) throws IOException {
        List<String> holidayDates = fetchHolidays();
        return holidayDates.contains(date);
    }


    private static List<String> fetchHolidays(){
        List<String> holidayDates = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();

            Element table = doc.selectFirst("table");
            if (table != null) {

                Elements header = table.select("th");
                System.out.println(header.toString());
                // Get all rows from the table
                String year = header.get(0).text();
                //System.out.println("Year is : "+year);
                Elements rows = table.select("tr");

                // Skip the first row (header row)
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");
                    // System.out.println(columns.toString());
                    // Extract holiday name and date from columns
                    String holidayDate = columns.get(0).text();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
                    try {
                        // Parse the input date
                        Date date = inputFormat.parse(holidayDate);
                        // Format the date to the desired output format
                        String outputDate = outputFormat.format(date);
                        outputDate = outputDate.substring(0, 6) + year;

                        LocalDate localDate = LocalDate.parse(outputDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                        //System.out.println(localDate.format(DateTimeFormatter.ISO_DATE));
                        holidayDates.add(localDate.format(DateTimeFormatter.ISO_DATE));
                    } catch (ParseException e) {
                        System.out.println("Error parsing the input date: " + e.getMessage());
                    }

                }
            } else {
                System.out.println("No holiday data found on the page.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return holidayDates;
    }
}