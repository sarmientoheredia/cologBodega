package com.bodcol.entidades;

import com.bodcol.entidades.Rack;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-21T16:07:55")
@StaticMetamodel(Seccion.class)
public class Seccion_ { 

    public static volatile SingularAttribute<Seccion, Integer> seccId;
    public static volatile SingularAttribute<Seccion, Boolean> seccBorrLogi;
    public static volatile SingularAttribute<Seccion, String> seccNombre;
    public static volatile ListAttribute<Seccion, Rack> rackList;

}