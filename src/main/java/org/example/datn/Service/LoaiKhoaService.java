// LoaiKhoaService.java
package org.example.datn.Service;

import org.example.datn.Entity.LoaiKhoa;
import org.example.datn.Repository.LoaiKhoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiKhoaService {
    private final LoaiKhoaRepository loaiKhoaRepository;

    public LoaiKhoaService(LoaiKhoaRepository loaiKhoaRepository) {
        this.loaiKhoaRepository = loaiKhoaRepository;
    }

    public List<LoaiKhoa> getAllLoaiKhoa() {
        return loaiKhoaRepository.findAll();
    }
}
