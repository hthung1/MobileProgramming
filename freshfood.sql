-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 23, 2021 lúc 06:23 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `freshfood`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `id` int(11) NOT NULL,
  `tongtien` text COLLATE utf8_unicode_ci NOT NULL,
  `iduser` int(11) NOT NULL,
  `ngay` text COLLATE utf8_unicode_ci NOT NULL,
  `gio` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`id`, `tongtien`, `iduser`, `ngay`, `gio`) VALUES
(1, '860.1', 2, '', ''),
(3, '36.4', 2, '', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `item_hoa_don`
--

CREATE TABLE `item_hoa_don` (
  `id` int(11) NOT NULL,
  `id_hoadon` int(11) NOT NULL,
  `id_sanpham` int(11) NOT NULL,
  `tenhang` text COLLATE utf8_unicode_ci NOT NULL,
  `so_luong` int(11) NOT NULL,
  `don_gia` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `item_hoa_don`
--

INSERT INTO `item_hoa_don` (`id`, `id_hoadon`, `id_sanpham`, `tenhang`, `so_luong`, `don_gia`) VALUES
(1, 1, 1, 'Chicago Pizza', 8, 846),
(2, 1, 2, 'Straberry Cake', 2, 14.1),
(3, 3, 2, 'Straberry Cake', 3, 21.15),
(4, 3, 5, 'Chicago Pizza', 5, 15.25);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tenhang` text COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL,
  `dongia` text COLLATE utf8_unicode_ci NOT NULL,
  `mota` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tenhang`, `image`, `dongia`, `mota`) VALUES
(1, 'Chicago Pizza', 'http://192.168.0.36/freshfood/image/lau-tieu-cua-bap-bo.jpg', '7.05', 'okko'),
(2, 'Straberry Cake', 'http://192.168.0.36/freshfood/image/banhxeo.jpeg', '7.05', 'kkk'),
(5, 'Chicago Pizza', 'http://192.168.0.36/freshfood/image/asiafood1.png', '3.05', 'ooo'),
(6, 'Straberry Cake', 'http://192.168.0.36/freshfood/image/popularfood1.png', '3', 'sss'),
(7, 'hhh', 'ttm', '200', 'haushdsahisu'),
(8, 'hhh', 'ttm', '200', 'haushdsahisu'),
(10, 'món mới', 'đýgs', '29', 'hshshshhs');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` text COLLATE utf8_unicode_ci NOT NULL,
  `email` text COLLATE utf8_unicode_ci NOT NULL,
  `phone` text COLLATE utf8_unicode_ci NOT NULL,
  `pass` text COLLATE utf8_unicode_ci NOT NULL,
  `role` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `phone`, `pass`, `role`) VALUES
(1, 'hung', 'hthung.19it2@vku.udn.vn', '0528187702', '123', 0),
(2, 'admin', 'hung0101@gmail.com', '0528187702', '123', 1),
(3, 'hthung', 'hung0101@gmail.com', '+84775413958', '123', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iduser` (`iduser`);

--
-- Chỉ mục cho bảng `item_hoa_don`
--
ALTER TABLE `item_hoa_don`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_hoa_don_ibfk_1` (`id_hoadon`),
  ADD KEY `item_hoa_don_ibfk_2` (`id_sanpham`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `item_hoa_don`
--
ALTER TABLE `item_hoa_don`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `item_hoa_don`
--
ALTER TABLE `item_hoa_don`
  ADD CONSTRAINT `item_hoa_don_ibfk_1` FOREIGN KEY (`id_hoadon`) REFERENCES `hoadon` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `item_hoa_don_ibfk_2` FOREIGN KEY (`id_sanpham`) REFERENCES `sanpham` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
