public class VATaxType extends TaxType{
    final double VATAX = 0.2; //величина налога НДС

    @Override
    public double calculateTaxFor(double amount){
        return (amount * VATAX);
    }
}
