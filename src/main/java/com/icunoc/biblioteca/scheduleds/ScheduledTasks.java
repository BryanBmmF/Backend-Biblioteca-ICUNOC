package com.icunoc.biblioteca.scheduleds;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.LibrosService;
import com.icunoc.biblioteca.services.PrestamoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private LibrosService librosService;

    @Scheduled(fixedRate = 30000)
    public void scheduleTaskVerificacionExpiracion() {
        Calendar miFecha = Calendar.getInstance();
        int milisecondsByDay = 86400000;
        List<Prestamo> list = prestamoService.listPorEstado(AppConstants.ESTADO_RESERVADO);
        for (int i=0; i<list.toArray().length; i++){
                int diasDesdeReservacion = (int) ((miFecha.getTime().getTime()-list.get(i).getFechaReservacion().getTime().getTime()) / milisecondsByDay);
                if (diasDesdeReservacion>1){
                    list.get(i).setEstado(AppConstants.ESTADO_EXPIRADO);
                    Libro actualizarLibro = librosService.getByCodigo((list.get(i).getCodigoLibro())).get();
                    int nuevoStock = actualizarLibro.getStock()+1;
                    actualizarLibro.setStock(nuevoStock);
                    librosService.save(actualizarLibro);
                }
            prestamoService.save(list.get(i));
        }
        logger.info("Se ha realizado la verificación de Expiración :: Hora de Ejecución - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }

    public void setPrestamoService(PrestamoService prestamoServiceMock) {
        this.prestamoService = prestamoServiceMock;
    }

    public void setLibrosService(LibrosService librosServiceMock) {
        this.librosService = librosServiceMock;
    }
}