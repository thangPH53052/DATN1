package org.example.datn.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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

    @Column(unique = true, nullable = false, length = 50)
    private String ma;

    @Column(nullable = false, length = 100)
    private String ten;

    @ManyToOne
    @JoinColumn(name = "IDDanhMuc", nullable = false)
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "IDChatLieu", nullable = false)
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "IDLoaiKhoa", nullable = false)
    private LoaiKhoa loaiKhoa;

    @ManyToOne
    @JoinColumn(name = "IDKieuDay", nullable = false)
    private KieuDay kieuDay;

    @ManyToOne
    @JoinColumn(name = "IDThuongHieu", nullable = false)
    private ThuongHieu thuongHieu;

    @Column(length = 500)
    private String moTa;

    private Float canNang;
    private Float dungTich;

    @Column(length = 100)
    private String kichThuoc;

    @Column(nullable = false)
    private Boolean trangThai = true;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HinhAnhSanPham> hinhAnhList;

    public SanPham(Integer id) {
        this.id = id;
    }

}
