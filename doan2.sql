-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 23, 2023 lúc 06:19 PM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `doan2`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `adminId` int(11) NOT NULL,
  `adminName` varchar(255) NOT NULL,
  `adminEmail` varchar(150) NOT NULL,
  `adminUser` varchar(255) NOT NULL,
  `adminPass` varchar(255) NOT NULL,
  `level` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_admin`
--

INSERT INTO `tbl_admin` (`adminId`, `adminName`, `adminEmail`, `adminUser`, `adminPass`, `level`) VALUES
(2, 'Trung', 'trungtrandinh203@gmail.com', 'TrungAdmin', 'e10adc3949ba59abbe56e057f20f883e', 0),
(3, 'trung', 'trung@gmail.com', '1', '123', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_binhluan`
--

CREATE TABLE `tbl_binhluan` (
  `binhluan_id` int(11) NOT NULL,
  `tenbinhluan` varchar(255) NOT NULL,
  `binhluan` text NOT NULL,
  `product_id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  `image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_binhluan`
--

INSERT INTO `tbl_binhluan` (`binhluan_id`, `tenbinhluan`, `binhluan`, `product_id`, `blog_id`, `image`) VALUES
(10, 'Trung', 'Tốt ngon', 38, 0, ''),
(11, 'Trần Đình Trung', 'rarasastádfnsnsdf····', 39, 0, ''),
(12, 'Trung', 'ftyuguj', 38, 0, ''),
(34, ' Trung', 'rat la ngon lun', 39, 0, ''),
(35, ' Trung', 'god', 42, 0, ''),
(37, ' Trung Toi', 'ngan ngu', 53, 0, ''),
(38, ' Trung Toi', 'hello', 53, 0, ''),
(39, ' Trung Toi', 'ngon', 38, 0, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_brand`
--

CREATE TABLE `tbl_brand` (
  `brandId` int(11) NOT NULL,
  `brandName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_brand`
--

INSERT INTO `tbl_brand` (`brandId`, `brandName`) VALUES
(4, 'Hải sản'),
(5, 'Món Âu'),
(6, 'Món Á'),
(7, 'Đồ uống'),
(8, 'Thức uống có cồn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_cart`
--

CREATE TABLE `tbl_cart` (
  `cartId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `sId` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `productName` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `image` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_cart`
--

INSERT INTO `tbl_cart` (`cartId`, `productId`, `sId`, `productName`, `quantity`, `image`, `price`) VALUES
(21, 34, '06qgr0phcihq25jaglqcpgvbhv', 'Bào ngu nướng', 1, '68a96bea2d.jpg', 20000),
(23, 34, 'n5kc0pnfeqrrlc7rn2rtf8kiut', 'Bào ngu nướng', 1, '68a96bea2d.jpg', 20000),
(24, 36, 'n5kc0pnfeqrrlc7rn2rtf8kiut', 'Trung', 1, '901ae3582d.jpg', 2),
(25, 35, 'n5kc0pnfeqrrlc7rn2rtf8kiut', 'Cá chình', 1, 'aaa6bdf63e.jpg', 5000000),
(26, 37, 'n5kc0pnfeqrrlc7rn2rtf8kiut', 'bánh', 1, '300734e3e8.jpg', 205),
(29, 37, 'nqdu13snd479ht1keotd1lv3ar', 'bánh', 1, '300734e3e8.jpg', 205),
(63, 38, 'jsh96bv6dj2is52ob596srpsg2', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 1, '9ff33b7b3a.jpg', 3),
(65, 42, '9fhep9n1oh3t6d0iiphsf8j0q4', 'Cua Hoàng Đế rang muối', 1, '8f7e32fc4e.jpg', 300),
(92, 42, 'bv6277itgovveq2l2qvq0kpvqb', 'Cua Hoàng Đế rang muối', 1, '8f7e32fc4e.jpg', 300),
(122, 44, 'pfdi2oe5uibg7fubdvfle5bqg5', 'Cơm chiên', 4, '7625cabe20.jpg', 205),
(164, 38, '', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 1, '9ff33b7b3a.jpg', 3),
(165, 38, 'trungtd@gmail.com', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 1, '9ff33b7b3a.jpg', 3),
(166, 38, 'trungtd@gmail.com', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 1, '9ff33b7b3a.jpg', 3),
(167, 42, 'trungtd@gmail.com', 'Cua Hoàng Đế rang muối', 1, '8f7e32fc4e.jpg', 300),
(215, 38, 'tdtrung@gmail.com', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 3),
(223, 46, 'tdtrung@gmail.com', 'Nước dâu tây Star Kombucha', 6, '3f1f784e5c.jpg', 20000),
(253, 39, '3@gmail.com', 'Mỳ Ý sốt bò bằm', 3, '3ab0f96d48.jpg', 500),
(254, 38, '3@gmail.com', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 2, '9ff33b7b3a.jpg', 3),
(255, 38, 'r2pk1m9diuiunesc8fignvdubl', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 1, '9ff33b7b3a.jpg', 3),
(256, 44, 'r2pk1m9diuiunesc8fignvdubl', 'Cơm chiên', 1, '7625cabe20.jpg', 205),
(263, 38, '3@gmail.com', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 1, '9ff33b7b3a.jpg', 3),
(264, 38, '3@gmail.com', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 1, '9ff33b7b3a.jpg', 3),
(268, 39, '2@gmail.com', 'Mỳ Ý sốt bò bằm', 10, '3ab0f96d48.jpg', 500),
(274, 38, 'ngan3@gmail.com', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 3),
(275, 45, 'ngan3@gmail.com', 'Cua hấp', 3, 'd79dfb31df.jpg', 300000),
(278, 45, 'ngan5@gmail.com', 'Cua hấp', 1, 'd79dfb31df.jpg', 300000),
(279, 46, 'ngan5@gmail.com', 'Nước dâu tây Star Kombucha', 5, '3f1f784e5c.jpg', 20000),
(281, 46, 'ngan5@gmail.com', 'Nước dâu tây Star Kombucha', 3, '3f1f784e5c.jpg', 20000),
(289, 38, 't@gmail.com', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 6, '9ff33b7b3a.jpg', 3),
(290, 56, 't@gmail.com', 'Cá Thờn Bơn sashimi', 2, '2930b91666.jpg', 3400),
(291, 39, 'ngan5@gnail.com', 'Mỳ Ý sốt bò bằm', 1, '3ab0f96d48.jpg', 500);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_category`
--

CREATE TABLE `tbl_category` (
  `catId` int(11) NOT NULL,
  `catName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_category`
--

INSERT INTO `tbl_category` (`catId`, `catName`) VALUES
(13, 'Món Âu'),
(14, 'Hải sản'),
(15, 'Đồ uống'),
(18, 'Món Á');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_customer`
--

CREATE TABLE `tbl_customer` (
  `id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `zipcode` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `country` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `phone` int(11) NOT NULL,
  `password` varchar(256) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_customer`
--

INSERT INTO `tbl_customer` (`id`, `name`, `zipcode`, `country`, `city`, `address`, `email`, `phone`, `password`) VALUES
(8, 'Trần Đình ', '0123', 'VietNam', '        Đà Nẵng', 'Đà nẵng', 'trungtrandinh003@gmail.com', 353748447, '111'),
(9, 'Trần Đình Trung', '0123', 'VietNam', 'bmt', 'daklak', 'trung3@gmail.com', 353748447, '123'),
(10, '', '', 'VietNam', '', '', '', 0, '125'),
(11, ' Trung Toi', '0934', 'Việt Nam', 'Đà Nẵng', 'Ngũ Hành Sơn', 't@gmail.com', 943789456, '123'),
(12, 'Trần Đình Trung', '0123', 'VietNam', 'bmt', 'daklak', 'trungtrandi@gmail.com', 353748447, '456'),
(13, 'kim ngan ngu', '50000', 'VietNam', '  dn', 'daklak', 'ngan@gmail.com', 333657488, '123'),
(19, '', '', '', '', '', '', 0, '4'),
(20, '1wesrfwsefsdfsdfsd', '0', 'dc', '???', 'cjj', 'trungtd@gmail.com', 0, '1'),
(21, 'Trung', '124234', 'aesdf', 'sdf', 'sdf', 'tdtrung@gmail.com', 123456, '123'),
(22, '', '', '5', '', '', '', 0, '4'),
(23, 'Ngan', '1234', 'Viet Nam', 'Da Nang', 'Ngu Hanh Son', 'ngan3@gmail.com', 985674657, '123'),
(24, 'Kim Ngan', '1234', 'Viet Nam', 'Da Nang', 'Ngu Hanh Son', 'ngan4@gnail.com', 986567465, '123'),
(25, 'ngan ngu', '2', '2', '22', '2', 'ngan5@gnail.com', 2, '2'),
(26, 'Ngan', '1234', 'Viet Nam', 'Da Nang', 'Ngu Hanh Son', 'ngan5@gmail.com', 941789567, '123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_order`
--

CREATE TABLE `tbl_order` (
  `id` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `customer_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `productName` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `image` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date_order` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_order`
--

INSERT INTO `tbl_order` (`id`, `productId`, `customer_id`, `productName`, `quantity`, `price`, `image`, `date_order`, `status`) VALUES
(163, 39, '11', 'Mỳ Ý sốt bò bằm', 1, 500, '3ab0f96d48.jpg', '2023-05-17 09:29:04', 2),
(164, 38, '11', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 1, 3, '9ff33b7b3a.jpg', '2023-05-17 09:34:05', 2),
(165, 50, '11', 'coca', 5, 50, '1e01d7e008.jpg', '2023-05-17 13:24:03', 2),
(166, 50, '11', 'coca', 5, 50, '1e01d7e008.jpg', '2023-05-18 01:09:40', 2),
(167, 50, '11', 'coca', 1, 50, '1e01d7e008.jpg', '2023-05-17 10:08:30', 1),
(186, 39, '26', 'Mỳ Ý sốt bò bằm', 1, 500, '3ab0f96d48.jpg', '2023-05-17 10:30:51', 0),
(187, 42, '26', 'Cua Hoàng Đế rang muối', 3, 300, '8f7e32fc4e.jpg', '2023-05-17 10:30:51', 0),
(188, 45, '26', 'Cua hấp', 1, 300000, 'd79dfb31df.jpg', '2023-05-17 10:31:34', 0),
(189, 46, '26', 'Nước dâu tây Star Kombucha', 5, 20000, '3f1f784e5c.jpg', '2023-05-17 10:31:34', 0),
(190, 45, '26', 'Cua hấp', 1, 300000, 'd79dfb31df.jpg', '2023-05-17 10:32:42', 0),
(191, 46, '26', 'Nước dâu tây Star Kombucha', 5, 20000, '3f1f784e5c.jpg', '2023-05-17 10:32:42', 0),
(192, 46, '26', 'Nước dâu tây Star Kombucha', 3, 20000, '3f1f784e5c.jpg', '2023-05-17 10:32:42', 0),
(200, 42, '11', 'Cua Hoàng Đế rang muối', 8, 300, '8f7e32fc4e.jpg', '2023-05-18 14:45:18', 0),
(201, 38, '11', 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 6, 3, '9ff33b7b3a.jpg', '2023-05-19 01:50:23', 2),
(202, 56, '11', 'Cá Thờn Bơn sashimi', 2, 3400, '2930b91666.jpg', '2023-05-19 01:49:31', 0),
(203, 39, '25', 'Mỳ Ý sốt bò bằm', 2, 500, '3ab0f96d48.jpg', '2023-06-23 15:45:13', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_post`
--

CREATE TABLE `tbl_post` (
  `postId` int(11) NOT NULL,
  `postTieude` varchar(255) NOT NULL,
  `postMota` longtext NOT NULL,
  `image` varchar(100) NOT NULL,
  `type` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_post`
--

INSERT INTO `tbl_post` (`postId`, `postTieude`, `postMota`, `image`, `type`) VALUES
(7, 'Các món ăn Giáng Sinh Việt Nam hấp dẫn', '<p><em>Gi&aacute;ng sinh l&agrave; một ng&agrave;y lễ lớn đặc biệt với những gia đ&igrave;nh theo đạo Thi&ecirc;n Ch&uacute;a th&igrave; đ&acirc;y c&ograve;n giống một ng&agrave;y Tết. Ch&iacute;nh v&igrave; vậy m&agrave; v&agrave;o dịp n&agrave;y mọi người rất ch&uacute; trọng về &yacute; nghĩa của từng m&oacute;n ăn. T&ugrave;y thuộc v&agrave;o văn h&oacute;a ri&ecirc;ng của từng quốc gia m&agrave; v&agrave;o đ&ecirc;m Noel lại c&oacute; thực đơn ri&ecirc;ng. Nếu bạn chưa chọn được menu cho m&igrave;nh, h&atilde;y để Vivu giới thiệu cho bạn c&aacute;c&nbsp;</em><span><em>m&oacute;n ăn Gi&aacute;ng Sinh Việt Nam</em></span><em>&nbsp;ngon, cuốn vị.</em> &gt;</p>', 'ea71ea40a6.jpg', 0),
(8, 'Thịt bò nướng tảng cho Giáng Sinh', '<p><span>M&oacute;n ăn nhanh gọn, đơn giản nhưng lại đem đến sự trọn vị. Thịt b&ograve; nướng tảng giữ được độ tươi ngọt, mềm ngon kh&ocirc;ng bị kh&ocirc; khi thưởng thức</span></p>', 'fcd2a9c471.jpg', 1),
(9, 'Giáng sinh ăn gì vừa hấp dẫn vừa ý nghĩa cùng người thân và bạn bè', '<p><em>Đ&ecirc;m Gi&aacute;ng Sinh l&agrave; đ&ecirc;m m&agrave; c&aacute;c th&agrave;nh vi&ecirc;n trong gia đ&igrave;nh hay bạn b&egrave; sẽ qu&acirc;y quần b&ecirc;n nhau, c&ugrave;ng ăn một bữa cơm đo&agrave;n vi&ecirc;n. Để đ&ecirc;m Gi&aacute;ng Sinh th&ecirc;m ấm &aacute;p, &yacute; nghĩa c&ugrave;ng người th&acirc;n v&agrave; bạn b&egrave;. Vivu sẽ gợi &yacute; gi&uacute;p bạn những c&acirc;u trả lời cho&nbsp;</em><span><em>Gi&aacute;ng Sinh ăn g&igrave;&nbsp;</em></span><em>hấp dẫn? C&ugrave;ng tham khảo qua b&agrave;i viết b&ecirc;n dưới nh&eacute;!</em></p>', '24413715c4.jpg', 0),
(10, 'Bánh mì heo quay Ty - chợ Cồn', '<p><span>B&aacute;nh m&igrave; chắc chắn l&agrave; m&oacute;n kh&ocirc;ng thể thiếu với c&aacute;c t&iacute;n đồ ăn khuya. Nếu H&agrave; Nội c&oacute; b&aacute;nh m&igrave; d&acirc;n tổ, th&igrave; Đ&agrave; Nẵng cũng c&oacute; điểm ăn khuya nổi tiếng kh&ocirc;ng k&eacute;m. Đ&oacute; l&agrave; qu&aacute;n b&aacute;nh m&igrave; heo quay Ty tại chợ Cồn. Qu&aacute;n mở cửa từ 19h - 3h s&aacute;ng, l&uacute;c n&agrave;o cũng đ&ocirc;ng kh&aacute;ch nhưng b&aacute;nh được phục vụ rất nhanh. B&aacute;nh m&igrave; gi&ograve;n, nh&acirc;n ngon, đầy &uacute; ụ m&agrave; gi&aacute; lại cực kỳ phải chăng. Tối đ&oacute;i bụng, l&oacute;t dạ bằng một ổ b&aacute;nh m&igrave; tại đ&acirc;y th&igrave; qu&aacute; hợp l&yacute;.</span></p>', '0d124a124e.jpg', 0),
(11, 'Cơm Gà Hội An De Faifo - Triệu Việt Vương', '<p><span>Cơm g&agrave; Hội An l&agrave; m&oacute;n ăn rất đỗi nổi tiếng v&agrave; quen thuộc với thực kh&aacute;ch khi đến với phố Hội. Kh&ocirc;ng chỉ ri&ecirc;ng tại Hội An, cơm g&agrave; c&ograve;n l&agrave; m&oacute;n ăn nổi tiếng tr&ecirc;n c&aacute;c tạp ch&iacute; ẩm thực thế giới v&agrave; được ưa chuộng tại nhiều th&agrave;nh phố kh&aacute;c tr&ecirc;n khắp cả nước. Cơm dẻo, thơm, v&agrave;ng ruộm kết hợp với thịt g&agrave; ta x&eacute; dai dai, tươi ngon, b&eacute;o ngậy c&ugrave;ng gỏi v&agrave; nước sốt đặc trưng đ&atilde; tạo n&ecirc;n m&oacute;n ăn khiến thực kh&aacute;ch ăn một lần l&agrave; nhớ m&atilde;i.&nbsp;</span></p>', '3cb77b0c32.jpg', 1),
(12, 'Chuỗi Bami Bread - Bánh Mì Hội An', '<p dir=\"ltr\">Nhắc tới Hội An th&igrave; sao m&agrave; qu&ecirc;n được m&oacute;n ăn &ldquo;quốc d&acirc;n&rdquo; b&aacute;nh m&igrave; - m&oacute;n ăn đ&atilde; được những chuy&ecirc;n gia ẩm thực h&agrave;ng đầu thế giới d&agrave;nh cho những lời khen c&oacute; c&aacute;nh v&agrave; sẵn l&ograve;ng giới thiệu tới bạn b&egrave; khắp năm ch&acirc;u. Điểm đặc biệt nhất tạo n&ecirc;n sự kh&aacute;c biệt lớn giữa b&aacute;nh m&igrave; Hội An với c&aacute;c v&ugrave;ng c&ograve;n lại l&agrave; thứ nước sốt đặc biệt đậm đ&agrave; được chế biến k&igrave; c&ocirc;ng v&agrave; tuyệt vời. Lớp vỏ gi&ograve;n rụm, phần nh&acirc;n b&eacute;o b&ugrave;i m&agrave; kh&ocirc;ng ngấy kết hợp với nhau tạo th&agrave;nh chiếc \"b&aacute;nh m&igrave; ngon nhất thế giới\" hấp dẫn mọi du kh&aacute;ch.</p>\r\n<div>&nbsp;</div>\r\n<p dir=\"ltr\">&nbsp;</p>', '4cebf411bb.jpg', 1),
(13, 'Top 10 món giải nhiệt cực đã khi tới đà nẵng', '<p><span>Du lịch Đ&agrave; Nẵng v&agrave;o m&ugrave;a h&egrave; thật l&agrave; một trải nghiệm l&yacute; th&uacute; phải kh&ocirc;ng? Biển xanh c&aacute;t trắng nắng v&agrave;ng, tiếng s&oacute;ng vỗ r&igrave; r&agrave;o vỗ về b&ecirc;n tai, tất cả tạo n&ecirc;n một khung cảnh đầy l&atilde;ng mạn v&agrave; mộng mơ. Nhưng sẽ c&ograve;n tuyệt vời hơn nếu ng&agrave;y h&egrave; nắng n&oacute;ng m&agrave; được thưởng thức ngay những m&oacute;n đồ giải nhiệt m&aacute;t lạnh, thanh lọc cho t&acirc;m hồn. L&ecirc;n xe ra phố v&agrave; t&igrave;m ngay top 10 m&oacute;n giải nhiệt cực đ&atilde; khi tới Đ&agrave; Nẵng th&ocirc;i n&agrave;o!</span></p>', '7cf430f4fd.jpg', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_product`
--

CREATE TABLE `tbl_product` (
  `productId` int(11) NOT NULL,
  `productName` tinytext NOT NULL,
  `catId` int(11) NOT NULL,
  `brandId` int(11) NOT NULL,
  `product_desc` text NOT NULL,
  `type` int(11) NOT NULL,
  `price` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_product`
--

INSERT INTO `tbl_product` (`productId`, `productName`, `catId`, `brandId`, `product_desc`, `type`, `price`, `image`) VALUES
(38, 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 15, 8, '<p><span>Giống nho. Blend ; Loại vang.&nbsp;</span><span>Rượu Vang</span><span>&nbsp;Trắng ; Nh&agrave; sản xuất.&nbsp;</span><span>Chateau Carbonnieux</span><span>&nbsp;; Quốc gia. Vang&nbsp;</span><span>Ph&aacute;p</span><span>&nbsp;; Nồng độ. 13% ABV*.</span></p>', 0, '2.500 ', '9ff33b7b3a.jpg'),
(39, 'Mỳ Ý sốt bò bằm', 13, 5, '<p><span>M&igrave;&nbsp;</span><span>&Yacute; sốt b&ograve; băm</span><span>&nbsp;hay c&ograve;n gọi l&agrave; Spaghetti, l&agrave; m&oacute;n ăn ngon v&agrave; phổ biến, d&ugrave;ng k&egrave;m với hỗn hợp&nbsp;</span><span>sốt</span><span>&nbsp;c&agrave; chua v&agrave; thịt&nbsp;</span><span>b&ograve; bằm</span><span>&nbsp;c&oacute; nguồn gốc từ Italia</span></p>', 0, '500 ', '3ab0f96d48.jpg'),
(42, 'Cua Hoàng Đế rang muối', 14, 6, '<p><span>Cua Ho&agrave;ng Đế Rang Muối</span><span>&nbsp;l&agrave; m&oacute;n ăn được nhiều gia đ&igrave;nh y&ecirc;u th&iacute;ch. Đ&acirc;y sẽ l&agrave; m&oacute;n ăn ngon ph&ugrave; hợp để l&agrave;m mới khẩu vị của tất cả c&aacute;c th&agrave;nh vi&ecirc;n.</span></p>', 0, '300 ', '8f7e32fc4e.jpg'),
(43, 'Sườn tỏi cay tứ xuyên', 18, 6, '<p>Nguyen lieu suon,...</p>', 0, '23000 ', 'bcd7f7ef6d.jpg'),
(44, 'Cơm chiên', 18, 6, '<p>nguy&ecirc;n liệu c&aacute; cơm</p>', 0, '205 ', '7625cabe20.jpg'),
(45, 'Cua hấp', 14, 4, '<p>Tươi, ngon</p>', 1, '300000 ', 'd79dfb31df.jpg'),
(46, 'Nước dâu tây Star Kombucha', 15, 7, '<p>rẻ, ngon dỡ</p>', 1, '20000 ', '3f1f784e5c.jpg'),
(50, 'coca', 15, 7, '<p>Ngon&nbsp;</p>', 1, '50 ', '1e01d7e008.jpg'),
(52, 'Mỳ Ý sốt kem trứng cá chuồn kiểu Nhật', 13, 5, '<p>Rất ngon v&agrave; tuyệt vời được l&agrave;m từ mỳ quảng</p>', 0, '20000 ', '7576423518.jpg'),
(53, 'Cơm gà kiểu New York', 13, 5, '<p><span>Cơm G&agrave; kiểu New York</span><span>&nbsp;m&oacute;n ăn b&igrave;nh d&acirc;n của người d&acirc;n New York, rất dễ t&igrave;m được m&oacute;n ăn n&agrave;y tr&ecirc;n mọi ng&otilde; đường phố tập nập</span> &gt;</p>', 0, '5000000 ', '3e851066b3.jpg'),
(54, 'Mực hấp', 0, 0, '<p><span>Hấp mực</span><span>. Đầu ti&ecirc;n, bạn cho nước v&agrave;o nồi ch&otilde;&nbsp;</span><span>hấp</span><span>&nbsp;rồi đặt l&ecirc;n bếp v&agrave; tiến h&agrave;nh đun c&ugrave;ng 5 - 6 l&aacute; ổi. Số l&aacute; ổi&nbsp;...</span></p>', 1, '23000 ', '4b17f1cc8f.jpg'),
(55, 'Cá Mú hấp xì dầu', 14, 4, '<h2>C&aacute; m&uacute; đ&atilde; từ l&acirc;u trở th&agrave;nh một nguy&ecirc;n liệu thơm ngon, bổ dưỡng.</h2>', 1, '30000 ', '7266367745.jpg'),
(56, 'Cá Thờn Bơn sashimi', 14, 4, '<p><span>oại&nbsp;</span><span>c&aacute;</span><span>&nbsp;n&agrave;y gi&agrave;u protein, axit b&eacute;o kh&ocirc;ng b&atilde;o h&ograve;a, kho&aacute;ng chất v&agrave; vitamin, g&oacute;p phần giải quyết vấn đề mất c&acirc;n bằng dinh dưỡng do chế độ ăn ki&ecirc;ng.</span></p>', 1, '3400 ', '2930b91666.jpg'),
(57, 'Rượu vang Mỹ Antica Cabernet Sauvignon 2017', 15, 7, '<p>Rượu vang Mỹ Antica Cabernet Sauvignon 2017</p>', 0, '1k ', '48fc49e81a.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_slider`
--

CREATE TABLE `tbl_slider` (
  `sliderId` int(11) NOT NULL,
  `sliderName` varchar(255) NOT NULL,
  `sliderImage` varchar(255) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_slider`
--

INSERT INTO `tbl_slider` (`sliderId`, `sliderName`, `sliderImage`, `type`) VALUES
(48, '2', 'df2d43217a.gif', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_wishlist`
--

CREATE TABLE `tbl_wishlist` (
  `id` int(11) NOT NULL,
  `productName` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(11) NOT NULL,
  `image` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `customer_id` int(11) NOT NULL,
  `productId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tbl_wishlist`
--

INSERT INTO `tbl_wishlist` (`id`, `productName`, `price`, `image`, `customer_id`, `productId`) VALUES
(20, 'Sườn tỏi cay tứ xuyên', 23000, 'bcd7f7ef6d.jpg', 8, 43),
(34, 'Cơm chiên', 205, '7625cabe20.jpg', 10, 44),
(37, 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 19, 38),
(38, 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 11, 38),
(39, 'Nước dâu tây Star Kombucha', 20000, '3f1f784e5c.jpg', 11, 46),
(40, 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 9, 38),
(43, 'Nước dâu tây Star Kombucha', 20000, '3f1f784e5c.jpg', 26, 46),
(44, 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 26, 38),
(46, 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 11, 38),
(48, 'Cá Thờn Bơn sashimi', 3400, '2930b91666.jpg', 11, 56),
(49, 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 11, 38),
(50, 'Mỳ Ý sốt bò bằm', 500, '3ab0f96d48.jpg', 11, 39),
(51, 'Rượu vang Pháp Chateau Carbonnieux Blanc 2020', 3, '9ff33b7b3a.jpg', 25, 38);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`adminId`);

--
-- Chỉ mục cho bảng `tbl_binhluan`
--
ALTER TABLE `tbl_binhluan`
  ADD PRIMARY KEY (`binhluan_id`);

--
-- Chỉ mục cho bảng `tbl_brand`
--
ALTER TABLE `tbl_brand`
  ADD PRIMARY KEY (`brandId`);

--
-- Chỉ mục cho bảng `tbl_cart`
--
ALTER TABLE `tbl_cart`
  ADD PRIMARY KEY (`cartId`);

--
-- Chỉ mục cho bảng `tbl_category`
--
ALTER TABLE `tbl_category`
  ADD PRIMARY KEY (`catId`);

--
-- Chỉ mục cho bảng `tbl_customer`
--
ALTER TABLE `tbl_customer`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_post`
--
ALTER TABLE `tbl_post`
  ADD PRIMARY KEY (`postId`);

--
-- Chỉ mục cho bảng `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD PRIMARY KEY (`productId`),
  ADD KEY `catId` (`catId`,`brandId`),
  ADD KEY `catId_2` (`catId`,`brandId`),
  ADD KEY `catId_3` (`catId`),
  ADD KEY `brandId` (`brandId`);

--
-- Chỉ mục cho bảng `tbl_slider`
--
ALTER TABLE `tbl_slider`
  ADD PRIMARY KEY (`sliderId`);

--
-- Chỉ mục cho bảng `tbl_wishlist`
--
ALTER TABLE `tbl_wishlist`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `adminId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tbl_binhluan`
--
ALTER TABLE `tbl_binhluan`
  MODIFY `binhluan_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT cho bảng `tbl_brand`
--
ALTER TABLE `tbl_brand`
  MODIFY `brandId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `tbl_cart`
--
ALTER TABLE `tbl_cart`
  MODIFY `cartId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=292;

--
-- AUTO_INCREMENT cho bảng `tbl_category`
--
ALTER TABLE `tbl_category`
  MODIFY `catId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `tbl_customer`
--
ALTER TABLE `tbl_customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `tbl_order`
--
ALTER TABLE `tbl_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=204;

--
-- AUTO_INCREMENT cho bảng `tbl_post`
--
ALTER TABLE `tbl_post`
  MODIFY `postId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `tbl_product`
--
ALTER TABLE `tbl_product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT cho bảng `tbl_slider`
--
ALTER TABLE `tbl_slider`
  MODIFY `sliderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `tbl_wishlist`
--
ALTER TABLE `tbl_wishlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
