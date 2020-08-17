package com.bodcol.entidades;

import com.bodcol.entidades.Ingreso;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-08-17T09:32:27")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, String> provRuc;
    public static volatile ListAttribute<Proveedor, Ingreso> ingresoList;
    public static volatile SingularAttribute<Proveedor, String> provEmail;
    public static volatile SingularAttribute<Proveedor, String> provNombre;
    public static volatile SingularAttribute<Proveedor, String> provTelefono;
    public static volatile SingularAttribute<Proveedor, Boolean> provBorrLogi;
    public static volatile SingularAttribute<Proveedor, Integer> provId;

}