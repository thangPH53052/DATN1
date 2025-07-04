package org.example.datn.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.example.datn.Entity.SanPham;
import org.example.datn.Entity.SanPhamChiTiet;
import org.example.datn.Repository.SanPhamChiTietRepository;
import org.example.datn.Response.SanPhamResponse;
import org.example.datn.Service.*;
import org.example.datn.dto.SanPhamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/san-pham")
@CrossOrigin(origins = "*")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private LoaiKhoaService loaiKhoaService;
    @Autowired
    private KieuDayService kieuDayService;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private KichThuocService kichThuocService;
    @Autowired
    private KhuyenMaiService khuyenMaiService;

    // ============ GIAO DIỆN VIEW ============

    // Hiển thị danh sách sản phẩm (view)
    @GetMapping("/view")
    public String viewSanPham(@RequestParam(defaultValue = "0") int page, Model model) {
        var sanPhamPage = sanPhamService.getAllSanPham(page);
        model.addAttribute("sanPhams", sanPhamPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sanPhamPage.getTotalPages());
        model.addAttribute("danhMucs", danhMucService.getAllDanhMuc());
        model.addAttribute("thuongHieus", thuongHieuService.getAllThuongHieu());
        model.addAttribute("chatLieus", chatLieuService.getAllChatLieu());
        model.addAttribute("loaiKhoas", loaiKhoaService.getAllLoaiKhoa());
        model.addAttribute("kieuDays", kieuDayService.getAllKieuDay());
        return "ViewSanPham/index";
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("danhMucs", danhMucService.getAllDanhMuc());
        model.addAttribute("thuongHieus", thuongHieuService.getAllThuongHieu());
        model.addAttribute("chatLieus", chatLieuService.getAllChatLieu());
        model.addAttribute("loaiKhoas", loaiKhoaService.getAllLoaiKhoa());
        model.addAttribute("kieuDays", kieuDayService.getAllKieuDay());
        return "ViewSanPham/add";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        SanPhamDTO sanPhamDTO = sanPhamService.getSanPhamDTOById(id);
        if (sanPhamDTO == null) {
            return "redirect:/san-pham/view";
        }

        model.addAttribute("sanPham", sanPhamDTO);
        model.addAttribute("danhMucs", danhMucService.getAllDanhMuc());
        model.addAttribute("thuongHieus", thuongHieuService.getAllThuongHieu());
        model.addAttribute("chatLieus", chatLieuService.getAllChatLieu());
        model.addAttribute("loaiKhoas", loaiKhoaService.getAllLoaiKhoa());
        model.addAttribute("kieuDays", kieuDayService.getAllKieuDay());
        model.addAttribute("chiTiets", sanPhamChiTietService.getBySanPhamId(id));
        model.addAttribute("mauSacs", mauSacService.getAllMauSac());
        model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
        model.addAttribute("khuyenMais", khuyenMaiService.getAllKhuyenMai());
        return "ViewSanPham/update";
    }

    // Form chi tiết sản phẩm (AJAX)
    @GetMapping("/san-pham-chi-tiet/add")
    public String showFormChiTiet(@RequestParam("sanPhamId") Integer sanPhamId, Model model) {
        model.addAttribute("sanPhamId", sanPhamId);
        model.addAttribute("mauSacs", mauSacService.getAllMauSac());
        model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
        model.addAttribute("khuyenMais", khuyenMaiService.getAllKhuyenMai());
        return "ViewSanPhamChiTiet/fragment_form_chi_tiet :: formChiTiet";
    }

    // ============ API JSON ============

    @PostMapping("/api/save")
    @ResponseBody
    public Integer apiSaveSanPham(
            @RequestParam("ma") String ma,
            @RequestParam("ten") String ten,
            @RequestParam("idDanhMuc") Integer idDanhMuc,
            @RequestParam("idChatLieu") Integer idChatLieu,
            @RequestParam("idLoaiKhoa") Integer idLoaiKhoa,
            @RequestParam("idKieuDay") Integer idKieuDay,
            @RequestParam("idThuongHieu") Integer idThuongHieu,
            @RequestParam(value = "moTa", required = false) String moTa,
            @RequestParam(value = "canNang", required = false) Float canNang,
            @RequestParam(value = "dungTich", required = false) Float dungTich,
            @RequestParam(value = "kichThuoc", required = false) String kichThuoc,
            @RequestParam("trangThai") Boolean trangThai,
            @RequestParam(value = "hinhAnhs", required = false) MultipartFile[] hinhAnhs) {
        return sanPhamService.addSanPham(ma, ten, idDanhMuc, idChatLieu, idLoaiKhoa, idKieuDay,
                idThuongHieu, moTa, canNang, dungTich, kichThuoc, trangThai, hinhAnhs);
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public SanPhamDTO getSanPhamDTOById(@PathVariable("id") Integer id) {
        return sanPhamService.getSanPhamDTOById(id);
    }

    @PostMapping("/update/{id}")
    public String updateSanPham(
            @PathVariable("id") Integer id,
            @RequestParam("ma") String ma,
            @RequestParam("ten") String ten,
            @RequestParam("idDanhMuc") Integer idDanhMuc,
            @RequestParam("idChatLieu") Integer idChatLieu,
            @RequestParam("idLoaiKhoa") Integer idLoaiKhoa,
            @RequestParam("idKieuDay") Integer idKieuDay,
            @RequestParam("idThuongHieu") Integer idThuongHieu,
            @RequestParam(value = "moTa", required = false) String moTa,
            @RequestParam(value = "canNang", required = false) Float canNang,
            @RequestParam(value = "dungTich", required = false) Float dungTich,
            @RequestParam(value = "kichThuoc", required = false) String kichThuoc,
            @RequestParam("trangThai") Boolean trangThai,
            @RequestParam(value = "hinhAnhs", required = false) MultipartFile[] hinhAnhs) {

        sanPhamService.updateSanPham(id, ma, ten, idDanhMuc, idChatLieu, idLoaiKhoa,
                idKieuDay, idThuongHieu, moTa, canNang, dungTich, kichThuoc, trangThai, hinhAnhs);
        return "redirect:/san-pham/view";
    }

    @GetMapping("/api/dang-ban")
    @ResponseBody
    public List<SanPhamResponse> getSanPhamDangBan() {
        List<SanPhamChiTiet> danhSach = sanPhamChiTietRepository.findAll();

        return danhSach.stream()
                .filter(spct -> spct.getSoLuong() != null && spct.getSoLuong() > 0 &&
                        spct.getSanPham() != null &&
                        Boolean.TRUE.equals(spct.getSanPham().getTrangThai()))
                .map(spct -> {
                    SanPham sanPham = spct.getSanPham();
                    String tenSanPham = sanPham.getTen();
                    String hinhAnh = (sanPham.getHinhAnhList() != null && !sanPham.getHinhAnhList().isEmpty())
                            ? "/uploads/images/" + sanPham.getHinhAnhList().get(0).getUrl()
                            : "img/default.jpg";

                    return new SanPhamResponse(
                            spct.getId(),
                            tenSanPham,
                            spct.getGiaBan(),
                            hinhAnh,
                            spct.getSoLuong());
                })
                .collect(Collectors.toList());
    }

}
