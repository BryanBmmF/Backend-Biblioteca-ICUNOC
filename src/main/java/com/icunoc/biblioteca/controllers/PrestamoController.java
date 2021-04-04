package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.PrestamoDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Prestamo;
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
    private static final String ESTADO_ACTIVO="ACTIVO";
    private static final String ESTADO_FINALIZADO="FINALIZADO";
    private static final String ESTADO_RESERVADO="RESERVADO";
    List<Prestamo> listaActivos;
    String estadoActual;
    @Autowired
    PrestamoService service;
    //metodo para mandar una lista de libros al cliente
    @GetMapping("/listaPrestamo/{estado}")
    public ResponseEntity<List<Prestamo>> listarPrestamos(@PathVariable("estado") String estado){
        double costoMora;
        this.estadoActual=estado;
        List<Prestamo> list = service.list(estado);
        for (int i=0; i<list.toArray().length; i++){
            if (list.get(i).getEstado().equals(ESTADO_ACTIVO)){
                Calendar miFecha = Calendar.getInstance();
                int milisecondsByDay = 86400000;
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
                service.save(list.get(i));
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }


    @PutMapping("/finalizar/{codigoReservacion}")
    public ResponseEntity<?> finalizarPrestamo(@PathVariable("codigoReservacion") String codigo){
        // si no hay problemas se guarda el usuario
        Calendar miFecha = Calendar.getInstance();
        Prestamo prestamo = service.getOne(codigo);
        prestamo.setEstado(ESTADO_FINALIZADO);
        prestamo.setFechaFin(miFecha);
        service.save(prestamo);
        return new ResponseEntity(new Mensaje("El prestamo finalizo correctamente !!!"), HttpStatus.OK);
    }

    @GetMapping(path = {"/{codigoReservacion}"})
    public Prestamo listarReservacion(@PathVariable("codigoReservacion") String codigo){
        return service.listarCodigoReservacion(codigo);
    }
    
    @GetMapping(path = {"/carnet/{carnet}"})
    public ResponseEntity<List<Prestamo>> listarReservacionxCarnet(@PathVariable("carnet") String carnet){
        List<Prestamo> list = service.listarCarnet(carnet);
        for (int i=0; i<list.size();i++){
            if (list.get(i).getEstado().equals(estadoActual)){
                listaActivos = new ArrayList<>();
                listaActivos.add(i, list.get(i));
                return new ResponseEntity(listaActivos,HttpStatus.OK);
            }
        }
        return new ResponseEntity(listaActivos,HttpStatus.OK);
    }

    @GetMapping(path = {"/dpi/{dpi}"})
    public ResponseEntity<List<Prestamo>> listarReservacionxDPI(@PathVariable("dpi") String dpi){
        List<Prestamo> list = service.listarDPI(dpi);
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @GetMapping(path = {"/FechaInicio/{fechaInicio}"})
    public ResponseEntity<List<Prestamo>> listarReservacionxFechaInicio(@PathVariable("fechaInicio") String fecha) throws ParseException {
        Calendar fechaBusqueda = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fechaBusqueda.setTime(sdf.parse(fecha));
        List<Prestamo> list = service.listarFechaInicio(fechaBusqueda);
        return new ResponseEntity(list,HttpStatus.OK);
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
                prestamoDto.getFechaReservacion(),
                prestamoDto.getEstado(),
                prestamoDto.getCodigoReservacion(),
                prestamoDto.isMora(),
                prestamoDto.getCodigoLibro()
        );
        service.save(nuevoPrestamo);
        return new ResponseEntity(new Mensaje("Se registro correctamente."), HttpStatus.OK);
    }

    public void setService(PrestamoService prestamoService) {
        this.service = prestamoService;
    }
}
