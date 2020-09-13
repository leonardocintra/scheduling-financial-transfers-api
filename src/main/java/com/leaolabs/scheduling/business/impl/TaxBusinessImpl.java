package com.leaolabs.scheduling.business.impl;

import com.leaolabs.scheduling.business.TaxBusiness;
import com.leaolabs.scheduling.commons.exception.EntityNotFoundException;
import com.leaolabs.scheduling.model.Tax;
import com.leaolabs.scheduling.model.TransferContract;
import com.leaolabs.scheduling.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TaxBusinessImpl implements TaxBusiness {

    @Autowired
    private TaxRepository taxRepository;

    private static double PERCENTAGE_TAX_SAME_DAY = 3.00; // percentage
    private static double FIXED_VALUE_OF_TAX_SAME_DAY = 3.00; // fixed tax
    private static double FIXED_VALUE_OF_TAX_TEN_DAY_RULE = 12.00; // fixed tax

    // TODO: usar variaveis de ambiente para alterar os parametros
    @Override
    public Tax caculate(final TransferContract transferContract) {
        final var transferDate = transferContract.getScheduling().getTransferDate();

        if (isSameDayTransfer(transferDate)) {
            return getTaxForSameDayTransfer(transferContract);
        }

        if (isInTheTenDayRule(transferDate)) {
            return getTaxForTheTenDayRule(transferContract, transferDate);
        }

        throw new EntityNotFoundException("Tax");
    }

    private Tax getTaxForSameDayTransfer(final TransferContract transferContract) {
        Tax tax = new Tax();
        var taxPercentage = calculatePercentage(PERCENTAGE_TAX_SAME_DAY, Double.parseDouble(transferContract.getAmount().toString()));
        var taxValue = taxPercentage + FIXED_VALUE_OF_TAX_SAME_DAY;
        tax.setAmount(BigDecimal.valueOf(taxValue));
        tax.setTaxDescription("Taxa: Transferências no mesmo dia do agendamento tem uma taxa de $3 mais 3% do valor a ser transferido.");

        return this.taxRepository.save(tax);
    }

    private Tax getTaxForTheTenDayRule(final TransferContract transferContract, final LocalDate transferDate) {
        Tax tax = new Tax();
        var days = Math.abs(LocalDate.now().getDayOfYear() - transferDate.getDayOfYear());
        var taxValue = days * FIXED_VALUE_OF_TAX_TEN_DAY_RULE;

        tax.setAmount(BigDecimal.valueOf(taxValue));
        tax.setTaxDescription("Taxa: Transferências até 10 dias da data de agendamento possuem uma taxa de $12 multiplicado pela quantidade de dias da data de agendamento até a data de transferência.");

        return this.taxRepository.save(tax);
    }

    private double calculatePercentage(final double percentage, final double amount) {
        return (amount * percentage) / 100;
    }

    private boolean isInTheTenDayRule(final LocalDate transferDate) {
        return LocalDate.now().getDayOfYear() - transferDate.getDayOfYear() < 10;
    }

    private boolean isSameDayTransfer(final LocalDate transferDate) {
        return transferDate.getDayOfYear() == LocalDate.now().getDayOfYear()
                && transferDate.getDayOfMonth() == LocalDate.now().getDayOfMonth();
    }
}
