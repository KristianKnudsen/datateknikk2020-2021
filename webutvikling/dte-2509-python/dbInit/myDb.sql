-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: 03. Feb, 2021 08:37 AM
-- Tjener-versjon: 10.5.4-MariaDB-1:10.5.4+maria~focal
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myDb`
--

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `student_v21`
--

CREATE TABLE `student_v21` (
                           `id` bigint(20) NOT NULL,
                           `givenName` varchar(128) NOT NULL,
                           `lastName` varchar(128) NOT NULL,
                           `email` varchar(128) NOT NULL,
                           `studyProgram` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `student_v21`
--

INSERT INTO `student_v21` (`id`, `givenName`, `lastName`, `email`, `studyProgram`) VALUES
(1, 'Thomas Blixth', 'Aasheim', 'taa048@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(2, 'Helena', 'Agustsson', 'hag001@post.uit.no', '\"Enkeltemner (ENKELTEMNE) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(3, 'Reza', 'Ajam', 'raj000@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(4, 'Mohammad Adnan', 'Albathish', 'mal130@post.uit.no', 'Datateknikk, ingeniør - bachelor (y-vei) (B-YDT) (Høst 2020 B-YDT, UiT Norges arktiske universitet i Narvik)'),
(5, 'Hussein Naaim', 'Albirouti', 'hal055@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(6, 'Victoria Tangen', 'Andersen', 'van061@post.uit.no', 'Datateknikk, ingeniør - bachelor (y-vei) (B-YDT) (Høst 2020 B-YDT, UiT Norges arktiske universitet i Narvik)'),
(7, 'Trond Jøran', 'Antonsen', 'tan098@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(8, 'Stephen Isaac', 'Befus', 'sbe121@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2019 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(9, 'Jan Erik Skaiå', 'Bisseth', 'jbi017@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(10, 'Ken Andre', 'Bull', 'kbu025@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(11, 'Magnus', 'Bårdlund', 'mba143@post.uit.no', '\"Enkeltemner ingeniør (ENKING) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(12, 'Julie Anette Gjelseth', 'Børli', 'jbo055@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(13, 'Patrick', 'Dahl', 'pda020@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(14, 'Oddvar', 'Danilin', 'oda032@post.uit.no', 'Enkeltemner ingeniør (ENKING) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(15, 'Daria', 'Danilina', 'dda003@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2019 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(16, 'Stefan', 'Eikland', 'sei061@post.uit.no', 'Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2019 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(17, 'Muhmad', 'Fauzi Kuhdume', 'mku028@post.uit.no', 'Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2020 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(18, 'August', 'Fredriksen', 'afr067@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(19, 'Mikael', 'Grande', 'mgr114@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(20, 'Alf Georg', 'Grastveit', 'agr086@post.uit.no', 'Enkeltemner ingeniør (ENKING) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(21, 'Henrik Walstad', 'Grevstad', 'hgr069@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(22, 'Alice', 'Grindheim', 'agr099@post.uit.no', 'Enkeltemner ingeniør (ENKING) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(23, 'Lars Jørgen', 'Hammervold', 'lha185@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(24, 'Ida-Britt', 'Hansen', 'iha185@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Alta, 1. år, UiT Norges arktiske universitet i Narvik)'),
(25, 'Jørund', 'Hansen', 'jha247@post.uit.no', '\"Enkeltemner informatikk (ENKINFORM) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(26, 'Armin', 'Hastor', 'aha488@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(27, 'Robin Magne Viken', 'Hatmosø', 'rha175@post.uit.no', 'Enkeltemner ingeniør (ENKING) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(28, 'Mats', 'Hokland', 'mho162@post.uit.no', 'Datateknikk, ingeniør - bachelor (y-vei) (B-YDT) (Høst 2019 Studiested Narvik, y-vei, UiT Norges arktiske universitet i Narvik)'),
(29, 'Marius', 'Homnes', 'mho188@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(30, 'Aleksandra Valerjevna', 'Jensen', 'aje074@post.uit.no', '\"Bygg, ingeniør - bachelor (B-BY) (Høst 2019 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(31, 'Markus', 'Johansen', 'mjo220@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Alta, 1. år, UiT Norges arktiske universitet i Narvik)'),
(32, 'Ole-Marius', 'Johansen', 'ojo067@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(33, 'Rikke Lund', 'Johansen', 'rjo130@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2019 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(34, 'Tor Håkon Bygdåsmo', 'Johansen', 'tjo183@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Narvik, ordinære, Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(35, 'Vebjørn', 'Johnsen', 'vjo055@post.uit.no', 'Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2019 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(36, 'Tine Nathalie', 'Joramo', 'tjo221@post.uit.no', '\"Enkeltemner (ENKELTEMNE) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(37, 'Jon-Michael', 'Josefsen', 'jjo216@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(38, 'Elshad', 'Kadeer', 'eka100@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(39, 'Klas Rogne', 'Kanestrøm', 'kka114@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(40, 'Joacim', 'Karlsen', 'jka099@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(41, 'Muhammad Shahzeb', 'Khan', 'mkh039@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2019 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(42, 'Lasse Husevåg', 'Klausen', 'lkl028@post.uit.no', '\"Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(43, 'Kristian Steinhaug', 'Knudsen', 'kkn035@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(44, 'Magnus Fjelldal', 'Korsaksel', 'mko095@post.uit.no', 'Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2020 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(45, 'Kenneth', 'Krogstad', 'kkr113@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(46, 'Vidar Johansen', 'Kvamme', 'vkv003@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(47, 'Richard', 'Kvennejorde', 'rkv014@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(48, 'Nils Mikal Rødahl', 'Lerøy', 'nle009@post.uit.no', '\"Enkeltemner (ENKELTEMNE) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(49, 'Marius', 'Larsen', 'mla132@post.uit.no', '\"Aerospace Control Engineering, sivilingeniør - master (M-ST) (Høst 2019 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(50, 'Vegard', 'Larsen', 'vla018@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(51, 'Tove Mette', 'Linløkken', 'tli061@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(52, 'Håvard', 'Lund', 'hlu046@post.uit.no', 'Enkeltemner ingeniør (ENKING) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(53, 'Sindre Andreas', 'Lundø', 'slu029@post.uit.no', '\"Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2020 Studiested Narvik, 3-semester, Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(54, 'Abdallah', 'Mahdi', 'ama236@post.uit.no', 'Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2020 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(55, 'Marius Lee', 'Midtbø-Hjelvik', 'mmi100@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(56, 'Simen Rognli', 'Mikalsen', 'smi052@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2019 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(57, 'Adelia', 'Mirgrad', 'yko012@post.uit.no', '\"Enkeltemner (ENKELTEMNE) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(58, 'Ali Reza', 'Moaddeli', 'amo095@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2019 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(59, 'Henrik', 'Myklebust', 'hmy026@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(60, 'Kristian Alvestad', 'Nedrevold', 'kne009@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(61, 'Mirian Santos', 'Neverdal', 'mne041@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2019 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(62, 'Vu Thuy Duong', 'Nguyen', 'vng011@post.uit.no', 'Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2019 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(63, 'Aleksander', 'Nilsen', 'ani150@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(64, 'Mathias Myhre', 'Nilsen', 'mni125@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(65, 'Marius Pedersen', 'Nordaas', 'mno046@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(66, 'Stefan Mikal', 'Nordvoll-Lauritsen', 'sno070@post.uit.no', '\"Bygg, ingeniør - bachelor (nettbasert) (NB-BY) (Høst 2018, UiT Norges arktiske universitet i Narvik)'),
(67, 'Frann-Eirik', 'Nygård', 'fny004@post.uit.no', 'Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2019 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(68, 'Simen Berg', 'Nøkleby', 'sno095@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(69, 'Eskil', 'Olaussen', 'eol095@post.uit.no', 'Satellitteknologi, ingeniør - bachelor (B-ST) (Høst 2019 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Alta)'),
(70, 'Fredrik Jørgensen', 'Olsen', 'fol016@post.uit.no', '\"Enkeltemner informatikk (ENKINFORM) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(71, 'Vegard', 'Olsen', 'vol033@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(72, 'Marit Olsvik', 'Opsahl', 'marit.o.opsahl@uit.no', '\"Enkeltemner (ENKELTEMNE) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(73, 'Simon Christian Vindahl', 'Pedersen', 'spe211@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(74, 'Benchawan', 'Penchan', 'bpe044@post.uit.no', '\"Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(75, 'Sharmila', 'Ratnasingam', 'sra084@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(76, 'Magnus Roald', 'Ree', 'mre056@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(77, 'Inge', 'Refseth', 'ire027@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(78, 'Kevin Birger Røstgård', 'Refsvik', 'kre043@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(79, 'Carlos Andrés', 'Reiakvam', 'cre032@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(80, 'Carl Christian', 'Reistad', 'cre028@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(81, 'Trygve', 'Reistad', 'tre052@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(82, 'Thomas', 'Rochmann', 'tro035@post.uit.no', '\"Enkeltemner informatikk (ENKINFORM) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(83, 'Hanstein', 'Rommerud', 'hro047@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(84, 'Bjørn Tore', 'Rosten', 'btr022@post.uit.no', 'Enkeltemner ingeniør (ENKING) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(85, 'Dag Aksel', 'Rønnebu', 'dro028@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(86, 'Martin', 'Samsonstuen', 'msa143@post.uit.no', '\"Enkeltemner informatikk (ENKINFORM) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(87, 'Thomas Andre', 'Sandstad', 'tsa097@post.uit.no', 'Enkeltemner ingeniør (ENKING) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(88, 'Emil', 'Simonsen', 'esi036@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(89, 'Jonathan', 'Skaue', 'jsk059@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(90, 'Morten Størkersen', 'Solbakken', 'mso201@post.uit.no', '\"Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Alta, 1. år, UiT Norges arktiske universitet i Narvik)'),
(91, 'Magnus', 'Strøm', 'mst255@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(92, 'Olav Kjølstad', 'Strømseng', 'ost057@post.uit.no', 'Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2020 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(93, 'Marthe Kristine Hagen', 'Strømsnes', 'mst294@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020, UiT Norges arktiske universitet i Bodø)'),
(94, 'Kristoffer Ytterbø', 'Sture', 'kst179@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(95, 'Kristian', 'Støre', 'kst155@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2019 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(96, 'Morten Juul', 'Sundnes', 'msu007@post.uit.no', '\"Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(97, 'Hanne Therese', 'Sørensen', 'hso103@post.uit.no', '\"Datateknikk, ingeniør - bachelor (3-semester) (B-DT3S) (Høst 2020 Studiested Narvik, 3-semester, UiT Norges arktiske universitet i Narvik)'),
(98, 'Mats Kristian Mollandsøy', 'Sørensen', 'mso247@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(99, 'Desta', 'Terefe', 'dte005@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(100, 'Magomed Amourkhanov', 'Timiev', 'mti014@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Narvik, ordinære, UiT Norges arktiske universitet i Narvik)'),
(101, 'Dena Esther', 'Utne', 'dut002@post.uit.no', 'Enkeltemner ingeniør (ENKING) (Vår 2021, UiT Norges arktiske universitet i Tromsø)'),
(102, 'Haakon Tiller', 'Vangsnes', 'hva028@post.uit.no', '\"Enkeltemner (ENKELTEMNE) (Høst 2020, UiT Norges arktiske universitet i Tromsø)'),
(103, 'Sverre Magnus', 'Vikan', 'svi037@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(104, 'Knut Arild', 'Vold', 'kvo016@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(105, 'Eirin', 'Wolden', 'ewo011@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(106, 'Tina Mari Larsen', 'Wågan', 'twa030@post.uit.no', 'Datateknikk, ingeniør - bachelor (B-DT) (Høst 2020 Studiested Bodø, ordinære, UiT Norges arktiske universitet i Bodø)'),
(107, 'Thomas Melchior', 'Ytterdal', 'tyt005@post.uit.no', 'Datateknikk, ingeniør - bachelor (nettbasert) (NB-DT) (Høst 2020 Studiested Narvik, nettbasert, UiT Norges arktiske universitet i Narvik)'),
(108, 'Salim', 'Zouhri', 'szo001@post.uit.no', '\"Enkeltemner (ENKELTEMNE) (Høst 2020, UiT Norges arktiske universitet i Tromsø)');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student_v21`
--
ALTER TABLE `student_v21`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student_v21`
--
ALTER TABLE `student_v21`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
