USE [master]
GO
/****** Object:  User [##MS_PolicyEventProcessingLogin##]    Script Date: 03/03/2023 19:38:56 ******/
CREATE USER [##MS_PolicyEventProcessingLogin##] FOR LOGIN [##MS_PolicyEventProcessingLogin##] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [##MS_AgentSigningCertificate##]    Script Date: 03/03/2023 19:38:56 ******/
CREATE USER [##MS_AgentSigningCertificate##] FOR LOGIN [##MS_AgentSigningCertificate##]
GO
/****** Object:  UserDefinedDataType [dbo].[DYDIS]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[DYDIS] FROM [varchar](10) NULL
GO
/****** Object:  UserDefinedDataType [dbo].[GAMINTOJAS]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[GAMINTOJAS] FROM [varchar](20) NULL
GO
/****** Object:  UserDefinedDataType [dbo].[ID]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[ID] FROM [numeric](7, 0) NULL
GO
/****** Object:  UserDefinedDataType [dbo].[KAINA]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[KAINA] FROM [money] NULL
GO
/****** Object:  UserDefinedDataType [dbo].[MODELIS]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[MODELIS] FROM [varchar](25) NULL
GO
/****** Object:  UserDefinedDataType [dbo].[PAVARDE_VARDAS]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[PAVARDE_VARDAS] FROM [varchar](40) NULL
GO
/****** Object:  UserDefinedDataType [dbo].[SERIJINIS_NR]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[SERIJINIS_NR] FROM [varchar](8) NULL
GO
/****** Object:  UserDefinedDataType [dbo].[TALPA]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[TALPA] FROM [numeric](7, 0) NULL
GO
/****** Object:  UserDefinedDataType [dbo].[TELEFONO_NR]    Script Date: 03/03/2023 19:38:56 ******/
CREATE TYPE [dbo].[TELEFONO_NR] FROM [varchar](15) NULL
GO
/****** Object:  Table [dbo].[Darbuotojas]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Darbuotojas](
	[Darbuotojo_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Darbuotojo_Pavarde_Vardas] [dbo].[PAVARDE_VARDAS] NOT NULL,
	[Telefono_Nr] [dbo].[TELEFONO_NR] NOT NULL,
 CONSTRAINT [PK_DARBUOTOJAS] PRIMARY KEY CLUSTERED 
(
	[Darbuotojo_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_DARBUOTOJO_PAVARDE_DARBUOTO] UNIQUE NONCLUSTERED 
(
	[Darbuotojo_Pavarde_Vardas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Kietasis_diskas]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Kietasis_diskas](
	[Disko_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Jungtis] [varchar](7) NOT NULL,
	[Greitis] [numeric](5, 0) NOT NULL,
	[Talpa] [dbo].[TALPA] NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
	[Modelis] [dbo].[MODELIS] NOT NULL,
	[Tipas] [varchar](3) NOT NULL,
 CONSTRAINT [PK_KIETASIS_DISKAS] PRIMARY KEY CLUSTERED 
(
	[Disko_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Klientas]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Klientas](
	[Kliento_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[El.Pastas] [varchar](30) NOT NULL,
	[Telefono_Nr] [dbo].[TELEFONO_NR] NOT NULL,
	[Kliento_Pavarde_Vardas] [dbo].[PAVARDE_VARDAS] NOT NULL,
 CONSTRAINT [PK_KLIENTAS] PRIMARY KEY CLUSTERED 
(
	[Kliento_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_KLIENTO_PAVARDE_VA_KLIENTAS] UNIQUE NONCLUSTERED 
(
	[Kliento_Pavarde_Vardas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Kompiuteris]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Kompiuteris](
	[Kompiuterio_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Uzsakymo_ID] [numeric](7, 0) NOT NULL,
	[Procesoriaus_ID] [numeric](7, 0) NOT NULL,
	[Bloko_ID] [numeric](7, 0) NOT NULL,
	[Darbuotojo_ID] [numeric](7, 0) NOT NULL,
	[Korpuso_ID] [numeric](7, 0) NOT NULL,
	[Ausintuvo_ID] [numeric](7, 0) NULL,
	[Plokstes_ID] [numeric](7, 0) NOT NULL,
	[Procesoriaus_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
	[Procesoriaus_ausintuvo_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
	[Plokstes_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
	[Korpuso_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
	[Bloko_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
 CONSTRAINT [PK_KOMPIUTERIS] PRIMARY KEY CLUSTERED 
(
	[Kompiuterio_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Korpusas]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Korpusas](
	[Korpuso_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Dydis] [dbo].[DYDIS] NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
	[Modelis] [dbo].[MODELIS] NOT NULL,
 CONSTRAINT [PK_KORPUSAS] PRIMARY KEY CLUSTERED 
(
	[Korpuso_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Maitinimo_blokas]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Maitinimo_blokas](
	[Bloko_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Maitinimo_galia] [numeric](5, 0) NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
	[Modelis] [dbo].[MODELIS] NULL,
 CONSTRAINT [PK_MAITINIMO_BLOKAS] PRIMARY KEY CLUSTERED 
(
	[Bloko_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Operatyvioji_atmintis]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Operatyvioji_atmintis](
	[Atminties_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Talpa] [dbo].[TALPA] NOT NULL,
	[Technologija] [varchar](7) NOT NULL,
	[Taktinis_daznis] [numeric](7, 0) NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
	[Modelis] [dbo].[MODELIS] NOT NULL,
 CONSTRAINT [PK_OPERATYVIOJI_ATMINTIS] PRIMARY KEY CLUSTERED 
(
	[Atminties_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pagrindine_plokste]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pagrindine_plokste](
	[Plokstes_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Procesoriaus_lizdo_tipas] [varchar](15) NOT NULL,
	[Lustu_rinkinys] [varchar](5) NOT NULL,
	[Dydis] [dbo].[DYDIS] NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
	[Modelis] [dbo].[MODELIS] NOT NULL,
 CONSTRAINT [PK_PAGRINDINE_PLOKSTE] PRIMARY KEY CLUSTERED 
(
	[Plokstes_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Procesoriaus_ausintuvas]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Procesoriaus_ausintuvas](
	[Ausintuvo_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Modelis] [dbo].[MODELIS] NOT NULL,
	[Ausinimo_budas] [varchar](7) NOT NULL,
	[Procesoriaus_lizdo_tipas] [varchar](15) NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
 CONSTRAINT [PK_PROCESORIAUS_AUSINTUVAS] PRIMARY KEY CLUSTERED 
(
	[Ausintuvo_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Procesorius]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Procesorius](
	[Procesoriaus_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Modelis] [dbo].[MODELIS] NOT NULL,
	[Procesoriaus_jungtis] [varchar](15) NOT NULL,
	[Procesoriaus_daznis] [numeric](5, 0) NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
 CONSTRAINT [PK_PROCESORIUS] PRIMARY KEY CLUSTERED 
(
	[Procesoriaus_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sudarymas_3]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sudarymas_3](
	[Atminties_ID] [numeric](7, 0) NOT NULL,
	[Kompiuterio_ID] [numeric](7, 0) NOT NULL,
	[Atminties_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
 CONSTRAINT [PK_Sudarymas_3_1] PRIMARY KEY CLUSTERED 
(
	[Atminties_Serijinis_Nr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sudarymas_4]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sudarymas_4](
	[Ventiliatoriaus_ID] [numeric](7, 0) NOT NULL,
	[Kompiuterio_ID] [numeric](7, 0) NOT NULL,
	[Ventiliatoriaus_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
 CONSTRAINT [PK_Sudarymas_4_1] PRIMARY KEY CLUSTERED 
(
	[Ventiliatoriaus_Serijinis_Nr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sudarymas_5]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sudarymas_5](
	[Disko_ID] [numeric](7, 0) NOT NULL,
	[Kompiuterio_ID] [numeric](7, 0) NOT NULL,
	[Disko_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
 CONSTRAINT [PK_Sudarymas_5_1] PRIMARY KEY CLUSTERED 
(
	[Disko_Serijinis_Nr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sudarymas_6]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sudarymas_6](
	[Vaizdo_plokstes_ID] [numeric](7, 0) NOT NULL,
	[Kompiuterio_ID] [numeric](7, 0) NOT NULL,
	[Vaizdo_plokstes_Serijinis_Nr] [dbo].[SERIJINIS_NR] NOT NULL,
 CONSTRAINT [PK_Sudarymas_6_1] PRIMARY KEY CLUSTERED 
(
	[Vaizdo_plokstes_Serijinis_Nr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Uzsakymas]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Uzsakymas](
	[Uzsakymo_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Kliento_ID] [numeric](7, 0) NULL,
	[Darbuotojo_ID] [numeric](7, 0) NOT NULL,
	[Kli_Kliento_ID] [numeric](7, 0) NOT NULL,
	[Prisatymo_adresas] [varchar](50) NOT NULL,
	[Apmokejimo_budas] [varchar](10) NOT NULL,
	[Sumoketa] [bit] NOT NULL,
	[Pristatyta] [bit] NOT NULL,
 CONSTRAINT [PK_UZSAKYMAS] PRIMARY KEY CLUSTERED 
(
	[Uzsakymo_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Vaizdo_plokste]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vaizdo_plokste](
	[Vaizdo_plokstes_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Talpa] [dbo].[TALPA] NOT NULL,
	[Greitis] [numeric](5, 0) NOT NULL,
	[Daznis] [numeric](5, 0) NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
	[Modelis] [dbo].[MODELIS] NOT NULL,
 CONSTRAINT [PK_VAIZDO_PLOKSTE] PRIMARY KEY CLUSTERED 
(
	[Vaizdo_plokstes_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ventiliatorius]    Script Date: 03/03/2023 19:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ventiliatorius](
	[Ventiliatoriaus_ID] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Gamintojas] [dbo].[GAMINTOJAS] NOT NULL,
	[Kaina] [dbo].[KAINA] NOT NULL,
	[Ventiliatoriaus_dydis] [numeric](5, 0) NOT NULL,
	[Modelis] [dbo].[MODELIS] NOT NULL,
 CONSTRAINT [PK_VENTILIATORIUS] PRIMARY KEY CLUSTERED 
(
	[Ventiliatoriaus_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Gamintojas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Gamintojas_AK] ON [dbo].[Kietasis_diskas]
(
	[Gamintojas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Talpa_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Talpa_AK] ON [dbo].[Kietasis_diskas]
(
	[Talpa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [El.Pastas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [El.Pastas_AK] ON [dbo].[Klientas]
(
	[El.Pastas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Ausintuvas_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Ausintuvas_FK] ON [dbo].[Kompiuteris]
(
	[Ausintuvo_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Blokas_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Blokas_FK] ON [dbo].[Kompiuteris]
(
	[Bloko_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Bloko_Serijinis_Nr_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Bloko_Serijinis_Nr_AK] ON [dbo].[Kompiuteris]
(
	[Bloko_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Darbuotojas_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Darbuotojas_FK] ON [dbo].[Kompiuteris]
(
	[Darbuotojo_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Korpusas_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Korpusas_FK] ON [dbo].[Kompiuteris]
(
	[Korpuso_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Korpuso_Serijinis_Nr_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Korpuso_Serijinis_Nr_AK] ON [dbo].[Kompiuteris]
(
	[Korpuso_Serijinis_Nr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Plokste_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Plokste_FK] ON [dbo].[Kompiuteris]
(
	[Plokstes_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Plokstes_Serijinis_Nr_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Plokstes_Serijinis_Nr_AK] ON [dbo].[Kompiuteris]
(
	[Plokstes_Serijinis_Nr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Procesoriaus_ausintuvo_Serijinis_Nr_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Procesoriaus_ausintuvo_Serijinis_Nr_AK] ON [dbo].[Kompiuteris]
(
	[Procesoriaus_ausintuvo_Serijinis_Nr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Procesoriaus_Serijinis_Nr_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Procesoriaus_Serijinis_Nr_AK] ON [dbo].[Kompiuteris]
(
	[Procesoriaus_Serijinis_Nr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Procesorius_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Procesorius_FK] ON [dbo].[Kompiuteris]
(
	[Procesoriaus_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Uzsakymas_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Uzsakymas_FK] ON [dbo].[Kompiuteris]
(
	[Uzsakymo_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Dydis_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Dydis_AK] ON [dbo].[Korpusas]
(
	[Dydis] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Gamintojas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Gamintojas_AK] ON [dbo].[Korpusas]
(
	[Gamintojas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Gamintojas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Gamintojas_AK] ON [dbo].[Maitinimo_blokas]
(
	[Gamintojas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Maitinimo_galia_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Maitinimo_galia_AK] ON [dbo].[Maitinimo_blokas]
(
	[Maitinimo_galia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Gamintojas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Gamintojas_AK] ON [dbo].[Operatyvioji_atmintis]
(
	[Gamintojas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Talpa_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Talpa_AK] ON [dbo].[Operatyvioji_atmintis]
(
	[Talpa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Technologija_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Technologija_AK] ON [dbo].[Operatyvioji_atmintis]
(
	[Technologija] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Gamintojas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Gamintojas_AK] ON [dbo].[Pagrindine_plokste]
(
	[Gamintojas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Procesoriaus_lizdo_tipas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Procesoriaus_lizdo_tipas_AK] ON [dbo].[Pagrindine_plokste]
(
	[Procesoriaus_lizdo_tipas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Ausinimo_budas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Ausinimo_budas_AK] ON [dbo].[Procesoriaus_ausintuvas]
(
	[Ausinimo_budas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Gamintojas_Modelis_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Gamintojas_Modelis_AK] ON [dbo].[Procesoriaus_ausintuvas]
(
	[Gamintojas] ASC,
	[Modelis] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Modelis_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Modelis_AK] ON [dbo].[Procesorius]
(
	[Modelis] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Atmintis_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Atmintis_FK] ON [dbo].[Sudarymas_3]
(
	[Atminties_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Kompiuteris_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Kompiuteris_FK] ON [dbo].[Sudarymas_3]
(
	[Kompiuterio_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Kompiuteris_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Kompiuteris_FK] ON [dbo].[Sudarymas_4]
(
	[Kompiuterio_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Ventiliatorius_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Ventiliatorius_FK] ON [dbo].[Sudarymas_4]
(
	[Ventiliatoriaus_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Diskas_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Diskas_FK] ON [dbo].[Sudarymas_5]
(
	[Disko_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Kompiuteris_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Kompiuteris_FK] ON [dbo].[Sudarymas_5]
(
	[Kompiuterio_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Kompiuteris_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Kompiuteris_FK] ON [dbo].[Sudarymas_6]
(
	[Kompiuterio_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Vaizdo_plokste_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Vaizdo_plokste_FK] ON [dbo].[Sudarymas_6]
(
	[Vaizdo_plokstes_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Darbuotojas_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Darbuotojas_FK] ON [dbo].[Uzsakymas]
(
	[Darbuotojo_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Klientas_FK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Klientas_FK] ON [dbo].[Uzsakymas]
(
	[Kliento_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Gamintojas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Gamintojas_AK] ON [dbo].[Vaizdo_plokste]
(
	[Gamintojas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Talpa_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Talpa_AK] ON [dbo].[Vaizdo_plokste]
(
	[Talpa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Dydis_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Dydis_AK] ON [dbo].[Ventiliatorius]
(
	[Ventiliatoriaus_dydis] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Gamintojas_AK]    Script Date: 03/03/2023 19:38:56 ******/
CREATE NONCLUSTERED INDEX [Gamintojas_AK] ON [dbo].[Ventiliatorius]
(
	[Gamintojas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Kompiuteris]  WITH CHECK ADD  CONSTRAINT [FK_KOMPIUTE_AUSINTUVA_PROCESOR] FOREIGN KEY([Ausintuvo_ID])
REFERENCES [dbo].[Procesoriaus_ausintuvas] ([Ausintuvo_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Kompiuteris] CHECK CONSTRAINT [FK_KOMPIUTE_AUSINTUVA_PROCESOR]
GO
ALTER TABLE [dbo].[Kompiuteris]  WITH CHECK ADD  CONSTRAINT [FK_KOMPIUTE_BLOKAS-KO_MAITINIM] FOREIGN KEY([Bloko_ID])
REFERENCES [dbo].[Maitinimo_blokas] ([Bloko_ID])
GO
ALTER TABLE [dbo].[Kompiuteris] CHECK CONSTRAINT [FK_KOMPIUTE_BLOKAS-KO_MAITINIM]
GO
ALTER TABLE [dbo].[Kompiuteris]  WITH CHECK ADD  CONSTRAINT [FK_KOMPIUTE_KORPUSAS-_KORPUSAS] FOREIGN KEY([Korpuso_ID])
REFERENCES [dbo].[Korpusas] ([Korpuso_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Kompiuteris] CHECK CONSTRAINT [FK_KOMPIUTE_KORPUSAS-_KORPUSAS]
GO
ALTER TABLE [dbo].[Kompiuteris]  WITH CHECK ADD  CONSTRAINT [FK_KOMPIUTE_PLOKSTE-K_PAGRINDI] FOREIGN KEY([Plokstes_ID])
REFERENCES [dbo].[Pagrindine_plokste] ([Plokstes_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Kompiuteris] CHECK CONSTRAINT [FK_KOMPIUTE_PLOKSTE-K_PAGRINDI]
GO
ALTER TABLE [dbo].[Kompiuteris]  WITH CHECK ADD  CONSTRAINT [FK_KOMPIUTE_PROCESORI_PROCESOR] FOREIGN KEY([Procesoriaus_ID])
REFERENCES [dbo].[Procesorius] ([Procesoriaus_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Kompiuteris] CHECK CONSTRAINT [FK_KOMPIUTE_PROCESORI_PROCESOR]
GO
ALTER TABLE [dbo].[Kompiuteris]  WITH CHECK ADD  CONSTRAINT [FK_KOMPIUTE_SUDARO_UZSAKYMA] FOREIGN KEY([Uzsakymo_ID])
REFERENCES [dbo].[Uzsakymas] ([Uzsakymo_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Kompiuteris] CHECK CONSTRAINT [FK_KOMPIUTE_SUDARO_UZSAKYMA]
GO
ALTER TABLE [dbo].[Kompiuteris]  WITH CHECK ADD  CONSTRAINT [FK_KOMPIUTE_SURENKA_DARBUOTO] FOREIGN KEY([Darbuotojo_ID])
REFERENCES [dbo].[Darbuotojas] ([Darbuotojo_ID])
GO
ALTER TABLE [dbo].[Kompiuteris] CHECK CONSTRAINT [FK_KOMPIUTE_SURENKA_DARBUOTO]
GO
ALTER TABLE [dbo].[Sudarymas_3]  WITH CHECK ADD  CONSTRAINT [FK_SUDARYMA_ATMINTIS-_KOMPIUTE] FOREIGN KEY([Kompiuterio_ID])
REFERENCES [dbo].[Kompiuteris] ([Kompiuterio_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Sudarymas_3] CHECK CONSTRAINT [FK_SUDARYMA_ATMINTIS-_KOMPIUTE]
GO
ALTER TABLE [dbo].[Sudarymas_3]  WITH CHECK ADD  CONSTRAINT [FK_SUDARYMA_SUDARYMAS_OPERATYV] FOREIGN KEY([Atminties_ID])
REFERENCES [dbo].[Operatyvioji_atmintis] ([Atminties_ID])
GO
ALTER TABLE [dbo].[Sudarymas_3] CHECK CONSTRAINT [FK_SUDARYMA_SUDARYMAS_OPERATYV]
GO
ALTER TABLE [dbo].[Sudarymas_4]  WITH CHECK ADD  CONSTRAINT [FK_SUDARYMA_SUDARYMAS_VENTILIA] FOREIGN KEY([Ventiliatoriaus_ID])
REFERENCES [dbo].[Ventiliatorius] ([Ventiliatoriaus_ID])
GO
ALTER TABLE [dbo].[Sudarymas_4] CHECK CONSTRAINT [FK_SUDARYMA_SUDARYMAS_VENTILIA]
GO
ALTER TABLE [dbo].[Sudarymas_4]  WITH CHECK ADD  CONSTRAINT [FK_SUDARYMA_VENTILIAT_KOMPIUTE] FOREIGN KEY([Kompiuterio_ID])
REFERENCES [dbo].[Kompiuteris] ([Kompiuterio_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Sudarymas_4] CHECK CONSTRAINT [FK_SUDARYMA_VENTILIAT_KOMPIUTE]
GO
ALTER TABLE [dbo].[Sudarymas_5]  WITH CHECK ADD  CONSTRAINT [FK_SUDARYMA_DISKAS-KO_KOMPIUTE] FOREIGN KEY([Kompiuterio_ID])
REFERENCES [dbo].[Kompiuteris] ([Kompiuterio_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Sudarymas_5] CHECK CONSTRAINT [FK_SUDARYMA_DISKAS-KO_KOMPIUTE]
GO
ALTER TABLE [dbo].[Sudarymas_5]  WITH CHECK ADD  CONSTRAINT [FK_SUDARYMA_SUDARYMAS_KIETASIS] FOREIGN KEY([Disko_ID])
REFERENCES [dbo].[Kietasis_diskas] ([Disko_ID])
GO
ALTER TABLE [dbo].[Sudarymas_5] CHECK CONSTRAINT [FK_SUDARYMA_SUDARYMAS_KIETASIS]
GO
ALTER TABLE [dbo].[Sudarymas_6]  WITH CHECK ADD  CONSTRAINT [FK_SUDARYMA_SUDARYMAS_VAIZDO_P] FOREIGN KEY([Vaizdo_plokstes_ID])
REFERENCES [dbo].[Vaizdo_plokste] ([Vaizdo_plokstes_ID])
GO
ALTER TABLE [dbo].[Sudarymas_6] CHECK CONSTRAINT [FK_SUDARYMA_SUDARYMAS_VAIZDO_P]
GO
ALTER TABLE [dbo].[Sudarymas_6]  WITH CHECK ADD  CONSTRAINT [FK_SUDARYMA_VAIZDO_PL_KOMPIUTE] FOREIGN KEY([Kompiuterio_ID])
REFERENCES [dbo].[Kompiuteris] ([Kompiuterio_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Sudarymas_6] CHECK CONSTRAINT [FK_SUDARYMA_VAIZDO_PL_KOMPIUTE]
GO
ALTER TABLE [dbo].[Uzsakymas]  WITH CHECK ADD  CONSTRAINT [FK_UZSAKYMA_PATEIKIA_KLIENTAS] FOREIGN KEY([Kliento_ID])
REFERENCES [dbo].[Klientas] ([Kliento_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Uzsakymas] CHECK CONSTRAINT [FK_UZSAKYMA_PATEIKIA_KLIENTAS]
GO
ALTER TABLE [dbo].[Uzsakymas]  WITH CHECK ADD  CONSTRAINT [FK_UZSAKYMA_RELATIONS_KLIENTAS] FOREIGN KEY([Kliento_ID])
REFERENCES [dbo].[Klientas] ([Kliento_ID])
GO
ALTER TABLE [dbo].[Uzsakymas] CHECK CONSTRAINT [FK_UZSAKYMA_RELATIONS_KLIENTAS]
GO
ALTER TABLE [dbo].[Uzsakymas]  WITH CHECK ADD  CONSTRAINT [FK_UZSAKYMA_VYKDO_DARBUOTO] FOREIGN KEY([Darbuotojo_ID])
REFERENCES [dbo].[Darbuotojas] ([Darbuotojo_ID])
GO
ALTER TABLE [dbo].[Uzsakymas] CHECK CONSTRAINT [FK_UZSAKYMA_VYKDO_DARBUOTO]
GO
