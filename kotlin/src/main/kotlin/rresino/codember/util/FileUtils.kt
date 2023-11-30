package rresino.codember.util

import java.io.File

object FileUtils {

    private const val BASE_PATH_MAIN = "src/main/resources"
    private const val BASE_PATH_TEST = "src/test/resources"

    fun readInput(name: String, cleanUp: Boolean = true): List<String> {
        val f = getFile(name)

        assert(f.isFile) { "It's not a file: $name" }
        assert(f.canRead())  { "Cannot read the file: $name" }
        val raw = f.readLines()

        if (cleanUp) {
            return raw
                .map { it.trim() }
                .filter { it.isNotBlank() }
        }
        return raw
    }

    private fun getFile(name: String): File {
        if (existInMain(name))
            return File(BASE_PATH_MAIN, name)
        else
            return File(BASE_PATH_TEST, name)
    }

    private fun existInMain(name: String): Boolean =
        File(BASE_PATH_MAIN, name).isFile

    private fun existInTest(name: String): Boolean =
        File(BASE_PATH_TEST, name).isFile


}