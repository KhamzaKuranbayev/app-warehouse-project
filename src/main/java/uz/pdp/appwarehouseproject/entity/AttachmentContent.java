package uz.pdp.appwarehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Attachment attachment;

    private byte[] asosiyContent;   // ASOSIY CONTENT (MAG'ZI)

    public AttachmentContent(Attachment attachment, byte[] asosiyContent) {
        this.attachment = attachment;
        this.asosiyContent = asosiyContent;
    }
}
