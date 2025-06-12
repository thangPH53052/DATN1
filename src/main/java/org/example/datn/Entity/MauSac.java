package org.example.datn.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MauSac")
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String ma;

    @Column(nullable = false)
    private String ten;

    private Boolean trangThai;

    @OneToMany(mappedBy = "mauSac")
    private List<SanPhamChiTiet> chiTietList;

    public MauSac(Integer id) {
    this.id = id;
}

}
