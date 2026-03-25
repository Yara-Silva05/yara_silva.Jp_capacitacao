package project.yara_silva.Jp_capacitacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.yara_silva.Jp_capacitacao.dtos.request.AuthenticationRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.LoginResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.RegisterRequestDTO;
import project.yara_silva.Jp_capacitacao.exceptions.UserExistsException;
import project.yara_silva.Jp_capacitacao.models.main.UserModel;
import project.yara_silva.Jp_capacitacao.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO login(AuthenticationRequestDTO body) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(body.email(), body.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        return new LoginResponseDTO(tokenService.generateToken((UserModel) auth.getPrincipal()));
    }


    public void register(RegisterRequestDTO body) {
        if(this.repository.findByEmail(body.email()) == null) {

            String encryptedPassword = new BCryptPasswordEncoder().encode(body.password());
            UserModel newUser = new UserModel(body.name(), body.email(), encryptedPassword, body.role());

            this.repository.save(newUser);
        }else {
            throw new UserExistsException();
        }
    }
}
