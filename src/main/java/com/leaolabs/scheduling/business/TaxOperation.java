package com.leaolabs.scheduling.business;

import com.leaolabs.scheduling.business.impl.tax.TaxOperationSameDayTransfer;
import com.leaolabs.scheduling.business.impl.tax.TaxOperationTransfersInUpToTenDays;
import com.leaolabs.scheduling.business.impl.tax.TaxOperationTypeC;
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
public class TaxOperation {

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private TaxOperationTypeC taxOperationTypeC;

    @Autowired
    private TaxOperationSameDayTransfer taxOperationSameDayTransfer;

    @Autowired
    private TaxOperationTransfersInUpToTenDays taxOperationTransfersInUpToTenDays;

    private final Utils utils = new Utils();

    public Tax getTax(final TransferContract transferContract) {
        final var transferDate = transferContract.getScheduling().getTransferDate();

        if (isSameDayTransfer(transferDate)) {
            return this.taxOperationSameDayTransfer.calculateTax(transferContract);
        }

        if (isInTheTenDayRule(transferDate)) {
            return this.taxOperationTransfersInUpToTenDays.calculateTax(transferContract);
        }

        if (isOperationTypeC(transferDate, transferContract.getAmount())) {
            return this.taxOperationTypeC.calculateTax(transferContract);
        }

        throw new EntityNotFoundException("Tax");
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
}
