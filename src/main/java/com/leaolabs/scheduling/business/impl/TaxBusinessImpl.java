package com.leaolabs.scheduling.business.impl;

import com.leaolabs.scheduling.business.TaxBusiness;
import com.leaolabs.scheduling.commons.exception.EntityNotFoundException;
import com.leaolabs.scheduling.commons.utils.Utils;
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

    private final Utils utils = new Utils();
    private final static double PERCENTAGE_TAX_SAME_DAY = 3.00; // percentage
    private final static double FIXED_VALUE_OF_TAX_SAME_DAY = 3.00; // fixed tax
    private final static double FIXED_VALUE_OF_TAX_TEN_DAY_RULE = 12.00; // fixed tax

    // TODO: usar variaveis de ambiente para alterar os parametros
    @Override
    public Tax caculate(final TransferContract transferContract) {
        final var transferDate = transferContract.getScheduling().getTransferDate();

        if (isSameDayTransfer(transferDate)) {
            return this.getTaxForSameDayTransfer(transferContract);
        }

        if (isInTheTenDayRule(transferDate)) {
            return this.getTaxForTheTenDayRule(transferContract);
        }

        if (isOperationTypeC(transferDate, transferContract.getAmount())) {
            return this.getTaxForOperationTypeC(transferContract);
        }

        throw new EntityNotFoundException("Tax");
    }

    private Tax getTaxForOperationTypeC(final TransferContract transferContract) {
        Tax tax = new Tax();

        final var daysDiff = utils.getDaysDiff(transferContract.getScheduling().getTransferDate());
        final var taxPercentageToCalculate = getTaxPercentageForOperationTypeC(transferContract.getAmount(), daysDiff);
        final var taxPercentage = utils.calculatePercentage(taxPercentageToCalculate, Double.parseDouble(transferContract.getAmount().toString()));

        tax.setAmount(BigDecimal.valueOf(taxPercentage));
        tax.setTaxDescription("Operações do tipo C tem uma taxa regressiva conforme a data de transferência: \n" +
                "de 10 até 20 dias: 8% \n " +
                "de 20 até 30 dias: 6% \n " +
                "de 30 até 40 dias: 4% \n " +
                "de 40 dias ou mais e valor superior a 100.000: 2%");

        return this.taxRepository.save(tax);
    }

    private Tax getTaxForSameDayTransfer(final TransferContract transferContract) {
        Tax tax = new Tax();
        var taxPercentage = utils.calculatePercentage(PERCENTAGE_TAX_SAME_DAY, Double.parseDouble(transferContract.getAmount().toString()));
        var taxValue = taxPercentage + FIXED_VALUE_OF_TAX_SAME_DAY;
        tax.setAmount(BigDecimal.valueOf(taxValue));
        tax.setTaxDescription("Taxa: Transferências no mesmo dia do agendamento tem uma taxa de $3 mais 3% do valor a ser transferido.");

        return this.taxRepository.save(tax);
    }

    private Tax getTaxForTheTenDayRule(final TransferContract transferContract) {
        Tax tax = new Tax();
        long days = utils.getDaysDiff(transferContract.getScheduling().getTransferDate());
        var taxValue = days * FIXED_VALUE_OF_TAX_TEN_DAY_RULE;

        tax.setAmount(BigDecimal.valueOf(taxValue));
        tax.setTaxDescription("Taxa: Transferências até 10 dias da data de agendamento possuem uma taxa de $12 multiplicado pela quantidade de dias da data de agendamento até a data de transferência.");

        return this.taxRepository.save(tax);
    }

    private boolean isOperationTypeC(final LocalDate transferDate, final BigDecimal amount) {
        long daysDiff = utils.getDaysDiff(transferDate);

        return (daysDiff > 10 && daysDiff <= 40)
                || daysDiff > 40 && Double.parseDouble(amount.toString()) > 100000.00;
    }

    private boolean isInTheTenDayRule(final LocalDate transferDate) {
        return utils.getDaysDiff(transferDate) <= 10;
    }

    private boolean isSameDayTransfer(final LocalDate transferDate) {
        return utils.getDaysDiff(transferDate) == 0;
    }

    private double getTaxPercentageForOperationTypeC(final BigDecimal amount, final long daysDiff) {
        double taxPercentage = 0.0;

        if (daysDiff > 10 && daysDiff <= 20) {
            taxPercentage = 8.0;
        } else if (daysDiff > 20 && daysDiff <= 30) {
            taxPercentage = 6.0;
        } else if (daysDiff > 30 && daysDiff <= 40) {
            taxPercentage = 4.0;
        } else if (daysDiff > 40 && Double.parseDouble(amount.toString()) > 100000.00) {
            taxPercentage = 2.0;
        }

        return taxPercentage;
    }
}
