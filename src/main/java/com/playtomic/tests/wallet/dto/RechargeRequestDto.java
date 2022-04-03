package com.playtomic.tests.wallet.dto;

import com.playtomic.tests.wallet.enums.PaymentProviderType;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.CreditCardNumber;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class RechargeRequestDto {

    @NotNull(message = "Walled id must be provided")
    private Long walletId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Please recharge your wallet with amount bigger than 0.")
    private BigDecimal amount;

    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Please enter valid card number.")
    private String cardNumber;

    @NotNull(message = "Payment provider must be added")
    private PaymentProviderType paymentProviderType;

}