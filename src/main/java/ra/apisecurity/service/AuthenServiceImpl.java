package ra.apisecurity.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.apisecurity.model.dto.request.FormLogin;
import ra.apisecurity.model.dto.request.FormRegister;
import ra.apisecurity.model.dto.response.JWTResponse;
import ra.apisecurity.model.dto.response.UserInfo;
import ra.apisecurity.model.entity.RoleName;
import ra.apisecurity.model.entity.User;
import ra.apisecurity.repository.IRoleRepository;
import ra.apisecurity.repository.IUserRepository;
import ra.apisecurity.security.jwt.JWTProvider;
import ra.apisecurity.security.principle.UserDetailCustom;

@Service
@RequiredArgsConstructor
public class AuthenServiceImpl implements IAuthenService{
    private  final AuthenticationManager authenticationManager;
    private  final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;
    private final ModelMapper mapper;

    @Override
    public JWTResponse login(FormLogin request) {
        Authentication authRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(request.getUsername(),request.getPassword());
        Authentication authResponse = null;
        try {
             authResponse = authenticationManager.authenticate(authRequest);
        }catch (AuthenticationException e){
            throw  new RuntimeException("username or password incorrect!");
        }
        // lấy ra UserDeatils
        UserDetailCustom userDetails = (UserDetailCustom) authResponse.getPrincipal();
        JWTResponse jwtResponse = new JWTResponse();
        jwtResponse.setAccessToken(jwtProvider.generateAccessToken(userDetails));
        jwtResponse.setRefreshToken(jwtProvider.generateRefreshToken(userDetails));
        UserInfo map = mapper.map(userDetails, UserInfo.class);
        map.setRole(userDetails.getRole().getRoleName().name());
        jwtResponse.setUser(map);
        return jwtResponse;
    }

    @Override
    public void register(FormRegister request) {
        User user = mapper.map(request,User.class);
        user.setRole(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() ->new RuntimeException("ko tim thay role")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
