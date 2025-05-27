package org.example.datn.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ma;
    private String ten;
    private String sdt;
    private String email;
    private String matKhau;
    private Boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private Integer trangThai;

    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDons;
}
