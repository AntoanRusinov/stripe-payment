package com.playtomic.tests.wallet;

import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@Component
@Profile({ "develop", "test" })
public class InitialDataSetup implements CommandLineRunner {
	
	@Autowired
    private WalletRepository walletRepository;

	@Override
	public void run(String... args) {
//		repository.save(new Wallet(BigDecimal.valueOf(15434)));
//        repository.save(new Wallet(BigDecimal.valueOf(32)));
//        repository.save(new Wallet(BigDecimal.valueOf(54)));

		Wallet wallet = Wallet.builder().balance(BigDecimal.valueOf(10)).currency(Currency.getInstance("USD")).build();
		walletRepository.save(wallet);
	}

}
