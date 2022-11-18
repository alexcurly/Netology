public class ProgressiveTaxType extends TaxType{
    final double MININCOMETAX = 0.13; //минимальная величина налога
    final double MAXINCOMETAX = 0.15; //минимальная величина налога
    final int MINAMOUNT = 100_000; // Пороговая сумма

    @Override
    public double calculateTaxFor(double amount){
        if (amount < MINAMOUNT){
            return (amount * MININCOMETAX);
        } else {
            return (amount * MAXINCOMETAX);
        }
    }
}
