package org.example.datn.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietDTO {
    private Integer id;
    private Integer soLuong;
    private Integer idGioHang;
    private Integer idSanPhamChiTiet;
}
