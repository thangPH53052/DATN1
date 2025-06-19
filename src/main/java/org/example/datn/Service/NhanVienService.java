package org.example.datn.Service;

import java.util.List;

import org.example.datn.Entity.NhanVien;
import org.example.datn.Repository.NhanVienRepository;

public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    public NhanVienService(NhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }

    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }
}
