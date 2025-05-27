package org.example.datn.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHangDTO {
    private Integer id;
    private Date ngayTao;
    private Integer idKhachHang;
}
