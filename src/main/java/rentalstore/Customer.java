package rentalstore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector rentals = new Vector();

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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements();
        String result = "<H1>Rental Record for <EM>" + getName() + "</EM></H1><P>\n";
        PrintBodyResult printBodyResult = new PrintBodyResult(totalAmount, frequentRenterPoints, rentals, result).invoke();
        totalAmount = printBodyResult.getTotalAmount();
        frequentRenterPoints = printBodyResult.getFrequentRenterPoints();
        result = printBodyResult.getResult();

        //add footer lines
        result += "<P>Amount owed is<EM>" + String.valueOf(totalAmount) + "</EM><P>\n";
        result += "You earned<EM>" + String.valueOf(frequentRenterPoints) + " </EM>frequent renter points";
        return result;
    }

    private class PrintBodyResult {
        private double totalAmount;
        private int frequentRenterPoints;
        private Enumeration rentals;
        private String result;

        public PrintBodyResult(double totalAmount, int frequentRenterPoints, Enumeration rentals, String result) {
            this.totalAmount = totalAmount;
            this.frequentRenterPoints = frequentRenterPoints;
            this.rentals = rentals;
            this.result = result;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public int getFrequentRenterPoints() {
            return frequentRenterPoints;
        }

        public String getResult() {
            return result;
        }

        public PrintBodyResult invoke() {
            while(rentals.hasMoreElements()){
                double thisAmount =0;
                Rental each = (Rental) rentals.nextElement();

                switch (each.getMovie().getPriceCode()){
                    case Movie.REGULAR:
                        thisAmount += 2;
                        if(each.getDayRented() > 2){
                            thisAmount+=(each.getDayRented() - 2) * 1.5;
                        }
                        break;
                    case Movie.NEW_RELEASE:
                        thisAmount+=each.getDayRented()*3;
                        break;
                        case Movie.CHILDRENS:
                            thisAmount+=1.5;
                            if(each.getDayRented() > 3){
                                thisAmount += (each.getDayRented() -3)*1.5;
                            }
                            break;
                }

                //add frequent renter points
                frequentRenterPoints ++;
                //add bonus for a two day new release rental
                if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDayRented() > 1){
                    frequentRenterPoints ++;
                }

                //show figures for this rental
                result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "<BR>\n";
                totalAmount += thisAmount;
            }
            return this;
        }
    }
}
