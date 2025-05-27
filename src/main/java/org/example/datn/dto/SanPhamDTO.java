package org.example.datn.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDTO {
    private Integer id;
    private String ma;
    private String ten;
    private Double giaBan;
    private Integer trangThai;
}
