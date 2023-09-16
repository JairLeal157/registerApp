package com.udea.modelo;

import com.udea.modelo.Vehiculos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-15T19:57:26")
@StaticMetamodel(Conductores.class)
public class Conductores_ { 

    public static volatile SingularAttribute<Conductores, Date> fechaRegistro;
    public static volatile SingularAttribute<Conductores, String> apellido;
    public static volatile CollectionAttribute<Conductores, Vehiculos> vehiculosCollection;
    public static volatile SingularAttribute<Conductores, Integer> id;
    public static volatile SingularAttribute<Conductores, String> telefono;
    public static volatile SingularAttribute<Conductores, String> nombre;
    public static volatile SingularAttribute<Conductores, String> email;

}