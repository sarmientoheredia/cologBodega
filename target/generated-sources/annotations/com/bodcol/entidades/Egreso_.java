package com.bodcol.entidades;

import com.bodcol.entidades.Detalleegreso;
import com.bodcol.entidades.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-22T10:33:28")
@StaticMetamodel(Egreso.class)
public class Egreso_ { 

    public static volatile SingularAttribute<Egreso, BigDecimal> egreTotal;
    public static volatile SingularAttribute<Egreso, Usuario> egreUsuaBodeguero;
    public static volatile SingularAttribute<Egreso, Boolean> egreBorrLogi;
    public static volatile SingularAttribute<Egreso, String> egreDepenUsar;
    public static volatile SingularAttribute<Egreso, Integer> egreId;
    public static volatile SingularAttribute<Egreso, Date> egreFecha;
    public static volatile SingularAttribute<Egreso, Usuario> egreUsuaSolicita;
    public static volatile ListAttribute<Egreso, Detalleegreso> detalleegresoList;
    public static volatile SingularAttribute<Egreso, Usuario> egreUsuaLogistico;

}