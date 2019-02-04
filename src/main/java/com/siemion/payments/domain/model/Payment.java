package com.siemion.payments.domain.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Version;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment { // must be public
    @SuppressFBWarnings("ES_COMPARING_PARAMETER_STRING_WITH_EQ")
    @Wither
    String id;
    @Version
    private Long version;
    String type;
    String organisationId;
    PaymentAttributes attributes;
}
