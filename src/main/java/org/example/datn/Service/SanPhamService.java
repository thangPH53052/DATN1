package org.example.datn.Service;

import org.example.datn.Entity.HinhAnhSanPham;
import org.example.datn.Entity.SanPham;
import org.example.datn.Repository.*;
import org.example.datn.dto.SanPhamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SanPhamService {

    private final SanPhamRepository sanPhamRepository;
    private final DanhMucRepository danhMucRepository;
    private final ChatLieuRepository chatLieuRepository;
    private final LoaiKhoaRepository loaiKhoaRepository;
    private final KieuDayRepository kieuDayRepository;
    private final ThuongHieuRepository thuongHieuRepository;
    private final HinhAnhSanPhamRepository hinhAnhSanPhamRepository;

    @Autowired
    public SanPhamService(SanPhamRepository sanPhamRepository,
            DanhMucRepository danhMucRepository,
            ChatLieuRepository chatLieuRepository,
            LoaiKhoaRepository loaiKhoaRepository,
            KieuDayRepository kieuDayRepository,
            ThuongHieuRepository thuongHieuRepository,
            HinhAnhSanPhamRepository hinhAnhSanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
        this.danhMucRepository = danhMucRepository;
        this.chatLieuRepository = chatLieuRepository;
        this.loaiKhoaRepository = loaiKhoaRepository;
        this.kieuDayRepository = kieuDayRepository;
        this.thuongHieuRepository = thuongHieuRepository;
        this.hinhAnhSanPhamRepository = hinhAnhSanPhamRepository;
    }

    public List<SanPhamDTO> getAllSanPham() {
        return sanPhamRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private SanPhamDTO mapToDTO(SanPham sp) {
        SanPhamDTO dto = new SanPhamDTO();
        dto.setId(sp.getId());
        dto.setMa(sp.getMa());
        dto.setTen(sp.getTen());

        // Gán ID nếu có
        dto.setDanhMucId(sp.getDanhMuc() != null ? sp.getDanhMuc().getId() : null);
        dto.setChatLieuId(sp.getChatLieu() != null ? sp.getChatLieu().getId() : null);
        dto.setLoaiKhoaId(sp.getLoaiKhoa() != null ? sp.getLoaiKhoa().getId() : null);
        dto.setKieuDayId(sp.getKieuDay() != null ? sp.getKieuDay().getId() : null);
        dto.setThuongHieuId(sp.getThuongHieu() != null ? sp.getThuongHieu().getId() : null);

        // Gán tên như cũ
        dto.setTenDanhMuc(sp.getDanhMuc() != null ? sp.getDanhMuc().getTen() : null);
        dto.setTenChatLieu(sp.getChatLieu() != null ? sp.getChatLieu().getTen() : null);
        dto.setTenLoaiKhoa(sp.getLoaiKhoa() != null ? sp.getLoaiKhoa().getTen() : null);
        dto.setTenKieuDay(sp.getKieuDay() != null ? sp.getKieuDay().getTen() : null);
        dto.setTenThuongHieu(sp.getThuongHieu() != null ? sp.getThuongHieu().getTen() : null);

        dto.setMoTa(sp.getMoTa());
        dto.setCanNang(sp.getCanNang());
        dto.setDungTich(sp.getDungTich());
        dto.setKichThuoc(sp.getKichThuoc());
        dto.setTrangThai(sp.getTrangThai());

        if (sp.getHinhAnhList() != null) {
            List<String> urls = sp.getHinhAnhList().stream()
                    .map(HinhAnhSanPham::getUrl)
                    .collect(Collectors.toList());
            dto.setHinhAnhUrls(urls);
        }

        return dto;
    }

    public Integer addSanPham(String ma, String ten, Integer idDanhMuc, Integer idChatLieu, Integer idLoaiKhoa,
            Integer idKieuDay, Integer idThuongHieu, String moTa, Float canNang, Float dungTich,
            String kichThuoc, Boolean trangThai, MultipartFile[] hinhAnhs) {

        SanPham sp = new SanPham();
        sp.setMa(ma);
        sp.setTen(ten);
        sp.setMoTa(moTa);
        sp.setCanNang(canNang);
        sp.setDungTich(dungTich);
        sp.setKichThuoc(kichThuoc);
        sp.setTrangThai(trangThai);

        sp.setDanhMuc(danhMucRepository.findById(idDanhMuc).orElse(null));
        sp.setChatLieu(chatLieuRepository.findById(idChatLieu).orElse(null));
        sp.setLoaiKhoa(loaiKhoaRepository.findById(idLoaiKhoa).orElse(null));
        sp.setKieuDay(kieuDayRepository.findById(idKieuDay).orElse(null));
        sp.setThuongHieu(thuongHieuRepository.findById(idThuongHieu).orElse(null));

        sanPhamRepository.save(sp); // Lưu vào DB để có ID

        for (MultipartFile file : hinhAnhs) {
            if (!file.isEmpty()) {
                String fileName = saveFile(file);
                HinhAnhSanPham img = new HinhAnhSanPham();
                img.setSanPham(sp);
                img.setUrl(fileName);
                hinhAnhSanPhamRepository.save(img);
            }
        }

        return sp.getId(); // Trả về ID sản phẩm mới
    }

    public SanPhamDTO getSanPhamDTOById(Integer id) {
        SanPham sp = sanPhamRepository.findById(id).orElse(null);
        if (sp == null)
            return null;
        return mapToDTO(sp);
    }

    public void updateSanPham(Integer id, String ma, String ten, Integer idDanhMuc, Integer idChatLieu,
            Integer idLoaiKhoa, Integer idKieuDay, Integer idThuongHieu, String moTa,
            Float canNang, Float dungTich, String kichThuoc, Boolean trangThai,
            MultipartFile[] hinhAnhs) {

        SanPham sp = sanPhamRepository.findById(id).orElse(null);
        if (sp == null)
            return;

        sp.setMa(ma);
        sp.setTen(ten);
        sp.setMoTa(moTa);
        sp.setCanNang(canNang);
        sp.setDungTich(dungTich);
        sp.setKichThuoc(kichThuoc);
        sp.setTrangThai(trangThai);

        sp.setDanhMuc(danhMucRepository.findById(idDanhMuc).orElse(null));
        sp.setChatLieu(chatLieuRepository.findById(idChatLieu).orElse(null));
        sp.setLoaiKhoa(loaiKhoaRepository.findById(idLoaiKhoa).orElse(null));
        sp.setKieuDay(kieuDayRepository.findById(idKieuDay).orElse(null));
        sp.setThuongHieu(thuongHieuRepository.findById(idThuongHieu).orElse(null));

        sanPhamRepository.save(sp);

        // Xử lý cập nhật ảnh (Xóa ảnh cũ nếu có ảnh mới)
        if (hinhAnhs != null && hinhAnhs.length > 0 && !hinhAnhs[0].isEmpty()) {
            // Xóa ảnh cũ trong DB
            hinhAnhSanPhamRepository.deleteAll(sp.getHinhAnhList());

            for (MultipartFile file : hinhAnhs) {
                if (!file.isEmpty()) {
                    String fileName = saveFile(file);
                    HinhAnhSanPham img = new HinhAnhSanPham();
                    img.setSanPham(sp);
                    img.setUrl(fileName);
                    hinhAnhSanPhamRepository.save(img);
                }
            }
        }
    }

    private String saveFile(MultipartFile file) {
        try {
            // Đường dẫn thư mục lưu ảnh
            String uploadDir = System.getProperty("user.dir") + "/uploads/images/";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            // Tạo file đích
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename(); // tránh trùng tên
            File dest = new File(uploadDir + fileName);
            file.transferTo(dest); // Ghi file vào ổ đĩa

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file ảnh: " + e.getMessage(), e);
        }
    }

}
