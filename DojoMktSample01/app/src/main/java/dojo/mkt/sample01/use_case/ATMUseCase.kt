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

    private fun validatarSaque(value: Int){

    }

    suspend fun execute(params: Params): Result<ATMModel> {

        //if (params.value == 0L) return Result.success(ATMModel(listOf()))

        var valorRestante = params.value

        if (validatarSaque(valorRestante)){

        }

        val notas = mutableListOf<Notas>()


        list.forEach {
            while (valorRestante >= it.valor ) {
                notas.add(it)
                valorRestante -= it.valor
            }
        }

        return Result.success(ATMModel(notas))
    }


}