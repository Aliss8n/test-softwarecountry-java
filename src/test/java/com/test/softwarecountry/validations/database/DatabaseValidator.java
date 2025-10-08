package com.test.softwarecountry.validations.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;

public class DatabaseValidator {

    public void checkReservation(ResultSet reservationResult, String expectedHotel, String expectedCheckin, String expectedCheckout) throws SQLException {
        Assertions.assertNotNull(reservationResult, "Reservation result is null. Did you run the query?");
        Assertions.assertTrue(reservationResult.next(), "No reservation record found for the user.");

        Assertions.assertEquals(expectedHotel, reservationResult.getString("hotel_name"), "Hotel name mismatch");
        Assertions.assertEquals(expectedCheckin, reservationResult.getDate("checkin_date")
                .toString(), "Check-in date mismatch");
        Assertions.assertEquals(expectedCheckout, reservationResult.getDate("checkout_date")
                .toString(), "Check-out date mismatch");
    }
}
