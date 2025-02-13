
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.model.Movie;
import com.example.model.MovieTicket;
import com.example.model.Screening;
import com.example.service.Order;
import com.example.strategies.StudentWeekdayPricingStrategy;
import com.example.strategies.WeekendGroupPricingStrategy;

class OrderTest {

    @Test
    void testCalculatePrice_StudentOnWeekday() {
        Movie movie = new Movie("Interstellar", 10.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 10, 18, 0)); // Maandag
        Order order = new Order(new StudentWeekdayPricingStrategy(),true);

        order.addTicket(new MovieTicket(screening, false)); // Ticket 1
        order.addTicket(new MovieTicket(screening, false)); // Ticket 2
        order.addTicket(new MovieTicket(screening, true));  // Ticket 3 (premium)
        order.addTicket(new MovieTicket(screening, true));  // Ticket 4

        double expectedPrice = 10.0 + (10.0 + 2.0);
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }

    @Test
    void testCalculatePrice_NonStudentWeekendWithGroupDiscount() {
        Movie movie = new Movie("Dune", 10.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 15, 20, 0)); // Zaterdag
        Order order = new Order(new WeekendGroupPricingStrategy(),false);

        for (int i = 0; i < 6; i++) {
            order.addTicket(new MovieTicket(screening, true)); // 6 premium tickets
        }

        double expectedPrice = 6 * (10.0 + 3.0) * 0.9; // 10% korting
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }

    @Test
    void testCalculatePrice_StudentWeekend() {
        Movie movie = new Movie("Avatar", 12.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 16, 16, 0)); // Zondag
        Order order = new Order(new StudentWeekdayPricingStrategy(),true);

        order.addTicket(new MovieTicket(screening, false)); // Ticket 1
        order.addTicket(new MovieTicket(screening, true));  // Ticket 2
        order.addTicket(new MovieTicket(screening, true));  // Ticket 3 (premium)
        order.addTicket(new MovieTicket(screening, false)); // Ticket 4

        double expectedPrice = 12.0 + (12.0 + 2.0);
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }

    @Test
    void testCalculatePrice_StudentWeekendWithGroupDiscount() {
        Movie movie = new Movie("Inception", 11.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 16, 18, 0)); // Zondag
        Order order = new Order(new WeekendGroupPricingStrategy(),true);

        for (int i = 0; i < 6; i++) {
            order.addTicket(new MovieTicket(screening, true)); // 6 premium student tickets
        }

        double expectedPrice = 6 * (11.0 + 2.0); // Geen groepskorting voor studenten
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }

    @Test
    void testCalculatePrice_NonStudentWeekday() {
        Movie movie = new Movie("Tenet", 15.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 12, 14, 0)); // Woensdag
        Order order = new Order(new StudentWeekdayPricingStrategy(),true);

        order.addTicket(new MovieTicket(screening, false)); // Ticket 1
        order.addTicket(new MovieTicket(screening, false)); // Ticket 2
        order.addTicket(new MovieTicket(screening, true));  // Ticket 3 (premium)
        order.addTicket(new MovieTicket(screening, true));  // Ticket 4

        double expectedPrice = 15.0 + (15.0 + 2.0);
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }
    @Test
    void testCalculatePrice_NonStudentWeekendWithoutGroupDiscount() {
        Movie movie = new Movie("Dune", 10.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 15, 20, 0)); // Zaterdag
        Order order = new Order(new WeekendGroupPricingStrategy(),false);

        for (int i = 0; i < 5; i++) {
            order.addTicket(new MovieTicket(screening, true)); // 5 premium tickets
        }

        double expectedPrice = 5 * (10.0 + 3.0); // Geen groepskorting
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }

    @Test
    void testCalculatePrice_StudentWeekendWithOddTickets() {
        Movie movie = new Movie("Avatar", 12.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 16, 16, 0)); // Zondag
        Order order = new Order(new StudentWeekdayPricingStrategy(),true);

        order.addTicket(new MovieTicket(screening, false)); // Ticket 1
        order.addTicket(new MovieTicket(screening, true));  // Ticket 2
        order.addTicket(new MovieTicket(screening, false)); // Ticket 3

        double expectedPrice = 12.0 + (12.0); // Tweede ticket gratis, derde telt wel mee
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }

    @Test
    void testCalculatePrice_StudentOnWeekday_MixedPremium() {
        Movie movie = new Movie("Tenet", 15.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 12, 14, 0)); // Woensdag
        Order order = new Order(new StudentWeekdayPricingStrategy(),true);

        order.addTicket(new MovieTicket(screening, false)); // Ticket 1
        order.addTicket(new MovieTicket(screening, true));  // Ticket 2 (premium)
        order.addTicket(new MovieTicket(screening, false)); // Ticket 3
        order.addTicket(new MovieTicket(screening, true));  // Ticket 4 (premium)

        double expectedPrice = 15.0 + (15.0); 
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }

    @Test
    void testCalculatePrice_NonStudentWeekdayPremium() {
        Movie movie = new Movie("Tenet", 15.0);
        Screening screening = new Screening(movie, LocalDateTime.of(2025, 2, 12, 14, 0)); // Woensdag
        Order order = new Order(new StudentWeekdayPricingStrategy(),false);

        order.addTicket(new MovieTicket(screening, true)); // Ticket 1 (premium)
        order.addTicket(new MovieTicket(screening, true)); // Ticket 2 (premium)

        double expectedPrice = (15.0 + 3.0) + (15.0 + 3.0);
        assertEquals(expectedPrice, order.calculatePrice(), 0.01);
    }
    @Test
    void testCalculatePrice_EmptyOrder() {
        Order order = new Order(new StudentWeekdayPricingStrategy(),true);
        assertEquals(0.0, order.calculatePrice(), 0.01);
    }

}
