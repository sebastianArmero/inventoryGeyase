/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ControllerDatabases.ClienteFacade;
import Entitys.Cliente;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ARMERO
 */
@RequestScoped
@ManagedBean
public class controllerClientCreate implements Serializable {

    @EJB
    ClienteFacade controllerCliente;

    private List<Cliente> cliente;

    private String nomCli;
    private String apeCli;
    private String sexoCli;
    private String cedCli;
    private String telCli;
    private String emailCli;
    private String dirCli;
    private int numcliente;

    public static Cliente clienteEditado;

    @PostConstruct
    public void despuesDeCargar() {
        listar();
        System.out.println("Entrooo");
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getApeCli() {
        return apeCli;
    }

    public void setApeCli(String apeCli) {
        this.apeCli = apeCli;
    }

    public String getSexoCli() {
        return sexoCli;
    }

    public void setSexoCli(String sexoCli) {
        this.sexoCli = sexoCli;
    }

    public String getCedCli() {
        return cedCli;
    }

    public void setCedCli(String cedCli) {
        this.cedCli = cedCli;
    }

    public String getTelCli() {
        return telCli;
    }

    public void setTelCli(String telCli) {
        this.telCli = telCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getDirCli() {
        return dirCli;
    }

    public void setDirCli(String dirCli) {
        this.dirCli = dirCli;
    }

 

    public Cliente getClienteEditado() {
        return clienteEditado;
    }

    public void setClienteEditado(Cliente clienteEditado) {
        this.clienteEditado = clienteEditado;
    }

    public int getNumcliente() {
        return numcliente;
    }

    public void setNumcliente(int numcliente) {
        this.numcliente = numcliente;
    }

    //crear Clientes
    public void create() {
        try {

            Cliente obj = new Cliente();

            obj.setNomCli(nomCli);
            obj.setApeCli(apeCli);
            obj.setCedCli(cedCli);
            obj.setSexoCli(sexoCli);
            obj.setDirCli(dirCli);
            obj.setEmailCli(emailCli);
            obj.setTelCli(telCli);

            controllerCliente.create(obj);
            System.out.println("Creado");

        } catch (Exception e) {
            System.out.println("error " + e);
        }

    }
//listar Clientes

    public void listar() {
        cliente = controllerCliente.findAll();
    }
    //Eliminar Cliente

    public void eliminarCliente(Cliente temp) {
        controllerCliente.remove(temp);
        System.out.println("eliminado");
        listar();
    }

    //Ver datos para editar cliente
    public String verDatosEditarCliente(Cliente obj) {
        setClienteEditado(obj);
        System.out.println("1--->" + this.clienteEditado);
        return "clienteEditar.xhtml";
    }


//    guardar datos editados
//    public void verDatosEditar() throws IOException {
//        redireccion
//        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
//        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
//        System.out.println("aaaaaaaaa");
//        System.out.println("2--->" + this.clienteEditado);
//        controllerCliente.edit(this.clienteEditado);
//        ctx.redirect(ctxPath + "/faces/Client/clientList.xhtml");
//
//    }
 //guardar datos editados
    public void verDatosEditar() throws IOException {
        //redireccion
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        System.out.println("aaaaaaaaa");
        System.out.println("2--->" + this.clienteEditado);
        controllerCliente.edit(this.clienteEditado);
        ctx.redirect(ctxPath + "/faces/Client/clientList.xhtml");

    }
    //suma de clientes
    public void totalCliente() {
        //  numcliente = (int) controllerCliente.totalCliente();
        setNumcliente(((Number) controllerCliente.totalCliente()).intValue());
        // numcliente =((Number) controllerCliente.totalCliente()).intValue();
        // System.out.println("total de clientes " + getNumcliente());
        // System.out.print("total de clientes " + numcliente);

    }
//generar PDF

    public void generarPDF() throws JRException, IOException {
        String filename = "reporteClientes.pdf";
        String jasperPath = "/Client/reporteClientes.jasper";
        this.PDF(null, jasperPath, cliente, filename);

        //this.PDF(params, jasperPath, cliente, filename);
    }

    public void PDF(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {

        String relativewebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);

        //String relativewebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath+".jrxml");
        //String relativewebPathJasper = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath+".jasper");
        //JasperCompileManager.compileReportToFile(relativewebPath, relativewebPathJasper);
        File file = new File(relativewebPath);
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public String redirigir() {
        return "../Client/clientCreate.xhtml";
    }

    public String redirigirList() {
        return "../Client/clientList.xhtml";
    }

    public String redirigirHome() {
        return "../Home/home.xhtml";
    }

}
