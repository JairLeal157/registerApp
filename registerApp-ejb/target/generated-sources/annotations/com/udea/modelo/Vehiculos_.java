package com.udea.modelo;

import com.udea.modelo.Conductores;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-15T21:06:15")
@StaticMetamodel(Vehiculos.class)
public class Vehiculos_ { 

    public static volatile SingularAttribute<Vehiculos, Conductores> conductorId;
    public static volatile SingularAttribute<Vehiculos, Integer> id;
    public static volatile SingularAttribute<Vehiculos, String> modelo;
    public static volatile SingularAttribute<Vehiculos, Integer> año;
    public static volatile SingularAttribute<Vehiculos, String> placa;

}