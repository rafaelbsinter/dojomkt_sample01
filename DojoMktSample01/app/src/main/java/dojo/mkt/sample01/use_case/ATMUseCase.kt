package dojo.mkt.sample01.use_case

import dojo.mkt.sample01.model.ATMModel
import java.lang.Exception

class ATMUseCase {

    class Params(value: Long)

    suspend fun execute(params: Params): Result<ATMModel> {
        return Result.failure(Exception("Não implementado"))
    }

}