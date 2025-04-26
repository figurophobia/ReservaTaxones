-- Inserción de datos de ejemplo

-- Insertando áreas geográficas (ahora con estructura unificada)
INSERT INTO area_geografica (nombre_reserva, extension, altitud_nivel_bajo, altitud_nivel_alto, profundidad, esAcuatica, esTerrestre) VALUES 
('Parque Nacional Doñana', 54252, 0, 40, NULL, FALSE, TRUE),
('Reserva Marina Cabo de Palos', 1898, NULL, NULL, 68, TRUE, FALSE),
('Parque Nacional Sierra Nevada', 85883, 800, 3479, NULL, FALSE, TRUE),
('Parque Nacional Marítimo-Terrestre Islas Atlánticas', 8480, 0, 200, 45, TRUE, TRUE);

-- Insertando países
INSERT INTO pais (nombre, area) VALUES 
('España', 'Parque Nacional Doñana'),
('España', 'Reserva Marina Cabo de Palos'),
('España', 'Parque Nacional Sierra Nevada'),
('España', 'Parque Nacional Marítimo-Terrestre Islas Atlánticas');

-- Insertando todos los taxones
INSERT INTO taxones (nombre, tipo, taxon_superior) VALUES
('Bacteria', 'Dominio', NULL),
('Archaea', 'Dominio', NULL),
('Eukarya', 'Dominio', NULL),
('Animalia', 'Reino', 'Eukarya'),
('Plantae', 'Reino', 'Eukarya'),
('Fungi', 'Reino', 'Eukarya'),
('Protista', 'Reino', 'Eukarya'),
('Chordata', 'Filo', 'Animalia'),
('Arthropoda', 'Filo', 'Animalia'),
('Mollusca', 'Filo', 'Animalia'),
('Echinodermata', 'Filo', 'Animalia'),
('Annelida', 'Filo', 'Animalia'),
('Nematoda', 'Filo', 'Animalia'),
('Cnidaria', 'Filo', 'Animalia'),
('Porifera', 'Filo', 'Animalia'),
('Platyhelminthes', 'Filo', 'Animalia'),
('Magnoliophyta', 'División', 'Plantae'),
('Pinophyta', 'División', 'Plantae'),
('Pteridophyta', 'División', 'Plantae'),
('Bryophyta', 'División', 'Plantae'),
('Cycadophyta', 'División', 'Plantae'),
('Ginkgophyta', 'División', 'Plantae'),
('Ascomycota', 'Filo', 'Fungi'),
('Basidiomycota', 'Filo', 'Fungi'),
('Zygomycota', 'Filo', 'Fungi'),
('Chytridiomycota', 'Filo', 'Fungi'),
('Glomeromycota', 'Filo', 'Fungi'),
('Euglenozoa', 'Filo', 'Protista'),
('Amoebozoa', 'Filo', 'Protista'),
('Apicomplexa', 'Filo', 'Protista'),
('Ciliophora', 'Filo', 'Protista'),
('Proteobacteria', 'Filo', 'Bacteria'),
('Firmicutes', 'Filo', 'Bacteria'),
('Actinobacteria', 'Filo', 'Bacteria'),
('Cyanobacteria', 'Filo', 'Bacteria'),
('Euryarchaeota', 'Filo', 'Archaea'),
('Crenarchaeota', 'Filo', 'Archaea'),
('Thaumarchaeota', 'Filo', 'Archaea'),
('Mammalia', 'Clase', 'Chordata'),
('Aves', 'Clase', 'Chordata'),
('Reptilia', 'Clase', 'Chordata'),
('Amphibia', 'Clase', 'Chordata'),
('Actinopterygii', 'Clase', 'Chordata'),
('Chondrichthyes', 'Clase', 'Chordata'),
('Insecta', 'Clase', 'Arthropoda'),
('Arachnida', 'Clase', 'Arthropoda'),
('Crustacea', 'Clase', 'Arthropoda'),
('Chilopoda', 'Clase', 'Arthropoda'),
('Diplopoda', 'Clase', 'Arthropoda'),
('Gastropoda', 'Clase', 'Mollusca'),
('Bivalvia', 'Clase', 'Mollusca'),
('Cephalopoda', 'Clase', 'Mollusca'),
('Magnoliopsida', 'Clase', 'Magnoliophyta'),
('Liliopsida', 'Clase', 'Magnoliophyta'),
('Pinopsida', 'Clase', 'Pinophyta'),
('Saccharomycetes', 'Clase', 'Ascomycota'),
('Pezizomycetes', 'Clase', 'Ascomycota'),
('Agaricomycetes', 'Clase', 'Basidiomycota'),
('Tremellomycetes', 'Clase', 'Basidiomycota'),
('Alphaproteobacteria', 'Clase', 'Proteobacteria'),
('Betaproteobacteria', 'Clase', 'Proteobacteria'),
('Gammaproteobacteria', 'Clase', 'Proteobacteria'),
('Deltaproteobacteria', 'Clase', 'Proteobacteria'),
('Bacilli', 'Clase', 'Firmicutes'),
('Clostridia', 'Clase', 'Firmicutes');

-- Insertando especies
INSERT INTO especies (nombre_cientifico, nombre_comun, descripcion, area_geografica, nombre_taxon) VALUES 
('Lynx pardinus', 'Lince ibérico', 'Felino en peligro de extinción endémico de la península ibérica', 'Parque Nacional Doñana', 'Mammalia'),
('Aquila adalberti', 'Águila imperial ibérica', 'Ave rapaz endémica de la península ibérica', 'Parque Nacional Doñana', 'Aves'),
('Muraena helena', 'Morena', 'Pez anguiliforme del Mediterráneo', 'Reserva Marina Cabo de Palos', 'Actinopterygii'),
('Capra pyrenaica', 'Cabra montés', 'Ungulado endémico de la península ibérica', 'Parque Nacional Sierra Nevada', 'Mammalia'),
('Posidonia oceanica', 'Posidonia', 'Planta acuática endémica del Mediterráneo', 'Reserva Marina Cabo de Palos', 'Liliopsida');

-- Insertando ejemplares
INSERT INTO ejemplar (id, nombre_cientifico_especie, mote, edad) VALUES 
(1, 'Lynx pardinus', 'Félix', 5),
(2, 'Lynx pardinus', 'Luna', 3),
(1, 'Aquila adalberti', 'Reina', 8),
(1, 'Capra pyrenaica', 'Montés', 6),
(2, 'Capra pyrenaica', 'Sierra', 4);

-- Insertando trabajadores
INSERT INTO trabajadores (dni, nombre, sueldo, horas) VALUES 
('11111111A', 'Pepe', 1250.00, 40);

-- Insertando misiones
INSERT INTO misiones (dni_trabajador, nombre_cientifico_especie, fecha_inicio, fecha_fin, descripcion) VALUES 
('11111111A', 'Lynx pardinus', '2024-03-15', '2024-05-30', 'Monitoreo de población de lince ibérico'),
('11111111A', 'Capra pyrenaica', '2024-06-01', NULL, 'Censo de cabra montés en Sierra Nevada');

-- Insertando relaciones trabajadores-misiones
INSERT INTO trabajadores_misiones (trabajador, nombre_cientifico_especie, fecha_inicio) VALUES 
('11111111A', 'Lynx pardinus', '2024-03-15'),
('11111111A', 'Capra pyrenaica', '2024-06-01');