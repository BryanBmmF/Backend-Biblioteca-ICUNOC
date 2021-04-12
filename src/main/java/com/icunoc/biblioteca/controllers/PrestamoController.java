package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.dto.PrestamoDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.LibrosService;
import com.icunoc.biblioteca.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/prestamos"})
public class PrestamoController {
    //constantes para el manejo de mora
    private static final int DIAS_PRESTAMO=7;
    private static final int DIAS_MOROSO_RESTART=0;
    private static final double COSTO_POR_DIA_MOROSO=5.00;
    private static final double COSTO_POR_DIA_MOROSO_RESTART=0;

    List<Prestamo> listaFiltrada;
    String estadoRecivido;

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
                if (dias>DIAS_PRESTAMO){
                    int diasAtrasado = dias-DIAS_PRESTAMO;
                    list.get(i).setDiasMoroso(diasAtrasado);
                    list.get(i).setMora(true);
                    costoMora = list.get(i).getDiasMoroso() * COSTO_POR_DIA_MOROSO;
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


    @PutMapping("/finalizar/{codigoReservacion}")
    public ResponseEntity<?> finalizarPrestamo(@PathVariable("codigoReservacion") String codigo){
        // si no hay problemas se guarda el usuario
        Calendar miFecha = Calendar.getInstance();
        Prestamo prestamo = prestamoService.getOne(codigo);
        prestamo.setEstado(AppConstants.ESTADO_FINALIZADO);
        prestamo.setFechaFin(miFecha);
        prestamoService.save(prestamo);
        return new ResponseEntity(new Mensaje("El prestamo finalizo correctamente !!!"), HttpStatus.OK);
    }

    @PutMapping("/iniciar/{codigoReservacion}")
    public ResponseEntity<?> iniciarPrestamo(@PathVariable("codigoReservacion") String codigo){
        // si no hay problemas se guarda el usuario
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

    @GetMapping(path = {"/codigoReservacion/{codigoReservacion}"})
    public ResponseEntity<List<Prestamo>> listarReservacionxCodigoReservacion(@PathVariable("codigoReservacion") String codigo){
        Prestamo prestamoPorCodReservacion = prestamoService.listarCodigoReservacion(codigo);
        listaFiltrada = new ArrayList<>();
            if (prestamoPorCodReservacion.getEstado().equals(estadoRecivido)){
                listaFiltrada.add(prestamoPorCodReservacion);
            }
        return new ResponseEntity(listaFiltrada,HttpStatus.OK);
    }
    
    @GetMapping(path = {"/carnet/{carnet}"})
    public ResponseEntity<List<Prestamo>> listarReservacionxCarnet(@PathVariable("carnet") String carnet){
        List<Prestamo> list = prestamoService.listarCarnet(carnet);
        listaFiltrada = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEstado().equals(estadoRecivido)){
                listaFiltrada.add(list.get(i));
            }
        }
        return new ResponseEntity(listaFiltrada,HttpStatus.OK);
    }

    @GetMapping(path = {"/dpi/{dpi}"})
    public ResponseEntity<List<Prestamo>> listarReservacionxDPI(@PathVariable("dpi") String dpi){
        List<Prestamo> list = prestamoService.listarDPI(dpi);
        listaFiltrada = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEstado().equals(estadoRecivido)){
                listaFiltrada.add(list.get(i));
            }
        }
        return new ResponseEntity(listaFiltrada,HttpStatus.OK);
    }

    @GetMapping(path = {"/FechaInicio/{fechaInicio}"})
    public ResponseEntity<List<Prestamo>> listarReservacionxFechaInicio(@PathVariable("fechaInicio") String fecha) throws ParseException {
        Calendar fechaBusqueda = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fechaBusqueda.setTime(sdf.parse(fecha));
        List<Prestamo> list = prestamoService.listarFechaInicio(fechaBusqueda);
        listaFiltrada = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEstado().equals(estadoRecivido)){
                listaFiltrada.add(list.get(i));
            }
        }
        return new ResponseEntity(listaFiltrada,HttpStatus.OK);
    }

    //Nuevo: se pasa un Json Libro por medio de http y se valida
    @PostMapping("/crearReservacion")
    public ResponseEntity<?> create(@RequestBody PrestamoDto prestamoDto){
        // guardar reservacion
        Prestamo nuevoPrestamo = new Prestamo(
                prestamoDto.getNombre(),
                prestamoDto.getApellido(),
                prestamoDto.getDpi(),
                prestamoDto.getCarnet(),
                prestamoDto.getCarrera(),
                Calendar.getInstance(),
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
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        prestamoService.delete(id);
        return new ResponseEntity(new Mensaje("El Usuario se elimino correctamente !!!"), HttpStatus.OK);
    }

    public void setPrestamoService(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }
}
