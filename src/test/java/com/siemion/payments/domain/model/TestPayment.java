package com.siemion.payments.domain.model;

import java.util.List;

public class TestPayment {

    public static Payment newPayment() {
        return newPayment(null, null);
    }

    public static Payment newPayment(String id, Long version) {
        return new Payment(id, version, "Payment", "743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb", newPaymentAttributes());
    }

    private static PaymentAttributes newPaymentAttributes() {
        return new PaymentAttributes("100.21", newBeneficiaryParty(),
                newChargesInformation(), "GBP", newDebtorParty(), "Wil piano Jan", newFx(),
                "1002001", "123456789012345678", "Paying for goods/services", "FPS", "Credit", "2017-01-18", "Payment for Em's piano lessons", "InternetBanking", "ImmediatePayment", newSponsorParty());
    }

    private static SponsorParty newSponsorParty() {
        return new SponsorParty("56781234", "123123", "GBDSC");
    }

    private static Fx newFx() {
        return new Fx("FX123", "2.00000", "200.42", "USD");
    }

    private static DebtorParty newDebtorParty() {
        return new DebtorParty("EJ Brown Black", "GB29XABC10161234567801", "IBAN", "10 Debtor Crescent Sourcetown NE1", "203301", "GBDSC", "Emelia Jane Brown");
    }

    private static ChargesInformation newChargesInformation() {
        return new ChargesInformation("SHAR", List.of(new SenderCharge("5.00", "GBP"), new SenderCharge("10.00", "USD")), "1.00", "USD");
    }

    private static BeneficiaryParty newBeneficiaryParty() {
        return new BeneficiaryParty("W Owens", "31926819", "BBAN", 0, "1 The Beneficiary Localtown SE2", "403000", "GBDSC", "Wilfred Jeremiah Owens");
    }
}