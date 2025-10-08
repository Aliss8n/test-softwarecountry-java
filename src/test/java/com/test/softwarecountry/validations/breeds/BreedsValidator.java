package com.test.softwarecountry.validations.breeds;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import com.test.softwarecountry.model.response.breeds.BreedsResponse;

public class BreedsValidator {

    public void checkBreeds(BreedsResponse breedsResponse, int limit) {
        Assertions.assertEquals(HttpStatus.SC_OK, breedsResponse.getStatusCode(), "Unexpected HTTP status code");
        Assertions.assertEquals(1, breedsResponse.getCurrent_page(), "Incorrect current page value");
        if (limit == 0) Assertions.assertEquals(15, breedsResponse.getPer_page(), "Incorrect per_page value");
        else Assertions.assertEquals(limit, breedsResponse.getPer_page(), "Incorrect per_page value");
        Assertions.assertTrue(breedsResponse.getTotal() > 0, "Expected total breeds to be greater than zero");
        Assertions.assertNotNull(breedsResponse.getData(), "Data list should not be null");
        Assertions.assertFalse(breedsResponse.getData().isEmpty(), "Data list should not be empty");
        Assertions.assertEquals(breedsResponse.getTo(), breedsResponse.getData()
                .size(), "Incorrect number of breeds returned in current page");
        Assertions.assertNotNull(breedsResponse.getData().get(0).getBreed(), "First breed name should not be null");
        Assertions.assertNotNull(breedsResponse.getData().get(0)
                                         .getCountry(), "First breed country should not be null");
        Assertions.assertNotNull(breedsResponse.getData().get(0)
                                         .getOrigin(), "First breed origin should not be null");
        Assertions.assertNotNull(breedsResponse.getData().get(0).getCoat(), "First breed coat should not be null");
        Assertions.assertNotNull(breedsResponse.getData().get(0)
                                         .getPattern(), "First breed pattern should not be null");
        Assertions.assertTrue(breedsResponse.getFirst_page_url().contains("?page=1"), "Invalid first_page_url");
        if (breedsResponse.getPer_page() > breedsResponse.getTotal())
            Assertions.assertNull(breedsResponse.getNext_page_url(), "Invalid next_page_url");
        else Assertions.assertTrue(breedsResponse.getNext_page_url().contains("?page=2"), "Invalid next_page_url");
        Assertions.assertTrue(breedsResponse.getLast_page() >= breedsResponse.getCurrent_page(), "Last page value is invalid");
    }
}
