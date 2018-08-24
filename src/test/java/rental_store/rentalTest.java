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
        int a=rentOrder.indexOf("is");
        int b=rentOrder.indexOf("You");
        String totalAmount=rentOrder.substring(a+2,a+5);
        assertThat(String.valueOf((double)3), is(totalAmount));

    }

    @Test
    public void when_rent_CHILDRENS_3_days_return_amount_one_point_five() throws Exception {
        Customer customer=new Customer("renter");
        Movie movie=new Movie("TomAndJerry",Movie.CHILDRENS);
        Rental rental=new Rental(movie,3);
        customer.addRental(rental);
        String rentOrder=customer.statement();
        int a=rentOrder.indexOf("is");
        int b=rentOrder.indexOf("You");
        String totalAmount=rentOrder.substring(a+2,a+5);
        assertThat(String.valueOf(1.5), is(totalAmount));
    }

//    @Test
//    public void should_return_200_when_payAmount_given_employee_is_Salesman_and_monthly_salary_is_100_and_commission_is_100() throws Exception {
//        Employee salesman = new Employee(1);
//        salesman.setMonthlySalary(100);
//        salesman.setCommission(100);
//
//        int payAmount = salesman.payAmount();
//
//        assertEquals(200, payAmount);
//    }
}

