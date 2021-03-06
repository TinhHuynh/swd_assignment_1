USE [fruit_shop]
GO
/****** Object:  Table [dbo].[category]    Script Date: 2/4/2018 5:06:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[description] [ntext] NULL,
 CONSTRAINT [PK__category__3213E83FC74D2E3F] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[customer]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[first_name] [nvarchar](30) NOT NULL,
	[last_name] [nvarchar](30) NOT NULL,
	[address] [nvarchar](200) NOT NULL,
	[phone] [varchar](20) NOT NULL,
	[email] [varchar](50) NULL,
	[sex] [bit] NULL,
	[join_date] [date] NULL,
	[birth_date] [date] NULL,
 CONSTRAINT [PK__customer__3213E83F16FC7D19] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[order]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_date] [datetime] NULL,
	[staff_id] [int] NULL,
	[customer_id] [int] NULL,
	[status_id] [int] NULL,
	[search_id_key] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[quantity] [float] NOT NULL,
	[price] [money] NOT NULL,
 CONSTRAINT [PK_order_detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[order_status]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[order_status](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK__order_st__3213E83FE612E409] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[product]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[unit] [nvarchar](30) NULL,
	[unit_price] [money] NOT NULL,
	[units_in_stock] [float] NOT NULL,
	[description] [ntext] NULL,
	[category_id] [int] NULL,
	[origin] [nvarchar](100) NULL,
	[available] [bit] NULL,
 CONSTRAINT [PK__product__3213E83FD5EE6B86] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[role]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK__role__3213E83FA46F392F] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[shipping_info]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shipping_info](
	[order_id] [int] NOT NULL,
	[ship_address] [nvarchar](200) NOT NULL,
	[ship_name] [nvarchar](50) NOT NULL,
	[ship_phone] [nvarchar](20) NOT NULL,
	[ship_date] [datetime] NOT NULL,
	[ship_fee] [money] NULL,
 CONSTRAINT [PK__shipping__465962294B702743] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[staff]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[staff](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](30) NOT NULL,
	[password] [varchar](30) NOT NULL,
	[first_name] [nvarchar](30) NOT NULL,
	[last_name] [nvarchar](30) NOT NULL,
	[birth_date] [date] NULL,
	[address] [nvarchar](200) NULL,
	[phone] [varchar](20) NOT NULL,
	[email] [varchar](50) NULL,
	[sex] [bit] NULL,
	[join_date] [date] NULL,
	[salary] [money] NULL,
	[role_id] [int] NOT NULL,
	[on_work] [bit] NULL,
 CONSTRAINT [PK__employee__3213E83F05152C1A] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id], [name], [description]) VALUES (0, N'Unknown', NULL)
INSERT [dbo].[category] ([id], [name], [description]) VALUES (1, N'Berries', N'Small, juicy fruits with thin skins, 
highly perishable')
INSERT [dbo].[category] ([id], [name], [description]) VALUES (2, N'Pits', N'Outer skin covering a soft, fleshy fruit
, the fruit surrounds a single, hard stone, or pit, which contains the seed')
INSERT [dbo].[category] ([id], [name], [description]) VALUES (3, N'Core', N'Central, seed  containing core surrounded by  athick layer of flesh')
INSERT [dbo].[category] ([id], [name], [description]) VALUES (4, N'Citrus Fruits', N'Thick outer rind, 
a thin membrane separates the flesh into segments')
INSERT [dbo].[category] ([id], [name], [description]) VALUES (5, N'Melons', N'Large, juicy fruits with thick skins and many seeds')
INSERT [dbo].[category] ([id], [name], [description]) VALUES (6, N'Tropical Fruits', N'Grown in warm climatesand are considered tobe somewhat exotic, 
available throughout the world')
SET IDENTITY_INSERT [dbo].[category] OFF
SET IDENTITY_INSERT [dbo].[customer] ON 

INSERT [dbo].[customer] ([id], [first_name], [last_name], [address], [phone], [email], [sex], [join_date], [birth_date]) VALUES (0, N'guest', N'', N'', N'', N'', NULL, NULL, NULL)
INSERT [dbo].[customer] ([id], [first_name], [last_name], [address], [phone], [email], [sex], [join_date], [birth_date]) VALUES (1, N'Dung', N'Nguyen Van', N'Toa Nha Gangnam, Quan 7', N'0921234217', N'', 1, CAST(N'2018-01-26' AS Date), CAST(N'1982-02-11' AS Date))
INSERT [dbo].[customer] ([id], [first_name], [last_name], [address], [phone], [email], [sex], [join_date], [birth_date]) VALUES (2, N'Ha', N'Tran Thi', N'203 Quang Trung, Go Vap', N'0917298300', N'hatran210@gmail.com', 0, CAST(N'2018-01-26' AS Date), CAST(N'1978-06-21' AS Date))
INSERT [dbo].[customer] ([id], [first_name], [last_name], [address], [phone], [email], [sex], [join_date], [birth_date]) VALUES (3, N'Hung', N'Dang Thanh', N'22/24 Nguyen Van Luong, Go Vap', N'0927212321', N'', 1, CAST(N'2018-01-26' AS Date), CAST(N'1991-06-06' AS Date))
INSERT [dbo].[customer] ([id], [first_name], [last_name], [address], [phone], [email], [sex], [join_date], [birth_date]) VALUES (4, N'Hai', N'Hoang', N'Ben xe quan 8', N'0921456221', N'', 1, CAST(N'2018-01-27' AS Date), CAST(N'1990-07-19' AS Date))
INSERT [dbo].[customer] ([id], [first_name], [last_name], [address], [phone], [email], [sex], [join_date], [birth_date]) VALUES (5, N'Thao', N'Mai Thi', N'Coopmart Quan Trung, Go vap', N'0905074923', N'thaolun111@gmail.com', 0, CAST(N'2018-02-03' AS Date), CAST(N'1994-05-10' AS Date))
INSERT [dbo].[customer] ([id], [first_name], [last_name], [address], [phone], [email], [sex], [join_date], [birth_date]) VALUES (6, N'Tri', N'Hang Ba', N'Chung cu XL, Nguyen Van Qua, quan 12', N'0985387794', N'hangbatri@gmail.com', 1, CAST(N'2018-02-03' AS Date), CAST(N'1984-08-20' AS Date))
INSERT [dbo].[customer] ([id], [first_name], [last_name], [address], [phone], [email], [sex], [join_date], [birth_date]) VALUES (7, N'Quyen', N'Nguyen Thi Ha', N'S3 Corp, Nguyen Van Troi, Tan Binh', N'0931684315', N'quyennth@s3corp.com.vn', 0, CAST(N'2018-02-03' AS Date), CAST(N'1999-12-09' AS Date))
SET IDENTITY_INSERT [dbo].[customer] OFF
SET IDENTITY_INSERT [dbo].[order] ON 

INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (1, CAST(N'2018-01-24 17:30:00.000' AS DateTime), 2, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (2, CAST(N'2018-01-26 21:52:55.983' AS DateTime), 11, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (3, CAST(N'2018-01-26 22:00:57.433' AS DateTime), 2, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (4, CAST(N'2018-01-26 22:09:35.847' AS DateTime), 2, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (5, CAST(N'2018-01-26 22:55:54.240' AS DateTime), 2, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (6, CAST(N'2018-01-26 22:58:32.970' AS DateTime), 2, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (7, CAST(N'2018-01-26 23:10:21.063' AS DateTime), 11, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (8, CAST(N'2018-01-26 23:12:04.960' AS DateTime), 11, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (9, CAST(N'2018-01-26 23:15:44.197' AS DateTime), 2, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (10, CAST(N'2018-01-26 23:21:59.427' AS DateTime), 11, 0, NULL, NULL)
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (13, CAST(N'2018-01-26 23:47:13.510' AS DateTime), 2, 0, NULL, N'2_20180126234713')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (14, CAST(N'2018-01-26 23:49:18.590' AS DateTime), 2, 0, NULL, N'2_20180126234918')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (15, CAST(N'2018-01-26 23:53:49.860' AS DateTime), 11, 0, NULL, N'11_20180126235349')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (16, CAST(N'2018-01-27 10:41:51.323' AS DateTime), 14, 0, NULL, N'14_20180127104151')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (17, CAST(N'2018-01-27 10:57:24.417' AS DateTime), 14, 0, NULL, N'14_20180127105724')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (18, CAST(N'2018-01-27 11:00:28.680' AS DateTime), 14, 1, NULL, N'14_20180127110028')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (19, CAST(N'2018-01-27 11:02:18.683' AS DateTime), 14, 0, NULL, N'14_20180127110218')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (21, CAST(N'2018-01-27 11:09:55.513' AS DateTime), 14, 2, NULL, N'14_20180127110955')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (22, CAST(N'2018-01-27 22:16:58.130' AS DateTime), 14, 2, NULL, N'14_20180127221658')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (23, CAST(N'2018-01-27 22:24:06.130' AS DateTime), 14, 2, NULL, N'14_20180127222406')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (24, CAST(N'2018-01-27 22:27:09.513' AS DateTime), 14, 0, NULL, N'14_20180127222709')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (25, CAST(N'2018-01-27 22:43:33.690' AS DateTime), 14, 0, NULL, N'14_20180127224333')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (26, CAST(N'2018-01-27 22:44:46.500' AS DateTime), 14, 3, NULL, N'14_20180127224446')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (27, CAST(N'2018-01-27 22:46:11.807' AS DateTime), 14, 0, NULL, N'14_20180127224611')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (28, CAST(N'2018-01-27 22:47:34.783' AS DateTime), 14, 1, NULL, N'14_20180127224734')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (29, CAST(N'2018-01-27 22:48:26.333' AS DateTime), 14, 0, NULL, N'14_20180127224826')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (30, CAST(N'2018-01-27 22:49:53.430' AS DateTime), 14, 1, NULL, N'14_20180127224953')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (33, CAST(N'2018-01-28 09:28:35.667' AS DateTime), 11, 0, NULL, N'11_20180128092835')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (34, CAST(N'2018-01-28 09:31:10.917' AS DateTime), 11, 4, NULL, N'11_20180128093110')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (36, CAST(N'2018-01-28 09:39:05.820' AS DateTime), 11, 0, NULL, N'11_20180128093905')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (37, CAST(N'2018-01-28 09:40:50.647' AS DateTime), 11, 3, NULL, N'11_20180128094050')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (38, CAST(N'2018-01-28 10:08:29.790' AS DateTime), 11, 2, NULL, N'11_20180128100829')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (39, CAST(N'2018-01-28 10:10:31.190' AS DateTime), 11, 4, NULL, N'11_20180128101031')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (40, CAST(N'2018-01-28 10:11:40.893' AS DateTime), 11, 1, NULL, N'11_20180128101140')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (41, CAST(N'2018-01-28 10:13:17.787' AS DateTime), 11, 1, NULL, N'11_20180128101317')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (42, CAST(N'2018-01-28 10:18:50.607' AS DateTime), 11, 2, NULL, N'11_20180128101850')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (43, CAST(N'2018-01-28 10:19:38.913' AS DateTime), 11, 2, NULL, N'11_20180128101938')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (44, CAST(N'2018-01-28 10:22:01.760' AS DateTime), 11, 0, NULL, N'11_20180128102201')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (45, CAST(N'2018-01-29 16:29:08.150' AS DateTime), 2, 0, NULL, N'2_20180129162908')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (46, CAST(N'2018-02-03 11:15:49.427' AS DateTime), 2, 0, NULL, N'2_20180203111549')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (47, CAST(N'2018-02-03 11:17:48.660' AS DateTime), 2, 0, NULL, N'2_20180203111748')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (48, CAST(N'2018-02-03 11:21:17.497' AS DateTime), 11, 0, NULL, N'11_20180203112117')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (49, CAST(N'2018-02-03 19:43:08.237' AS DateTime), 2, 0, NULL, N'2_20180203194308')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (50, CAST(N'2018-02-03 20:09:35.407' AS DateTime), 15, 0, NULL, N'15_20180203200935')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (51, CAST(N'2018-02-03 20:24:24.913' AS DateTime), 11, 0, NULL, N'11_20180203202424')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (52, CAST(N'2018-02-03 20:27:22.983' AS DateTime), 15, 4, NULL, N'15_20180203202722')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (53, CAST(N'2018-02-03 20:31:11.960' AS DateTime), 15, 0, NULL, N'15_20180203203111')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (54, CAST(N'2018-02-03 20:36:30.693' AS DateTime), 15, 6, NULL, N'15_20180203203630')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (55, CAST(N'2018-02-03 21:09:10.217' AS DateTime), 15, 0, NULL, N'15_20180203210910')
INSERT [dbo].[order] ([id], [order_date], [staff_id], [customer_id], [status_id], [search_id_key]) VALUES (56, CAST(N'2018-02-03 21:09:59.750' AS DateTime), 15, 1, NULL, N'15_20180203210959')
SET IDENTITY_INSERT [dbo].[order] OFF
SET IDENTITY_INSERT [dbo].[order_detail] ON 

INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (1, 1, 4, 0.5, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (2, 1, 4, 1, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (3, 1, 6, 0.25, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (4, 1, 13, 0.2, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (10, 2, 13, 0.5, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (12, 2, 14, 0.5, 25000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (13, 3, 10, 0.25, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (14, 3, 9, 0.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (15, 4, 6, 1, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (16, 4, 13, 0.5, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (17, 4, 12, 1, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (18, 4, 12, 1, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (19, 5, 7, 2, 44000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (20, 5, 11, 0.5, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (25, 6, 4, 0.5, 21000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (26, 7, 4, 1, 21000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (27, 7, 8, 1, 27000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (28, 7, 4, 1, 21000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (32, 8, 13, 1, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (36, 8, 14, 1, 25000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (39, 9, 11, 1, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (41, 10, 4, 3, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (43, 13, 8, 1, 27000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (44, 13, 9, 0.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (45, 13, 9, 1.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (46, 14, 10, 0.25, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (47, 14, 7, 0.5, 44000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (48, 15, 4, 0.5, 21000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (49, 15, 6, 1.5, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (50, 15, 7, 1, 44000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (51, 15, 7, 0.5, 44000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (52, 16, 7, 1, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (53, 17, 8, 0.75, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (54, 17, 10, 1, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (55, 17, 4, 0.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (56, 18, 4, 1.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (57, 18, 4, 0.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (58, 19, 9, 0.25, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (59, 21, 9, 1.25, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (60, 22, 8, 1.5, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (61, 23, 6, 0.5, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (62, 24, 10, 1, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (63, 24, 10, 1, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (64, 25, 13, 1.5, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (65, 25, 12, 1, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (66, 26, 11, 2, 25000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (67, 27, 12, 1, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (68, 28, 8, 1, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (69, 28, 4, 0.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (70, 29, 7, 5, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (71, 30, 9, 2, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (72, 33, 4, 0.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (73, 33, 8, 1, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (74, 33, 4, 0.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (75, 34, 8, 0.5, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (76, 34, 11, 2, 25000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (77, 34, 6, 1, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (80, 36, 8, 1.25, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (81, 36, 10, 1, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (82, 37, 4, 2, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (83, 37, 13, 1, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (84, 38, 4, 1, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (85, 38, 13, 1, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (86, 38, 6, 2, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (87, 39, 4, 2, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (88, 39, 12, 2, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (89, 40, 10, 5, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (90, 41, 8, 3, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (91, 42, 4, 12, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (92, 43, 7, 1, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (93, 43, 13, 3, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (94, 44, 6, 2, 35000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (95, 45, 18, 2, 23000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (96, 46, 6, 1, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (97, 47, 14, 0.5, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (98, 47, 12, 0.40000000596046448, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (99, 48, 4, 1, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (100, 49, 14, 1.25, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (101, 49, 8, 0.75, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (102, 50, 9, 1.5, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (103, 50, 10, 2, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (104, 51, 10, 2, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (105, 51, 13, 1.3999999761581421, 20000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (106, 51, 4, 1, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (107, 51, 9, 1.25, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (108, 52, 8, 12, 14000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (109, 53, 21, 1, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (110, 53, 20, 2, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (111, 53, 9, 1, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (112, 54, 22, 2, 26000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (113, 54, 6, 1.2000000476837158, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (114, 54, 10, 2, 28000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (115, 55, 6, 1, 30000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (116, 55, 22, 1.6000000238418579, 26000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (117, 56, 19, 2.7000000476837158, 15000.0000)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity], [price]) VALUES (118, 56, 23, 1.4500000476837158, 15000.0000)
SET IDENTITY_INSERT [dbo].[order_detail] OFF
SET IDENTITY_INSERT [dbo].[order_status] ON 

INSERT [dbo].[order_status] ([id], [name]) VALUES (1, N'On Processing')
INSERT [dbo].[order_status] ([id], [name]) VALUES (2, N'Shipping')
INSERT [dbo].[order_status] ([id], [name]) VALUES (3, N'Checked Out')
SET IDENTITY_INSERT [dbo].[order_status] OFF
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (4, N'Chuoi gia', N'kg', 15000.0000, 21, N'', 6, N'Tien Giang', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (6, N'Tao Gala', N'kg', 30000.0000, 39.299999952316284, N'', 3, N'', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (7, N'Cam ngot', N'kg', 30000.0000, 26, N'', 4, N'', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (8, N'Dua hau khong hat', N'kg', 14000.0000, 28.25, N'', 5, N'Tiền Giang, Vĩnh Long', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (9, N'Du Du', N'kg', 15000.0000, 90.75, N'', 5, N'Tiền Giang', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (10, N'Chanh Day', N'kg', 28000.0000, 34.75, N'', 5, N'', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (11, N'Xoai Cat', N'kg', 25000.0000, 46, N'', 3, N'Cần Giờ, Cần Thơ', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (12, N'Dau Tay Da Lat', N'kg', 30000.0000, 45.599999994039536, N'', 1, N'Da Lat', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (13, N'Nho Tim', N'kg', 20000.0000, 42.100000023841858, N'', 1, N'', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (14, N'Nho xanh', N'kg', 20000.0000, 48.25, N'', 1, N'', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (18, N'Chom Chom', N'kg', 23000.0000, 48, N'', 2, N'Tien Giang', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (19, N'Dua Hau Vang', N'kg', 15000.0000, 37.299999952316284, N'', 5, N'', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (20, N'Vu sua Lo Ren', N'kg', 30000.0000, 48, N'', 3, N'Tien Giang', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (21, N'Cam sanh', N'kg', 15000.0000, 49, N'', 4, N'', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (22, N'Sau rieng', N'kg', 26000.0000, 16.399999976158142, N'', 0, N'', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (23, N'Sen Dong Thap', N'kg', 15000.0000, 18.549999952316284, N'', 0, N'Dong Thap', 1)
INSERT [dbo].[product] ([id], [name], [unit], [unit_price], [units_in_stock], [description], [category_id], [origin], [available]) VALUES (24, N'Chuoi Xiem', N'kg', 15000.0000, 30, N'', 6, N'Dak Lak', 1)
SET IDENTITY_INSERT [dbo].[product] OFF
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([id], [name]) VALUES (1, N'Admin')
INSERT [dbo].[role] ([id], [name]) VALUES (2, N'Clerk')
SET IDENTITY_INSERT [dbo].[role] OFF
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (23, N'203 Quang Trung, Go Vap', N'Tran Thi Ha', N'0917298300', CAST(N'2018-01-27 00:00:00.000' AS DateTime), 20000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (24, N'102 Thong Nhat, Go vap', N'Anh Tinh', N'0922130984', CAST(N'2018-01-27 00:00:00.000' AS DateTime), 15000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (25, N'23 Pham Van Chieu, Go Vap', N'Mr.Hieu', N'0212111032', CAST(N'2018-01-27 00:00:00.000' AS DateTime), 21000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (30, N'Cong vien gia dinh', N'Nguyen Van Dung', N'0921234217', CAST(N'2018-01-27 22:49:53.430' AS DateTime), 15000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (36, N'dai hoc FPT, quan 12', N'Tinh', N'0922466313', CAST(N'2018-01-28 09:39:05.817' AS DateTime), 15000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (37, N'22/24 Nguyen Van Luong, Go Vap', N'Nguyen Thi Ha', N'0921456213', CAST(N'2018-01-28 09:40:50.647' AS DateTime), 12000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (39, N'Ben xe quan 8', N'Hoang Hai', N'0921456221', CAST(N'2018-01-28 00:00:00.000' AS DateTime), 26000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (40, N'Toa Nha Gangnam, Quan 7', N'Nguyen Van Dung', N'0921234217', CAST(N'2018-01-28 00:00:00.000' AS DateTime), 15000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (42, N'203 Quang Trung, Go Vap', N'Tran Thi Ha', N'0917298300', CAST(N'2018-01-28 00:00:00.000' AS DateTime), 24000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (44, N'Cho cau, go vap', N'Huynh Van Phuc', N'0926437255', CAST(N'2018-02-01 00:00:00.000' AS DateTime), 16000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (49, N'Nga tu ga', N'Hung', N'0921543621', CAST(N'2018-02-03 00:00:00.000' AS DateTime), 12000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (50, N'Nga 5 chuong cho, go vap', N'Anh Thinh', N'0946219421', CAST(N'2018-02-03 00:00:00.000' AS DateTime), 20000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (51, N'98 Phan Xich Long', N'anh Ngan', N'0905786451', CAST(N'2018-02-03 00:00:00.000' AS DateTime), 25000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (52, N'Ben xe quan 8', N'Hoang Hai', N'0921456221', CAST(N'2018-02-04 00:00:00.000' AS DateTime), 25000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (53, N'Nha Hang Nam Long, Tan Phu', N'chi Hong', N'0957214291', CAST(N'2018-02-04 00:00:00.000' AS DateTime), 20000.0000)
INSERT [dbo].[shipping_info] ([order_id], [ship_address], [ship_name], [ship_phone], [ship_date], [ship_fee]) VALUES (54, N'Chung cu XL, Nguyen Van Qua, quan 12', N'Hang Ba Tri', N'0985387794', CAST(N'2018-02-03 00:00:00.000' AS DateTime), 17000.0000)
SET IDENTITY_INSERT [dbo].[staff] ON 

INSERT [dbo].[staff] ([id], [username], [password], [first_name], [last_name], [birth_date], [address], [phone], [email], [sex], [join_date], [salary], [role_id], [on_work]) VALUES (2, N'admin', N'admin', N'Tinh', N'Huynh', CAST(N'1997-04-13' AS Date), N'Quận Gò Vấp', N'0211234211', NULL, 1, CAST(N'2018-01-18' AS Date), NULL, 1, 1)
INSERT [dbo].[staff] ([id], [username], [password], [first_name], [last_name], [birth_date], [address], [phone], [email], [sex], [join_date], [salary], [role_id], [on_work]) VALUES (11, N'thuthao', N'thuthao', N'Thao', N'Nguyen Thi', CAST(N'1999-06-12' AS Date), N'quan 10', N'0976123023', N'', 0, CAST(N'2018-01-18' AS Date), 6500000.0000, 2, 1)
INSERT [dbo].[staff] ([id], [username], [password], [first_name], [last_name], [birth_date], [address], [phone], [email], [sex], [join_date], [salary], [role_id], [on_work]) VALUES (14, N'phatdat', N'phatdat', N'Phat', N'Huynh Tan', CAST(N'1995-10-21' AS Date), N'Tô Ký, quận 12', N'0992276109', NULL, 1, CAST(N'2018-01-18' AS Date), 5000000.0000, 2, 1)
INSERT [dbo].[staff] ([id], [username], [password], [first_name], [last_name], [birth_date], [address], [phone], [email], [sex], [join_date], [salary], [role_id], [on_work]) VALUES (15, N'huyenmy', N'huyenmy', N'My', N'Nguyen Thi Huyen', CAST(N'1996-09-20' AS Date), N'Nga tu ga, quan 12', N'0928356324', N'', 0, CAST(N'2018-01-31' AS Date), 5000000.0000, 2, 1)
INSERT [dbo].[staff] ([id], [username], [password], [first_name], [last_name], [birth_date], [address], [phone], [email], [sex], [join_date], [salary], [role_id], [on_work]) VALUES (16, N'namtran', N'namtran', N'Nam', N'Tran', CAST(N'1996-07-15' AS Date), N'An Suong, Quan 12', N'0927316392', N'', 1, CAST(N'2018-01-31' AS Date), 5500000.0000, 2, 1)
SET IDENTITY_INSERT [dbo].[staff] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__customer__B43B145F5CE4B304]    Script Date: 2/4/2018 5:06:51 PM ******/
ALTER TABLE [dbo].[customer] ADD  CONSTRAINT [UQ__customer__B43B145F5CE4B304] UNIQUE NONCLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__employee__B43B145FF2664543]    Script Date: 2/4/2018 5:06:51 PM ******/
ALTER TABLE [dbo].[staff] ADD  CONSTRAINT [UQ__employee__B43B145FF2664543] UNIQUE NONCLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__employee__F3DBC5728627B7D2]    Script Date: 2/4/2018 5:06:51 PM ******/
ALTER TABLE [dbo].[staff] ADD  CONSTRAINT [UQ__employee__F3DBC5728627B7D2] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK__order__customer___4F7CD00D] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK__order__customer___4F7CD00D]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK__order__employee___4E88ABD4] FOREIGN KEY([staff_id])
REFERENCES [dbo].[staff] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK__order__employee___4E88ABD4]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD FOREIGN KEY([status_id])
REFERENCES [dbo].[order_status] ([id])
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK__order_det__order__6B24EA82] FOREIGN KEY([order_id])
REFERENCES [dbo].[order] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK__order_det__order__6B24EA82]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK__order_det__produ__6A30C649] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK__order_det__produ__6A30C649]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK__order_det__produ__6C190EBB] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK__order_det__produ__6C190EBB]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK__product__categor__60A75C0F] FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK__product__categor__60A75C0F]
GO
ALTER TABLE [dbo].[shipping_info]  WITH CHECK ADD  CONSTRAINT [FK__shipping___order__5441852A] FOREIGN KEY([order_id])
REFERENCES [dbo].[order] ([id])
GO
ALTER TABLE [dbo].[shipping_info] CHECK CONSTRAINT [FK__shipping___order__5441852A]
GO
ALTER TABLE [dbo].[staff]  WITH CHECK ADD  CONSTRAINT [FK__employee__role_i__3F466844] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[staff] CHECK CONSTRAINT [FK__employee__role_i__3F466844]
GO
/****** Object:  StoredProcedure [dbo].[usp_printOrderByOrderId]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[usp_printOrderByOrderId]
@order_id INT
AS
BEGIN

DECLARE @order_date DATETIME
DECLARE @staff_first_name NVARCHAR(20)
DECLARE @staff_last_name NVARCHAR(20)
DECLARE @customer_first_name NVARCHAR(20)
DECLARE @customer_last_name NVARCHAR(20)
DECLARE @total_money FLOAT

-- print basic information --
SELECT TOP 1  
		@order_date = order_date,
		@staff_first_name = staff.first_name,
		@staff_last_name = staff.last_name,
		@customer_first_name = customer.first_name,
		@customer_last_name = customer.last_name,
		@total_money = SUM(order_detail.quantity*order_detail.price)
FROM [order]
JOIN staff ON staff.id = staff_id
JOIN customer ON customer.id = customer_id
JOIN order_detail ON order_detail.order_id = [order].id
WHERE [order].id = @order_id
GROUP BY [order].id, order_date, staff.first_name, staff.last_name, customer.first_name, customer.last_name

PRINT N'Order date: ' + CONVERT(VARCHAR(19), @order_date, 120);
PRINT N'Staff: ' + @staff_last_name + ' ' + @staff_first_name;  
PRINT N'Customer: ' + @customer_last_name + ' ' + @customer_first_name;

-- print detail --
PRINT N'=========================================================================================================================='
PRINT N'Product name                 Quantity           Price           Money'

PRINT N'=========================================================================================================================='

DECLARE @product_name NVARCHAR(100)
DECLARE @quantity FLOAT
DECLARE @price FLOAT
DECLARE @detail_total_money FLOAT
DECLARE @order_detail_id INT

DECLARE DetailCursor CURSOR FOR
SELECT 
	product.name, 
	quantity, 
	price, 
	SUM(quantity * price),
	order_detail.id
FROM order_detail
JOIN [order] ON order_detail.order_id = [order].id
JOIN [product] ON product.id = product_id
WHERE [order].id = @order_id
GROUP BY product.name, quantity, price, order_detail.id

OPEN DetailCursor
FETCH NEXT FROM DetailCursor INTO @product_name, @quantity, @price, @detail_total_money, @order_detail_id
	WHILE @@FETCH_STATUS = 0
		BEGIN
		PRINT @product_name + '                 ' + CAST(@quantity AS VARCHAR) + '           ' + CAST(@price AS VARCHAR) + '           ' + CAST(@detail_total_money AS VARCHAR);   
		FETCH NEXT FROM DetailCursor INTO @product_name, @quantity, @price, @detail_total_money, @order_detail_id
		END
CLOSE DetailCursor
DEALLOCATE DetailCursor


-- print total money --
PRINT N'--------------------------------------------------------------------------------------------------------------------------'
PRINT N'Total money: ' + CAST(@total_money AS VARCHAR); 
  

END

GO
/****** Object:  StoredProcedure [dbo].[uspGet3CustomersBoughtMostOrdersBydates]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*

*/
CREATE PROCEDURE [dbo].[uspGet3CustomersBoughtMostOrdersBydates]
@from_date DATETIME = '2018-1-24',
@to_date DATETIME = '2018-1-29'

AS
SELECT TOP 3 customer_id, customer.first_name, customer.last_name, COUNT([order].id) AS num_of_order
FROM [order]
JOIN customer ON customer.id = customer_id
WHERE order_date >= @from_date AND order_date <= @to_date
GROUP BY customer_id, customer.first_name, customer.last_name
HAVING customer_id != 0
ORDER BY COUNT([order].id) DESC

GO
/****** Object:  StoredProcedure [dbo].[uspGet3CustomersBoughtMostQuantitiesBydates]    Script Date: 2/4/2018 5:06:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
  2.Get 3 customers who bought the most quantities by dates
*/
CREATE PROCEDURE [dbo].[uspGet3CustomersBoughtMostQuantitiesBydates]
@from_date DATETIME = '2018-1-24',
@to_date DATETIME = '2018-1-29'

AS
SELECT TOP 3 customer.id, customer.first_name, customer.last_name, SUM(quantity) AS num_of_order
FROM [order_detail]
JOIN [order] ON [order].customer_id = order_id 
JOIN customer ON customer.id = [order].customer_id
WHERE order_date >= @from_date AND order_date <= @to_date
GROUP BY customer.id, customer.first_name, customer.last_name
HAVING customer.id != 0
ORDER BY COUNT([order].id) DESC

GO
