package dojo.mkt.sample01.use_case

import dojo.mkt.sample01.model.ATMModel
import dojo.mkt.sample01.model.Notas
import java.lang.Exception

class ATMUseCase {

    data class Params(val value: Long)

    val list = listOf(Notas.DEZ_REAIS,Notas.VINTE_REAIS, Notas.CINQUENTA_REAIS, Notas.CEM_REAIS).asReversed()

    suspend fun execute(params: Params): Result<ATMModel> {

        if (params.value == 0L) return Result.success(ATMModel(listOf()))

        val result = mutableListOf<Notas>()
        var valorSobrante = params.value
       while (true) {

           list.forEach {
               if (it.valor <= valorSobrante) {
                   result.add(it)
                   valorSobrante -= it.valor
               }
           }

           if (valorSobrante == 0L) return Result.success(ATMModel(list))
       }


        return Result.failure(Exception("Não implementado"))
    }



}