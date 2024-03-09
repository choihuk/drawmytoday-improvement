package tipitapi.drawmytodayimprovement.domain.ticket.application.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.event.*;
import tipitapi.drawmytodayimprovement.service.TicketCreator;
import tipitapi.drawmytodayimprovement.service.TicketService;
import tipitapi.drawmytodayimprovement.service.TicketValidator;

@Component
@RequiredArgsConstructor
class TicketHandler {

    private final TicketValidator ticketValidator;
    private final TicketCreator ticketCreator;
    private final TicketService ticketService;

    @EventListener
    public void handle(BeforeCreateDiaryEvent event) {
        ticketValidator.validateAvailableTicketLeft(event.userId());
    }

    @EventListener
    public void handle(AfterCreateDiaryEvent event) {
        ticketService.useTicket(event.userId());
    }

    @EventListener
    public void handle(AfterRegisterUserEvent event) {
        ticketCreator.createAndSaveJoinTickets(event.userId());
    }

    @EventListener
    public void handler(BeforeRegenerateDiaryEvent event) {
        ticketValidator.validateAvailableTicketLeft(event.userId());
    }

    @EventListener
    public void handle(AfterRegenerateDiaryEvent event) {
        ticketService.useTicket(event.userId());
    }
}
