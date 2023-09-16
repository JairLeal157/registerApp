/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.ejb;

import com.udea.modelo.Conductores;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jairl
 */
@Stateless
public class ConductoresFacade extends AbstractFacade<Conductores> implements ConductoresFacadeLocal {

    @PersistenceContext(unitName = "com.udea_registerApp-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConductoresFacade() {
        super(Conductores.class);
    }
    
}
