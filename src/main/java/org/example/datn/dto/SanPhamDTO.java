package org.example.datn.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDTO {
    private Integer id;
    private String ma;
    private String ten;

    // ID các thực thể liên kết
    private Integer danhMucId;
    private Integer chatLieuId;
    private Integer loaiKhoaId;
    private Integer kieuDayId;
    private Integer thuongHieuId;

    // Tên các thực thể liên kết
    private String tenDanhMuc;
    private String tenChatLieu;
    private String tenLoaiKhoa;
    private String tenKieuDay;
    private String tenThuongHieu;

    private String moTa;
    private Float canNang;
    private Float dungTich;
    private String kichThuoc;
    private Boolean trangThai;

    private List<String> hinhAnhUrls;
}
