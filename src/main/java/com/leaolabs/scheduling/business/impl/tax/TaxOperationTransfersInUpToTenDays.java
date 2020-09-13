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
public class TaxOperationTransfersInUpToTenDays implements TaxBusiness {

    @Autowired
    private TaxRepository taxRepository;

    private final Utils utils = new Utils();
    private final static double FIXED_VALUE_OF_TAX_TEN_DAY_RULE = 12.00; // fixed tax

    @Override
    public Tax calculateTax(TransferContract transferContract) {
        Tax tax = new Tax();
        long days = utils.getDaysDiff(transferContract.getScheduling().getTransferDate());
        var taxValue = days * FIXED_VALUE_OF_TAX_TEN_DAY_RULE;

        tax.setAmount(BigDecimal.valueOf(taxValue));
        tax.setTaxDescription("Taxa: Transferências até 10 dias da data de agendamento possuem uma taxa de $12 multiplicado pela quantidade de dias da data de agendamento até a data de transferência.");

        return this.taxRepository.save(tax);
    }
}
