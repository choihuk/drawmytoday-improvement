package tipitapi.drawmytodayimprovement.karlo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

/**
 * Request Body 정보:
 * https://developers.kakao.com/docs/latest/ko/karlo/rest-api#text-to-image-request-body
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
record CreateKarloImageRequest(String prompt, String negativePrompt, String imageFormat, Integer samples,
                                      String returnType, Integer priorNumInferenceSteps, Double priorGuidanceScale,
                                      Integer numInferenceSteps, Double guidanceScale, String scheduler, Long[] seed) {


    public static CreateKarloImageRequest createRequest(String prompt, String negativePrompt) {
        return CreateKarloImageRequest.builder()
            .prompt(prompt)
            .negativePrompt(negativePrompt)
            .numInferenceSteps(100)
            .guidanceScale(20D)
            .imageFormat("webp")
            .samples(1)
            .returnType("url")
            .build();
    }

    // public static CreateKarloImageRequest createTestRequest(KarloParameter param) {
    //     return CreateKarloImageRequest.builder()
    //         .prompt(param.getPrompt())
    //         .negativePrompt(param.getNegativePrompt())
    //         .imageFormat("webp")
    //         .samples(param.getSamples())
    //         .returnType("url")
    //         .priorNumInferenceSteps(param.getPriorNumInferenceSteps())
    //         .priorGuidanceScale(param.getPriorGuidanceScale())
    //         .numInferenceSteps(param.getNumInferenceSteps())
    //         .guidanceScale(param.getGuidanceScale())
    //         .scheduler(param.getScheduler())
    //         .seed(param.getSeed())
    //         .build();
    // }
}
