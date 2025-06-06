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
    private Boolean trangThai;
    private String tenDanhMuc;
    private String tenChatLieu;
    private String tenLoaiKhoa;
    private String tenKieuDay;
    private String tenThuongHieu;
    private List<String> hinhAnhUrls;  
}
