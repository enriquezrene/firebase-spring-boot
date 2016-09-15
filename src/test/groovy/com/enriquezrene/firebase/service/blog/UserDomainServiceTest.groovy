package com.enriquezrene.firebase.service.blog

import com.enriquezrene.domain.blog.User
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import spock.lang.Specification

/**
 * Created by rene on 15/09/16.
 */
class UserDomainServiceTest extends Specification {

    def "on successful insert, the callback should not thrown exceptions"(){
        given:
        UserDomainService userDomainService = Spy(UserDomainService)
        DatabaseReference usersRef = Mock(DatabaseReference)
        userDomainService.usersRef = usersRef

        DatabaseReference childRef = Mock(DatabaseReference)

        User user = new User(username: 'a', name: 'Rene')
        DatabaseReference.CompletionListener callback = new DatabaseReference.CompletionListener(){
            @Override
            void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError!=null){
                    throw new RuntimeException("No data saved");
                }
            }
        }


        when:
        userDomainService.save(user, callback)
        
        then:
        1 * userDomainService.usersRef.child(user.username) >> childRef
        1 * childRef.setValue(user, callback)

    }

    def "to save a user, his username is used to get a child and the whole object for setValue"(){
        given:
        UserDomainService userDomainService = Spy(UserDomainService)
        User user = new User(username: 'a', name: 'Rene')

        DatabaseReference usersRef = Mock(DatabaseReference)
        userDomainService.usersRef = usersRef

        DatabaseReference childRef = Mock(DatabaseReference)

        when:
        userDomainService.save(user)

        then:
        1 * userDomainService.usersRef.child(user.username) >> childRef
        1 * childRef.setValue(user)
    }

    def "For updateFields the updateChildren method should be invoked"(){
        given:
        UserDomainService userDomainService = Spy(UserDomainService)
        Map<String, Object> fields = new HashMap<>()
        fields.field_one = 'foo'

        DatabaseReference usersRef = Mock(DatabaseReference)
        userDomainService.usersRef = usersRef

        DatabaseReference childRef = Mock(DatabaseReference)

        when:
        userDomainService.updateFields('a', fields)

        then:
        1 * userDomainService.usersRef.child('a') >> childRef
        1 * childRef.updateChildren(fields)
    }
}
