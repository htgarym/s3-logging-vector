package fluent.bit.logback

import com.github.javafaker.Faker
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main() {
    var running = true
    val logger = LoggerFactory.getLogger("Test Logger")
    val faker = Faker()

    Runtime.getRuntime().addShutdownHook(thread(start = false, isDaemon = true, name = "Shutdown Thread") {
        running = false
    })

    while(running) {
        logger.trace("{}", faker.ancient().hero())
        logger.debug("{}", faker.aquaTeenHungerForce().character())
        logger.info("{}", faker.backToTheFuture().quote())
        logger.warn("{}", faker.beer().name())
        logger.error("{}", faker.app().name())
        logger.error("Exception", Throwable(faker.book().author()))
        TimeUnit.MILLISECONDS.sleep(500)
    }
}
