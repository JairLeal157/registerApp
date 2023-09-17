package com.udea.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.udea.ejb.ConductoresFacadeLocal;
import com.udea.modelo.Conductores;
import javax.faces.application.FacesMessage;

public class ConductoresController  implements Serializable {

    @EJB
    private ConductoresFacadeLocal conductoresFacade;
     
    private String warningMessage;

    private UIComponent myButton;
    private int cedula;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;;
    private List<Conductores> conductoresList;
    public boolean disable = true;
    
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

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
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

   private Locale locale= FacesContext.getCurrentInstance().getViewRoot().getLocale();
    public Locale getLocale(){
    return locale;   
}

    public String getLanguage(){
       return locale.getLanguage();
    }
    
    
    public void changeLanguage(String language){
       locale=new Locale(language);
       
       FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
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
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cédula ya existe"));
            return null;
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
            FacesContext context = FacesContext.getCurrentInstance();
             context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se registro el usuario"));
            return null;
        }
        return "success";
    };
    
    
    
    
    
    
    
    
    
  
}
