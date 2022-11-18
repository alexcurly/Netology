public class IncomeTaxType extends TaxType {
    final double INCOMETAX = 0.13; //величина подоходного налога

    @Override
    public double calculateTaxFor(double amount){
        return (amount * INCOMETAX);
    }
}
