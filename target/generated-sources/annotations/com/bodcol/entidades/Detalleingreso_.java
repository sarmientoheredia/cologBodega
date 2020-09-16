package com.bodcol.entidades;

import com.bodcol.entidades.Ingreso;
import com.bodcol.entidades.Producto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-15T16:06:39")
@StaticMetamodel(Detalleingreso.class)
public class Detalleingreso_ { 

    public static volatile SingularAttribute<Detalleingreso, Producto> detaIngrProdId;
    public static volatile SingularAttribute<Detalleingreso, BigDecimal> detaIngrCantIngresa;
    public static volatile SingularAttribute<Detalleingreso, BigDecimal> deraIngrPreciIngresa;
    public static volatile SingularAttribute<Detalleingreso, Boolean> detaIngrBorrLogi;
    public static volatile SingularAttribute<Detalleingreso, BigDecimal> detaIngrTotal;
    public static volatile SingularAttribute<Detalleingreso, Ingreso> detaIngrIngrId;
    public static volatile SingularAttribute<Detalleingreso, Integer> detaIngrId;

}