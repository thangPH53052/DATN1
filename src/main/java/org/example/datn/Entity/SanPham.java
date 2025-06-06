package org.example.datn.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String ma;

    @Column(nullable = false)
    private String ten;

    private String moTa;
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "idDanhMuc")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "idChatLieu")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "idLoaiKhoa")
    private LoaiKhoa loaiKhoa;

    @ManyToOne
    @JoinColumn(name = "idKieuDay")
    private KieuDay kieuDay;

    @ManyToOne
    @JoinColumn(name = "idThuongHieu")
    private ThuongHieu thuongHieu;

    @OneToMany(mappedBy = "sanPham")
    private List<SanPhamChiTiet> chiTietList;
    
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
    private List<HinhAnhSanPham> hinhAnhList;

}
