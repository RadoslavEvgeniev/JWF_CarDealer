package app.cardealer.services;

import app.cardealer.models.binding.UserRegisterBindingModel;
import app.cardealer.models.view.UserViewModel;

public interface UserService {

    void importUser(UserRegisterBindingModel userBindingModel);

    UserViewModel extractUserByUsername(String username);
}
