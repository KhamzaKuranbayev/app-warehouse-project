package uz.pdp.appwarehouseproject.controller;

import com.google.common.io.Files;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Attachment;
import uz.pdp.appwarehouseproject.entity.AttachmentContent;
import uz.pdp.appwarehouseproject.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    final AttachmentService attachmentService;

    private final Path root = Paths.get("src/main/resources/files");

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }


    @PostMapping("/uploadArray")
    public String saveArray(List<MultipartFile> files, List<MultipartFile> images) {

        List<MultipartFile> files1 = files;
        for (MultipartFile file : files1) {

        }

        List<MultipartFile> images1 = images;
        for (MultipartFile file : images1) {

        }

        return "Test";

    }


    @SneakyThrows
    @PostMapping("/upload")
    public Response uploadFileToDB(MultipartHttpServletRequest request) {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {

            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();

            Attachment attachment = new Attachment(
                    originalFilename,
                    size,
                    contentType
            );

           Attachment savedAttachment = attachmentService.saveAttachment(attachment);

           // FILENI CONTENTINI SAQLAYMIZ
            AttachmentContent attachmentContent = new AttachmentContent(
                    savedAttachment,
                    file.getBytes()
            );

            return attachmentService.saveAttachmentContent(attachmentContent);
        }

        return new Response("Error!", false);
    }

    @GetMapping("/getFile/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response){
         attachmentService.getFile(id, response);
    }


    // File system ga upload qilishni ko'rish

    /*@PostMapping("/uploadToSystem")
    public Response uploadFileToSystem(MultipartHttpServletRequest request) {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if(file != null) {

            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();

            Attachment attachment = new Attachment(
                    originalFilename,
                    size,
                    contentType
            );





        }


    }*/

}