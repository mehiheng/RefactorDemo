package rentalstore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector rentals = new Vector();
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg){
        rentals.addElement(arg);
    }

    public String getName() {
        return name;
    }

    public String statement(){
        Enumeration rentals = this.rentals.elements();
        String result = "<H1>Rental Record for <EM>" + getName() + "</EM></H1><P>\n";
        result += printBodyResult(rentals, result);
        result += "<P>Amount owed is<EM>" + String.valueOf(totalAmount) + "</EM><P>\n";
        result += "You earned<EM>" + String.valueOf(frequentRenterPoints) + " </EM>frequent renter points";
        return result;
    }

    private String printBodyResult(Enumeration rentals, String result) {
        while(rentals.hasMoreElements()){
            double thisAmount =0;
            Rental each = (Rental) rentals.nextElement();
            int getPriceCode=each.getMovie().getPriceCode();
            thisAmount = REGULAR(thisAmount, each, getPriceCode);
            thisAmount = NEW_RELEASE(thisAmount, each, getPriceCode);
            thisAmount = CHILDRENS(thisAmount, each, getPriceCode);
            frequentRenterPoints(each);
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "<BR>\n";
            totalAmount += thisAmount;
        }
        return result;
    }

    private double CHILDRENS(double thisAmount, Rental each, int getPriceCode) {
        if(getPriceCode==Movie.CHILDRENS){
            thisAmount+=1.5;
            if(each.getDayRented() > 3){
                thisAmount += (each.getDayRented() -3)*1.5;
            }
        }
        return thisAmount;
    }

    private double NEW_RELEASE(double thisAmount, Rental each, int getPriceCode) {
        if(getPriceCode==Movie.NEW_RELEASE){
            thisAmount+=each.getDayRented()*3;
        }
        return thisAmount;
    }

    private double REGULAR(double thisAmount, Rental each, int getPriceCode) {
        if(getPriceCode==Movie.REGULAR){
            thisAmount += 2;
            if(each.getDayRented() > 2){
                thisAmount+=(each.getDayRented() - 2) * 1.5;
            }
        }
        return thisAmount;
    }

    private void frequentRenterPoints(Rental each) {
        frequentRenterPoints ++;
        if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDayRented() > 1){
            frequentRenterPoints ++;
        }
    }
}
