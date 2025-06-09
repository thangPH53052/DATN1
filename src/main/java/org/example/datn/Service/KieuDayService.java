// KieuDayService.java
package org.example.datn.Service;

import org.example.datn.Entity.KieuDay;
import org.example.datn.Repository.KieuDayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KieuDayService {
    private final KieuDayRepository kieuDayRepository;

    public KieuDayService(KieuDayRepository kieuDayRepository) {
        this.kieuDayRepository = kieuDayRepository;
    }

    public List<KieuDay> getAllKieuDay() {
        return kieuDayRepository.findAll();
    }
}
