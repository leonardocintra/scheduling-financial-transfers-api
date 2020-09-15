package com.leaolabs.scheduling.business.impl.tax;

import com.leaolabs.scheduling.business.TaxBusiness;
import com.leaolabs.scheduling.commons.utils.Utils;
import com.leaolabs.scheduling.model.Tax;
import com.leaolabs.scheduling.model.TransferContract;
import com.leaolabs.scheduling.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxOperationTypeC implements TaxBusiness {

    @Autowired
    private TaxRepository taxRepository;

    private final Utils utils = new Utils();

    @Override
    public Tax calculateTax(final TransferContract transferContract) {
        Tax tax = new Tax();

        final var daysDiff = utils.getDaysDiff(transferContract.getScheduling().getTransferDate());
        final var taxPercentageToCalculate = getTaxPercentageForOperationTypeC(transferContract.getAmount(), daysDiff);
        final var taxPercentage = utils.calculatePercentage(taxPercentageToCalculate, Double.parseDouble(transferContract.getAmount().toString()));

        tax.setAmount(BigDecimal.valueOf(taxPercentage));
        tax.setTaxDescription("Operações do tipo C tem uma taxa regressiva conforme a data de transferência: " +
                "de 10 até 20 dias: 8% " +
                "de 20 até 30 dias: 6% " +
                "de 30 até 40 dias: 4% " +
                "de 40 dias ou mais e valor superior a 100.000: 2%");

        return this.taxRepository.save(tax);
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
