import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.time.Duration

import static java.time.Duration.*
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BewareGStringLog4jTest {

    @Test
    void log_gstring_is_extremely_slow() {
        println 'Groovy version: ' + GroovySystem.version
        Logger log = LogManager.getLogger('aaa')
        User user = new User(name: 'Ivan', id:1000)
        Duration timeTook = measure {
            log.info("Logging in user: ${user}")
        }
        int appendedLogMessageLength = '- Logging in user: Ivan:1000'.length()
        assertThat(user.toStringCalledTimes).isEqualTo(appendedLogMessageLength)
        assertThat(timeTook).isBetween(ofSeconds(appendedLogMessageLength - 1),
                                       ofSeconds(appendedLogMessageLength + 1))
    }

    private Duration measure(Closure code){
        long start = System.currentTimeMillis()
        code.call()
        long end = System.currentTimeMillis()
        Duration timeTook = ofMillis(end - start)
        println('Time took: ' + timeTook)
        return timeTook
    }

}