@ExtendWith(MockitoExtension.class)
public class MultiplicationServiceImplTest {
 @Mock
 private ChallengeAttemptRepository attemptRepository;
 @Test
 public void getRandomMultiplicationTest() throws Exception {
   // given
   given(challengeGeneratorService.randomChallenge())
   .willReturn(new Challenge(70, 20));
   // when
   MockHttpServletResponse response = mvc.perform(
   get("/multiplications/random")
   .accept(MediaType.APPLICATION_JSON))
   .andReturn().getResponse();
   // then
   then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
   then(response.getContentAsString())
   .isEqualTo(json.write(new Challenge(70, 20)).getJson());
 } 
}
