package com.bodcol.entidades;

import com.bodcol.entidades.Producto;
import com.bodcol.entidades.Seccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-15T16:06:39")
@StaticMetamodel(Rack.class)
public class Rack_ { 

    public static volatile SingularAttribute<Rack, Boolean> rackBorrLogi;
    public static volatile SingularAttribute<Rack, Seccion> rackSeccId;
    public static volatile ListAttribute<Rack, Producto> productoList;
    public static volatile SingularAttribute<Rack, String> rackNombre;
    public static volatile SingularAttribute<Rack, Integer> rackId;

}