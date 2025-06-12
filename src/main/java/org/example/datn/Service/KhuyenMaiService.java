package org.example.datn.Service;

import org.example.datn.Entity.KhuyenMai;
import org.example.datn.Repository.KhuyenMaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    public List<KhuyenMai> getAllKhuyenMai() {
        return khuyenMaiRepository.findAll();
    }

    public KhuyenMai getById(Integer id) {
        return khuyenMaiRepository.findById(id).orElse(null);
    }

    public KhuyenMai getByMa(String ma) {
        return khuyenMaiRepository.findByMa(ma);
    }

    public KhuyenMai saveOrUpdate(KhuyenMai khuyenMai) {
        return khuyenMaiRepository.save(khuyenMai);
    }

    public void deleteById(Integer id) {
        khuyenMaiRepository.deleteById(id);
    }
}
