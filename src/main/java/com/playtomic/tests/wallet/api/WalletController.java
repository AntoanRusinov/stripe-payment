package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.dto.RechargeRequestDto;
import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping(value = "/{id}")
    public Wallet getWallet(@PathVariable("id") Long walletId) {
        log.info("Getting wallet with id: {}", walletId);
        return walletService.getWallet(walletId);
    }

    @GetMapping(value = "/")
    public List<Wallet> getAllWallets() {
        log.info("Getting all wallets available");
        return walletService.getWallets();
    }

    @PatchMapping("/recharge/{id}")
    public Wallet rechargeWallet(@Valid @RequestBody RechargeRequestDto request) {
        log.info("Recharge amount of {} to wallet with id {}.", request.getAmount(), request.getWalletId());
        return walletService.rechargeWallet(request);
    }

}