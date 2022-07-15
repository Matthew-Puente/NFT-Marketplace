package com.raywenderlich.nftmarketplace11.nftmarketplace11.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice // 1
class NFTErrorHandler  {
    @ExceptionHandler(NFTNotFoundException::class) // 2
    fun handleNFTNotFoundException(
        servletRequest: HttpServletRequest,
        exception: Exception
    ): ResponseEntity<String> {
        return ResponseEntity("NFT not found", HttpStatus.NOT_FOUND) // 3
    }
}
