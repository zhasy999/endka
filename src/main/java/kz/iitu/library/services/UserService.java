package kz.iitu.library.services;

import kz.iitu.library.models.*;
import kz.iitu.library.repo.CarRepository;
import kz.iitu.library.repo.RoleRepository;
import kz.iitu.library.repo.UserRepository;
import kz.iitu.library.services.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserServiceInt, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Autowired
    public void setCarRepository(CarRepository carRepository){
        this.carRepository=carRepository;
    }

    @Transactional
    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public boolean addUser(User user){
        if(userRepository.findUserByUsernameIgnoreCase(user.getUsername())!=null)
            return false;
        user.setRoles(Collections.singletonList(roleRepository.findRoleByName("USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    @Transactional
    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    @Override
    public Long deleteUserByName(String name) {
        return userRepository.deleteUserByUsernameIgnoreCase(name);
    }

    @Transactional
    public User findUserByName(String name) {
        return userRepository.findUserByUsernameIgnoreCase(name);
    }
    @Transactional
    public void clear() {
        userRepository.deleteAll();
    }
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public boolean addCar(Long userId, Long carId) {
        Car car = carRepository.findById(carId).get();
        car.setStatus(Status.FIXING);
        car.setUser(userRepository.findById(userId).get());
        carRepository.save(car);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameIgnoreCase(username);
        if(user == null){
            throw new UsernameNotFoundException("User: " +username+" has not found");
        }
        return user;
    }
}
