package com.enriquezrene.firebase

import spock.lang.Specification

/**
 * Created by rene on 15/09/16.
 */
class FireBaseRootTest extends Specification{

    def "when a FireBaseRoot instance is created, the fireBaseDatabase and rootRef must not be null"(){
        given:
        FireBaseRoot fireBaseRoot

        when:
        fireBaseRoot = FireBaseRoot.instance

        then:
        fireBaseRoot.fireBaseDatabase != null
        fireBaseRoot.rootReference != null
    }
}
