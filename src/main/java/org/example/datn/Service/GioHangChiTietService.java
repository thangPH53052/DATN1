package org.example.datn.Service;

import java.util.List;

import org.example.datn.dto.GioHangChiTietDTO;

public interface GioHangChiTietService {
    List<GioHangChiTietDTO> getGioHangByKhachHang(Integer khachHangid);
    void themVaoGio(GioHangChiTietDTO dto);
    void capNhatSoLuong(Integer id, Integer soLuongMoi);
    void xoaGioHang(Integer id);
}
