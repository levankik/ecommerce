package com.softgen.softlab.ecommercebackend.api.security;

import com.softgen.softlab.ecommercebackend.model.LocalUser;
import com.softgen.softlab.ecommercebackend.model.dao.LocalUserDAO;
import com.softgen.softlab.ecommercebackend.service.JWTService;
import lombok.val;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JWTRequestFilterTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private LocalUserDAO localUserDAO;
    private static final String AUTHENTICATED_PATH = "/auth/me";

    @Test
    public void textUnauthenticatedRequest() throws Exception {
        mvc.perform(get(AUTHENTICATED_PATH)).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }
    @Test
    public void testBadToken() throws Exception {
        val forbidden = HttpStatus.FORBIDDEN;
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "BadTokenThatIsNotValid"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "Bearer BadTokenThatIsNotValid"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void testUnverified() throws Exception {
        LocalUser user = localUserDAO.findByUsernameIgnoreCase("UserB").get();
        String token = jwtService.generateJWT(user);
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "Bearer " + token))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void testSuccessful() throws Exception {
        LocalUser user = localUserDAO.findByUsernameIgnoreCase("UserA").get();
        String token = jwtService.generateJWT(user);
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "Bearer " + token))
                .andExpect(status().is(HttpStatus.OK.value()));

    }
}
