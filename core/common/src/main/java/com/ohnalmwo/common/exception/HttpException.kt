package com.ohnalmwo.common.exception

data object BadRequestException : RuntimeException() // 400
data object UnAuthorizedException : RuntimeException() // 401
data object ForbiddenException : RuntimeException() // 402
data object NotFoundException : RuntimeException() // 404
data object MethodNotAllowedException : RuntimeException() // 405
data object NotAcceptableException : RuntimeException() // 406
data object ConnectionTimeOutException : RuntimeException() // 408
data object ConflictException : RuntimeException() // 409
data object TooManyRequestException : RuntimeException() // 429
data object ServerException : RuntimeException() // 500 ~ 599
data object NoInternetException : RuntimeException()