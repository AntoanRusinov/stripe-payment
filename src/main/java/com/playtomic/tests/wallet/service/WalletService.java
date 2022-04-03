package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.dto.PayRequestDto;
import com.playtomic.tests.wallet.dto.RechargeRequestDto;
import com.playtomic.tests.wallet.entity.Transaction;
import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.enums.PaymentProviderType;
import com.playtomic.tests.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final StripeService stripeService;

    public Wallet getWallet(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow();
    }

    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    @Transactional
    public Wallet rechargeWallet(RechargeRequestDto request) {
        Wallet wallet = walletRepository.findById(request.getWalletId()).orElseThrow();

        Payment payment = null;
        if (request.getPaymentProviderType().equals(PaymentProviderType.STRIPE)) {
            payment = chargeWithStripe(request.getCardNumber(), request.getAmount());
        }

        BigDecimal newBalance = wallet.getBalance().add(request.getAmount());
        wallet.setBalance(newBalance);

        Transaction successfulTransaction = Transaction.builder().paymentProviderId(
                payment.getId()).paymentProvider(PaymentProviderType.STRIPE).amount(request.getAmount()).build();
        wallet.addTransaction(successfulTransaction);
        return walletRepository.save(wallet);
    }

    public Transaction pay(PayRequestDto payRequestDto) {
        // TODO: implement and use all data in PayRequestDto.class
        return null;
    }

    public Transaction refund(String transactionId) {
        // TODO: by transactionId we will find the wallet and
        return null;
    }

    // TODO: add this exception
    @Retryable( /*value = StripePaymentNotSuccessful.class,*/
            maxAttempts = 5, backoff = @Backoff(delay = 3000))
    private Payment chargeWithStripe(String creditCardNumber, BigDecimal amount) {
        // TODO: Lock.lock() by credit card number
        Payment payment = stripeService.charge(creditCardNumber, amount);
        // TODO: Lock.UNLOCK()!!!
        return payment;
    }

}