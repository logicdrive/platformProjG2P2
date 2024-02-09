package bookGenerator._global.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bookGenerator.domain.UserManageService;

@Service
public class ServiceUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new ServiceUserDetail(UserManageService.getInstance().findByEmailOrThrow(username));
    }
    
}
