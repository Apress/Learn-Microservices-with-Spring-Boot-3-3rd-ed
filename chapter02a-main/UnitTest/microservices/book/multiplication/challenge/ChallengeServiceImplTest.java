package microservices.book.multiplication.challenge;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import microservices.book.multiplication.event.ChallengeSolvedEvent;
import microservices.book.multiplication.event.EventDispatcher;
import microservices.book.multiplication.user.User;
import microservices.book.multiplication.user.UserRepository;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.*;
@ExtendWith(MockitoExtension.class)
public class ChallengeServiceImplTest {
 private ChallengeServiceImpl challengeServiceImpl;
 @Mock
 private ChallengeAttemptRepository attemptRepository;
 @Mock
 private UserRepository userRepository;
  @Mock
 private EventDispatcher eventDispatcher;
 @BeforeEach
 public void setUp() {
 challengeServiceImpl = new ChallengeServiceImpl(attemptRepository,
 userRepository, eventDispatcher);
 }
 @Test
 public void checkCorrectAttemptTest() {
 // given
 long userId = 9L, attemptId = 1L;
 User user = new User("john_doe");
 User savedUser = new User(userId, "john_doe");
 ChallengeAttemptDTO attemptDTO =
 new ChallengeAttemptDTO(50, 60, "john_doe", 3000);
 ChallengeAttempt attempt =
 new ChallengeAttempt(null, savedUser, 50, 60, 3000, true);
 ChallengeAttempt storedAttempt =
 new ChallengeAttempt(attemptId, savedUser, 50, 60, 3000, true);
 ChallengeSolvedEvent event = new ChallengeSolvedEvent(attemptId, true,
 attempt.getFactorA(), attempt.getFactorB(), userId,
 attempt.getUser().getAlias());
 // user does not exist, should be created
 given(userRepository.findByAlias("john_doe"))
 .willReturn(Optional.empty());
 given(userRepository.save(user))
 .willReturn(savedUser);
 given(attemptRepository.save(attempt))
 .willReturn(storedAttempt);
 // when
 ChallengeAttempt resultAttempt =
 challengeServiceImpl.checkAttempt(attemptDTO);
 // then
 then(resultAttempt.isCorrect()).isTrue();
 verify(userRepository).save(user);
   verify(attemptRepository).save(attempt);
 verify(eventDispatcher).send(event);
 }
}
