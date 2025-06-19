package org.example.datn.Service;

import java.util.List;

import org.example.datn.Entity.KhachHang;
import org.example.datn.Repository.KhachHangRepository;

public class KhachHangService {
    private final KhachHangRepository khachHangRepository;

    public KhachHangService(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }
}
