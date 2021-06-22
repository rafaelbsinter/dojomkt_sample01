package dojo.mkt.sample01.use_case

import dojo.mkt.sample01.model.ATMModel
import dojo.mkt.sample01.model.Notas

class ATMUseCase {

    data class Params(val value: Long, val saldo: Long = Long.MAX_VALUE)

    val list = listOf(
        Notas.CEM_REAIS,
        Notas.CINQUENTA_REAIS,
        Notas.VINTE_REAIS,
        Notas.DEZ_REAIS
    )

    suspend fun execute(params: Params): Result<ATMModel> {

        if (params.value <= 0L || params.value % 10 > 0)
            return Result.failure(exception = Exception("Saque indisponivel"))

        if()

        var valorRestante = params.value


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