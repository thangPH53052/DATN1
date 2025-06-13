package org.example.datn.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietDTO {
    private Integer id;
    private Integer idKhachHang;
    private Integer idSanPhamChiTiet;
    private Integer soLuong;
}
