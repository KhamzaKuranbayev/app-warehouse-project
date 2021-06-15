package uz.pdp.appwarehouseproject.service.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Attachment;
import uz.pdp.appwarehouseproject.entity.AttachmentContent;
import uz.pdp.appwarehouseproject.repository.AttachmentContentRepository;
import uz.pdp.appwarehouseproject.repository.AttachmentRepository;
import uz.pdp.appwarehouseproject.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;

    private static final String uploadDirectory = "uploads";

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository,
                                 AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    @SneakyThrows
    @Override
    public Response uploadFilesToSystem(List<MultipartFile> files, List<MultipartFile> images) {

        files.stream().filter(Objects::nonNull).forEach(this::copyToSystem);
        images.stream().filter(Objects::nonNull).forEach(this::copyToSystem);

        return new Response("Files saved to System!", true);
    }

    @SneakyThrows
    private void copyToSystem(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        // doc.um.ent.doc, otchet.xls, ...
        String name = "";
        if (originalFilename != null) {
            String[] split = Objects.requireNonNull(originalFilename).split("\\.");
            name = UUID.randomUUID() + "." + split[split.length - 1];
        }

        Attachment attachment = new Attachment(
                originalFilename,
                file.getSize(),
                file.getContentType(),
                name
        );

        attachmentRepository.save(attachment);

        Path path = Paths.get(uploadDirectory + "/" + name);
        Files.copy(file.getInputStream(), path);
    }

    @Override
    public Attachment saveAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public Response saveAttachmentContent(AttachmentContent content) {
        AttachmentContent content1 = attachmentContentRepository.save(content);
        return new Response("File saved!", true, content1.getAttachment().getId());
    }

    @SneakyThrows
    @Override
    public void getFile(Integer attachmentId, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();

            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachment_Id(attachmentId);
            if (optionalAttachmentContent.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();

                response.setContentType(attachment.getContentType());
                response.setHeader("Content-Type", "File: " + attachment.getFileOriginalName());

                FileCopyUtils.copy(attachmentContent.getAsosiyContent(), response.getOutputStream());
            }
        }
    }


}
