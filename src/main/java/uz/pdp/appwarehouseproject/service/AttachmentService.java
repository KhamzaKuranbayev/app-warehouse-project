package uz.pdp.appwarehouseproject.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Attachment;
import uz.pdp.appwarehouseproject.entity.AttachmentContent;

import javax.servlet.http.HttpServletResponse;

@Service
public interface AttachmentService {

    Attachment saveAttachment(Attachment attachment);

    Response saveAttachmentContent(AttachmentContent content);

    void getFile(Integer id, HttpServletResponse response);
}
