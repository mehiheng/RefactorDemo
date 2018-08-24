package rental_store;
import org.junit.Test;
import remove_switch.Employee;
import rentalstore.Customer;
import rentalstore.Movie;
import rentalstore.Rental;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class rentalTest {

    @Test
    public void when_rent_CHILDRENS_4_days_return_amount_3() throws Exception {
        Customer customer=new Customer("renter");
        Movie movie=new Movie("TomAndJerry",Movie.CHILDRENS);
        Rental rental=new Rental(movie,4);
        customer.addRental(rental);
        String rentOrder=customer.statement();
        int indexOfis=rentOrder.indexOf("is<EM>");
        String totalAmount=rentOrder.substring(indexOfis+6,indexOfis+9);
        assertThat(String.valueOf((double)3), is(totalAmount));

    }

    @Test
    public void when_rent_CHILDRENS_3_days_return_amount_one_point_five() throws Exception {
        Customer customer=new Customer("renter");
        Movie movie=new Movie("TomAndJerry",Movie.CHILDRENS);
        Rental rental=new Rental(movie,3);
        customer.addRental(rental);
        String rentOrder=customer.statement();
        int indexOfis=rentOrder.indexOf("is<EM>");
        String totalAmount=rentOrder.substring(indexOfis+6,indexOfis+9);
        assertThat(String.valueOf(1.5), is(totalAmount));
    }

    @Test
    public void when_rent_CHILDRENS_3_days_REGULAR_3_days_return_amount_5_and_frequentRenterPoints_2() throws Exception {
        Customer customer=new Customer("renter");
        Movie movie=new Movie("TomAndJerry",Movie.CHILDRENS);
        Movie movie1=new Movie("kingOfRing",Movie.REGULAR);
        Rental rental=new Rental(movie,3);
        Rental rental1=new Rental(movie1,3);
        customer.addRental(rental);
        customer.addRental(rental1);
        String rentOrder=customer.statement();
        int indexOfis=rentOrder.indexOf("is<EM>");
        int indexOfEarned=rentOrder.indexOf("earned<EM>");
        int indexOfFrequent=rentOrder.indexOf(" </EM>frequent");
        String totalAmount=rentOrder.substring(indexOfis+6,indexOfis+9);
        String frequentRenterPoints=rentOrder.substring(indexOfEarned+10,indexOfFrequent);
        assertThat(String.valueOf((double) 5), is(totalAmount));
        assertThat(String.valueOf(2), is(frequentRenterPoints));
    }

    @Test
    public void when_rent_CHILDRENS_3_days_NEW_RELEASE_1_days_return_amount_ten_point_five_and_frequentRenterPoints_1() throws Exception {
        Customer customer=new Customer("renter");
        Movie movie=new Movie("TomAndJerry",Movie.CHILDRENS);
        Movie movie1=new Movie("AntMan",Movie.NEW_RELEASE);
        Rental rental=new Rental(movie,3);
        Rental rental1=new Rental(movie1,3);
        customer.addRental(rental);
        customer.addRental(rental1);
        String rentOrder=customer.statement();
        int indexOfis=rentOrder.indexOf("is<EM>");
        int indexOfEarned=rentOrder.indexOf("earned<EM>");
        int indexOfFrequent=rentOrder.indexOf(" </EM>frequent");
        String totalAmount=rentOrder.substring(indexOfis+6,indexOfis+10);
        String frequentRenterPoints=rentOrder.substring(indexOfEarned+10,indexOfFrequent);
        assertThat(String.valueOf(10.5), is(totalAmount));
        assertThat(String.valueOf(3), is(frequentRenterPoints));
    }

}

