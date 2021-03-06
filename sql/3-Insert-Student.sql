-- ******************** Kassenbonerstellung *************************** --

insert into KASSENBON (FILIALENR, DATUM, KASSENNR, BARGELD, SUMME)
VALUES

(14, CONVERT([datetime], '2019-02-10 08:22:00.000', 20), 1, 400.00, 400.00),
(14, CONVERT([datetime], '2019-02-11 10:22:00.000', 20), 1, 33.00, 33.00),
(14, CONVERT([datetime], '2019-02-16 08:22:00.000', 20), 1, 12.00, 12.00),
(14, CONVERT([datetime], '2019-02-17 10:22:00.000', 20), 1, 123.00, 150.00),
(14, CONVERT([datetime], '2019-02-20 11:29:00.000', 20), 1, 134.00, 200.00),
(14, CONVERT([datetime], '2019-02-22 13:22:00.000', 20), 1, 22.00, 100.00),
(14, CONVERT([datetime], '2019-02-23 14:25:00.000', 20), 1, 43.00, 50.00),
(14, CONVERT([datetime], '2019-02-25 08:30:00.000', 20), 1, 40.00, 100.00),
(14, CONVERT([datetime], '2019-03-10 12:29:00.000', 20), 1, 100.00, 100.00),
(14, CONVERT([datetime], '2019-04-22 14:20:00.000', 20), 1, 223.00, 230.00),
(14, CONVERT([datetime], '2019-04-11 13:20:00.000', 20), 1, 433.00, 450.00),
(14, CONVERT([datetime], '2019-05-22 15:20:00.000', 20), 1, 333.00, 335.00),
(14, CONVERT([datetime], '2019-05-23 14:40:00.000', 20), 1, 555.00, 555.00),
(14, CONVERT([datetime], '2019-05-24 16:22:00.000', 20), 1, 244.00, 250.00),
(14, CONVERT([datetime], '2019-05-25 15:30:00.000', 20), 1, 260.00, 260.00),
(14, CONVERT([datetime], '2019-05-25 16:23:00.000', 20), 1, 50.00, 50.00),
(14, CONVERT([datetime], '2019-05-25 16:45:00.000', 20), 1, 78.44, 100.00),
(14, CONVERT([datetime], '2019-05-25 17:44:00.000', 20), 1, 444.00, 450.00),
(14, CONVERT([datetime], '2019-05-26 17:55:00.000', 20), 1, 50.00, 50.00),
(14, CONVERT([datetime], '2019-05-27 12:42:00.000', 20), 1, 33.50, 35.00),
(14, CONVERT([datetime], '2019-05-28 17:50:00.000', 20), 1, 447.00, 450.00),
(14, CONVERT([datetime], '2019-06-02 12:44:00.000', 20), 1, 1000.00, 1000.00),
(14, CONVERT([datetime], '2019-06-12 10:44:00.000', 20), 1, 23.00, 34.00),
(14, CONVERT([datetime], '2019-06-12 12:44:00.000', 20), 1, 25.00, 37.00),
(14, CONVERT([datetime], '2019-06-13 10:44:00.000', 20), 1, 399.00, 400.00),
(14, CONVERT([datetime], '2019-06-14 12:44:00.000', 20), 1, 23.00, 23.00),
(14, CONVERT([datetime], '2019-06-17 12:30:00.000', 20), 1, 12.00, 50.00),
(14, CONVERT([datetime], '2019-06-20 10:33:00.000', 20), 1, 66.00, 100.00),
(14, CONVERT([datetime], '2019-06-25 10:44:00.000', 20), 1, 33.00, 100.00),
(14, CONVERT([datetime], '2019-06-25 10:55:00.000', 20), 1, 13.00, 23.00),
(14, CONVERT([datetime], '2019-06-25 10:20:00.000', 20), 1, 124.00, 200.00),
(14, CONVERT([datetime], '2019-06-28 09:55:00.000', 20), 1, 45.00, 50.00),
(14, CONVERT([datetime], '2019-06-30 13:45:00.000', 20), 1, 66.00, 70.00),
(14, CONVERT([datetime], '2019-07-01 16:50:00.000', 20), 1, 898.00, 1000.00),
(14, CONVERT([datetime], '2019-08-02 10:44:00.000', 20), 1, 87.00, 100.00),
(14, CONVERT([datetime], '2019-08-24 12:44:00.000', 20), 1, 34.00, 35.00),
(14, CONVERT([datetime], '2019-08-25 15:26:00.000', 20), 1, 400.00, 500.00),
(14, CONVERT([datetime], '2019-09-11 09:22:00.000', 20), 1, 298.00, 300.00),
(14, CONVERT([datetime], '2019-09-11 12:33:00.000', 20), 1, 500.00, 700.00),
(14, CONVERT([datetime], '2019-09-12 11:20:00.000', 20), 1, 500.00, 100.00)

-- ******************** BONPOSITIONEN *************************** --


insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(10, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(10, 2, 'N', 'N', 1, 11.9900, 11.9900 * 0.07),
(10, 3, 'Y', 'Y', 10, 24.900, 24.900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(11, 2, 'Y', 'Y', 200, 2398.00, 2398.00 * 0.07),
(11, 15, 'Y', 'N', 10, 2.9900, 2.9900 * 0.07),
(11, 4, 'Y', 'Y', 10, 20.9, 20.9 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(12, 15, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(12, 21, 'Y', 'N', 1, 2.4900, 2.4900 * 0.07),
(12, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(13, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(13, 21, 'Y', 'Y', 200, 149.0, 149.0 * 0.07),
(13, 15, 'Y', 'N', 1, 2.9900, 2.9900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(14, 10, 'Y', 'Y', 20, 10.8, 10.8 * 0.07),
(14, 20, 'Y', 'Y', 200, 149.0, 149.0 * 0.07),
(14, 15, 'N', 'N', 1, 2.9900, 2.9900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(15, 14, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(15, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(15, 15, 'N', 'N', 1, 2.9900, 2.9900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(16, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(16, 14, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(16, 15, 'Y', 'Y', 1, 2.9900, 2.9900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(17, 21, 'Y', 'Y', 200, 149.0, 149.0 * 0.07),
(17, 15, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(17, 161, 'N', 'N', 1, 9.9000, 9.9000 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(18, 15, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(18, 13, 'Y', 'Y', 10, 20.9, 20.9 * 0.07),
(18, 21, 'Y', 'Y', 200, 149.0, 149.0 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(19, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(19, 12, 'Y', 'Y', 10, 7.900, 7.900 * 0.07),
(19, 15, 'Y', 'Y', 1, 2.9900, 2.9900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(20, 15, 'Y', 'N', 1, 12.7900, 12.7900 * 0.07),
(20, 169, 'N', 'N', 1, 3.7900, 3.7900 * 0.07),
(20, 172, 'Y', 'Y', 10, 29.900, 29.900 * 0.07);


insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(21, 15, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(21, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(21, 21, 'Y', 'Y', 200, 149.0, 149.0 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(22, 167, 'Y', 'Y', 10, 28.900, 28.900 * 0.07),
(22, 169, 'Y', 'Y', 1, 3.7900, 3.7900 * 0.07),
(22, 172, 'Y', 'Y', 200, 2.9900, 2.9900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(23, 12, 'Y', 'Y', 10, 7.90, 7.90 * 0.07),
(23, 158, 'Y', 'Y', 1, 3.9500, 3.9500 * 0.07),
(23, 160, 'Y', 'Y', 10, 45.500, 45.500 * 0.07);


insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(24, 15, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(24, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(24, 16, 'Y', 'Y', 20, 59.800, 59.800 * 0.07);


insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(25, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(25, 16, 'Y', 'Y', 1,2.9900, 2.9900 * 0.07),
(25, 19, 'Y', 'Y', 200, 133.0, 133.0 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(26, 12, 'Y', 'Y', 10, 7.900, 7.900 * 0.07),
(26, 163, 'Y', 'Y', 1, 3.9900, 3.9900 * 0.07),
(26, 164, 'Y', 'Y', 10, 19.900, 19.900 * 0.07);


insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(27, 169, 'Y', 'N', 10, 37.900, 37.900 * 0.07),
(27, 172, 'Y', 'Y', 15, 44.85, 44.85 * 0.07),
(27, 159, 'Y', 'Y', 10, 45.500, 45.500 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(28, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(28, 21, 'Y', 'Y', 200, 149.0, 149.0 * 0.07),
(28, 7, 'N', 'Y', 20, 27.8, 27.8 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(29, 6, 'Y', 'N', 10, 144.900, 144.900 * 0.07),
(29, 4, 'Y', 'Y', 10, 127.900, 127.900 * 0.07),
(29, 3, 'N', 'Y', 1, 2.4900, 2.4900 * 0.07);


insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(30, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(30, 19, 'Y', 'Y', 200, 133.0, 133.0 * 0.07),
(30, 6, 'N', 'Y', 2, 24.98, 24.98 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(31, 12, 'Y', 'Y', 20, 10.8, 10.8 * 0.07),
(31, 19, 'Y', 'Y', 200, 133.0, 133.0 * 0.07),
(31, 170, 'Y', 'Y', 200, 2.1900 * 200, 2.1900 *200 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(32, 169, 'Y', 'N', 43, 3.7900 *43, 3.7900 *43 * 0.07),
(32, 21, 'Y', 'Y', 10, 24.900, 24.900 * 0.07),
(32, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(33, 12, 'Y', 'Y', 30, 0.7900 * 30, 0.7900 * 30 * 0.07),
(33, 21, 'Y', 'Y', 200, 149.0, 149.0 * 0.07),
(33, 6, 'N', 'Y', 2, 24.98, 24.98 * 0.07);


insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(34, 15, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(34, 19, 'Y', 'Y', 200, 133.0, 133.0 * 0.07),
(34, 160, 'Y', 'Y', 1, 4.5500, 4.5500 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(35, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(35, 12, 'Y', 'Y', 10, 7.900, 7.900 * 0.07),
(35, 6, 'N', 'Y', 2, 24.98, 24.98 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(36, 170, 'Y', 'Y', 21, 2.1900 * 21, 2.1900 * 21 * 0.07),
(36, 15, 'Y', 'Y', 200, 133.0, 133.0 * 0.07),
(36, 9, 'N', 'Y', 20, 0.7900 * 20, 0.7900 * 20 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(37, 172, 'Y', 'N', 20, 2.9900 * 20, 2.9900 * 20 * 0.07),
(37, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(37, 164, 'Y', 'Y', 200, 1.9900 * 200, 1.9900 * 200 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(38, 18, 'Y', 'Y', 10, 21.900, 21.900 * 0.07),
(38, 12, 'Y', 'Y', 30, 0.7900 * 30, 0.7900 * 30 * 0.07),
(38, 15, 'N', 'Y', 2, 24.98, 24.98 * 0.07);


insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(39, 165, 'Y', 'Y', 200, 1.9900 * 200, 1.9900 * 200 * 0.07),
(39, 168, 'Y', 'Y', 100, 100 * 2.8900, 100 * 2.8900 * 0.07),
(39, 6, 'N', 'Y', 3, 14.4900 * 3, 14.4900 * 3 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(40, 166, 'Y', 'N', 50, 14.9900 * 50, 14.9900 * 50 * 0.07),
(40, 165, 'N', 'Y', 8, 1.9900 * 8, 1.9900 * 8 * 0.07),
(40, 171, 'Y', 'Y', 15, 1.9900 * 15, 1.9900 * 15 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(41, 2, 'Y', 'N', 10, 11.9900 * 10, 11.9900 * 10 * 0.07),
(41, 3, 'N', 'N', 45, 2.4900 * 45, 2.4900 * 45 * 0.07),
(41, 4, 'Y', 'Y', 1, 12.7900, 12.7900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(42, 163, 'N', 'N', 5, 3.9900 * 5, 3.9900 * 5 * 0.07),
(42, 17, 'Y', 'Y', 50, 2.1900 * 50, 2.1900 * 50 * 0.07),
(42, 18, 'Y', 'Y', 30, 2.1900 * 30, 2.1900 * 30 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(43, 169, 'Y', 'Y', 20, 3.7900* 20, 3.7900* 20 * 0.07),
(43, 20, 'Y', 'Y',10, 24.900, 24.900 * 0.07),
(43, 21, 'N', 'Y', 1, 2.4900, 2.4900 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(44, 170, 'Y', 'Y', 10, 21.900, 21.900 * 0.07),
(44, 162, 'Y', 'Y', 20, 2.9900 * 20, 2.9900 * 20 * 0.07),
(44, 21, 'Y', 'Y', 200, 149.0, 149.0 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(45, 15, 'Y', 'N', 10, 29.9, 29.9 * 0.07),
(45, 1, 'N', 'N', 1, 12.79, 12.79 * 0.07),
(45, 162, 'Y', 'Y', 20, 2.9900 * 20, 2.9900 * 20 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(46, 12, 'Y', 'Y', 20, 10.8, 10.8 * 0.07),
(46, 162, 'Y', 'Y', 20, 2.9900 * 20, 2.9900 * 20 * 0.07),
(46, 170, 'Y', 'Y', 10, 21.900, 21.900 * 0.07)

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(47, 13, 'Y', 'Y', 10, 20.9, 20.9 * 0.07),
(47, 21, 'Y', 'Y', 200, 149.0, 149.0 * 0.07),
(47, 6, 'N', 'Y', 2, 24.98, 24.98 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(48, 170, 'Y', 'Y', 10, 21.900, 21.900 * 0.07),
(48, 162, 'Y', 'Y', 20, 2.9900 * 20, 2.9900 * 20 * 0.07),
(48, 15, 'N', 'Y', 2, 24.98, 24.98 * 0.07);

insert into BONPOSITION (BONNR, PRODUKTNR, AKTIONSWARE, RABATTWARE, VERKAUFSMENGE, VERKAUFSPREIS, MWST)
values
(49, 13, 'Y', 'Y', 10, 20.9, 20.9 * 0.07),
(49, 170, 'Y', 'Y', 10, 21.900, 21.900 * 0.07),
(49, 6, 'N', 'Y', 2, 24.98, 24.98 * 0.07);

-- ******* Tabelle HANDELSMARKE ******************************** --


insert into HANDELSMARKE (NAME, LAND)
values
('Schär', 'Deutschland'),
('Nestlé', 'Schweiz'),
('Kellog''s', 'USA'),
('Coca Cola', 'USA'),
('Goutess', 'Deutschland'),
('Gutfried', 'Deutschland'),
('Jever', 'Deutschland'),
('Nick', 'USA'),
('Basic', 'USA');

-- ********************************************************** --
-- ******* Tabelle FACHBEREICH ******************************** --
-- ********************************************************** --

-- ** Gewürze wurden aus dem Java-Programm heraus hinzugefügt *** --
insert into FACHBEREICH (FACHBEREICHNR, NAME)
values
(1040,'Snacks'),
(1041,'Oriental'),
(1042,'Asia'),
(1043,'Italienisch'),
(1044,'Drogerie'),
(1045,'Haushalt'),
(1046,'Getränke'),
(1047,'Desserts');
