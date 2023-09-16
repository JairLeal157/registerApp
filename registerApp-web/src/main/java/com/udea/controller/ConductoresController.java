package com.udea.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.udea.ejb.ConductoresFacadeLocal;
import com.udea.ejb.VehiculosFacadeLocal;
import com.udea.modelo.Conductores;

public class ConductoresController  implements Serializable {

    @EJB
    private ConductoresFacadeLocal conductoresFacade;
    @EJB
    private VehiculosFacadeLocal vehiculosFacade;
    
    private UIComponent myButton;
    private int cedula;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;;
    private List<Conductores> conductoresList;
    public boolean disable = true;
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public ConductoresFacadeLocal getConductoresFacade() {
        return conductoresFacade;
    }

    public void setConductoresFacade(ConductoresFacadeLocal conductoresFacade) {
        this.conductoresFacade = conductoresFacade;
    }

    public UIComponent getMyButton() {
        return myButton;
    }

    public void setMyButton(UIComponent myButton) {
        this.myButton = myButton;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Conductores> getConductoresList() {
       if((this.conductoresList == null) || (this.conductoresList.isEmpty())){
           this.conductoresList=  this.refresh();
       }
       return this.conductoresList;
    }
    private List<Conductores>  refresh() {
        return conductoresFacade.findAll();
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
    
    
    public ConductoresController(){
        
    } 
    //verifica que la cedula no exista en la lista 
    public boolean verificarCedula(){
        boolean existe = false;
        for(Conductores conductor : this.getConductoresList()){
            if(conductor.getId() == this.cedula){      
              existe = true;
              break;
            }
        }
        return existe;
    }

    public String guardar(){
        if(this.verificarCedula()){
            return "La cedula ya existe";
        }
        Conductores conductor = new Conductores();
        conductor.setId(this.cedula);
        conductor.setNombre(this.nombre);
        conductor.setApellido(this.apellidos);
        conductor.setEmail(this.correo);
        conductor.setTelefono(this.telefono);
        this.conductoresFacade.create(conductor);
        this.conductoresList = this.refresh();
        Conductores result = this.conductoresFacade.find(conductor.getId());
        if(result == null){
            return "El conductor no se ha guardado";
        };
        return "El conductor se ha guardado exitosamente";
    };
    
    
  
}
