package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.user.address.AddressMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public UserDTO mapToDTO(User user) {
        return new UserDTO(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getINSS(), user.getRole());
    }

    public User mapToDomain(CreateUserDTO createUserDTO){
        return new User(createUserDTO.getFirstName(), createUserDTO.getLastName(), createUserDTO.getEmail(), addressMapper.mapToDomain(createUserDTO.getAddress()), createUserDTO.getINSS(), createUserDTO.getRole());
    }

}
