package io.metamask.androidsdk
import kotlinx.serialization.Serializable
import java.net.MalformedURLException
import java.net.URL

@Serializable
data class DappMetadata(
    val name: String,
    val url: String,
    val iconUrl: String? = null,
    val base64Icon: String? = null,
    val deeplink: String = METAMASK_BIND_DEEPLINK
) {
    fun hasValidUrl(): Boolean {
        return try {
            val url = URL(url)
            url.protocol != null && url.host != null
        } catch (e: MalformedURLException) {
            false
        }
    }

    fun hasValidName(): Boolean {
        return name.isNotEmpty()
    }

    val validationError: RequestError?
        get() {
        if (!hasValidUrl()) {
            return RequestError(-101, "Please use a valid Dapp url")
        }
        if (!hasValidName()) {
            return RequestError(-102, "Please use a valid Dapp name")
        }
        return null
    }
}
