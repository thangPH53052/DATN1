package org.example.datn.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SanPhamChiTiet")
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer soLuong;
    private Double giaBan;

    @Column(nullable = false) // ✅ THÊM
    private Double giaNhap;

    @ManyToOne
    @JoinColumn(name = "idSanPham")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "idMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idKichThuoc")
    private KichThuoc kichThuoc;

    @ManyToOne
    @JoinColumn(name = "idKhuyenMai")
    private KhuyenMai khuyenMai;
}

