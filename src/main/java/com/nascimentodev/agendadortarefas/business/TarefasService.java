package com.nascimentodev.agendadortarefas.business;

import com.nascimentodev.agendadortarefas.business.dto.TarefasDTO;
import com.nascimentodev.agendadortarefas.business.mapper.TarefasConverter;
import com.nascimentodev.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.nascimentodev.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.nascimentodev.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.nascimentodev.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);

        TarefasEntity tarefa = tarefasConverter.paraTarefaEntity(dto);

        return tarefasConverter.paraTarefaDto(
                tarefasRepository.save(tarefa));

    }

    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasConverter.paraListaTarefasDto(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token);

        return tarefasConverter.paraListaTarefasDto(
                tarefasRepository.findByEmailUsuario(email));
    }

}
