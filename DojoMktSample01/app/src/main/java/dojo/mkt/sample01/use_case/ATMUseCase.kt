package dojo.mkt.sample01.use_case

import dojo.mkt.sample01.model.ATMModel
import dojo.mkt.sample01.model.Notas

class ATMUseCase {

    data class Params(val value: Long)

    val list = listOf(
        Notas.CEM_REAIS,
        Notas.CINQUENTA_REAIS,
        Notas.VINTE_REAIS,
        Notas.DEZ_REAIS
    )

    suspend fun execute(params: Params): Result<ATMModel> {

        if (params.value == 0L) return Result.success(ATMModel(listOf()))

        val result = mutableListOf<Notas>()
        var valorRestante = params.value

        while (valorRestante >= 0) {

            list.forEach {
                if (it.valor <= valorRestante) {
                    result.add(it)
                    valorRestante -= it.valor
                }
            }
        }

        return Result.success(ATMModel(result))

        return Result.failure(Exception("Não implementado"))
    }


}