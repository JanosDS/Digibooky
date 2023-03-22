package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.address.AddressMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    private UserDTO mapToDTO(User user) {
        return null;
    }


    public User mapToDomain(CreateUserDTO createUserDTO){
        return new User(createUserDTO.getFirstName(), createUserDTO.getLastName(), createUserDTO.getEmail(), addressMapper.mapToDomain(createUserDTO.getAddress()), createUserDTO.getINSS(), Role.MEMBER);
    }

}
