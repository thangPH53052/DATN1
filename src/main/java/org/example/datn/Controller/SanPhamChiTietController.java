    package org.example.datn.Controller;

    import java.util.List;

    import org.example.datn.Service.KhuyenMaiService;
    import org.example.datn.Service.KichThuocService;
    import org.example.datn.Service.MauSacService;
    import org.example.datn.Service.SanPhamChiTietService;
    import org.example.datn.Service.SanPhamService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import org.springframework.ui.Model;

    @Controller
    @RequestMapping("/san-pham-chi-tiet")
    public class SanPhamChiTietController {

        @Autowired
        private SanPhamService sanPhamService;
        @Autowired
        private MauSacService mauSacService;
        @Autowired
        private KichThuocService kichThuocService;
        @Autowired
        private KhuyenMaiService khuyenMaiService;
        @Autowired
        private SanPhamChiTietService sanPhamChiTietService;

        // Hiển thị form thêm chi tiết sản phẩm
        @GetMapping("/add")
        public String showAddForm(@RequestParam("sanPhamId") Integer sanPhamId, Model model) {
            model.addAttribute("sanPham", sanPhamService.getSanPhamDTOById(sanPhamId));
            model.addAttribute("mauSacs", mauSacService.getAllMauSac());
            model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
            model.addAttribute("khuyenMais", khuyenMaiService.getAllKhuyenMai());
            return "ViewSanPhamChiTiet/add";
        }

        // Lưu nhiều chi tiết sản phẩm
        @PostMapping("/save-multiple")
        public String saveMultiple(
                @RequestParam("idSanPham") Integer idSanPham,
                @RequestParam("idMauSac") List<Integer> idMauSac,
                @RequestParam("idKichThuoc") List<Integer> idKichThuoc,
                @RequestParam(value = "idKhuyenMai", required = false) List<Integer> idKhuyenMai,
                @RequestParam("giaBan") List<Double> giaBan,
                @RequestParam("giaNhap") List<Double> giaNhap,
                @RequestParam("soLuong") List<Integer> soLuong) {

            for (int i = 0; i < idMauSac.size(); i++) {
                Integer khuyenMaiId = (idKhuyenMai != null && i < idKhuyenMai.size()) ? idKhuyenMai.get(i) : null;

                sanPhamChiTietService.saveChiTiet(
                        idSanPham,
                        idMauSac.get(i),
                        idKichThuoc.get(i),
                        khuyenMaiId,
                        giaBan.get(i),
                        giaNhap.get(i),
                        soLuong.get(i));
            }

            return "redirect:/san-pham/view";
        }

        // Cập nhật nhiều chi tiết sản phẩm
        @PostMapping("/update-multiple")
        public String updateMultiple(
                @RequestParam("chiTietIds") List<Integer> chiTietIds,
                @RequestParam("idMauSac") List<Integer> idMauSac,
                @RequestParam("idKichThuoc") List<Integer> idKichThuoc,
                @RequestParam("idKhuyenMai") List<String> idKhuyenMai,
                @RequestParam("giaBan") List<Double> giaBan,
                @RequestParam("giaNhap") List<Double> giaNhap,
                @RequestParam("soLuong") List<Integer> soLuong) {

            for (int i = 0; i < chiTietIds.size(); i++) {
                Integer kmId = (idKhuyenMai.get(i) == null || idKhuyenMai.get(i).isEmpty()) ? null
                        : Integer.parseInt(idKhuyenMai.get(i));
                sanPhamChiTietService.updateChiTiet(
                        chiTietIds.get(i),
                        idMauSac.get(i),
                        idKichThuoc.get(i),
                        kmId,
                        giaBan.get(i),
                        giaNhap.get(i),
                        soLuong.get(i));
            }

            return "redirect:/san-pham/view";
        }

       @GetMapping("/san-pham-chi-tiet/add")
public String showFormChiTiet(@RequestParam("sanPhamId") Integer sanPhamId, Model model) {
    model.addAttribute("sanPhamId", sanPhamId);
    model.addAttribute("mauSacs", mauSacService.getAllMauSac());
    model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
    model.addAttribute("khuyenMais", khuyenMaiService.getAllKhuyenMai());
    return "ViewSanPhamChiTiet/fragment_form_chi_tiet :: formChiTiet";
}


    }
