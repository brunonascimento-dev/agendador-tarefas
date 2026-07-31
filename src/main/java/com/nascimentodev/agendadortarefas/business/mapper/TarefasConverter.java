package com.nascimentodev.agendadortarefas.business.mapper;

import com.nascimentodev.agendadortarefas.business.dto.TarefasDTO;
import com.nascimentodev.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = ("spring"))
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDto(TarefasEntity entity);

}
