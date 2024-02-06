package bookGenerator._global.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bookGenerator.user.service.UserManageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceUserDetailService implements UserDetailsService {
    final private UserManageService userManageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new ServiceUserDetail(this.userManageService.findByEmail(username));
    }
}
