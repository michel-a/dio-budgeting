package com.malves.budgeting;

import java.io.IOException;
import java.nio.file.Files;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiSpeechModelIT {
    @Autowired 
    OpenAiAudioSpeechModel openAiAudioAudioModel;

    @Test 
    public void should_produceAudio_when_textIsProvided() throws IOException {
        var response = openAiAudioAudioModel.call("O valor total do serviço ficou em 80 reais. Posso confirmar o pagamento:");
        
        Assertions.assertThat(response).hasSizeGreaterThan(1024);

        var tempFile = Files.createTempFile("AUDIO_", ".mp3");
        System.out.println(tempFile.toAbsolutePath());
    }
}
