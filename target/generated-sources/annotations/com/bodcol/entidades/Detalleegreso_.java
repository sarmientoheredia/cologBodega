package com.bodcol.entidades;

import com.bodcol.entidades.Egreso;
import com.bodcol.entidades.Producto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-08-17T09:32:27")
@StaticMetamodel(Detalleegreso.class)
public class Detalleegreso_ { 

    public static volatile SingularAttribute<Detalleegreso, BigDecimal> detaEgreCantEgresa;
    public static volatile SingularAttribute<Detalleegreso, BigDecimal> detaTotal;
    public static volatile SingularAttribute<Detalleegreso, Egreso> detaEgreEgreId;
    public static volatile SingularAttribute<Detalleegreso, String> detaEgreUnidMedida;
    public static volatile SingularAttribute<Detalleegreso, Boolean> detaEgreBorrLogi;
    public static volatile SingularAttribute<Detalleegreso, Integer> detaEgreId;
    public static volatile SingularAttribute<Detalleegreso, Producto> detaEgreProdId;
    public static volatile SingularAttribute<Detalleegreso, BigDecimal> detaEgrePreciEgresa;

}