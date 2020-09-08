package com.bodcol.entidades;

import com.bodcol.entidades.Egreso;
import com.bodcol.entidades.Ingreso;
import com.bodcol.entidades.Rol;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-08T11:50:45")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Rol> usuaRolId;
    public static volatile SingularAttribute<Usuario, String> usuaPassword;
    public static volatile ListAttribute<Usuario, Egreso> egresoList1;
    public static volatile SingularAttribute<Usuario, String> usuaUsuario;
    public static volatile ListAttribute<Usuario, Egreso> egresoList2;
    public static volatile SingularAttribute<Usuario, String> usuaNombre;
    public static volatile ListAttribute<Usuario, Ingreso> ingresoList;
    public static volatile ListAttribute<Usuario, Egreso> egresoList;
    public static volatile SingularAttribute<Usuario, String> usuaCedula;
    public static volatile ListAttribute<Usuario, Ingreso> ingresoList2;
    public static volatile SingularAttribute<Usuario, Integer> usuaId;
    public static volatile SingularAttribute<Usuario, String> usuaiArma;
    public static volatile ListAttribute<Usuario, Ingreso> ingresoList1;
    public static volatile SingularAttribute<Usuario, String> usuaApellido;
    public static volatile SingularAttribute<Usuario, Boolean> usuaBorrLogi;
    public static volatile SingularAttribute<Usuario, String> usuaGrado;

}