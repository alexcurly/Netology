public class Main {

    public static void main(String[] args) {
        TaxService taxService = new TaxService();
        Bill[] payments = new Bill[] {
                // TODO создать платежи с различным типами налогообложения
                new BillA(70_000, new IncomeTaxType(), new TaxService()),
                new BillB(100_000, new VATaxType(), new TaxService()),
                new BillC(1_000_000, new ProgressiveTaxType(), new TaxService())

        };
        for (int i = 0; i < payments.length; ++i) {
            Bill bill = payments[i];
            bill.payTaxes();
        }
    }
}
