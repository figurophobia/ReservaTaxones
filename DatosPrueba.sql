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
('Primates', 'Orden', 'Mammalia'),
('Carnivora', 'Orden', 'Mammalia'),
('Artiodactyla', 'Orden', 'Mammalia'),
('Perissodactyla', 'Orden', 'Mammalia'),
('Proboscidea', 'Orden', 'Mammalia'),
('Rodentia', 'Orden', 'Mammalia'),
('Cetacea', 'Orden', 'Mammalia'),
('Galliformes', 'Orden', 'Aves'),
('Accipitriformes', 'Orden', 'Aves'),
('Sphenisciformes', 'Orden', 'Aves'),
('Columbiformes', 'Orden', 'Aves'),
('Psittaciformes', 'Orden', 'Aves'),
('Crocodilia', 'Orden', 'Reptilia'),
('Squamata', 'Orden', 'Reptilia'),
('Testudines', 'Orden', 'Reptilia'),
('Anura', 'Orden', 'Amphibia'),
('Caudata', 'Orden', 'Amphibia'),
('Salmoniformes', 'Orden', 'Actinopterygii'),
('Perciformes', 'Orden', 'Actinopterygii'),
('Lepidoptera', 'Orden', 'Insecta'),
('Hymenoptera', 'Orden', 'Insecta'),
('Hominidae', 'Familia', 'Primates'),
('Canidae', 'Familia', 'Carnivora'),
('Felidae', 'Familia', 'Carnivora'),
('Ursidae', 'Familia', 'Carnivora'),
('Bovidae', 'Familia', 'Artiodactyla'),
('Suidae', 'Familia', 'Artiodactyla'),
('Equidae', 'Familia', 'Perissodactyla'),
-- Familias de Proboscidea
('Elephantidae', 'Familia', 'Proboscidea'),
-- Familias de Rodentia
('Muridae', 'Familia', 'Rodentia'),
('Sciuridae', 'Familia', 'Rodentia'),
-- Familias de Cetacea
('Delphinidae', 'Familia', 'Cetacea'),
-- Familias de Galliformes
('Phasianidae', 'Familia', 'Galliformes'),
-- Familias de Accipitriformes
('Accipitridae', 'Familia', 'Accipitriformes'),
-- Familias de Sphenisciformes
('Spheniscidae', 'Familia', 'Sphenisciformes'),
-- Familias de Columbiformes
('Columbidae', 'Familia', 'Columbiformes'),
-- Familias de Psittaciformes
('Psittacidae', 'Familia', 'Psittaciformes'),
-- Familias de Crocodilia
('Crocodylidae', 'Familia', 'Crocodilia'),
-- Familias de Squamata
('Pythonidae', 'Familia', 'Squamata'),
('Colubridae', 'Familia', 'Squamata'),
-- Familias de Testudines
('Cheloniidae', 'Familia', 'Testudines'),
-- Familias de Anura
('Ranidae', 'Familia', 'Anura'),
-- Familias de Caudata
('Ambystomatidae', 'Familia', 'Caudata'),
-- Familias de Salmoniformes
('Salmonidae', 'Familia', 'Salmoniformes'),
-- Familias de Perciformes
('Scombridae', 'Familia', 'Perciformes'),
-- Familias de Lepidoptera
('Nymphalidae', 'Familia', 'Lepidoptera'),
-- Familias de Hymenoptera
('Apidae', 'Familia', 'Hymenoptera'),
('Formicidae', 'Familia', 'Hymenoptera'),
-- Géneros de Hominidae
('Homo', 'Género', 'Hominidae'),
-- Géneros de Canidae
('Canis', 'Género', 'Canidae'),
('Vulpes', 'Género', 'Canidae'),
-- Géneros de Felidae
('Felis', 'Género', 'Felidae'),
('Panthera', 'Género', 'Felidae'),
-- Géneros de Ursidae
('Ursus', 'Género', 'Ursidae'),
-- Géneros de Bovidae
('Bos', 'Género', 'Bovidae'),
('Ovis', 'Género', 'Bovidae'),
-- Géneros de Suidae
('Sus', 'Género', 'Suidae'),
-- Géneros de Equidae
('Equus', 'Género', 'Equidae'),
-- Géneros de Elephantidae
('Loxodonta', 'Género', 'Elephantidae'),
('Elephas', 'Género', 'Elephantidae'),
-- Géneros de Muridae
('Mus', 'Género', 'Muridae'),
('Rattus', 'Género', 'Muridae'),
-- Géneros de Sciuridae
('Sciurus', 'Género', 'Sciuridae'),
-- Géneros de Delphinidae
('Tursiops', 'Género', 'Delphinidae'),
('Delphinus', 'Género', 'Delphinidae'),
-- Géneros de Phasianidae
('Gallus', 'Género', 'Phasianidae'),
-- Géneros de Accipitridae
('Haliaeetus', 'Género', 'Accipitridae'),
-- Géneros de Spheniscidae
('Spheniscus', 'Género', 'Spheniscidae'),
-- Géneros de Columbidae
('Columba', 'Género', 'Columbidae'),
-- Géneros de Psittacidae
('Psittacus', 'Género', 'Psittacidae'),
-- Géneros de Crocodylidae
('Crocodylus', 'Género', 'Crocodylidae'),
-- Géneros de Pythonidae
('Python', 'Género', 'Pythonidae'),
-- Géneros de Cheloniidae
('Chelonia', 'Género', 'Cheloniidae'),
-- Géneros de Ranidae
('Lithobates', 'Género', 'Ranidae'),
-- Géneros de Ambystomatidae
('Ambystoma', 'Género', 'Ambystomatidae'),
-- Géneros de Salmonidae
('Salmo', 'Género', 'Salmonidae'),
-- Géneros de Scombridae
('Thunnus', 'Género', 'Scombridae'),
-- Géneros de Nymphalidae
('Danaus', 'Género', 'Nymphalidae'),
-- Géneros de Apidae
('Apis', 'Género', 'Apidae'),
-- Géneros de Formicidae
('Formica', 'Género', 'Formicidae'),
-- Especies de Homo
('Homo sapiens', 'Especie', 'Homo'),
-- Especies de Canis
('Canis lupus familiaris', 'Especie', 'Canis'),
('Canis lupus', 'Especie', 'Canis'),
-- Especies de Vulpes
('Vulpes vulpes', 'Especie', 'Vulpes'),
-- Especies de Felis
('Felis catus', 'Especie', 'Felis'),
-- Especies de Panthera
('Panthera leo', 'Especie', 'Panthera'),
('Panthera tigris', 'Especie', 'Panthera'),
('Panthera onca', 'Especie', 'Panthera'),
-- Especies de Ursus
('Ursus arctos', 'Especie', 'Ursus'),
('Ursus maritimus', 'Especie', 'Ursus'),
-- Especies de Bos
('Bos taurus', 'Especie', 'Bos'),
-- Especies de Ovis
('Ovis aries', 'Especie', 'Ovis'),
-- Especies de Sus
('Sus scrofa domesticus', 'Especie', 'Sus'),
-- Especies de Equus
('Equus caballus', 'Especie', 'Equus'),
('Equus asinus', 'Especie', 'Equus'),
-- Especies de Loxodonta
('Loxodonta africana', 'Especie', 'Loxodonta'),
-- Especies de Elephas
('Elephas maximus', 'Especie', 'Elephas'),
-- Especies de Mus
('Mus musculus', 'Especie', 'Mus'),
-- Especies de Rattus
('Rattus norvegicus', 'Especie', 'Rattus'),
-- Especies de Sciurus
('Sciurus vulgaris', 'Especie', 'Sciurus'),
-- Especies de Tursiops
('Tursiops truncatus', 'Especie', 'Tursiops'),
-- Especies de Delphinus
('Delphinus delphis', 'Especie', 'Delphinus'),
-- Especies de Gallus
('Gallus gallus domesticus', 'Especie', 'Gallus'),
-- Especies de Haliaeetus
('Haliaeetus leucocephalus', 'Especie', 'Haliaeetus'),
-- Especies de Spheniscus
('Spheniscus demersus', 'Especie', 'Spheniscus'),
-- Especies de Columba
('Columba livia', 'Especie', 'Columba'),
-- Especies de Psittacus
('Psittacus erithacus', 'Especie', 'Psittacus'),
-- Especies de Crocodylus
('Crocodylus niloticus', 'Especie', 'Crocodylus'),
('Crocodylus porosus', 'Especie', 'Crocodylus'),
-- Especies de Python
('Python regius', 'Especie', 'Python'),
('Python molurus', 'Especie', 'Python'),
-- Especies de Chelonia
('Chelonia mydas', 'Especie', 'Chelonia'),
-- Especies de Lithobates
('Lithobates catesbeianus', 'Especie', 'Lithobates'),
-- Especies de Ambystoma
('Ambystoma tigrinum', 'Especie', 'Ambystoma'),
-- Especies de Salmo
('Salmo salar', 'Especie', 'Salmo'),
('Salmo trutta', 'Especie', 'Salmo'),
-- Especies de Thunnus
('Thunnus thynnus', 'Especie', 'Thunnus'),
-- Especies de Danaus
('Danaus plexippus', 'Especie', 'Danaus'),
-- Especies de Apis
('Apis mellifera', 'Especie', 'Apis'),
-- Especies de Formica
('Formica rufa', 'Especie', 'Formica');

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