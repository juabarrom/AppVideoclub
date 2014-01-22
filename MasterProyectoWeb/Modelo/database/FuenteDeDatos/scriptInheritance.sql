SET SQLBLANKLINES ON
CREATE TABLE JBRF_EXISTENCIA 
(
  ID_EXISTENCIA VARCHAR(20) NOT NULL 
, TIPO_EXISTENCIA VARCHAR2(20) NOT NULL 
, ESTADO VARCHAR2(20) 
, DESCUENTO NUMBER 
, TIENE_DESCUENTO VARCHAR2(20) 
, CONSTRAINT JBRF_EXISTENCIA_PK PRIMARY KEY 
  (
    ID_EXISTENCIA 
  )
  ENABLE 
);

CREATE TABLE JBRF_JUEGO 
(
  ID_EXISTENCIA VARCHAR(20) NOT NULL 
, NOMBRE VARCHAR2(20) 
, GENERO VARCHAR2(20) 
, PLATAFORMA VARCHAR2(20) 
, DESCRIPCION VARCHAR2(500) 
, GAMEPLAY VARCHAR2(100) 
, IMAGEN_PEQUENYA VARCHAR2(100) 
, IMAGEN_GRANDE VARCHAR2(100) 
, PUNTUACION NUMBER 
, NPERSONA_VOTACION INTEGER 
, CONSTRAINT JBRF_JUEGO_PK PRIMARY KEY 
  (
    ID_EXISTENCIA 
  )
  ENABLE 
);

CREATE TABLE JBRF_VIDEO 
(
  ID_EXISTENCIA VARCHAR(20) NOT NULL 
, NOMBRE VARCHAR2(40) 
, ANYO DATE 
, GENERO VARCHAR2(20) 
, DURACION INTEGER 
, AUTORIZADO INTEGER 
, SINOPSIS VARCHAR2(500) 
, TRAILER VARCHAR2(100) 
, IMAGEN_PEQUENYA VARCHAR2(100) 
, IMAGEN_GRANDE VARCHAR2(100) 
, ES_NOVEDAD VARCHAR2(20) 
, ES_PROXIMAMENTE VARCHAR2(20) 
, PUNTUACION NUMBER 
, NPERSONAS_VOTACION INTEGER 
, CONSTRAINT JBRF_VIDEO_PK PRIMARY KEY 
  (
    ID_EXISTENCIA 
  )
  ENABLE 
);

COMMENT ON COLUMN JBRF_JUEGO.GAMEPLAY IS 'RUTA DEL VIDEO PERTENECIENTE AL JUEGO';

COMMENT ON COLUMN JBRF_JUEGO.IMAGEN_PEQUENYA IS 'RUTA DE LA IMAGEN PEQUENYA PERTENECIENTE AL JUEGO';

COMMENT ON COLUMN JBRF_JUEGO.IMAGEN_GRANDE IS 'RUTA DE LA IMAGEN GRANDE PERTENECIENTE AL JUEGO';

COMMENT ON COLUMN JBRF_JUEGO.NPERSONA_VOTACION IS 'NUMERO DE PERSONAS QUE HAN PARTICIPADO EN LA VOTACION';

COMMENT ON COLUMN JBRF_VIDEO.AUTORIZADO IS 'ANYOS A PARTIR DEL QUE LA PELICULA ESTA AUTORIZADA PARA SU VISIONADO';

COMMENT ON COLUMN JBRF_VIDEO.TRAILER IS 'RUTA DEL TRAILER PERTENECIENTE AL JUEGO';

COMMENT ON COLUMN JBRF_VIDEO.IMAGEN_PEQUENYA IS 'RUTA DE LA IMAGEN PEQUENYA PERTENECIENTE AL JUEGO';

COMMENT ON COLUMN JBRF_VIDEO.IMAGEN_GRANDE IS 'RUTA DE LA IMAGEN GRANDE PERTENECIENTE AL JUEGO';

COMMENT ON COLUMN JBRF_VIDEO.NPERSONAS_VOTACION IS 'NUMERO DE PERSONAS QUE HAN PARTICIPADO EN LA VOTACION';