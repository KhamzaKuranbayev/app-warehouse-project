package uz.pdp.appwarehouseproject.service;


import org.springframework.stereotype.Service;
import uz.pdp.appwarehouseproject.dto.OperationDTO;
import uz.pdp.appwarehouseproject.dto.Response;
import uz.pdp.appwarehouseproject.entity.Category;
import uz.pdp.appwarehouseproject.entity.OperationHistory;
import uz.pdp.appwarehouseproject.entity.Product;
import uz.pdp.appwarehouseproject.entity.User;
import uz.pdp.appwarehouseproject.repository.OperationRepository;
import uz.pdp.appwarehouseproject.repository.ProductRepository;
import uz.pdp.appwarehouseproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    final OperationRepository operationRepository;
    final UserRepository userRepository;
    final ProductRepository productRepository;


    public OperationService(OperationRepository operationRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    public Response create(OperationDTO operationDTO) {

        Optional<User> optionalUser = userRepository.findById(operationDTO.getUserId());

        User user = optionalUser.get();
        Optional<Product> optionalProduct = productRepository.findById(operationDTO.getProductId());
        Product product = optionalProduct.get();

        OperationHistory operationHistory = new OperationHistory();

        operationHistory.setUser(user);
        operationHistory.setProduct(product);
        operationHistory.setAmount(operationDTO.getAmount());
        operationHistory.setPrice(operationDTO.getPrice());
        operationHistory.setDate(operationDTO.getDate());
        operationHistory.setActionType(operationDTO.getActionType());

        operationRepository.save(operationHistory);
        return new Response("Operation well done", true);
    }


    public Response getALl() {

        List<OperationHistory> list = operationRepository.findAll();
        if (list.isEmpty())
            return new Response("Database is empty", false);

        return new Response("Database ", true, list);

    }


    public Response update(OperationDTO operationDTO, Integer operationId) {


        if (operationRepository.existsById(operationId)) {

            Optional<OperationHistory> byId = operationRepository.findById(operationId);
            Optional<Product> optionalProduct = productRepository.findById(operationDTO.getProductId());
            Product product = optionalProduct.get();
            Optional<User> optionalUser = userRepository.findById(operationDTO.getUserId());
            User user = optionalUser.get();

            if (byId.isPresent()) {
                OperationHistory operationHistory1 = byId.get();


                operationHistory1.setAmount(operationDTO.getAmount());
                operationHistory1.setActionType(operationDTO.getActionType());
                operationHistory1.setDate(operationDTO.getDate());
                operationHistory1.setPrice(operationDTO.getPrice());


                operationHistory1.setProduct(product);
                operationHistory1.setUser(user);

                operationRepository.save(operationHistory1);
                return new Response("Operation well done", true);
            }

        }

        return new Response("Such operation id was not found", false) ;
    }

    public Response delete(Integer id) {

        if (operationRepository.existsById(id)) {
            operationRepository.deleteById(id);

            return new Response("Deleted", true);

        }

        return new Response("Not Deleted", false);


    }
}
