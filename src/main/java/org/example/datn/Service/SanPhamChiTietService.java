package org.example.datn.Service;

import org.example.datn.Entity.*;
import org.example.datn.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private KichThuocRepository kichThuocRepository;

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    public List<SanPhamChiTiet> getAll() {
        return sanPhamChiTietRepository.findAll();
    }

    public List<SanPhamChiTiet> getBySanPhamId(Integer sanPhamId) {
        return sanPhamChiTietRepository.findBySanPham_Id(sanPhamId);
    }

    public SanPhamChiTiet getById(Integer id) {
        return sanPhamChiTietRepository.findById(id).orElse(null);
    }

    public void saveChiTiet(
            Integer idSanPham,
            Integer idMauSac,
            Integer idKichThuoc,
            Integer idKhuyenMai,
            Double giaBan,
            Double giaNhap,
            Integer soLuong) {

        SanPhamChiTiet chiTiet = new SanPhamChiTiet();

        chiTiet.setSanPham(new SanPham(idSanPham));
        chiTiet.setMauSac(new MauSac(idMauSac));
        chiTiet.setKichThuoc(new KichThuoc(idKichThuoc));
        chiTiet.setKhuyenMai(idKhuyenMai != null ? new KhuyenMai(idKhuyenMai) : null);
        chiTiet.setGiaBan(giaBan);
        chiTiet.setGiaNhap(giaNhap);
        chiTiet.setSoLuong(soLuong);

        sanPhamChiTietRepository.save(chiTiet);
    }

    public void updateChiTiet(Integer id, Integer idMauSac, Integer idKichThuoc, Integer idKhuyenMai,
                               Double giaBan, Double giaNhap, Integer soLuong) {
        SanPhamChiTiet ct = sanPhamChiTietRepository.findById(id).orElse(null);
        if (ct == null) return;

        ct.setMauSac(mauSacRepository.findById(idMauSac).orElse(null));
        ct.setKichThuoc(kichThuocRepository.findById(idKichThuoc).orElse(null));
        ct.setKhuyenMai(idKhuyenMai != null ? khuyenMaiRepository.findById(idKhuyenMai).orElse(null) : null);
        ct.setGiaBan(giaBan);
        ct.setGiaNhap(giaNhap);
        ct.setSoLuong(soLuong);

        sanPhamChiTietRepository.save(ct);
    }

    public void deleteById(Integer id) {
        sanPhamChiTietRepository.deleteById(id);
    }
}