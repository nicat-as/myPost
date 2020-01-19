package az.blogoot.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import az.blogoot.domain.User;
import az.blogoot.service.UserService;

/**
 * UserDetailsServiceImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.getUserByEmail(email);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setUserRole(userService.getRoles(user.getId()));
            UserPrincipal userPrincipal = new UserPrincipal(user);
            return userPrincipal;
            
        }else{
            throw new UsernameNotFoundException("User with email " + email + " not found!");
        }
    }

    
}