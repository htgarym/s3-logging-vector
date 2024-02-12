package dev.garymyers.logging.vector

import com.github.javafaker.Faker
import org.slf4j.LoggerFactory

fun main() {
    val logger = LoggerFactory.getLogger("Test Logger")
    val faker = Faker()

    logger.trace("{}", faker.ancient().hero())
    logger.debug("{}", faker.aquaTeenHungerForce().character())
    logger.info("{}", faker.backToTheFuture().quote())
    logger.warn("{}", faker.beer().name())
    logger.error("{}", faker.app().name())
    logger.error("Exception", Throwable(faker.book().author()))
}
