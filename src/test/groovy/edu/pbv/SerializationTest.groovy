package edu.pbv

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS

class SerializationTest extends Specification {

    @Shared
    ObjectMapper objectMapper = new ObjectMapper()

    def setupSpec() {
        objectMapper.registerModule new JavaTimeModule()
        objectMapper.disable WRITE_DATES_AS_TIMESTAMPS
    }

    def "SerializeTest"(){
        expect:
        objectMapper.writeValueAsString(new TestEntity(LocalDateTime.now())) == ""
    }


    def "DeserializeTest"(){
        expect:
        objectMapper.readValue("{\"dateTime\":\"2018-01-09 23:43:59\"}", TestEntity).dateTime == null
    }
}
