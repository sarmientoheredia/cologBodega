package com.bodcol.entidades;

import com.bodcol.entidades.Producto;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-01T22:29:28")
@StaticMetamodel(Historicoproducto.class)
public class Historicoproducto_ { 

    public static volatile SingularAttribute<Historicoproducto, Date> hPFecha;
    public static volatile SingularAttribute<Historicoproducto, BigDecimal> hPStocAnterior;
    public static volatile SingularAttribute<Historicoproducto, Producto> hPProdId;
    public static volatile SingularAttribute<Historicoproducto, Integer> hPId;
    public static volatile SingularAttribute<Historicoproducto, BigDecimal> hPTotal;
    public static volatile SingularAttribute<Historicoproducto, Boolean> hPBorrLogi;
    public static volatile SingularAttribute<Historicoproducto, BigDecimal> hPCostAnterior;

}