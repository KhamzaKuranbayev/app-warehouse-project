package uz.pdp.appwarehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileOriginalName;    // pdp.jpg, inn.pdf, ...

    private long size;                  // 2 545 545 bytes

    private String contentType;         // application/json, image/jpg, ...

    // BU NAME FILE NI SYSTEMGA SAQLAGANDA KERAK BO'LADI, VA PAPKANI ICHIDAN QIDIRISH KERAK BO'LADI
    private String name;

    public Attachment(String fileOriginalName, long size, String contentType) {
        this.fileOriginalName = fileOriginalName;
        this.size = size;
        this.contentType = contentType;
    }

    public Attachment(String fileOriginalName, long size, String contentType, String name) {
        this.fileOriginalName = fileOriginalName;
        this.size = size;
        this.contentType = contentType;
        this.name = name;
    }
}
