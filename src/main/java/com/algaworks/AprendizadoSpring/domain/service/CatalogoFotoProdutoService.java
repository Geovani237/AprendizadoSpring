package com.algaworks.AprendizadoSpring.domain.service;

import com.algaworks.AprendizadoSpring.domain.exception.FotoProdutoNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.FotoProduto;
import com.algaworks.AprendizadoSpring.domain.repository.ProdutoRepository;
import com.algaworks.AprendizadoSpring.domain.service.FotoStorageService.NovaFoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente = produtoRepository
                .findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeNovoArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();

//        if (nomeArquivoExistente != null) {
//            fotoStorageService.remover(nomeArquivoExistente);
//        }

        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }

    @Transactional
    public void excluir(Long restauranteId, Long produtoId) {
        FotoProduto fotoProduto = buscarOuFalhar(restauranteId, produtoId);

        produtoRepository.delete(fotoProduto);
        produtoRepository.flush();

        fotoStorageService.remover(fotoProduto.getNomeArquivo());
    }


    public FotoProduto buscarOuFalhar(Long resturanteId, Long produtoId) {
        return produtoRepository.findFotoById(resturanteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(produtoId, resturanteId));
    }
}
