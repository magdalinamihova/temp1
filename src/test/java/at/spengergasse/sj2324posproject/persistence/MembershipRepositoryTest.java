package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.TestContainerConfiguration;
import at.spengergasse.sj2324posproject.domain.entities.Membership;
import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestContainerConfiguration.class)
class MembershipRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReadingGroupRepository readingGroupRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @BeforeEach
    void setup() {
        User groupCreator = userRepository.save(user());
        User groupMember = userRepository.save(user1());
        ReadingGroup readingGroup = readingGroup(groupCreator);
        readingGroupRepository.save(readingGroup);
        Membership membership = membership(groupMember, readingGroup);
        membershipRepository.saveAndFlush(membership);
    }

    @Test
    void ensureSavingAndRereadingMembershipWorks() {
        assertThat(membershipRepository).isNotNull();
    }

    @Test
    void ensureFindByReadingGroup() {
        User groupCreator = userRepository.save(user());
        ReadingGroup readingGroup = readingGroup(groupCreator);
        readingGroupRepository.save(readingGroup);

        assertThat(membershipRepository.findByReadingGroup(readingGroup).isPresent());

    }

}
