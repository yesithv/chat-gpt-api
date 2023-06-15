package ironcoding.chatgptapi;

import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chatboot")
@AllArgsConstructor
public class ChatGptController {

    private final ChatgptService chatgptService;

    @GetMapping("/chat")
    public String chatWith(@RequestParam String message) {
        System.out.println("Meesage for chat: " + message);
        String response = chatgptService.sendMessage(message);
        System.out.println("Response for chat: " + response);
        return response;
    }

    @GetMapping("/prompt")
    public ChatResponse promptWith(@RequestParam String message) {
        System.out.println("Message for prompt: " + message);
        Integer maxTokens = 300;
        String model = "text-davinci-003";
        Double temperature = 0.5;
        Double topP = 1.0;
        ChatRequest chatRequest = new ChatRequest(model, message, maxTokens, temperature, topP);
        ChatResponse chatResponse = chatgptService.sendChatRequest(chatRequest);
        System.out.println("Response for prompt: " + chatResponse.toString());
        return chatResponse;
    }
}
