package org.odideveloper.sample

import spock.lang.Specification

class HelloWorldSpecification extends Specification {

    private HelloWorld odiRepoConfig = new HelloWorld()

    def "message() should return hello"() {
        given:
        assert odiRepoConfig != null

        when:
        String val = odiRepoConfig.message()

        then:
        assert 'hello' == val
    }
}

