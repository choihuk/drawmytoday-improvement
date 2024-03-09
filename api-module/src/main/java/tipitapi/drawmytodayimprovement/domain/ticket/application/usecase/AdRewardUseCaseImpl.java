package tipitapi.drawmytodayimprovement.domain.ticket.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.service.AdRewardCreator;
import tipitapi.drawmytodayimprovement.service.TicketCreator;
import tipitapi.drawmytodayimprovement.service.UserValidator;

@Component
@RequiredArgsConstructor
public class AdRewardUseCaseImpl implements AdRewardUseCase {

    private final AdRewardCreator adRewardCreator;
    private final UserValidator userValidator;
    private final TicketCreator ticketCreator;

    @Override
    @Transactional
    public void createAdReward(Long userId) {
        userValidator.validateByUserId(userId);
        adRewardCreator.createAndSaveAdReward(userId);
        ticketCreator.createAndSaveAdRewardTicket(userId);
    }
}
