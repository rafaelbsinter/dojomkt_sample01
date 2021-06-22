package dojo.mkt.sample01.use_case

import dojo.mkt.sample01.model.ATMModel
import dojo.mkt.sample01.model.Notas

class ATMUseCase {

    data class Params(
        val valor: Long,
        val saldo: Long = Long.MAX_VALUE,
        val limite: Long = 500

    )

    val list = listOf(
        Notas.CEM_REAIS,
        Notas.CINQUENTA_REAIS,
        Notas.VINTE_REAIS,
        Notas.DEZ_REAIS
    )

    suspend fun execute(params: Params): Result<ATMModel> {

        var valorRestante = params.valor
        var valorNegativoOuZerado = valorRestante <= 0L
        val menorNotaDisponivel = valorRestante % 10 > 0

        if (valorRestante % 10 > 0)
            return Result.failure(exception = Exception("Saque indisponivel"))

        if(params.saldo < valorRestante)
            return Result.failure(exception = Exception("Saque indisponivel"))

        val notas = mutableListOf<Notas>()

        list.forEach {
            while (valorRestante >= it.valor) {
                notas.add(it)
                valorRestante -= it.valor
            }
        }

        return Result.success(ATMModel(notas))
    }

}