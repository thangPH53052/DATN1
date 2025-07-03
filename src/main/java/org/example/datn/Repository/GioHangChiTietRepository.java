package org.example.datn.Repository;

import java.util.List;
import java.util.Optional;

import org.example.datn.Entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {

    // Lấy toàn bộ giỏ hàng đang hoạt động của một khách hàng
    List<GioHangChiTiet> findByKhachHangIdAndTrangThai(Integer khachHangId, Boolean trangThai);

    // Tìm theo khách hàng và sản phẩm 
    Optional<GioHangChiTiet> findByKhachHangIdAndSanPhamChiTietIdAndTrangThai(Integer khachHangId,
            Integer sanPhamChiTietId, Boolean trangThai);

    // Xoá toàn bộ giỏ hàng của khách hàng (ví dụ khi đặt hàng xong)
    void deleteByKhachHangId(Integer khachHangId);
}
