package com.enriquezrene.firebase.service.dinosaurs

import com.enriquezrene.domain.dinosaurs.Dinosaur
import com.enriquezrene.firebase.FireBaseRoot
import com.google.firebase.database.DatabaseReference

/**
 * Created by rene on 15/09/16.
 */
class DinosaurDomainService {

    DatabaseReference dinosaursRef
    DatabaseReference scoresRef

    public DinosaurDomainService(){
        DatabaseReference rootRef = FireBaseRoot.instance.rootReference
        dinosaursRef = rootRef.child("dinosaurs")
        scoresRef = rootRef.child("scores")
    }

    def getDinosaursOrderedByField(String path) {
        dinosaursRef.orderByChild(path)
    }

    void save(Dinosaur dinosaur) {
        DatabaseReference childRef = dinosaursRef.child(dinosaur.name)
        childRef.setValue(dinosaur)
    }

    void saveScore(String dinosaurName, int score) {
        DatabaseReference dinosaurScore = scoresRef.child(dinosaurName)
        dinosaurScore.setValue(score)
    }

    def sortDinosaursByScore() {
        scoresRef.orderByValue()
    }
}
