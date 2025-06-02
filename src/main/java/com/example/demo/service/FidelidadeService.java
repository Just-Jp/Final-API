package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FidelidadeDTO;
import com.example.demo.exception.TratamentoException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Fidelidade;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.FidelidadeRepository;

@Service
public class FidelidadeService {
	@Autowired
    private FidelidadeRepository fidelidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public FidelidadeDTO consultarPontos(Long clienteId) {
        Fidelidade f = fidelidadeRepository.findById(clienteId)
                .orElseThrow(() -> new TratamentoException("Cliente sem programa de fidelidade"));

        return toDTO(f);
    }

    public FidelidadeDTO adicionarPontos(Long clienteId, Integer pontosGanhos) {
        Fidelidade f = fidelidadeRepository.findById(clienteId)
                .orElseThrow(() -> new TratamentoException("Cliente sem programa de fidelidade"));

        f.setPontos(f.getPontos() + pontosGanhos);
        fidelidadeRepository.save(f);
        return toDTO(f);
    }

    public FidelidadeDTO criarPrograma(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new TratamentoException("Cliente não encontrado"));

        Fidelidade f = new Fidelidade();
        f.setCliente(cliente);
        f.setPontos(0);
        fidelidadeRepository.save(f);

        return toDTO(f);
    }

    private FidelidadeDTO toDTO(Fidelidade f) {
        FidelidadeDTO dto = new FidelidadeDTO();
        dto.setClienteId(f.getCliente().getId());
        dto.setPontos(f.getPontos());
        return dto;
    }

	
}
