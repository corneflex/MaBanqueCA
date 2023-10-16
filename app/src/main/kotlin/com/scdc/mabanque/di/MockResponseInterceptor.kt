package com.scdc.mabanque.di

import okhttp3.Interceptor
import okhttp3.Response

class MockResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        println(request)
        // Check if the request URL matches your specific endpoint
      /*  if (request.url.toString().contains("your-specific-endpoint")) {
            val responseBody = """
                {
                    "key": "value",
                    "another_key": "another_value"
                }
            """.trimIndent()

            return Response.Builder()
                .code(200)
                .message(responseBody)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(
                    responseBody.toResponseBody("application/json".toMediaTypeOrNull())
                )
                .build()
        }*/

        // For other requests, just proceed with the actual network call
        return chain.proceed(request)
    }
}
