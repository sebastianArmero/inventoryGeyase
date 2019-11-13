-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-10-2019 a las 15:22:07
-- Versión del servidor: 10.3.15-MariaDB
-- Versión de PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `invemtariosgeyase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cod_cli` int(11) NOT NULL,
  `nom_cli` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `ape_cli` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `sexo_cli` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `cedCli` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `tel_cli` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `email_cli` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `dir_cli` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cod_cli`, `nom_cli`, `ape_cli`, `sexo_cli`, `cedCli`, `tel_cli`, `email_cli`, `dir_cli`) VALUES
(1, 'yamileth', 'Melo', 'F', '29506356', '3218008515', 'yami@gmail.com', 'TARRAGONA'),
(2, 'Danilo', 'ARMERO', 'M', '16890099', '3006266342', 'german@gmail.com', 'TARRAGONA'),
(3, 'Nancy', 'Armero', 'F', '234354323', '21343213', 'aaa@gmail.com', 'Tarragona');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallefactura`
--

CREATE TABLE `detallefactura` (
  `id_detalle` int(11) NOT NULL,
  `id_factura` int(11) NOT NULL,
  `cod_pro` int(11) NOT NULL,
  `cantidad` int(200) NOT NULL,
  `importe` int(200) NOT NULL,
  `total` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `detallefactura`
--

INSERT INTO `detallefactura` (`id_detalle`, `id_factura`, `cod_pro`, `cantidad`, `importe`, `total`) VALUES
(66, 41, 6, 7, 3192, 0),
(67, 41, 3, 6, 20400, 0),
(68, 42, 6, 7, 3192, 0),
(69, 42, 3, 6, 20400, 0),
(70, 43, 6, 7, 3192, 0),
(71, 43, 3, 6, 20400, 0),
(72, 43, 4, 6, 5400, 0),
(73, 43, 5, 4, 4000, 0),
(74, 44, 6, 5, 2280, 0),
(75, 44, 6, 5, 2280, 0),
(76, 45, 1, 3, 7500, 0),
(77, 45, 2, 1, 2500, 0),
(78, 46, 1, 3, 7500, 0),
(79, 46, 2, 1, 2500, 0),
(80, 47, 1, 3, 7500, 0),
(81, 47, 2, 1, 2500, 0),
(82, 48, 1, 3, 7500, 0),
(83, 48, 2, 1, 2500, 0),
(84, 49, 1, 3, 7500, 0),
(85, 49, 2, 1, 2500, 0),
(86, 50, 1, 3, 7500, 0),
(87, 50, 2, 1, 2500, 0),
(88, 51, 1, 3, 7500, 0),
(89, 51, 2, 1, 2500, 0),
(90, 52, 1, 3, 7500, 0),
(91, 52, 2, 1, 2500, 0),
(92, 53, 3, 2, 6800, 0),
(93, 53, 2, 4, 10000, 0),
(94, 54, 2, 0, 0, 0),
(95, 54, 1, 6, 15000, 0),
(96, 55, 2, 6, 15000, 0),
(97, 55, 1, 2, 5000, 0),
(98, 56, 4, 6, 5400, 0),
(99, 56, 5, 8, 8000, 0),
(100, 57, 4, 4, 3600, 0),
(101, 57, 7, 4, 9600, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL,
  `cod_cli` int(11) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id_factura`, `cod_cli`, `fecha`) VALUES
(41, 3, '2019-10-22 16:20:21'),
(42, 3, '2019-10-22 16:20:30'),
(43, 2, '2019-10-22 16:21:23'),
(44, 2, '2019-10-29 10:19:42'),
(45, 1, '2019-10-29 10:50:47'),
(46, 1, '2019-10-29 10:50:49'),
(47, 1, '2019-10-29 10:50:50'),
(48, 1, '2019-10-29 10:50:51'),
(49, 1, '2019-10-29 10:50:51'),
(50, 1, '2019-10-29 10:50:56'),
(51, 1, '2019-10-29 10:50:57'),
(52, 1, '2019-10-29 10:51:04'),
(53, 1, '2019-10-29 10:53:15'),
(54, 1, '2019-10-29 10:54:44'),
(55, 2, '2019-10-29 10:59:31'),
(56, 1, '2019-10-29 19:36:13'),
(57, 2, '2019-10-30 07:09:44');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `id_user` int(11) NOT NULL,
  `usuario` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`id_user`, `usuario`, `email`, `password`) VALUES
(11, 'sebas', 'germanarmero@gmail.com', '12345'),
(12, 'sebas', 'armerosebas7@gmail.com', '12345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `cod_pro` int(11) NOT NULL,
  `despro` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `prepro` int(200) NOT NULL,
  `cantidadpro` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`cod_pro`, `despro`, `prepro`, `cantidadpro`) VALUES
(1, 'Reds', 2500, 5),
(2, 'gaseosa 1LT', 2500, 45),
(3, 'aguacate', 3400, 50),
(4, 'tomate', 900, 150),
(5, 'cilantro', 1000, 23),
(6, 'tomate', 456, 45),
(7, 'gaseosa x 2Lt', 2400, 23),
(8, 'papel Carta', 100, 100),
(9, 'lapiceros ', 2500, 20);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cli`);

--
-- Indices de la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_factura` (`id_factura`),
  ADD KEY `cod_pro` (`cod_pro`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `cod_cli` (`cod_cli`);

--
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`cod_pro`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cod_cli` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT de la tabla `login`
--
ALTER TABLE `login`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `cod_pro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD CONSTRAINT `detallefactura_ibfk_1` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id_factura`),
  ADD CONSTRAINT `detallefactura_ibfk_2` FOREIGN KEY (`cod_pro`) REFERENCES `productos` (`cod_pro`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`cod_cli`) REFERENCES `cliente` (`cod_cli`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
