package com.test.softwarecountry.steps.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import com.test.softwarecountry.hooks.common.DatabaseHook;
import com.test.softwarecountry.validations.database.DatabaseValidator;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DatabaseSteps {

    private static final DatabaseValidator databaseValidator = new DatabaseValidator();
    private ResultSet reservationResult;

    @When("I insert a user named {string}")
    public void iInsertAUserNamed(String name) throws SQLException {
        Connection conn = DatabaseHook.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name) VALUES (?)");
        stmt.setString(1, name);
        stmt.executeUpdate();
        stmt.close();
    }

    @Then("I should see a user named {string}")
    public void iShouldSeeAUserNamed(String name) throws SQLException {
        Connection conn = DatabaseHook.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        Assertions.assertTrue(rs.next(), "User '" + name + "' was not found in the database.");
        stmt.close();
    }

    @When("I query the reservations for user {string}")
    public void iQueryTheReservationsForUserUserName(String userName) throws SQLException {
        Connection conn = DatabaseHook.getConnection();
        String sql = """
                    SELECT h.name AS hotel_name, r.checkin_date, r.checkout_date
                    FROM reservations r
                    JOIN users u ON r.user_id = u.id
                    JOIN hotels h ON r.hotel_id = h.id
                    WHERE u.name = ?
                """;

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userName);
        reservationResult = stmt.executeQuery();
    }

    @Then("I should see a reservation at hotel {string} with check-in {string} and check-out {string}")
    public void iShouldSeeAReservationAtHotelHotelNameWithCheckInCheckInDateAndCheckOutCheckOutDate(String expectedHotel, String expectedCheckin, String expectedCheckout) throws SQLException {
        databaseValidator.checkReservation(reservationResult, expectedHotel, expectedCheckin, expectedCheckout);
    }
}
