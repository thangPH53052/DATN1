// ThuongHieuService.java
package org.example.datn.Service;

import org.example.datn.Entity.ThuongHieu;
import org.example.datn.Repository.ThuongHieuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThuongHieuService {
    private final ThuongHieuRepository thuongHieuRepository;

    public ThuongHieuService(ThuongHieuRepository thuongHieuRepository) {
        this.thuongHieuRepository = thuongHieuRepository;
    }

    public List<ThuongHieu> getAllThuongHieu() {
        return thuongHieuRepository.findAll();
    }
}
