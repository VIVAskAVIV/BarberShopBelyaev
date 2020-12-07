package belyaev.ai182.barbershop.services;

import belyaev.ai182.barbershop.entities.Role;
import belyaev.ai182.barbershop.entities.User;
import belyaev.ai182.barbershop.repositories.RoleRepository;
import belyaev.ai182.barbershop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByEmail(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void setAdmin(String email) {
        User user = userRepository.findByEmail(email);
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
        userRepository.save(user);
    }

    public void setWorker(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_WORKER")));
            userRepository.save(user);
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}