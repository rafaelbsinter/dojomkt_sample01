package dojo.mkt.sample01.use_case

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNullOrEmpty
import assertk.assertions.isTrue
import dojo.mkt.sample01.model.Notas
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ATMUseCaseTest{

    private lateinit var atmUseCase: ATMUseCase

    @Before
    fun setup(){
        atmUseCase = ATMUseCase()
    }

    @Test
    fun `caso valor do saque seja 0 deve retornar 0`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(0)

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.notas).isNullOrEmpty()
    }

    @Test
    fun `caso valor do saque seja 30 reais deve retornar 1 nota de 20 e 1 nota de 10`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(30)

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.notas).isEqualTo(listOf(Notas.VINTE_REAIS, Notas.DEZ_REAIS))
    }

    @Test
    fun `caso valor do saque seja 57 reais deve retornar um erro`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(57)

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isFailure).isTrue()
        assertThat(result.getOrNull()?.notas).isEqualTo("o valor do saque é inválido")
    }

}