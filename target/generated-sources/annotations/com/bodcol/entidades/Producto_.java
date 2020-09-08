package com.bodcol.entidades;

import com.bodcol.entidades.Detalleegreso;
import com.bodcol.entidades.Detalleingreso;
import com.bodcol.entidades.Historicoproducto;
import com.bodcol.entidades.Rack;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-08T11:01:40")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, BigDecimal> prodCostActual;
    public static volatile SingularAttribute<Producto, String> prodRackColumna;
    public static volatile SingularAttribute<Producto, Integer> prodId;
    public static volatile ListAttribute<Producto, Detalleegreso> detalleegresoList;
    public static volatile ListAttribute<Producto, Historicoproducto> historicoproductoList;
    public static volatile SingularAttribute<Producto, String> prodUnidMedida;
    public static volatile ListAttribute<Producto, Detalleingreso> detalleingresoList;
    public static volatile SingularAttribute<Producto, String> prodRackFila;
    public static volatile SingularAttribute<Producto, String> prodNombre;
    public static volatile SingularAttribute<Producto, BigDecimal> prodStocActual;
    public static volatile SingularAttribute<Producto, String> prodStatus;
    public static volatile SingularAttribute<Producto, Rack> prodRackId;
    public static volatile SingularAttribute<Producto, BigDecimal> prodTotalActual;
    public static volatile SingularAttribute<Producto, String> prodCodigo;
    public static volatile SingularAttribute<Producto, Boolean> prodBorrLogi;

}