package kr.co.shortenurlservice;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.presentation.ShortenUrlController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShortenUrlController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("ResponseStatusException 발생 시 404 응답")
    void handleResponseStatusException() throws Exception {
        when(shortenUrlService.findByKey(anyString()))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 short key 입니다."));

        mockMvc.perform(get("/check/invalidKey"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("존재하지 않는 short key 입니다."));
    }

    @Test
    @DisplayName("MethodArgumentNotValidException 발생 시 400 응답")
    void handleValidationException() throws Exception {
        String invalidRequestJson = "{\"originalUrl\":\"\"}";

        mockMvc.perform(post("/shortenUrl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("입력값 오류: URL 입력이 잘못되었습니다."));
    }

    @Test
    @DisplayName("IllegalArgumentException 발생 시 400 응답")
    void handleIllegalArgumentException() throws Exception {
        doThrow(new IllegalArgumentException("URL 형식에 맞지 않습니다."))
                .when(shortenUrlService).createShortUrl(anyString());

        String invalidUrlRequest = "{\"originalUrl\":\"invalid-url\"}";

        mockMvc.perform(post("/shortenUrl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUrlRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("잘못된 입력값: URL 형식에 맞지 않습니다."));
    }

    @Test
    @DisplayName("Exception 발생 시 500 응답")
    void handleGenericException() throws Exception {
        doThrow(new RuntimeException("Unexpected error"))
                .when(shortenUrlService).redirectUrl(anyString());

        mockMvc.perform(get("/invalidShortKey"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("서버 오류가 발생했습니다: Unexpected error"));
    }
}
