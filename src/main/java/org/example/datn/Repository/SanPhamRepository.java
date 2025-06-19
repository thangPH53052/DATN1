package org.example.datn.Repository;

import org.example.datn.Entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

    @Override
    @EntityGraph(attributePaths = "hinhAnhList")
    Page<SanPham> findAll(Pageable pageable);

}
