package ra.apisecurity.service;

import ra.apisecurity.model.dto.request.FormLogin;
import ra.apisecurity.model.dto.request.FormRegister;
import ra.apisecurity.model.dto.response.JWTResponse;

public interface IAuthenService {
    JWTResponse login(FormLogin request);
    void register(FormRegister request);
}
