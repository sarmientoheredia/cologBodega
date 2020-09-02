drop database bodcol;
create database bodcol;
use bodcol;


CREATE TABLE rol (
  rol_Id int(10) AUTO_INCREMENT primary KEY,
  rol_Nombre varchar(45) unique,
  rol_BorrLogi boolean DEFAULT '1'
);


CREATE TABLE usuario (
  usua_Id int(10) AUTO_INCREMENT PRIMARY KEY,
  usua_Grado varchar(20) default 'S/G',
  usuai_Arma varchar(20) DEFAULT 'S/A',
  usua_Cedula varchar(12)  UNIQUE,
  usua_Nombre varchar(60) NOT NULL,
  usua_Apellido varchar(60) NOT NULL,
  usua_Usuario varchar(45) unique,
  usua_Password varchar(45) not null,
  usua_Rol_Id int(10) NOT NULL,
  usua_BorrLogi boolean DEFAULT '1',
  CONSTRAINT Fk_Usuario_Rol FOREIGN KEY (usua_Rol_Id) REFERENCES rol (rol_Id)
);




CREATE TABLE proveedor (
  prov_Id int(10)  AUTO_INCREMENT PRIMARY KEY,
  prov_Ruc varchar(50) unique,
  prov_Nombre varchar(50) not null,
  prov_Telefono varchar(30) DEFAULT NULL,
  prov_Email varchar(50) not null,
  prov_BorrLogi boolean DEFAULT '1'
);



CREATE TABLE seccion (
  secc_Id int(10) AUTO_INCREMENT PRIMARY KEY,
  secc_Nombre varchar(50) unique,
  secc_BorrLogi boolean DEFAULT '1'
);



CREATE TABLE rack (
  rack_Id int(10) AUTO_INCREMENT PRIMARY KEY,
  rack_Secc_Id int(10) NOT NULL,
  rack_Nombre varchar(20) unique,
  rack_BorrLogi boolean DEFAULT '1',
  CONSTRAINT Fk_Rack_Seccion FOREIGN KEY (rack_Secc_Id) REFERENCES seccion (secc_id)
);



CREATE TABLE producto (
  prod_Id int(10) AUTO_INCREMENT PRIMARY KEY,
  prod_Rack_Id int(10) NOT NULL,
  prod_RackFila varchar(10) NOT NULL,
  prod_RackColumna varchar(10) NOT NULL,
  prod_Codigo varchar(60) unique,
  prod_Nombre varchar(80) not null,
  prod_UnidMedida varchar(30) NOT NULL,
  prod_StocActual decimal(10,4) DEFAULT '0.0000',
  prod_CostActual decimal(10,4) DEFAULT '0.0000',
  prod_TotalActual decimal(10,4) DEFAULT '0.0000',
  prod_Status varchar(30) DEFAULT 'Ativo',
  prod_BorrLogi boolean DEFAULT '1',
  CONSTRAINT Fk_Producto_Racks FOREIGN KEY (prod_Rack_Id) REFERENCES rack (rack_id)
);


CREATE TABLE historicoproducto (
  HP_Id int(10) AUTO_INCREMENT PRIMARY KEY,
  HP_Fecha date,
  HP_Prod_Id int(10) unique,
  HP_StocAnterior decimal(10,4) DEFAULT '0.0000',
  HP_CostAnterior decimal(10,4) DEFAULT '0.0000',
  HP_Total decimal(10,4) DEFAULT '0.0000',
  HP_BorrLogi boolean DEFAULT '1',
  CONSTRAINT Fk_SH_Productos FOREIGN KEY (HP_Prod_Id) REFERENCES producto (prod_id)
);





CREATE TABLE ingreso (
  ingr_Id int(10) AUTO_INCREMENT PRIMARY KEY,
  ingr_Fecha date DEFAULT NULL,
  ingr_NumeFactura varchar(80) UNIQUE,
  ingr_Prove_Id int(10)NOT NULL,
  ingr_UnidMilitar varchar(40) NOT NULL,
  ingr_Usua_Bodeguero int(10) NOT NULL,
  ingr_Usua_CompPublicas int(10)  NOT NULL,
  ingr_Usua_Logistica int(10) NOT NULL,
  ingr_Total decimal(10,4) DEFAULT '0.0000',
  ingr_BorrLogi boolean DEFAULT '1',
  CONSTRAINT Fk_Ingreso_MilitarBodeguero FOREIGN KEY (ingr_Usua_Bodeguero) REFERENCES usuario (usua_id),
  CONSTRAINT Fk_Ingreso_MilitarComprasPublicas FOREIGN KEY (ingr_Usua_CompPublicas) REFERENCES usuario (usua_id),
  CONSTRAINT Fk_Ingreso_MilitarOficial FOREIGN KEY (ingr_Usua_Logistica) REFERENCES usuario (usua_id),
  CONSTRAINT Fk_Ingreso_Proveedor FOREIGN KEY (ingr_Prove_Id) REFERENCES proveedor (prov_id)
);


CREATE TABLE  detalleingreso  (
   detaIngr_Id  int(10)  AUTO_INCREMENT PRIMARY KEY,
   detaIngr_Ingr_Id  int(10) NOT NULL,
   detaIngr_Prod_Id  int(10) UNIQUE,
   detaIngr_unidMedida  varchar(45) DEFAULT NULL,
   detaIngr_CantIngresa  decimal(10,4) DEFAULT '0.0000',
   deraIngr_PreciIngresa  decimal(10,4) DEFAULT '0.0000',
   detaIngr_Total  decimal(10,4) DEFAULT '0.0000',
   detaIngr_BorrLogi  boolean DEFAULT '1',
  CONSTRAINT  Fk_detalleIngreso_Ingreso  FOREIGN KEY ( detaIngr_Ingr_Id ) REFERENCES  ingreso  ( ingr_id ),
  CONSTRAINT  Fk_detalleIngreso_Producto  FOREIGN KEY ( detaIngr_Prod_Id ) REFERENCES  producto  ( prod_id )
);






CREATE TABLE  egreso  (
   egre_Id  int(10) AUTO_INCREMENT PRIMARY KEY,
   egre_Fecha  date ,
   egre_Usua_Solicita  int(10)  NOT NULL,
   egre_DepenUsar  varchar(60) DEFAULT NULL,
   egre_Usua_Bodeguero  int(10) NOT NULL,
   egre_Usua_Logistico  int(10)  NOT NULL,
   egre_Total  decimal(10,4) DEFAULT '0.0000',
   egre_BorrLogi  boolean DEFAULT '1',
  CONSTRAINT  Fk_Egresos_MilitarBodeguero  FOREIGN KEY ( egre_Usua_Bodeguero ) REFERENCES  usuario  ( usua_id ),
  CONSTRAINT  Fk_Egresos_MilitarLogistico  FOREIGN KEY ( egre_Usua_Logistico ) REFERENCES  usuario  ( usua_id ),
  CONSTRAINT  Fk_Egresos_MilitarSolicita  FOREIGN KEY ( egre_Usua_Solicita ) REFERENCES  usuario  ( usua_id )
);



CREATE TABLE  detalleegreso  (
   detaEgre_Id  int(10) AUTO_INCREMENT PRIMARY KEY,
   detaEgre_Egre_Id  int(10)  NOT NULL,
   detaEgre_Prod_Id  int(10)  unique,
   detaEgre_UnidMedida  varchar(45) NOT NULL,
   detaEgre_CantEgresa  decimal(10,4) DEFAULT '0.0000',
   detaEgre_PreciEgresa  decimal(10,4) DEFAULT '0.0000',
   deta_Total  decimal(10,4) DEFAULT '0.0000',
   detaEgre_BorrLogi  boolean DEFAULT '1',
  CONSTRAINT  Fk_detEgreso_Egreso  FOREIGN KEY ( detaEgre_Egre_Id ) REFERENCES  egreso  ( egre_id ),
  CONSTRAINT  Fk_detEgreso_Producto  FOREIGN KEY ( detaEgre_Prod_Id ) REFERENCES  producto  ( prod_id )
);





