package com.injuk.zipster.presentation.controllers.v1

import com.injuk.zipster.models.ZippingRequest
import com.injuk.zipster.models.ZippingResponse
import com.injuk.zipster.operations.ObjectsZipApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ObjectsZipController: ObjectsZipApi {
    override fun zipObjects(zippingRequest: ZippingRequest?): ResponseEntity<ZippingResponse> {
        return super.zipObjects(zippingRequest)
    }
}