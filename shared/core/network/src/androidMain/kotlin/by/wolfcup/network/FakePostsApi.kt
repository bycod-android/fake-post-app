package by.wolfcup.network

import by.wolfcup.network.dto.FakePostDto
import by.wolfcup.network.remote.Repository
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

actual class FakePostsApi actual constructor(rootSourceUrl: String): Repository {
    private val rootSource: String = rootSourceUrl
    private val client = HttpClient(OkHttp) {
        engine {
            config {
                followRedirects(true)
            }
        }
    }
    override suspend fun getAllPosts(): List<FakePostDto> {
        val result = client.get("$rootSource/posts")
        return Json.decodeFromString<List<FakePostDto>>(result.bodyAsText())
    }

    override suspend fun getPost(id: Int): FakePostDto {
        val result = client.get("$rootSource/posts/$id")
        return Json.decodeFromString<FakePostDto>(result.bodyAsText())
    }
}