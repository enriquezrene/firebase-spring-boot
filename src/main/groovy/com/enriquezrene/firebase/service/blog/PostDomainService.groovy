package com.enriquezrene.firebase.service.blog

import com.enriquezrene.domain.blog.Post
import com.enriquezrene.firebase.FireBaseRoot
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction

/**
 * Created by rene on 15/09/16.
 */
class PostDomainService {

    DatabaseReference rootRef
    DatabaseReference postsRef
    DatabaseReference votesRef

    public PostDomainService(){
        rootRef = FireBaseRoot.instance.rootReference
        postsRef = rootRef.child("posts");
    }

    String saveAndGetKey(Post post) {
        DatabaseReference newPostRef = postsRef.push()
        newPostRef.setValue(post)
        return newPostRef.key
    }

    String addUpVoteForBlog(String postId) {
        votesRef = rootRef.child("posts/${postId}/upvotes")
        votesRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer currentValue = mutableData.getValue(Integer.class);
                if (currentValue == null) {
                    mutableData.setValue(1);
                } else {
                    mutableData.setValue(currentValue + 1);
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                if(databaseError!=null){
                    throw new RuntimeException("No up vote was added")
                }
            }
        });
    }
}
