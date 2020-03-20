package io.micronaut.example

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.multipart.CompletedFileUpload

@Controller("/example")
class ExampleController {

    @Post("/foo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    fun foo(content: CompletedFileUpload): Any? {
        return extract(content)
    }

    @Post("/bar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    suspend fun bar(content: CompletedFileUpload): Any? {
        return extract(content)
    }

    private fun extract(content: CompletedFileUpload): String {
        return content.filename + " ${content.size}"
    }
}