package app.cardealer.services;

import app.cardealer.entites.User;
import app.cardealer.models.binding.UserRegisterBindingModel;
import app.cardealer.models.view.UserViewModel;
import app.cardealer.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importUser(UserRegisterBindingModel userBindingModel) {
        User user = this.modelMapper.map(userBindingModel, User.class);

        this.userRepository.save(user);
    }

    @Override
    public UserViewModel extractUserByUsername(String username) {
        User userFromDb = this.userRepository.findByUsername(username);

        if (userFromDb == null) {
            return null;
        }

        UserViewModel userViewModel = this.modelMapper.map(userFromDb, UserViewModel.class);

        return userViewModel;
    }


}
