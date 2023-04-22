package com.injuk.zipster.presentation.controllers.v1

import com.injuk.zipster.models.Injuk
import com.injuk.zipster.operations.InjukApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class InjukController: InjukApi {

    override fun getInjuk(): ResponseEntity<Injuk> = ResponseEntity.ok(
        Injuk(
            meaning = "In just using Kotlin",
        )
    )
}