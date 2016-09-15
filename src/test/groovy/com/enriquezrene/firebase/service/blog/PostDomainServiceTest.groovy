package com.enriquezrene.firebase.service.blog

import com.enriquezrene.domain.blog.Post
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Transaction
import spock.lang.Specification

/**
 * Created by rene on 15/09/16.
 */
class PostDomainServiceTest extends Specification {

    def "for save and get the generated key, push method should be used and then set value"(){
        given:
        Post post = new Post(author: 'gracehop', title: 'The Touring Machine')

        PostDomainService service = Spy(PostDomainService)
        service.postsRef = Mock(DatabaseReference)

        DatabaseReference newPostRef = Mock(DatabaseReference)
        newPostRef.key >> 'foo'

        when:
        String key = service.saveAndGetKey(post)on aon a

        then:
        1 * service.postsRef.push() >> newPostRef
        1 * newPostRef.setValue(post)
        key != null
    }

    // TODO: with gatling check when multiple users run the same request concurrently
    def "for save data in a transaction, runTransaction from ref should be used"(){
        given:
        PostDomainService service = Spy(PostDomainService)
        service.rootRef = Mock(DatabaseReference)
        DatabaseReference blogVotesRef = Mock(DatabaseReference)
        def postId = '123'

        when:
        service.addUpVoteForBlog(postId)

        then:
        1 * service.rootRef.child("posts/${postId}/upvotes") >> blogVotesRef
        1 * blogVotesRef.runTransaction(_ as Transaction.Handler)
    }
}
