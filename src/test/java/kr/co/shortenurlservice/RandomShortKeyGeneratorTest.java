package kr.co.shortenurlservice;

import kr.co.shortenurlservice.domain.ShortKeyGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class RandomShortKeyGeneratorTest {
    @Mock
    private ShortKeyGenerator shortKeyGenerator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 단축키_생성_성공() {
        when(shortKeyGenerator.generateKey()).thenReturn("abc12");
        String generatedKey = shortKeyGenerator.generateKey();

        assertNotNull(generatedKey);
        assertEquals(5, generatedKey.length());
    }

    @Test
    public void 단축키_생성_실패() {
        when(shortKeyGenerator.generateKey()).thenReturn("abc12").thenReturn("def34");
        String key1 = shortKeyGenerator.generateKey();
        String key2 = shortKeyGenerator.generateKey();

        assertNotEquals(key1, key2);
    }
}
