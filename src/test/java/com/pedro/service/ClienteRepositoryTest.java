package com.pedro.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pedro.domain.Cliente;
import com.pedro.repository.ClienteRepository;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {

	@Autowired
	ClienteRepository repository;

	@Test
	public void buscaClientesCadastrados() {

		Page<Cliente> clientes = this.repository.findAll(PageRequest.of(0, 10));
		assertThat(clientes.getTotalElements()).isGreaterThan(1L);
	}

	@Test
	public void buscaClientePedro() {
		
		Cliente clienteNaoEncontrado = this.repository.findByNome("Pedro");
		assertThat(clienteNaoEncontrado).isNull();

		Cliente cliente = this.repository.findByNome("Pedro Leite");
		assertThat(cliente).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Pedro Leite");
		assertThat(cliente.getEndereco()).isEqualTo("Bairro de Fátima");
		
	}
 
}