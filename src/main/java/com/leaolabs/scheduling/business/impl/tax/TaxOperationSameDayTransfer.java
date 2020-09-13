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
public class TaxOperationSameDayTransfer implements TaxBusiness {

    @Autowired
    private TaxRepository taxRepository;

    private final Utils utils = new Utils();
    private final static double PERCENTAGE_TAX_SAME_DAY = 3.00; // percentage
    private final static double FIXED_VALUE_OF_TAX_SAME_DAY = 3.00; // fixed tax

    @Override
    public Tax calculateTax(TransferContract transferContract) {
        Tax tax = new Tax();
        var taxPercentage = utils.calculatePercentage(PERCENTAGE_TAX_SAME_DAY, Double.parseDouble(transferContract.getAmount().toString()));
        var taxValue = taxPercentage + FIXED_VALUE_OF_TAX_SAME_DAY;
        tax.setAmount(BigDecimal.valueOf(taxValue));
        tax.setTaxDescription("Taxa: Transferências no mesmo dia do agendamento tem uma taxa de $3 mais 3% do valor a ser transferido.");

        return this.taxRepository.save(tax);
    }
}
