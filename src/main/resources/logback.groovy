import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.filter.AbstractMatcherFilter
import ch.qos.logback.core.spi.FilterReply
import ch.qos.logback.core.status.OnConsoleStatusListener

statusListener(OnConsoleStatusListener)
def defaultPattern = '%d{HH:mm:ss.SSS} %thread %-5level %logger{5} - %msg%n%ex'
new File('trace.log').delete()
appender('ITS-FILE', FileAppender) {
//    filter(NameFileter)
    file = 'trace.log'
    encoder(PatternLayoutEncoder) { pattern = defaultPattern; }
}
appender('ITS-CONSOLE', ConsoleAppender) {
//    filter(ThresholdFilter) {
//        level = DEBUG
//    }
    encoder(PatternLayoutEncoder) { pattern = defaultPattern; }
}
//root(TRACE, ['ITS-CONSOLE'])
logger('com.its', TRACE, ['ITS-CONSOLE'], false)
//logger('com.its', TRACE, ['ITS-FILE'], false)
