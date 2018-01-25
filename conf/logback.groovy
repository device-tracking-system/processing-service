
import biz.paluch.logging.gelf.logback.GelfLogbackAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import logging.LogbackProperties
appender(LogbackProperties.gelf(), GelfLogbackAppender) {
    host = LogbackProperties.protocolColonHost()
    port = LogbackProperties.port()
    extractStackTrace = LogbackProperties.extractStackTrace()
    filterStackTrace = LogbackProperties.filterStackTrace()
    mdcProfiling = LogbackProperties.mdcProfiling()
    timestampPattern = LogbackProperties.timestamp()
    maximumMessageSize = LogbackProperties.maximumMessageSize()
    filter(ThresholdFilter) {
        level = LogbackProperties.gelfFilterLevel()
    }
}
appender(LogbackProperties.stdout(), ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = LogbackProperties.stdoutPattern()
    }
    filter(ThresholdFilter) {
        level = LogbackProperties.stdoutFilterLevel()
    }
}
allAppenders = [LogbackProperties.gelf(), LogbackProperties.stdout()]
root(LogbackProperties.rootLogLevel(), allAppenders)
LogbackProperties.customLoggers().each { customLogger, level ->
    logger(customLogger, level, allAppenders, false)
}