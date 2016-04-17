package com.ctproject.tokohislamhologram.Models;

/**
 * Created by farhan on 2/29/16.
 */
import java.util.HashMap;
import java.util.Map;

public class Listdosen {
    //Variabel dalam json
    private Integer id;
    private String nama;
    private String info;
    private String carabaca;
    private String foto;
    private String video;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    //getter dan setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getInfo() { return info; }

    public void setInfo(String info) { this.info = info; }

    public String getCarabaca() { return carabaca; }

    public void setCarabaca(String carabaca) { this.carabaca = carabaca; }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
