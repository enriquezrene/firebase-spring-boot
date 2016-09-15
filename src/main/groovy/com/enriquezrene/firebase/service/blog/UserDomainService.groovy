package com.enriquezrene.firebase.service.blog;

import com.enriquezrene.domain.blog.User
import com.enriquezrene.firebase.FireBaseRoot
import com.google.firebase.database.DatabaseReference

/**
 * Created by rene on 15/09/16.
 */
class UserDomainService {

    DatabaseReference usersRef

    public UserDomainService(){
        DatabaseReference rootRef = FireBaseRoot.instance.rootReference
        usersRef = rootRef.child("users");
    }

    public void save(User user, DatabaseReference.CompletionListener callback) {
        DatabaseReference childRef = usersRef.child(user.username)
        childRef.setValue(user, callback)
    }

    public void save(User user) {
        DatabaseReference childRef = usersRef.child(user.username)
        childRef.setValue(user)
    }

    public void updateFields(String username, Map<String, Object> fieldsForUpdate) {
        DatabaseReference userForUpdateReference = usersRef.child(username)
        if(userForUpdateReference!=null){
            userForUpdateReference.updateChildren(fieldsForUpdate)
        }
    }

}
