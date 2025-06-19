package org.example.datn.Service;

import java.util.List;


import org.example.datn.Entity.Voucher;
import org.example.datn.Repository.VoucherRepository;

public class VoucherService {
     private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public List<Voucher> getAllVoucher() {
        return voucherRepository.findAll();
    }
}
