package com.example.customerms.mapper;

import com.example.customerms.dto.CustomerDto;
import com.example.customerms.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto toDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .id(customerEntity.getId())
                .fullName(customerEntity.getFullName())
                .age(customerEntity.getAge())
                .pin(customerEntity.getPin())
                .balance(customerEntity.getBalance())
                .build();
    }

    public CustomerEntity toEntity(CustomerDto customerDto) {
        return CustomerEntity.builder()
                .id(customerDto.getId())
                .fullName(customerDto.getFullName())
                .age(customerDto.getAge())
                .pin(customerDto.getPin())
                .balance(customerDto.getBalance())
                .build();
    }
}
