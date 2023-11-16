package com.example.monitoringservice;
import com.example.monitoringservice.Security.AuthResponse;
import com.example.monitoringservice.Service.Interface.AuthService;
import com.example.monitoringservice.dto.AuthRequestDTO;
import com.example.monitoringservice.dto.RegisterRequestDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    // También puedes necesitar mockear otras dependencias si las tienes

    @DisplayName("Test register")
    @Test
    public void testA1() throws Exception {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();

        registerRequestDTO.setUsername("username");
        registerRequestDTO.setPassword("password");
        registerRequestDTO.setAdmin(true);
        registerRequestDTO.setMaintenance(false);

        AuthResponse expectedResponse = new AuthResponse("token"); // Ajusta esto según tu implementación

        when(authService.register(any(RegisterRequestDTO.class))).thenReturn(expectedResponse);

        // Realiza la solicitud y verifica el resultado
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(expectedResponse.getToken()));
    }

    @DisplayName("Test login")
    @Test
    public void testA2() throws Exception {
        // Configura el objeto de prueba (puedes ajustar esto según tus necesidades)
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("username");
        authRequestDTO.setPassword("password");
        AuthResponse expectedResponse = new AuthResponse("token"); // Ajusta esto según tu implementación

        when(authService.login(anyString(), anyString())).thenReturn(expectedResponse);

        // Realiza la solicitud y verifica el resultado
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(expectedResponse.getToken()));
    }

    // Método auxiliar para convertir objetos a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

