package com.example.customerms.service.impl;

import com.example.customerms.dto.CustomerDto;
import com.example.customerms.entity.CustomerEntity;
import com.example.customerms.exception.ResourceNotFoundException;
import com.example.customerms.mapper.CustomerMapper;
import com.example.customerms.repository.CustomerRepository;
import com.example.customerms.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        logger.info("Creating author: {}", customerDto);
        CustomerEntity customerEntity = customerMapper.toEntity(customerDto);
        CustomerDto createdCustomer = customerMapper.toDto(customerRepository.save(customerEntity));
        logger.info("Customer created successfully with ID: {}", createdCustomer.getId());
        return createdCustomer;

    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        logger.info("Fetching customer with ID: {}", id);
        return customerRepository.findById(id).map(customerMapper::toDto)
                .orElseThrow(() -> {
                    logger.error("Customer not found with ID: {}", id);
                    return new ResourceNotFoundException("Customer not found");
                });

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        logger.info("Fetching all customers");
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        logger.info("Attempting to update customer with ID: {}", id);
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customerEntity.setFullName(customerDto.getFullName());
        customerEntity.setAge(customerDto.getAge());
        customerEntity.setPin(customerDto.getPin());
        customerEntity.setBalance(customerDto.getBalance());
        logger.info("Customer with ID: {} updated successfully", id);
        return customerMapper.toDto(customerRepository.save(customerEntity));
    }

    @Override
    public void deleteCustomer(Long id) {
        logger.info("Deleting customer with ID: {}", id);
        customerRepository.deleteById(id);
        logger.info("Customer with ID: {} deleted successfully", id);

    }

}
