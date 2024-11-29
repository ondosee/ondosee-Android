package com.ohnalmwo.network.util

import com.ohnalmwo.common.exception.BadRequestException
import com.ohnalmwo.common.exception.ConflictException
import com.ohnalmwo.common.exception.ConnectionTimeOutException
import com.ohnalmwo.common.exception.ForbiddenException
import com.ohnalmwo.common.exception.MethodNotAllowedException
import com.ohnalmwo.common.exception.NoInternetException
import com.ohnalmwo.common.exception.NotAcceptableException
import com.ohnalmwo.common.exception.NotFoundException
import com.ohnalmwo.common.exception.ServerException
import com.ohnalmwo.common.exception.TooManyRequestException
import com.ohnalmwo.common.exception.UnAuthorizedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ApiHandler<T> {
    suspend fun request(httpRequest: suspend () -> T): T =
        try {
            withContext(Dispatchers.IO) {
                httpRequest()
            }
        } catch (e: HttpException) {
            throw when (e.code()) {
                400 -> BadRequestException
                401 -> UnAuthorizedException
                403 -> ForbiddenException
                404 -> NotFoundException
                405 -> MethodNotAllowedException
                406 -> NotAcceptableException
                409 -> ConflictException
                429 -> TooManyRequestException
                in 500..599 -> ServerException
                else -> e
            }
        } catch (e: SocketTimeoutException) {
            throw ConnectionTimeOutException
        } catch (e: UnknownHostException) {
            throw NoInternetException
        } catch (e: Exception) {
            throw e
        }
}
