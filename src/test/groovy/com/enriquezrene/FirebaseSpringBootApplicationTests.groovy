package com.enriquezrene

import com.enriquezrene.firebase.service.dinosaurs.DinosaurDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration;
import spock.lang.Specification;

@ContextConfiguration
@SpringBootTest(classes = DinosaurDomainService)
public class FirebaseSpringBootApplicationTests extends Specification{

	@Autowired
	DinosaurDomainService service

	def "should boot up without errors"() {
		expect: "spring bean should not be null"
		service != null
	}

}
