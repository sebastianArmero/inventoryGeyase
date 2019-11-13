/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ControllerDatabases.ProductosFacade;
import Entitys.Productos;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
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

public class controllerProduct implements Serializable {

    @EJB
    ProductosFacade controllerProducto;

    private List<Productos> producto;

    private String descriPro;
    private int prePro;
    private int cantidadPro;
    private BigDecimal sumProductos;

    public static Productos productoEditado;

    @PostConstruct
    public void despuesDeCargar() {
        listarProductos();
        System.out.println("Entrooo");
    }

    public String getDescriPro() {
        return descriPro;
    }

    public void setDescriPro(String descriPro) {
        this.descriPro = descriPro;
    }

    public int getPrePro() {
        return prePro;
    }

    public void setPrePro(int prePro) {
        this.prePro = prePro;
    }

    public int getCantidadPro() {
        return cantidadPro;
    }

    public void setCantidadPro(int cantidadPro) {
        this.cantidadPro = cantidadPro;
    }

    public List<Productos> getProducto() {
        return producto;
    }

    public void setProducto(List<Productos> producto) {
        this.producto = producto;
    }

    public Productos getProductoEditado() {
        return productoEditado;
    }

    public void setProductoEditado(Productos productoEditado) {
        this.productoEditado = productoEditado;
    }

    //crear Producto
    public void createPro() {
        try {
            Productos obj = new Productos();

            obj.setDespro(descriPro);
            obj.setPrepro(prePro);
            obj.setCantidadpro(cantidadPro);

            controllerProducto.create(obj);

            System.out.println("Creado");
        } catch (Exception e) {
            System.out.println("error " + e);

        }
    }
//listar Producto

    public void listarProductos() {

        producto = controllerProducto.findAll();
    }

    //Borrar producto
    public void borrarProducto(Productos temp) {
        controllerProducto.remove(temp);
        listarProductos();
    }

    //ver datos para editarlos
    public String verDatosEditarProducto(Productos obj) {
        setProductoEditado(obj);
        System.out.println("1--->" + this.productoEditado);
        return "producEdit.xhtml";
    }

    //guardar datos editados
    public void verDatosEditar() throws IOException {
        //redireccion
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        System.out.println("aaaaaaaaa");
        System.out.println("2--->" + this.productoEditado);
        controllerProducto.edit(this.productoEditado);
        ctx.redirect(ctxPath + "/faces/Product/productList.xhtml");

    }
//suma de la cantidad de productos

    public void calcularSumaProducto() {
        sumProductos = (BigDecimal) controllerProducto.totalProductos();
        System.out.println(sumProductos);
    }

    //generar PDF

    public void generarPDF() throws JRException, IOException {
        String filename = "reportes de productos.pdf";
        String jasperPath = "/Product/reporteProductos.jasper";
        this.PDF(null, jasperPath, producto, filename);

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
    
    
    
    public String redirigirPro() {
        return "../Product/productCreate.xhtml";
    }

    public String redirigirListPro() {
        return "../Product/productList.xhtml";
    }

    public BigDecimal getSumProductos() {
        return sumProductos;
    }

    public void setSumProductos(BigDecimal sumProductos) {
        this.sumProductos = sumProductos;
    }

}
