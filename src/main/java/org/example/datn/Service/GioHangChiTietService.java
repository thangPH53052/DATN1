package org.example.datn.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.example.datn.Entity.GioHangChiTiet;
import org.example.datn.Entity.KhachHang;
import org.example.datn.Entity.SanPhamChiTiet;
import org.example.datn.Repository.GioHangChiTietRepository;
import org.example.datn.dto.GioHangChiTietDTO;
import org.springframework.stereotype.Component;

@Component
public class GioHangChiTietService {

    private final GioHangChiTietRepository repo;

    public GioHangChiTietService(GioHangChiTietRepository repo) {
        this.repo = repo;
    }

    public List<GioHangChiTietDTO> getGioHangByKhachHang(Integer khachHangId) {
        return repo.findByKhachHangIdAndTrangThai(khachHangId, true)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void themVaoGio(GioHangChiTietDTO dto) {
        GioHangChiTiet entity = convertToEntity(dto);
        entity.setNgayThem(LocalDateTime.now());
        entity.setTrangThai(true);
        repo.save(entity);
    }

    public void capNhatSoLuong(Integer id, Integer soLuongMoi) {
        GioHangChiTiet item = repo.findById(id).orElseThrow();
        item.setSoLuong(soLuongMoi);
        repo.save(item);
    }

    public void xoaGioHang(Integer id) {
        repo.deleteById(id);
    }

    // === Mapping thủ công ===
    private GioHangChiTietDTO convertToDto(GioHangChiTiet e) {
        GioHangChiTietDTO dto = new GioHangChiTietDTO();
        dto.setId(e.getId());
        dto.setIdKhachHang(e.getKhachHang() != null ? e.getKhachHang().getId() : null);
        dto.setIdSanPhamChiTiet(e.getSanPhamChiTiet() != null ? e.getSanPhamChiTiet().getId() : null);
        dto.setSoLuong(e.getSoLuong());
        return dto;
    }

    private GioHangChiTiet convertToEntity(GioHangChiTietDTO dto) {
        GioHangChiTiet e = new GioHangChiTiet();
        e.setId(dto.getId());
        e.setSoLuong(dto.getSoLuong());

        
        KhachHang kh = new KhachHang();
        kh.setId(dto.getIdKhachHang());
        e.setKhachHang(kh);

        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setId(dto.getIdSanPhamChiTiet());
        e.setSanPhamChiTiet(spct);

        return e;
    }
}
