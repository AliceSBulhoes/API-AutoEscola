package br.com.fiap3esph.autoescola3esph.domain.agenda.validacao;

import br.com.fiap3esph.autoescola3esph.domain.agenda.DadosAgendamento;
import br.com.fiap3esph.autoescola3esph.domain.agenda.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento {
    @Override
    public void validar(DadosAgendamento dados) {
        LocalDateTime dataEscolhida = dados.dataHora();
        boolean domingo = dataEscolhida.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean preAbertura = dataEscolhida.getHour() < 6;
        boolean posFechamento = dataEscolhida.getHour() > 20;

        if(domingo || preAbertura || posFechamento){
            throw new ValidacaoException("Tentativa de agendamento fora o horário de funcionamento.");
        }

    }
}
