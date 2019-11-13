/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ControllerDatabases.DetallefacturaFacade;
import ControllerDatabases.FacturaFacade;
import Entitys.Cliente;
import Entitys.Detallefactura;
import Entitys.Factura;
import Entitys.Productos;
import static com.sun.faces.facelets.util.Path.context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ARMERO
 */
@RequestScoped
@ManagedBean
public class controllerFactura implements Serializable {

    @EJB
    FacturaFacade controllerFactura;

    @EJB
    DetallefacturaFacade controllerDetalleFactura;

    private List<Factura> factura;
    private List<Cliente> cliente;
    private List<Productos> producto;

    private int cantidad;
    private int total;
    private int totalAPagar;

    public static Cliente llamarCliente = new Cliente(); //llama la lista de los clientes
    public static List<Detallefactura> llamarProducto = new ArrayList<>();//lista los detalles factura

    @PostConstruct
    public void alIniciar() {
        totalAPagar = 0;

    }

    public List<Factura> getFactura() {
        return factura;
    }

    public void setFactura(List<Factura> factura) {
        this.factura = factura;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public List<Productos> getProducto() {
        return producto;
    }

    public void setProducto(List<Productos> producto) {
        this.producto = producto;
    }

    public Cliente getLlamarCliente() {
        return llamarCliente;
    }

    public void setLlamarCliente(Cliente llamarCliente) {
        this.llamarCliente = llamarCliente;
    }

    public List<Detallefactura> getLlamarProducto() {
        return llamarProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
//colocar los productos en la tabla

    public void setLlamarProducto(Productos productoNuevo) {
        Detallefactura temp = new Detallefactura();

        temp.setCodPro(productoNuevo);
        temp.setCantidad(productoNuevo.getCantidadSeleccionada());
        temp.setImporte(productoNuevo.getPrepro() * temp.getCantidad());
        //setTotal(temp.getImporte());

        llamarProducto.add(temp);
        //llamarProducto = new ArrayList();
    }
//calcula los importes

    public void calcular() {
        totalAPagar = 0;

        try {

            for (Detallefactura det : llamarProducto) {
                totalAPagar += det.getImporte();
            }

            System.out.println("total " + totalAPagar);
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    //facturar
    public void facturar() {

        try {
            if (llamarProducto.size() == 0) {
                System.out.println("No se puede crear una factura sin productos");
            } else {
                Factura obj = new Factura();
                obj.setCodCli(llamarCliente);
                obj.setFecha(new Date());
                controllerFactura.create(obj);

                for (Detallefactura detallefactura : llamarProducto) {

                    detallefactura.setIdFactura(obj);
                    controllerDetalleFactura.create(detallefactura);

                }

                totalAPagar = 0;
                llamarCliente = new Cliente();
                llamarProducto = new ArrayList<>();

            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("INFORMACION", "FACTURA CREADA"));

        } catch (Exception e) {
            System.out.println("error " + e);

        }

    }

    //ver datos clientes
    public String llamarDatosCliente(Cliente obj) {
        setLlamarCliente(obj);
        System.out.println("1--->" + this.llamarCliente);
        return "facturaCreate.xhtml";
    }

    //ver datos productos
    public String llamarDatosProducto(Productos obj) {
        setLlamarProducto(obj);
        System.out.println("Cantidad------>" + obj.getCantidadSeleccionada());
        System.out.println("1--->" + this.llamarProducto);
        return "facturaCreate.xhtml";
    }

    public String redirigirFact() {
        return "../Factura/facturaCreate.xhtml";
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(int totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

}
