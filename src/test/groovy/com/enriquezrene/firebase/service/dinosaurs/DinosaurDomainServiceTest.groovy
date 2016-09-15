package com.enriquezrene.firebase.service.dinosaurs

import com.enriquezrene.domain.dinosaurs.Dinosaur
import com.google.firebase.database.DatabaseReference
import spock.lang.Specification

/**
 * Created by rene on 15/09/16.
 */
class DinosaurDomainServiceTest extends Specification{

    def "To sort the dinosaurs by their score orderByValue could be used"(){
        given:
        DatabaseReference childRef = Mock(DatabaseReference)
        DinosaurDomainService service = Spy(DinosaurDomainService)
        service.scoresRef = childRef

        when:
        service.sortDinosaursByScore()


        then:
        1 * service.scoresRef.orderByValue()
    }

    def "in order to save a dinosaur score its name is used as its key"(){
        given:
        int score = 99
        DatabaseReference childRef = Mock(DatabaseReference)
        def dinosaurName = 't-rex'

        DinosaurDomainService service = Spy(DinosaurDomainService)
        service.scoresRef = childRef

        when:
        service.saveScore(dinosaurName, score)


        then:
        1 * service.scoresRef.child(dinosaurName) >> childRef
        1 * childRef.setValue(score)
    }

    // TODO: validate indexOn feature in order to improve the performance for this kind of queries
    def "in order to get ordered child facts, the orderByChild method should be used"(){
        DinosaurDomainService service = Spy(DinosaurDomainService)
        service.dinosaursRef = Mock(DatabaseReference)
        String path = 'dimensions/height'

        when:
        service.getDinosaursOrderedByField(path);

        then:
        1 * service.dinosaursRef.orderByChild(path)
    }

    def "dinosaurs are unique by name, so that to save its name is the key"(){
        given:
        DinosaurDomainService service = Spy(DinosaurDomainService)
        Dinosaur dinosaur = new Dinosaur(name:'rex', height:98)

        DatabaseReference dinosaursRef = Mock(DatabaseReference)
        service.dinosaursRef = dinosaursRef

        DatabaseReference childRef = Mock(DatabaseReference)

        when:
        service.save(dinosaur)

        then:
        1 * service.dinosaursRef.child(dinosaur.name) >> childRef
        1 * childRef.setValue(dinosaur)
    }
}
