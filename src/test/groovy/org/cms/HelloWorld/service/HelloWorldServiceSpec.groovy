package org.cms.HelloWorld.service

import spock.lang.Specification

class HelloWorldServiceSpec extends Specification {

    HelloWorldService helloWorldService = new HelloWorldService()
    String subject = "mars"
    Stack<String> stack = new Stack<>()

    def "add a new planet to the stack"() {
        when:
        helloWorldService.addPlanet(subject, stack)

        then:
        stack.push(subject)
    }
}
