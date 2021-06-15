package uz.pdp.appwarehouseproject.service;


import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Attachment;
import uz.pdp.appwarehouseproject.entity.AttachmentContent;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public interface AttachmentService {



    Attachment saveAttachment(Attachment attachment);

    Response saveAttachmentContent(AttachmentContent attachmentContent);


    @SneakyThrows
    void getFile(Integer attachmentId, HttpServletResponse response);



    Response uploadFilesToSystem(List<MultipartFile> files, List<MultipartFile>  images);
}
