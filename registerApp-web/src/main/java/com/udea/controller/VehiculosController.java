/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.VehiculosController to edit this template
 */
package com.udea.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.udea.ejb.ConductoresFacadeLocal;
import com.udea.ejb.VehiculosFacadeLocal;
import com.udea.modelo.Conductores;
import com.udea.modelo.Vehiculos;
import java.util.ArrayList;
import javax.faces.component.UIComponent;

/**
 *
 * @author jairl
 */
public class VehiculosController implements Serializable {
    @EJB
    private VehiculosFacadeLocal vehiculosFacade;
    @EJB
    private ConductoresFacadeLocal conductoresFacade;
    
    
    
    private String warningMessage;

    private UIComponent myButton;
    private String modelo;
    private String placa;
    private int year;
    private int conductor_id;
    private List<Vehiculos> vehiculosList;
    private static int conductorIdAux;
    
    
    public int getConductorIdAux() {
        return conductorIdAux;
    }
    
    public List<Vehiculos> vehiculosByConductor(int conductorId){
      List<Vehiculos> result=new ArrayList<Vehiculos>();
      
      for(Vehiculos vehiculo:this.getVehiculosList()){
        
          if(vehiculo.getConductorId()== conductorId){
              
              result.add(vehiculo);
              
              
          }
       } 
      return result;
    };
    
    public String setConductorIdAux(int conductorIdAux) {
        this.conductorIdAux = conductorIdAux;
        
        return "success";
        
    }
    public boolean disable = true;
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    
    public List<Vehiculos> getVehiculosList() {
        if((this.vehiculosList == null) || (this.vehiculosList.isEmpty())){
            this.vehiculosList = this.refresh();
        }
        return this.vehiculosList;
    }

    private List<Vehiculos> refresh() {
        return vehiculosFacade.findAll();
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
    
    public String getLanguage(){
        return this.getLocale().getLanguage();
    }
    public void  changeLanguage(String language){
        this.setLocale(new Locale(language) );
    }
    
    public VehiculosController(){
        
    }

    //verifica que exista el conductor
    public boolean existeConductor(){
        boolean existe = false;
         for(Conductores conductor : this.conductoresFacade.findAll()){
            if(conductor.getId() == this.conductor_id){      
              existe = true;
              break;
            }
        }
        
        return existe;
    }


    public String guardar(){
        if(!this.existeConductor()){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El conductor no existe"));
            return null;
        }
        Vehiculos vehiculo = new Vehiculos();
        vehiculo.setModelo(this.modelo);
        vehiculo.setPlaca(this.placa);
        vehiculo.setAño(this.year);
        vehiculo.setConductorId(this.conductor_id);
        this.vehiculosFacade.create(vehiculo);
        this.vehiculosList = this.refresh();
        Vehiculos result = this.vehiculosFacade.find(vehiculo.getId());
        if(result == null){
            FacesContext context = FacesContext.getCurrentInstance();
             context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se registro el vehiculo"));
            return null;
        }
        return "success";
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getConductor_id() {
        return conductor_id;
    }

    public void setConductor_id(int conductor_id) {
        this.conductor_id = conductor_id;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public UIComponent getMyButton() {
        return myButton;
    }

    public void setMyButton(UIComponent myButton) {
        this.myButton = myButton;
    }
    
    
    
}
