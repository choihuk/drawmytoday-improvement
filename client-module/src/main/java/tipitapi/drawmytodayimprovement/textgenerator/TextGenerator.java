package tipitapi.drawmytodayimprovement.textgenerator;


import java.util.List;

public interface TextGenerator {

    List<? extends TextGeneratorContent> generatePrompt(String diaryNote);
}
