package domain;

import com.example.eatgo.domain.Restaurant;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


class RestaurantTest {

    @Test
    public void creation() {
        Restaurant restaurant =
                Restaurant.builder()
                        .id(100L)
                        .name("chicken")
                        .address("")
                        .build();

        assertThat(restaurant.getName(), Is.is("chicken"));
    }

    @Test
    public void information() {
        Restaurant restaurant =
                Restaurant.builder()
                        .id(100L)
                        .name("chicken")
                        .address("Seoul")
                        .build();
        
        assertThat(restaurant.getInformation(), Is.is("chicken in Seoul"));
    }

}