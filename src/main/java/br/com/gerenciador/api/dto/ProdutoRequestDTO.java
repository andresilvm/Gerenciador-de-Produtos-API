package br.com.gerenciador.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank(message = "Nome é Obrigatório!")
        String nome,

        @NotNull(message = "Preço é Obrigatório!")
        @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero!")
        BigDecimal preco,

        String descricao,

        @NotNull(message = "Quantidade em estoque é obrigatória!")
        @Min(value = 0, message = "Quantidade não pode ser negativa")
        Integer quantidadeEstoque,

        @NotNull(message = "ID do Fornecedor é Obrigatório!")
        Long fornecedorId
) {
}
