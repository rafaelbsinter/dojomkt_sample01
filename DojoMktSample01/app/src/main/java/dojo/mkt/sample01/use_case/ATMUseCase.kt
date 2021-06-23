package dojo.mkt.sample01.use_case

import dojo.mkt.sample01.model.ATMModel
import dojo.mkt.sample01.model.Notas

class ATMUseCase {

    data class Params(
        val valorSaque: Long,
        val saldoConta: Long = Long.MAX_VALUE,
        val limiteSaqueATM: Long = Long.MAX_VALUE,
        val notasDisponiveis: Map<Notas, Int> = mapOf(
            Notas.CEM_REAIS to Int.MAX_VALUE,
            Notas.CINQUENTA_REAIS to Int.MAX_VALUE,
            Notas.VINTE_REAIS to Int.MAX_VALUE,
            Notas.DEZ_REAIS to Int.MAX_VALUE,
        )
    )

    suspend fun execute(params: Params): Result<ATMModel> {

        var valorRestante = params.valorSaque

        val notasDisponiveis = params.notasDisponiveis


        val valorNegativoOuZerado = valorRestante <= 0L
        val saqueImpossivel = valorRestante % (notasDisponiveis.keys.maxByOrNull { it.valor }!!.valor) > 0
        val saldoNaoDisponivel = params.saldoConta < valorRestante
        val limiteNaoDisponivelATM = params.limiteSaqueATM < valorRestante

        if (valorNegativoOuZerado || saqueImpossivel || saldoNaoDisponivel || limiteNaoDisponivelATM)
            return Result.failure(exception = Exception("Saque indisponivel"))

        val notas = mutableListOf<Notas>()

        getNotas().forEach {
            val quantidadeNotas = params.notasDisponiveis[it]
            while (valorRestante >= it.valor && quantidadeNotas != null) {
                notas.add(it)
                quantidadeNotas.minus(1)
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