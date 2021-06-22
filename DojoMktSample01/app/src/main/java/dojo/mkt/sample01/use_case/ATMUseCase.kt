package dojo.mkt.sample01.use_case

import dojo.mkt.sample01.model.ATMModel
import dojo.mkt.sample01.model.Notas

class ATMUseCase {

    data class Params(
        val valorSaque: Long,
        val saldoConta: Long = Long.MAX_VALUE,
        val limiteSaqueATM: Long = Long.MAX_VALUE,
        val notasDisponiveis : Map<Int,Notas> = mapOf(
            Int.MAX_VALUE to Notas.DEZ_REAIS,

        )
    )

    suspend fun execute(params: Params): Result<ATMModel> {

        var valorRestante = params.valorSaque

        val valorNegativoOuZerado = valorRestante <= 0L
        val menorNotaDisponivel = valorRestante % 10 > 0
        val saldoNaoDisponivel = params.saldoConta < valorRestante
        val limiteNaoDisponivelATM = params.limiteSaqueATM < params.valorSaque

        if (valorNegativoOuZerado || menorNotaDisponivel || saldoNaoDisponivel || limiteNaoDisponivelATM)
            return Result.failure(exception = Exception("Saque indisponivel"))

        val notas = mutableListOf<Notas>()

        getNotas().forEach {
            while (valorRestante >= it.valor){
                notas.add(it)
                valorRestante -= it.valor
            }
        }

        return Result.success(ATMModel(notas))
    }

    private fun getNotas() = listOf(
            Notas.DEZ_REAIS,
            Notas.CEM_REAIS,
            Notas.CINQUENTA_REAIS,
            Notas.VINTE_REAIS
        ).sortedByDescending {
            it.valor
        }
}