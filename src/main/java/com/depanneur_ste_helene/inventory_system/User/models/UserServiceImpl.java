package com.depanneur_ste_helene.inventory_system.User.models;

import com.depanneur_ste_helene.inventory_system.User.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<UserDTO> getAllUsersDTO(){
        return userMapper.userListToUserDTOList(getAllUsers());
    }

    public User getUserByUserId(long userId) throws NotFoundException {
        return userRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("No user found for userId: " + userId));
    }

    @Override
    public UserDTO getUserDTOByUserId(long userId) throws NotFoundException {
        return userMapper.userToUserDTO(getUserByUserId(userId));
    }
}
