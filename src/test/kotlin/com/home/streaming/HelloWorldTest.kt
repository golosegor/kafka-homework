package com.home.streaming

import mu.KLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloWorldTest {
    companion object : KLogging()

    @Test
    fun `this is dummy test`() {
        assertThat("whatever").isEqualTo("whatever")
        logger.info { "What" }
    }
}