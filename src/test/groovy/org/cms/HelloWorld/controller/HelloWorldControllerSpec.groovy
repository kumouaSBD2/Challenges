package org.cms.HelloWorld.controller

import org.cms.HelloWorld.HelloWorldApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(classes = HelloWorldApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldControllerSpec extends Specification {

    @Autowired
    MockMvc mvc

    def "GET default message when planet and params are not set"() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, world!"))
    }

    def "GET updated message when planet is set but params are not set"() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get("/hello/mars"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, mars!"))
    }

    def "GET all caps when SHOUT is true"() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get("/hello/mars?shout=true"))
                .andExpect(status().isOk())
                .andExpect(content().string("HELLO, MARS!"))

        mvc.perform(MockMvcRequestBuilders
                .get("/hello?shout=true"))
                .andExpect(status().isOk())
                .andExpect(content().string("HELLO, WORLD!"))
    }

    def "GET reverse when REVERSE is true"() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get("/hello/mars?reverse=true"))
                .andExpect(status().isOk())
                .andExpect(content().string("!sram ,olleH"))

        mvc.perform(MockMvcRequestBuilders
                .get("/hello?reverse=true"))
                .andExpect(status().isOk())
                .andExpect(content().string("!dlrow ,olleH"))
    }

    def "GET reverse when REVERSE and SHOUT is true"() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get("/hello/mars?shout=true&reverse=true"))
                .andExpect(status().isOk())
                .andExpect(content().string("!SRAM ,OLLEH"))

        mvc.perform(MockMvcRequestBuilders
                .get("/hello?shout=true&reverse=true"))
                .andExpect(status().isOk())
                .andExpect(content().string("!DLROW ,OLLEH"))
    }

    def "PUT planet to the stack"() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .put("/hello/mars"))
                .andExpect(status().isOk())
                .andExpect(content().string("mars"))
    }

    def "DELETE planet from the stack"() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .delete("/hello?pop=true"))
                .andExpect(status().isOk())
    }

}
