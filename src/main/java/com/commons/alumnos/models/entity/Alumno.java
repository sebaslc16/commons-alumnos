package com.commons.alumnos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    @Column(name = "create_at")
    //Temporal para indicar que la fecha incluya el formato que se desee
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Lob
    @JsonIgnore
    private byte[] foto;

    //Metodo que se pre-ejecuta antes de hacer un insert,
    //para asignar la fecha automaticamente
    @PrePersist
    public void prePersistDate() {
        this.createAt = new Date();
    }

    public Integer getFotoHasCode() {
        return (this.foto != null) ? this.foto.hashCode() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    //Sobrescribir el metodo equals para validaciones
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(!(obj instanceof Alumno)){
            return false;
        }
        Alumno alumno = (Alumno) obj;
        return this.id != null && this.id.equals(alumno.getId());
    }
}
