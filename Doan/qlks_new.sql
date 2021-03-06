USE master
GO
CREATE DATABASE [QLKS]
GO
USE [QLKS]
GO
/****** Object:  Table [dbo].[CaLamViec]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamViec](
	[MaCLV] [int] NOT NULL,
	[TenCaLam] [nvarchar](30) NOT NULL,
	[GioBatDau] [time](7) NOT NULL,
	[GioKetThuc] [time](7) NOT NULL,
 CONSTRAINT [PK_CaLamViec] PRIMARY KEY CLUSTERED 
(
	[MaCLV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[MaCV] [varchar](10) NOT NULL,
	[TenChucVu] [nvarchar](50) NOT NULL,
	[HeSoLuong] [float] NOT NULL,
 CONSTRAINT [PK_ChucVu] PRIMARY KEY CLUSTERED 
(
	[MaCV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [int] IDENTITY(1,1) NOT NULL,
	[TenNV] [nvarchar](50) NOT NULL,
	[Ngaysinh] [date] NOT NULL,
	[CMND] [varchar](20) NOT NULL,
	[SDT] [varchar](11) NOT NULL,
	[Luongcoban] [money] NOT NULL,
	[TrangThai] [nvarchar](50) NOT NULL,
	[MaCLV] [int] NULL,
	[MaCV] [varchar](10) NULL,
	[GioiTinh] [nvarchar](10) NULL,
	[user] [varchar](50) NULL,
	[Password] [varchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[NgayGiaNhap] [date] NULL,
	[HinhAnh] [varchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[Info_NV]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create view [dbo].[Info_NV]
as
SELECT nv.MaNV AS [Mã nhân viên],nv.TenNV AS [Họ tên],nv.Ngaysinh AS [Ngày sinh],nv.GioiTinh AS [Giới tính],
nv.Email ,nv.CMND as [CMND],nv.SDT as [SDT],cv.HeSoLuong * nv.Luongcoban as [Lương tháng],cv.TenChucVu as [Chức vụ],
nv.NgayGiaNhap as [Ngày gia nhập],clv.TenCaLam as[Tên ca làm việc],nv.TrangThai as [Tình trạng],nv.HinhAnh as [Hình ảnh]
from NhanVien nv,ChucVu cv,CaLamViec clv 
where cv.MaCV = nv.MaCV and nv.MaCLV = clv.MaCLV
GO
/****** Object:  Table [dbo].[KHACH]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACH](
	[MaKHACH] [int] IDENTITY(1,1) NOT NULL,
	[TenKHACH] [nvarchar](50) NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[SDT] [varchar](11) NOT NULL,
	[Email] [nvarchar](60) NOT NULL,
	[CMND] [varchar](20) NOT NULL,
	[QuocTich] [nvarchar](50) NOT NULL,
	[GioiTinh] [nvarchar](10) NULL,
 CONSTRAINT [PK_KHACH] PRIMARY KEY CLUSTERED 
(
	[MaKHACH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Loaiphong]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loaiphong](
	[MaLP] [varchar](20) NOT NULL,
	[TenLoaiPhong] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Loaiphong] PRIMARY KEY CLUSTERED 
(
	[MaLP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuDatPhong]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDatPhong](
	[MaPhieuDP] [int] NOT NULL,
	[MaPhong] [int] NOT NULL,
	[MaKHACH] [int] NOT NULL,
	[MaNV] [int] NOT NULL,
	[NgayDatPhong] [datetime] NOT NULL,
	[Traphong] [datetime] NOT NULL,
	[DonGiaThue] [money] NOT NULL,
	[DonGiaPhong] [money] NULL,
 CONSTRAINT [PK_PhieuDatPhong_1] PRIMARY KEY CLUSTERED 
(
	[MaPhieuDP] ASC,
	[MaPhong] ASC,
	[MaKHACH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[MaPhong] [int] IDENTITY(1,1) NOT NULL,
	[Tenphong] [nvarchar](30) NOT NULL,
	[Songuoi] [int] NOT NULL,
	[DonGia] [money] NOT NULL,
	[Trangthai] [nvarchar](50) NOT NULL,
	[MaLP] [varchar](20) NOT NULL,
	[Tang] [int] NOT NULL,
 CONSTRAINT [PK_Phong] PRIMARY KEY CLUSTERED 
(
	[MaPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[View_PDP]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[View_PDP]
as
 Select pdp.MaPhieuDP as [Mã PDP], p.MaPhong as [Mã Phòng],p.Tenphong as [Tên Phòng],lp.MaLP as [Mã LP], lp.TenLoaiPhong as [Tên LP], 
		kh.MaKHACH as [Mã Khách],kh.TenKHACH as [Khách], kh.GioiTinh as [Giới Tính],kh.NgaySinh as [Ngày sinh],
		kh.Email,kh.CMND,kh.SDT,kh.QuocTich, 
		pdp.NgayDatPhong as [Ngày đặt], pdp.Traphong as [Ngày trả],
		pdp.DonGiaThue as [Tiền trả], pdp.DonGiaPhong as [Giá Phòng] ,nv.TenNV as [Nhân viên thanh toán]
 From PhieuDatPhong pdp,Phong p, NhanVien nv,KHACH kh,Loaiphong lp
 Where pdp.MaPhong = p.MaPhong and pdp.MaKHACH = kh.MaKHACH and pdp.MaNV = nv.MaNV
		and lp.MaLP = p.MaLP
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [int] IDENTITY(1,1) NOT NULL,
	[MaPhong] [int] NOT NULL,
	[TongTien] [money] NOT NULL,
	[NgayInHD] [datetime] NOT NULL,
	[MaNV] [int] NOT NULL,
 CONSTRAINT [PK_HoaDon_1] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[View_DetailsHD]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 create view [dbo].[View_DetailsHD]
  as
  select hd.MaHD,hd.MaPhong,hd.TongTien,hd.NgayInHD,hd.MaNV,nv.TenNV,kh.MaKHACH,kh.TenKHACH, p.Tenphong,p.Songuoi,pdp.DonGiaPhong,p.MaLP,lp.TenLoaiPhong,p.Tang,pdp.MaPhieuDP,pdp.NgayDatPhong,
		pdp.Traphong,pdp.DonGiaThue
  from HoaDon hd,Phong p,PhieuDatPhong pdp,Loaiphong lp, KHACH kh, NhanVien nv
  where hd.MaPhong = p.MaPhong and pdp.MaPhong = p.MaPhong and pdp.Traphong = hd.NgayInHD and lp.MaLP = p.MaLP and kh.MaKHACH = pdp.MaKHACH and nv.MaNV = hd.MaNV
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DichVu](
	[MaDV] [varchar](20) NOT NULL,
	[TenDV] [nvarchar](60) NOT NULL,
	[DonGiaDV] [money] NOT NULL,
	[MaLoaiDV] [varchar](20) NULL,
 CONSTRAINT [PK_DichVu] PRIMARY KEY CLUSTERED 
(
	[MaDV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiDV]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiDV](
	[MaLoaiDV] [varchar](20) NOT NULL,
	[TenloaiDV] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_LoaiDV] PRIMARY KEY CLUSTERED 
(
	[MaLoaiDV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuDV]    Script Date: 12-05-2021 7:48:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDV](
	[MaPhieuDV] [int] NOT NULL,
	[MaPhong] [int] NOT NULL,
	[MaDV] [varchar](20) NOT NULL,
	[TienDV] [money] NOT NULL,
	[Soluong] [int] NOT NULL,
	[NgaySD] [datetime] NOT NULL,
	[GiaDV] [money] NULL,
 CONSTRAINT [PK_PhieuDV] PRIMARY KEY CLUSTERED 
(
	[MaPhieuDV] ASC,
	[NgaySD] ASC,
	[MaDV] ASC,
	[MaPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CaLamViec] ([MaCLV], [TenCaLam], [GioBatDau], [GioKetThuc]) VALUES (1, N'Sáng', CAST(N'08:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[CaLamViec] ([MaCLV], [TenCaLam], [GioBatDau], [GioKetThuc]) VALUES (2, N'Tối', CAST(N'18:00:00' AS Time), CAST(N'05:00:00' AS Time))
GO
INSERT [dbo].[ChucVu] ([MaCV], [TenChucVu], [HeSoLuong]) VALUES (N'Admin', N'admin', 2)
INSERT [dbo].[ChucVu] ([MaCV], [TenChucVu], [HeSoLuong]) VALUES (N'BV', N'Bảo vệ', 1.2)
INSERT [dbo].[ChucVu] ([MaCV], [TenChucVu], [HeSoLuong]) VALUES (N'KT', N'Kế toán', 1.6)
INSERT [dbo].[ChucVu] ([MaCV], [TenChucVu], [HeSoLuong]) VALUES (N'NV', N'Nhân viên', 1.4)
INSERT [dbo].[ChucVu] ([MaCV], [TenChucVu], [HeSoLuong]) VALUES (N'QL', N'Quản lý', 1.8)
GO
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'BE01', N'Bia Tigher', 40000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'BE02', N'Bia Heineken', 45000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'BE03', N'Bia 333', 38000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'BE04', N'Bia Sài Gòn', 35000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'COM01', N'Cơm chiên dương châu', 70000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'COM02', N'Cơm chiên hải sản', 121000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'COM03', N'Cơm chiên gà nấm', 73000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'HAU', N'Hàu nướng mỡ hành', 140000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'KEM01', N'Kem dâu hoàng gia', 97000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'KEM02', N'Kem sầu riêng', 56000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'KEM03', N'Kem Dak Lak', 83000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'KT', N'Khoai Tây Chiên', 49000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'MI01', N'Mì xào bò', 25000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'MN', N'Mực Nướng', 120000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'MX', N'Mát - xa', 2300000.0000, N'SK')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'NU01', N'Nước Cam', 25000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'NU02', N'Nước Suối', 20000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'NU03', N'Cocacola', 35000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'NU04', N'Pepsi', 35000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'NU05', N'Red Bull', 35000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'RV01', N'Rượu Robert Mondavi', 1200000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'RV02', N'Rượu Chateau Gruaud-Larose', 1200000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'RV03', N'Rượu Chile', 120000.0000, N'DU')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'SL', N'Salad', 16000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'TH', N'Tôm hùm hấp', 2000000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'TX', N'Thịt xông khói', 100000.0000, N'TA')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [DonGiaDV], [MaLoaiDV]) VALUES (N'YT', N'Dịch vụ y tế', 500000.0000, N'SK')
GO
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (1, 1, 400000.0000, CAST(N'2021-04-05T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (2, 2, 500000.0000, CAST(N'2021-04-06T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (3, 3, 400000.0000, CAST(N'2021-04-04T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (4, 4, 500000.0000, CAST(N'2021-01-21T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (5, 5, 700000.0000, CAST(N'2021-02-24T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (6, 7, 400000.0000, CAST(N'2021-03-26T00:00:00.000' AS DateTime), 6)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (7, 8, 400000.0000, CAST(N'2021-01-29T00:00:00.000' AS DateTime), 6)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (8, 9, 500000.0000, CAST(N'2021-01-16T00:00:00.000' AS DateTime), 6)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (9, 10, 500000.0000, CAST(N'2021-01-18T00:00:00.000' AS DateTime), 6)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (10, 11, 700000.0000, CAST(N'2021-02-26T00:00:00.000' AS DateTime), 7)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (11, 13, 400000.0000, CAST(N'2021-01-04T00:00:00.000' AS DateTime), 7)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (13, 14, 400000.0000, CAST(N'2021-02-05T00:00:00.000' AS DateTime), 7)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (14, 6, 700000.0000, CAST(N'2021-04-05T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (15, 1, 1360000.0000, CAST(N'2021-01-03T00:00:00.000' AS DateTime), 6)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (16, 1, 800000.0000, CAST(N'2021-05-09T00:00:00.000' AS DateTime), 6)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (17, 2, 2300000.0000, CAST(N'2021-05-11T00:00:00.000' AS DateTime), 6)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (18, 3, 800000.0000, CAST(N'2021-05-11T00:00:00.000' AS DateTime), 7)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (19, 10, 1000000.0000, CAST(N'2021-05-11T00:00:00.000' AS DateTime), 11)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (20, 18, 8819000.0000, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 11)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (21, 13, 1200000.0000, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 11)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (22, 11, 7498000.0000, CAST(N'2021-05-13T00:00:00.000' AS DateTime), 11)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (23, 7, 3695000.0000, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 11)
INSERT [dbo].[HoaDon] ([MaHD], [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (24, 1, 13000000.0000, CAST(N'2021-05-15T00:00:00.000' AS DateTime), 11)
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
GO
SET IDENTITY_INSERT [dbo].[KHACH] ON 

INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (1, N'Nguyễn Phước Lớn', CAST(N'2000-02-02' AS Date), N'0933216771', N'nplsolidary@gmail.com', N'0961334567', N'Việt Nam', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (2, N'Đặng Thành Người', CAST(N'2004-03-02' AS Date), N'0961533697', N'linhanh@gmail.com', N'0126250367', N'Việt Nam', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (3, N'Vũ Duy Trì', CAST(N'2005-04-03' AS Date), N'0933112678', N'duytri2001@gmail.com', N'0967561281', N'Việt Nam', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (4, N'Nguyễn Dio Minh Tùng', CAST(N'2003-02-11' AS Date), N'0967531264', N'diotung2003@gmail.com', N'0961253441', N'Anh', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (5, N'Jotaro Rinousuke', CAST(N'1997-11-27' AS Date), N'0933114267', N'jotaro97@gmail.com', N'0977544321', N'Anh', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (6, N'Elia Heila', CAST(N'1996-11-20' AS Date), N'0933251667', N'elia96@gmail.com', N'0965431277', N'Anh', N'Nữ')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (7, N'Shiori Hideo', CAST(N'1998-04-25' AS Date), N'0931256777', N'shiorijp98@gmail.com', N'0932125411', N'Nhật', N'Nữ')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (8, N'Jeremy Harison', CAST(N'1996-04-25' AS Date), N'0971254222', N'jeremai@gmail.com', N'0933254111', N'Mỹ', N'Nữ')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (9, N'Edward Thomas', CAST(N'1996-01-25' AS Date), N'0937372355', N'edwardk@gmail.com', N'0926355441', N'Mỹ', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (10, N'Peter Parker', CAST(N'1994-02-12' AS Date), N'0926751244', N'parkered@gmail.com', N'0931256377', N'Mỹ', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (11, N'Mary Jane', CAST(N'1994-02-26' AS Date), N'0926333441', N'ilovespider@gmail.com', N'0937526631', N'Mỹ', N'Nữ')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (12, N'Phan Pearl Trí', CAST(N'2000-03-27' AS Date), N'0933477531', N'pearltri@gmail.com', N'0933567891', N'Việt Nam', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (13, N'Ash Brown', CAST(N'2000-05-09' AS Date), N'0933567981', N'ash@g', N'135792468', N'Mỹ', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (14, N'John Jr Dean', CAST(N'2000-05-09' AS Date), N'0983561247', N'deanK@g', N'147258369', N'Anh', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (15, N'Maria Ozaru', CAST(N'1995-05-18' AS Date), N'0933567981', N'mariaoz@g', N'1593572468', N'Nhật', N'Nữ')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (16, N'Shinzo Nanase', CAST(N'2000-05-09' AS Date), N'0933567891', N'nannane@g', N'157932648', N'Nhật', N'Nữ')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (17, N'Edward Thomas', CAST(N'1994-05-20' AS Date), N'0963541237', N'edthomas@g', N'16947823', N'Mỹ', N'Nam')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (18, N'Huỳnh Thiên Ngân', CAST(N'1999-05-06' AS Date), N'0933567451', N'ngankiute@g', N'146978532', N'Việt Nam', N'Nữ')
INSERT [dbo].[KHACH] ([MaKHACH], [TenKHACH], [NgaySinh], [SDT], [Email], [CMND], [QuocTich], [GioiTinh]) VALUES (19, N'Br.Downey', CAST(N'2000-05-10' AS Date), N'0933567890', N'brdown@g', N'133567890', N'Mỹ', N'Nam')
SET IDENTITY_INSERT [dbo].[KHACH] OFF
GO
INSERT [dbo].[LoaiDV] ([MaLoaiDV], [TenloaiDV]) VALUES (N'DD', N'Dọn dẹp')
INSERT [dbo].[LoaiDV] ([MaLoaiDV], [TenloaiDV]) VALUES (N'DU', N'Đồ Uống')
INSERT [dbo].[LoaiDV] ([MaLoaiDV], [TenloaiDV]) VALUES (N'SK', N'Hỗ trợ sức khỏe')
INSERT [dbo].[LoaiDV] ([MaLoaiDV], [TenloaiDV]) VALUES (N'TA', N'Thức Ăn')
GO
INSERT [dbo].[Loaiphong] ([MaLP], [TenLoaiPhong]) VALUES (N'DOI', N'Đôi')
INSERT [dbo].[Loaiphong] ([MaLP], [TenLoaiPhong]) VALUES (N'DON', N'Đơn')
INSERT [dbo].[Loaiphong] ([MaLP], [TenLoaiPhong]) VALUES (N'MANY', N'Ghép')
GO
SET IDENTITY_INSERT [dbo].[NhanVien] ON 

INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (1, N'Vũ Duy Linh', CAST(N'2000-06-13' AS Date), N'123409876', N'0857853609', 6000000.0000, N'Đang hoạt động', 1, N'NV', N'Nam', N'linh', N'bede', N'linh1@g', CAST(N'2020-01-01' AS Date), N'NV1')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (2, N'Admin', CAST(N'1988-01-11' AS Date), N'124536789', N'0875347853', 6000000.0000, N'Đang hoạt động', 2, N'Admin', N'Nu', N'Admin', N'Admin', N'admin@g', CAST(N'2020-02-02' AS Date), N'NV2')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (3, N'Huy Bảo', CAST(N'1991-01-22' AS Date), N'129860436', N'0936731234', 6000000.0000, N'Đang hoạt động', 2, N'BV', N'Nam', N'bao', N'ass123', N'bao@g', CAST(N'2020-03-03' AS Date), N'NV3')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (4, N'Đặng Thành Nhân', CAST(N'1991-04-13' AS Date), N'129865439', N'0934348888', 6000000.0000, N'Ngưng hoạt động', 1, N'KT', N'Nam', N'no', N'name', N'no@g', CAST(N'2020-04-04' AS Date), N'NV4')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (5, N'Nguyễn Kiều Tú Oanh', CAST(N'2001-05-11' AS Date), N'126678543', N'0937853509', 6000000.0000, N'Đang hoạt động', 1, N'QL', N'Nu', N'oanh', N'oanh', N'oanh@g', CAST(N'2020-05-05' AS Date), N'NV5')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (6, N'Đặng Thanh Tâm', CAST(N'2000-01-16' AS Date), N'126577777', N'0857683609', 6000000.0000, N'Đang hoạt động', 1, N'NV', N'Nu', N'tam', N'tam', N'tam@g', CAST(N'2020-06-06' AS Date), N'NV6')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (7, N'Vũ Kiều Nhi', CAST(N'1998-07-12' AS Date), N'12679436', N'0937853201', 6000000.0000, N'Đang hoạt động', 2, N'NV', N'Nu', N'nhi', N'nhi', N'nhi@g', CAST(N'2020-07-07' AS Date), N'NV7')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (8, N'Ngô Trọng Huy', CAST(N'1987-02-19' AS Date), N'129765004', N'0987653409', 6000000.0000, N'Ngưng hoạt động', 1, N'NV', N'Nam', N'huy', N'huy', N'huy@g', CAST(N'2020-08-08' AS Date), N'NV8')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (9, N'Cao Minh Trung', CAST(N'2000-06-11' AS Date), N'129876543', N'0938763509', 6000000.0000, N'Đang hoạt động', 2, N'BV', N'Nam', N'trung', N'trung', N'trung@g', CAST(N'2020-09-09' AS Date), N'NV9')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (10, N'Phan Ngọc Trí', CAST(N'1999-08-11' AS Date), N'129867054', N'0937853609', 6000000.0000, N'Ngưng hoạt động', 2, N'QL', N'Nam', N'tri', N'tri', N'tri@', CAST(N'2020-10-10' AS Date), N'NV10')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (11, N'Hoàng Kim Sa', CAST(N'2000-05-24' AS Date), N'1223344566', N'0933526771', 6000000.0000, N'Đang hoạt động', 1, N'NV', N'Nữ', N'sa', N'sa', N'sa@g', CAST(N'2021-05-05' AS Date), N'NV11')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (12, N'Lê An Quy', CAST(N'1995-05-17' AS Date), N'1233567890', N'0911650287', 6000000.0000, N'Đang hoạt động', 2, N'BV', N'Nam', N'aq', N'aq', N'aq@', CAST(N'2021-05-05' AS Date), N'NV12')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (13, N'Ngô San Kiêu', CAST(N'2021-05-20' AS Date), N'1124567890', N'0933551247', 6000000.0000, N'Đang hoạt động', 2, N'NV', N'Nữ', N'sq', N'sq', N'sq@g', CAST(N'2021-05-05' AS Date), N'NV13')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (14, N'Phan Trần Huy Duy', CAST(N'2000-05-16' AS Date), N'1223344566', N'0933526771', 6000000.0000, N'Đang hoạt động', 1, N'NV', N'Nam', N'huyduy', N'huyduy', N'sa@g', CAST(N'2021-05-08' AS Date), N'NV14')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap], [HinhAnh]) VALUES (15, N'Phạm Trần Huy Bảo', CAST(N'1991-01-22' AS Date), N'1223345678', N'0937278255', 6000000.0000, N'Đang hoạt động', 2, N'QL', N'Nam', N'ash', N'ash', N'huybaoash@g', CAST(N'2021-05-08' AS Date), N'NV15')
SET IDENTITY_INSERT [dbo].[NhanVien] OFF
GO
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (1, 1, 1, 1, CAST(N'2021-04-04T00:00:00.000' AS DateTime), CAST(N'2021-04-05T00:00:00.000' AS DateTime), 400000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (2, 2, 2, 1, CAST(N'2021-04-05T00:00:00.000' AS DateTime), CAST(N'2021-04-06T00:00:00.000' AS DateTime), 500000.0000, 500000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (3, 3, 3, 1, CAST(N'2021-04-03T00:00:00.000' AS DateTime), CAST(N'2021-04-04T00:00:00.000' AS DateTime), 400000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (4, 4, 4, 1, CAST(N'2021-01-20T00:00:00.000' AS DateTime), CAST(N'2021-01-21T00:00:00.000' AS DateTime), 500000.0000, 500000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (5, 5, 5, 1, CAST(N'2021-02-23T00:00:00.000' AS DateTime), CAST(N'2021-02-24T00:00:00.000' AS DateTime), 700000.0000, 700000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (6, 7, 6, 6, CAST(N'2021-03-25T00:00:00.000' AS DateTime), CAST(N'2021-03-26T00:00:00.000' AS DateTime), 400000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (7, 8, 7, 6, CAST(N'2021-01-28T00:00:00.000' AS DateTime), CAST(N'2021-01-29T00:00:00.000' AS DateTime), 400000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (8, 9, 8, 6, CAST(N'2021-01-15T00:00:00.000' AS DateTime), CAST(N'2021-01-16T00:00:00.000' AS DateTime), 500000.0000, 500000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (9, 10, 9, 6, CAST(N'2021-01-17T00:00:00.000' AS DateTime), CAST(N'2021-01-18T00:00:00.000' AS DateTime), 500000.0000, 500000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (10, 11, 10, 7, CAST(N'2021-02-25T00:00:00.000' AS DateTime), CAST(N'2021-02-26T00:00:00.000' AS DateTime), 700000.0000, 700000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (11, 13, 11, 7, CAST(N'2021-01-03T00:00:00.000' AS DateTime), CAST(N'2021-01-04T00:00:00.000' AS DateTime), 400000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (12, 14, 12, 7, CAST(N'2021-02-04T00:00:00.000' AS DateTime), CAST(N'2021-02-05T00:00:00.000' AS DateTime), 400000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (13, 6, 1, 1, CAST(N'2021-04-04T00:00:00.000' AS DateTime), CAST(N'2021-04-05T00:00:00.000' AS DateTime), 700000.0000, 700000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (14, 1, 3, 6, CAST(N'2021-05-07T00:00:00.000' AS DateTime), CAST(N'2021-05-09T00:00:00.000' AS DateTime), 800000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (15, 2, 3, 6, CAST(N'2021-05-07T00:00:00.000' AS DateTime), CAST(N'2021-05-11T00:00:00.000' AS DateTime), 2000000.0000, 500000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (16, 3, 13, 7, CAST(N'2021-05-09T00:00:00.000' AS DateTime), CAST(N'2021-05-11T00:00:00.000' AS DateTime), 800000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (17, 10, 14, 11, CAST(N'2021-05-09T00:00:00.000' AS DateTime), CAST(N'2021-05-11T00:00:00.000' AS DateTime), 1000000.0000, 500000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (18, 18, 15, 11, CAST(N'2021-05-09T00:00:00.000' AS DateTime), CAST(N'2021-05-12T00:00:00.000' AS DateTime), 2100000.0000, 700000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (19, 13, 16, 11, CAST(N'2021-05-09T00:00:00.000' AS DateTime), CAST(N'2021-05-12T00:00:00.000' AS DateTime), 1200000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (20, 11, 17, 11, CAST(N'2021-05-09T00:00:00.000' AS DateTime), CAST(N'2021-05-13T00:00:00.000' AS DateTime), 2800000.0000, 700000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (21, 7, 18, 11, CAST(N'2021-05-09T00:00:00.000' AS DateTime), CAST(N'2021-05-12T00:00:00.000' AS DateTime), 1200000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (22, 1, 4, 6, CAST(N'2020-12-31T00:00:00.000' AS DateTime), CAST(N'2021-01-03T00:00:00.000' AS DateTime), 1200000.0000, 400000.0000)
INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong], [Traphong], [DonGiaThue], [DonGiaPhong]) VALUES (23, 1, 19, 11, CAST(N'2021-05-10T00:00:00.000' AS DateTime), CAST(N'2021-05-15T00:00:00.000' AS DateTime), 2000000.0000, 400000.0000)
GO
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (1, 1, N'be01', 40000.0000, 1, CAST(N'2021-01-01T00:00:00.000' AS DateTime), 40000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (1, 1, N'be01', 40000.0000, 1, CAST(N'2021-01-02T00:00:00.000' AS DateTime), 40000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (1, 1, N'be01', 40000.0000, 1, CAST(N'2021-01-02T00:00:01.000' AS DateTime), 40000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (2, 1, N'be01', 40000.0000, 1, CAST(N'2021-01-02T00:00:00.000' AS DateTime), 40000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (3, 2, N'TX', 300000.0000, 3, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 100000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (4, 7, N'COM01', 70000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 70000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (4, 7, N'TX', 200000.0000, 2, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 100000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (5, 11, N'BE01', 40000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 40000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (5, 11, N'BE03', 38000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 38000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (5, 11, N'MN', 120000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 120000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (5, 11, N'MX', 2300000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 2300000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (5, 11, N'TH', 2000000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 2000000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (5, 11, N'TX', 200000.0000, 2, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 100000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (6, 18, N'MX', 2300000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 2300000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (6, 18, N'TX', 200000.0000, 2, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 100000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (7, 1, N'MX', 2300000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 2300000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (7, 1, N'RV01', 2400000.0000, 2, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 1200000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (7, 1, N'TH', 2000000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 2000000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (7, 1, N'TX', 100000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 100000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (8, 7, N'MI01', 25000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 25000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (8, 7, N'TH', 2000000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 2000000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (8, 7, N'TX', 200000.0000, 2, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 100000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (9, 1, N'RV01', 1200000.0000, 1, CAST(N'2021-05-10T00:00:00.000' AS DateTime), 1200000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (10, 18, N'TH', 4000000.0000, 2, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 2000000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (11, 18, N'COM03', 219000.0000, 3, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 73000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (12, 1, N'BE02', 180000.0000, 4, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 45000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (12, 1, N'COM01', 140000.0000, 2, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 70000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (12, 1, N'MI01', 100000.0000, 4, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 25000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (12, 1, N'MN', 360000.0000, 3, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 120000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (12, 1, N'RV03', 120000.0000, 1, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 120000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (12, 1, N'TH', 2000000.0000, 1, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 2000000.0000)
INSERT [dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD], [GiaDV]) VALUES (12, 1, N'TX', 100000.0000, 1, CAST(N'2021-05-12T00:00:00.000' AS DateTime), 100000.0000)
GO
SET IDENTITY_INSERT [dbo].[Phong] ON 

INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (1, N'1-01', 1, 400000.0000, N'YES', N'DOI', 1)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (2, N'1-02', 2, 500000.0000, N'YES', N'DON', 1)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (3, N'1-03', 1, 400000.0000, N'YES', N'DOI', 1)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (4, N'1-04', 2, 500000.0000, N'YES', N'DON', 1)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (5, N'1-05', 4, 700000.0000, N'YES', N'MANY', 1)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (6, N'1-06', 4, 700000.0000, N'NO', N'MANY', 1)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (7, N'2-01', 1, 400000.0000, N'YES', N'DON', 2)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (8, N'2-02', 1, 400000.0000, N'YES', N'DON', 2)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (9, N'2-03', 2, 500000.0000, N'YES', N'DOI', 2)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (10, N'2-04', 2, 500000.0000, N'YES', N'DOI', 2)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (11, N'2-05', 4, 700000.0000, N'YES', N'MANY', 2)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (12, N'2-06', 4, 700000.0000, N'NO', N'MANY', 2)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (13, N'3-01', 1, 400000.0000, N'YES', N'DON', 3)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (14, N'3-02', 1, 400000.0000, N'YES', N'DON', 3)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (15, N'3-03', 2, 500000.0000, N'NO', N'DOI', 3)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (16, N'3-04', 2, 500000.0000, N'NO', N'DOI', 3)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (17, N'3-05', 4, 700000.0000, N'NO', N'MANY', 3)
INSERT [dbo].[Phong] ([MaPhong], [Tenphong], [Songuoi], [DonGia], [Trangthai], [MaLP], [Tang]) VALUES (18, N'3-06', 4, 700000.0000, N'NO', N'MANY', 3)
SET IDENTITY_INSERT [dbo].[Phong] OFF
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD  CONSTRAINT [FK_DichVu_LoaiDV] FOREIGN KEY([MaLoaiDV])
REFERENCES [dbo].[LoaiDV] ([MaLoaiDV])
GO
ALTER TABLE [dbo].[DichVu] CHECK CONSTRAINT [FK_DichVu_LoaiDV]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_Phong] FOREIGN KEY([MaPhong])
REFERENCES [dbo].[Phong] ([MaPhong])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_Phong]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [Relationship14] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [Relationship14]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_CaLamViec] FOREIGN KEY([MaCLV])
REFERENCES [dbo].[CaLamViec] ([MaCLV])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_CaLamViec]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChucVu] FOREIGN KEY([MaCV])
REFERENCES [dbo].[ChucVu] ([MaCV])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChucVu]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatPhong_KHACH] FOREIGN KEY([MaKHACH])
REFERENCES [dbo].[KHACH] ([MaKHACH])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [FK_PhieuDatPhong_KHACH]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatPhong_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [FK_PhieuDatPhong_NhanVien]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatPhong_Phong] FOREIGN KEY([MaPhong])
REFERENCES [dbo].[Phong] ([MaPhong])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [FK_PhieuDatPhong_Phong]
GO
ALTER TABLE [dbo].[PhieuDV]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDV_DichVu] FOREIGN KEY([MaDV])
REFERENCES [dbo].[DichVu] ([MaDV])
GO
ALTER TABLE [dbo].[PhieuDV] CHECK CONSTRAINT [FK_PhieuDV_DichVu]
GO
ALTER TABLE [dbo].[PhieuDV]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDV_Phong] FOREIGN KEY([MaPhong])
REFERENCES [dbo].[Phong] ([MaPhong])
GO
ALTER TABLE [dbo].[PhieuDV] CHECK CONSTRAINT [FK_PhieuDV_Phong]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [FK_Phong_Loaiphong] FOREIGN KEY([MaLP])
REFERENCES [dbo].[Loaiphong] ([MaLP])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [FK_Phong_Loaiphong]
GO
