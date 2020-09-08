package com.bodcol.entidades;

import com.bodcol.entidades.Detalleingreso;
import com.bodcol.entidades.Proveedor;
import com.bodcol.entidades.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-08T11:01:40")
@StaticMetamodel(Ingreso.class)
public class Ingreso_ { 

    public static volatile SingularAttribute<Ingreso, Integer> ingrId;
    public static volatile ListAttribute<Ingreso, Detalleingreso> detalleingresoList;
    public static volatile SingularAttribute<Ingreso, String> ingrNumeFactura;
    public static volatile SingularAttribute<Ingreso, Usuario> ingrUsuaBodeguero;
    public static volatile SingularAttribute<Ingreso, BigDecimal> ingrTotal;
    public static volatile SingularAttribute<Ingreso, Usuario> ingrUsuaCompPublicas;
    public static volatile SingularAttribute<Ingreso, Boolean> ingrBorrLogi;
    public static volatile SingularAttribute<Ingreso, Date> ingrFecha;
    public static volatile SingularAttribute<Ingreso, String> ingrUnidMilitar;
    public static volatile SingularAttribute<Ingreso, Usuario> ingrUsuaLogistica;
    public static volatile SingularAttribute<Ingreso, Proveedor> ingrProveId;

}