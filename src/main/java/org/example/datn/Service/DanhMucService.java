// DanhMucService.java
package org.example.datn.Service;

import org.example.datn.Entity.DanhMuc;
import org.example.datn.Repository.DanhMucRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucService {
    private final DanhMucRepository danhMucRepository;

    public DanhMucService(DanhMucRepository danhMucRepository) {
        this.danhMucRepository = danhMucRepository;
    }

    public List<DanhMuc> getAllDanhMuc() {
        return danhMucRepository.findAll();
    }
}
