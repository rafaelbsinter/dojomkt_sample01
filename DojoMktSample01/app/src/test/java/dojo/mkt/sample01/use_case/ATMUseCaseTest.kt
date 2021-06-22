package dojo.mkt.sample01.use_case

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import dojo.mkt.sample01.model.Notas
import dojo.mkt.sample01.model.Notas.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ATMUseCaseTest {

    private lateinit var atmUseCase: ATMUseCase

    @Before
    fun setup() {
        atmUseCase = ATMUseCase()
    }

    @Test
    fun `caso valor do saque seja 0 deve retornar error`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(0)

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("Saque indisponivel")
    }

    @Test
    fun `caso valor do saque seja 30 reais deve retornar 1 nota de 20 e 1 nota de 10`() =
        runBlocking {
            // Dados e mocks
            val params = ATMUseCase.Params(30)

            // Execução
            val result = atmUseCase.execute(params)

            // Asserções e validações
            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()?.notas).isEqualTo(listOf(VINTE_REAIS, Notas.DEZ_REAIS))
        }

    @Test
    fun `caso valor do saque seja 57 reais deve retornar um erro`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(57)

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("Saque indisponivel")
    }

    @Test
    fun `caso valor do saque seja 190 reais deve retornar 1 nota de 100, 1 nota de 50 e 2 notas de 20`() =
        runBlocking {
            // Dados e mocks
            val params = ATMUseCase.Params(190)

            // Execução
            val result = atmUseCase.execute(params)

            // Asserções e validações
            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()?.notas).isEqualTo(
                listOf(
                    CEM_REAIS,
                    CINQUENTA_REAIS,
                    VINTE_REAIS,
                    VINTE_REAIS
                )
            )
        }

    @Test
    fun `caso valor do saque seja menor que 0 deve retornar erro`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(-190)

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("Saque indisponivel")
    }

    @Test
    fun `caso o saldo seja menor do que o saque retornar erro`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(190, 30)

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("Saque indisponivel")
    }

    @Test
    fun `caso o saldo seja de 1480, ele deve retornar sucesso`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(
            valorSaque = 1480,
            saldoConta = 1500,
            limiteSaqueATM = 1500
        )

        // Execução
        val result = atmUseCase.execute(params)

        val notas = mutableListOf<Notas>()
        repeat(14){
            notas.add(CEM_REAIS)
        }

        // Asserções e validações
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.notas).isEqualTo(notas.plus(listOf(CINQUENTA_REAIS, VINTE_REAIS, DEZ_REAIS)))
    }

    @Test
    fun `caso o saldo limite seja menor, ele deve retornar errr0`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(
            valorSaque = 1480,
            saldoConta = 1500,
            limiteSaqueATM = 1300
        )

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("Saque indisponivel")
    }

    @Test
    fun `caso o o saque seja 80 reais e so tenha nota de 20 deve retornar 4 notas de 20`() = runBlocking {
        // Dados e mocks
        val params = ATMUseCase.Params(
            valorSaque = 80
        )

        // Execução
        val result = atmUseCase.execute(params)

        // Asserções e validações
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.notas).isEqualTo(
            listOf(
                VINTE_REAIS,
                VINTE_REAIS,
                VINTE_REAIS,
                VINTE_REAIS
            )
        )
    }

}