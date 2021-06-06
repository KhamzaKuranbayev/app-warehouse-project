package uz.pdp.appwarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouseproject.entity.AttachmentContent;

import java.util.Optional;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    // JPA Query
    Optional<AttachmentContent> findByAttachment_Id(Integer attachment_id);

    // JP/QL

    // Native Query

}
