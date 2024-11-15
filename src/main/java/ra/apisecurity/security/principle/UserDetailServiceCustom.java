package ra.apisecurity.security.principle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.apisecurity.model.entity.User;
import ra.apisecurity.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceCustom implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        UserDetailCustom detailCustom = new UserDetailCustom();
        detailCustom.setUsername(userInfo.getUsername());
        detailCustom.setId(userInfo.getId());
        detailCustom.setPassword(userInfo.getPassword());
        detailCustom.setStatus(userInfo.getStatus());
        detailCustom.setRole(userInfo.getRole());
        detailCustom.setFullName(userInfo.getFullName());
        // biến đổi từ ROLE Enum -> ROle GrandAuthority
        List<GrantedAuthority> authorities =new ArrayList<>();
        GrantedAuthority role = new SimpleGrantedAuthority(userInfo.getRole().getRoleName().name());
        authorities.add(role);

        detailCustom.setAuthorities(authorities);
        return  detailCustom;
    }
}
