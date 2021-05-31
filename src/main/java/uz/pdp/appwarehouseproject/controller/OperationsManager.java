package uz.pdp.appwarehouseproject.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseproject.dto.OperationsHistoryDTO;
import uz.pdp.appwarehouseproject.entity.OperationHistory;
import uz.pdp.appwarehouseproject.entity.Product;
import uz.pdp.appwarehouseproject.entity.User;
import uz.pdp.appwarehouseproject.enums.ActionType;
import uz.pdp.appwarehouseproject.repository.OperationsRepository;
import uz.pdp.appwarehouseproject.repository.ProductRepository;
import uz.pdp.appwarehouseproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/history")
public class OperationsManager {

    final OperationsRepository operationsRepository;
    final UserRepository userRepository;
    final ProductRepository productRepository;

    public OperationsManager(OperationsRepository operationsRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.operationsRepository = operationsRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<OperationHistory> getAllHistory() {
        return operationsRepository.findAll();
    }

    @GetMapping("/{id}")
    public OperationHistory getOneHistory(@PathVariable Integer id) {
        Optional<OperationHistory> history = operationsRepository.findById(id);
        if (history.isEmpty())
            return null;
        return history.get();

    }

    @PostMapping
    public String addHistory(@RequestBody OperationsHistoryDTO operationsHistoryDTO) {
        OperationHistory operationHistory = new OperationHistory();
        Optional<User> optionalUser = userRepository.findById(operationsHistoryDTO.getUserId());
        if (optionalUser.isEmpty())
            return "such user id was not found";
        Optional<Product> optionalProduct = productRepository.findById(operationsHistoryDTO.getProductId());
        if (optionalProduct.isEmpty())
            return "such product id was not found";
        operationHistory.setUser(optionalUser.get());
        operationHistory.setProduct(optionalProduct.get());
        operationHistory.setPrice(operationsHistoryDTO.getPrice());
        operationHistory.setAmount(operationsHistoryDTO.getAmount());
        operationHistory.setDate(operationsHistoryDTO.getDate());
        operationHistory.setActionType(operationsHistoryDTO.getActionType());
        operationsRepository.save(operationHistory);

        return "Operation history added";
    }

    @PutMapping("/{id}")
    public String updateHistory(@PathVariable Integer id, @RequestBody OperationsHistoryDTO operationsHistoryDTO) {
        Optional<OperationHistory> history = operationsRepository.findById(id);
        if (history.isEmpty())
            return "such operations history id was not found";
        OperationHistory operationHistory = history.get();

        Optional<User> optionalUser = userRepository.findById(operationsHistoryDTO.getUserId());
        if (optionalUser.isEmpty())
            return "such user id was not found";
        Optional<Product> optionalProduct = productRepository.findById(operationsHistoryDTO.getProductId());
        if (optionalProduct.isEmpty())
            return "such product id was not found";
        operationHistory.setUser(optionalUser.get());
        operationHistory.setProduct(optionalProduct.get());
        operationHistory.setAmount(operationsHistoryDTO.getAmount());
        operationHistory.setDate(operationsHistoryDTO.getDate());
        //ActionType actionType = ActionType.valueOf(operationsHistoryDTO.getActionType());
        operationHistory.setActionType(operationsHistoryDTO.getActionType());
        operationsRepository.save(operationHistory);

        return "Operation history updated";

    }

    @DeleteMapping("/{id}")
    public String  deleteHistory(@PathVariable Integer id){
        operationsRepository.deleteById(id);
        return "History deleted";

    }




}
