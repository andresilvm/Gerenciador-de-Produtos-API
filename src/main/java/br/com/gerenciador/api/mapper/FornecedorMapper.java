package br.com.gerenciador.api.mapper;

import br.com.gerenciador.api.dto.FornecedorRequestDTO;
import br.com.gerenciador.api.dto.FornecedorResponseDTO;
import br.com.gerenciador.api.model.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    //conversor de DTO p/ Entidade:
    @Mapping(target = "id", ignore = true)
    Fornecedor toEntity(FornecedorRequestDTO dto);

    //conversor da entidade p/ DTO:
    FornecedorResponseDTO toDTO(Fornecedor fornecedor);
}
