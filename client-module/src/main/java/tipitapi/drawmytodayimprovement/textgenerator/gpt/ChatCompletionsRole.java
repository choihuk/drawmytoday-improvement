package tipitapi.drawmytodayimprovement.textgenerator.gpt;

import com.fasterxml.jackson.annotation.JsonProperty;

enum ChatCompletionsRole {
    @JsonProperty("system")
    system,
    @JsonProperty("user")
    user,
    @JsonProperty("assistant")
    assistant;

    ChatCompletionsRole() {
    }
}
