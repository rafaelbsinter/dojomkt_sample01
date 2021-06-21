package dojo.mkt.sample01.use_case

import dojo.mkt.sample01.model.ATMModel
import java.lang.Exception

class ATMUseCase {

    data class Params(val value: Long)

    suspend fun execute(params: Params): Result<ATMModel> {

        if (params.value == 0L) return Result.success(ATMModel(listOf()))



        return Result.failure(Exception("Não implementado"))
    }

}