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

    private static double TAX_SAME_DAY = 3.00; // percentage
    private static double TAX_SAME_DAY_CONSTANT = 3.00; // fixed tax

    // TODO: usar variaveis de ambiente para alterar os parametros
    @Override
    public Tax caculate(final TransferContract transferContract) {
        Tax tax = new Tax();
        final var transferDate = transferContract.getScheduling().getTransferDate();

        if (transferDate.getDayOfYear() == LocalDate.now().getDayOfYear() && transferDate.getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
            var taxaPercentage = calculatePercentage(TAX_SAME_DAY, Double.parseDouble(transferContract.getAmount().toString()));
            var taxa = taxaPercentage + TAX_SAME_DAY_CONSTANT;
            tax.setAmount(BigDecimal.valueOf(taxa));
            tax.setTaxDescription("Taxa: R$ 3.00 + 3% do valor transferido.");
        } else {
            throw new EntityNotFoundException("Tax");
        }

        return tax;
    }

    @Override
    public Tax create(Tax tax) {
        return this.taxRepository.save(tax);
    }

    public double calculatePercentage(final double percentage, final double amount) {
        return (amount * percentage ) / 100;
    }
}
