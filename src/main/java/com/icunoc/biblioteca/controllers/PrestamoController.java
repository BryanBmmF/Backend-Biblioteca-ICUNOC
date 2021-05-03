package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.dto.PrestamoDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.InfoBibliotecaService;
import com.icunoc.biblioteca.services.LibrosService;
import com.icunoc.biblioteca.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/prestamos"})
public class PrestamoController {
    //constantes para el manejo de mora
    private static final int DIAS_MOROSO_RESTART=0;
    private static final double COSTO_POR_DIA_MOROSO_RESTART=0;

    List<Prestamo> listaFiltrada;
    String estadoRecivido;
    @Autowired
    InfoBibliotecaService serviceInfo;
    @Autowired
    PrestamoService prestamoService;
    @Autowired
    LibrosService librosService;
    //metodo para mandar una lista de libros al cliente
    @GetMapping("/listaPrestamo/{estado}")
    public ResponseEntity<List<Prestamo>> listarPrestamos(@PathVariable("estado") String estado){
        double costoMora;
        this.estadoRecivido = estado;
        Calendar miFecha = Calendar.getInstance();
        int milisecondsByDay = 86400000;
        List<Prestamo> list = prestamoService.listPorEstado(estado);
        for (int i=0; i<list.toArray().length; i++){
            if (list.get(i).getEstado().equals(AppConstants.ESTADO_ACTIVO)){
                int dias = (int) ((miFecha.getTime().getTime()-list.get(i).getFechaInicio().getTime().getTime()) / milisecondsByDay);
                if (dias>serviceInfo.getOne(1).get().getDiasHabilesPrestamo()){
                    int diasAtrasado = dias-serviceInfo.getOne(1).get().getDiasHabilesPrestamo();
                    list.get(i).setDiasMoroso(diasAtrasado);
                    list.get(i).setMora(true);
                    costoMora = list.get(i).getDiasMoroso() * serviceInfo.getOne(1).get().getCostoDiaMoroso();
                    list.get(i).setCosto(costoMora);
                }else{
                    list.get(i).setDiasMoroso(DIAS_MOROSO_RESTART);
                    list.get(i).setMora(false);
                    list.get(i).setCosto(COSTO_POR_DIA_MOROSO_RESTART);
                }
                prestamoService.save(list.get(i));
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/misPrestamos/{mora}/{estado}")
    public ResponseEntity<List<Prestamo>> listarPrestamo(@PathVariable("mora") boolean estadoMora, @PathVariable("estado") String estado){
        List<Prestamo> list = prestamoService.listPorMora(estadoMora, estado);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/misPrestamos/reporte1")
    public ResponseEntity<List<Object>> reporte1(){
        List<Object> list = prestamoService.reporte1();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/misPrestamos/reporte3")
    public ResponseEntity<List<Object>> reporte3(){
        List<Object> list = prestamoService.reporte3();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/finalizar/{codigoReservacion}")
    public ResponseEntity<Mensaje> finalizarPrestamo(@PathVariable("codigoReservacion") String codigo){
        Calendar miFecha = Calendar.getInstance();
        Prestamo prestamo = prestamoService.getOne(codigo);
        prestamo.setEstado(AppConstants.ESTADO_FINALIZADO);
        prestamo.setFechaFin(miFecha);
        prestamoService.save(prestamo);
        return new ResponseEntity(new Mensaje("El prestamo finalizo correctamente !!!"), HttpStatus.OK);
    }

    @PutMapping("/cancelar/{codigoReservacion}")
    public ResponseEntity<Mensaje> cancelarReservacion(@PathVariable("codigoReservacion") String codigo){
        Prestamo prestamo = prestamoService.getOne(codigo);
        prestamo.setEstado(AppConstants.ESTADO_CANCELADO);
        prestamoService.save(prestamo);
        return new ResponseEntity(new Mensaje("La reservacion finalizo con exito!!!"), HttpStatus.OK);
    }

    @PutMapping("/iniciar/{codigoReservacion}")
    public ResponseEntity<Mensaje> iniciarPrestamo(@PathVariable("codigoReservacion") String codigo){
        Calendar miFecha = Calendar.getInstance();
        Prestamo prestamo = prestamoService.getOne(codigo);
        prestamo.setEstado(AppConstants.ESTADO_ACTIVO);
        prestamo.setFechaInicio(miFecha);
        prestamoService.save(prestamo);
        return new ResponseEntity(new Mensaje("El prestamo inicio correctamente !!!"), HttpStatus.OK);
    }

    @GetMapping(path = {"/{codigoReservacion}"})
    public Prestamo listarReservacion(@PathVariable("codigoReservacion") String codigo){
        return prestamoService.listarCodigoReservacion(codigo);
    }

    //Nuevo: se pasa un Json Libro por medio de http y se valida
    @PostMapping("/crearReservacion")
    public ResponseEntity<Mensaje> create(@RequestBody PrestamoDto prestamoDto){
        // guardar reservacion
        Prestamo nuevoPrestamo = new Prestamo(
                prestamoDto.getNombre(),
                prestamoDto.getApellido(),
                prestamoDto.getDpi(),
                prestamoDto.getCarnet(),
                prestamoDto.getCarrera(),
                Calendar.getInstance(),
                null,
                null,
                prestamoDto.getEstado(),
                prestamoDto.getCodigoReservacion(),
                prestamoDto.isMora(),
                prestamoDto.getCodigoLibro()
        );
        prestamoService.save(nuevoPrestamo);
        return new ResponseEntity(new Mensaje("Se registro correctamente."), HttpStatus.OK);
    }

    @PostMapping("/crearPrestamo")
    public ResponseEntity<Mensaje> crearPrestamo(@RequestBody PrestamoDto prestamoDto){
        // guardar reservacion
        Prestamo nuevoPrestamo = new Prestamo(
                prestamoDto.getNombre(),
                prestamoDto.getApellido(),
                prestamoDto.getDpi(),
                prestamoDto.getCarnet(),
                prestamoDto.getCarrera(),
                null,
                Calendar.getInstance(),
                null,
                prestamoDto.getEstado(),
                prestamoDto.getCodigoReservacion(),
                prestamoDto.isMora(),
                prestamoDto.getCodigoLibro()
        );
        prestamoService.save(nuevoPrestamo);
        return new ResponseEntity(new Mensaje("Se registro correctamente."), HttpStatus.OK);
    }

    //eliminar
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id){
        prestamoService.delete(id);
        return new ResponseEntity(new Mensaje("El Usuario se elimino correctamente !!!"), HttpStatus.OK);
    }

    @GetMapping(path = {"/verificacion/{dpi}/{carnet}"})
    public ResponseEntity<Integer> contarPrestamosReservacionesActivas(@PathVariable("dpi") String dpi, @PathVariable("carnet") String carnet){
        if (dpi.equals("NA")){
            dpi = "";
        }
        if (carnet.equals("NA")){
            carnet = "";
        }
        long conteo = prestamoService.countReservacionesPrestamosActivos(dpi,carnet);
        return new ResponseEntity(conteo,HttpStatus.OK);
    }

    //metodo para mandar una lista de libros al cliente
    @GetMapping("/prestamosFiltrados/{busqueda}/{estado}")
    public ResponseEntity<List<Prestamo>> listarPrestamosPorBusquedayEstado(@PathVariable("busqueda") String busqueda, @PathVariable("estado") String estado){
        List<Prestamo> list = prestamoService.findPrestamoByBusquedaAndEstado(busqueda, estado);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //metodo para mandar una lista de libros al cliente
    @GetMapping("/bitacoraPrestamos/{busqueda}")
    public ResponseEntity<List<Prestamo>> listarPrestamosPorBitacora(@PathVariable("busqueda") String busqueda){
        List<Prestamo> list = prestamoService.findPrestamoByBitacora(busqueda);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //para el mock
    public void setService(InfoBibliotecaService bibliotecaService){
        this.serviceInfo = bibliotecaService;
    }

    public void setPrestamoService(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }
}
