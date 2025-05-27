package org.example.datn.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ma;
    private String ten;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Integer phanTramGiam;
    private Boolean trangThai;

    @OneToMany(mappedBy = "voucher")
    private List<HoaDon> hoaDons;
}
