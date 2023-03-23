package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.user.address.AddressMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<UserDTO> mapToDTO(List<User> userList){
       return userList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO mapToDTO(User user) {
        return new UserDTO(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getRole());
    }

    public User mapToDomain(CreateUserDTO createUserDTO){
        return new User(createUserDTO.getFirstName(), createUserDTO.getLastName(), createUserDTO.getEmail(), addressMapper.mapToDomain(createUserDTO.getAddress()), createUserDTO.getInss(), createUserDTO.getRole());
    }

}
