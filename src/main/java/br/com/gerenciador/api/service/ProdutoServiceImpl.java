package br.com.gerenciador.api.service;

import br.com.gerenciador.api.dto.ProdutoRequestDTO;
import br.com.gerenciador.api.dto.ProdutoResponseDTO;
import br.com.gerenciador.api.mapper.ProdutoMapper;
import br.com.gerenciador.api.model.Fornecedor;
import br.com.gerenciador.api.model.Produto;
import br.com.gerenciador.api.repository.FornecedorRepository;
import br.com.gerenciador.api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoMapper produtoMapper;

    @Override
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        produto.setFornecedor(buscarFornecedor(dto.fornecedorId()));
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    @Override
    public List<ProdutoResponseDTO> listarTodosProdutos() {
        return produtoRepository.findAll().stream()
                .map(produtoMapper::toDTO)
                .toList();
    }

    @Override
    public ProdutoResponseDTO buscarProdutoPeloId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
        return produtoMapper.toDTO(produto);
    }

    private Fornecedor buscarFornecedor(Long id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forneedor nao encontrado"));
    }
}
