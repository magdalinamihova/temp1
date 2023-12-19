package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.entities.MembershipRequest;
import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static at.spengergasse.sj2324posproject.domain.TestFixtures.readingGroup;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MembershipRequestRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReadingGroupRepository readingGroupRepository;

    @Autowired
    private MembershipRequestRepository membershipRequestRepository;

    @BeforeEach
    void setup() {
        User groupCreator = userRepository.save(user());
        User requestingUser = userRepository.save(user1());
        ReadingGroup readingGroup = readingGroup(groupCreator);
        readingGroupRepository.save(readingGroup);
        MembershipRequest membershipRequest = membershipRequest(requestingUser, readingGroup);
        membershipRequestRepository.save(membershipRequest);
    }

    @Test
    void ensureSavingAndRereadingMembershipRequestWorks() {
        assertThat(membershipRequestRepository).isNotNull();
    }

}