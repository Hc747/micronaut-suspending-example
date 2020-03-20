import io.micronaut.runtime.Micronaut

object ApplicationEntryPoint {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build().start()
    }
}