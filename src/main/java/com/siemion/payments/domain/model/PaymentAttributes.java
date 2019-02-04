package com.siemion.payments.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
class PaymentAttributes {
    String amount;
    BeneficiaryParty beneficiaryParty;
    ChargesInformation chargesInformation;
    String currency;
    DebtorParty debtorParty;
    String endToEndReference;
    Fx fx;
    String numericReference;
    String paymentId;
    String paymentPurpose;
    String paymentScheme;
    String paymentType;
    String processingDate;
    String reference;
    String schemePaymentSubType;
    String schemePaymentType;
    SponsorParty sponsorParty;
}
