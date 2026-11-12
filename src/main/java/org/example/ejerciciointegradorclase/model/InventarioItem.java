package org.example.ejerciciointegradorclase.model;

import java.time.LocalDate;


public class InventarioItem {

    private int id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private String estado;

    public InventarioItem() {
    }

    public InventarioItem(int id, String nombre, String descripcion, LocalDate fecha, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public InventarioItem(String nombre, String descripcion, LocalDate fecha, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
