// Kafka event class sample code
// If you want to use kafka, you should add @EventNameAnnotation(eventName="SignUpCompleted") annotation to the class
// If your event includes User attributes, you should extend UserEvent class
@EventNameAnnotation(eventName="SignUpCompleted")
@NoArgsConstructor
public class SignUpCompleted extends UserEvent {
    public SignUpCompleted(User aggregate) {
        super(aggregate);
    }
}

// Http end point class sample code
// You should include the following annotations to the class 
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/users")
public class SignUpEndPoints {

    // When you create function, you should include try ~ catch block to handle exception
    @PutMapping("/signUp")
    public ResponseEntity<SignUpResDto> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        try {

            // You should log ReqDto class by using CustomLogger.debugObject at front
            CustomLogger.debugObject(CustomLoggerType.ENTER, signUpReqDto);

            // Make a new User object and save it to the database
            User savedUser = User.repository().save(
                    User.builder()
                        .email(signUpReqDto.getEmail())
                        .password(this.passwordEncoder.encode(signUpReqDto.getPassword()))
                        .name(signUpReqDto.getName())
                        .role("User")
                        .build()
                );

            // Publish event by using Kafka
            (new SignUpCompleted(savedUser)).publish();

            // You should log ResDto class by using CustomLogger.debugObject at back
            SignUpResDto signUpResDto = new SignUpResDto(savedUser);
            CustomLogger.debugObject(CustomLoggerType.EXIT, signUpResDto);

            return ResponseEntity.ok(signUpResDto);
            
        } catch(Exception e) {

            // You need to log by using CustomLogger.errorObject and return INTERNAL_SERVER_ERROR if exception is occured
            CustomLogger.errorObject(e, signUpReqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

    @PutMapping("/updateName")
    public ResponseEntity<Void> updateName(@RequestHeader("User-Id") Long userId, @RequestBody UpdateNameReqDto updateNameReqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, updateNameReqDto);

            // You can use UserManageService.getInstance() to query user
            User userToUpdate = UserManageService.getInstance().findByIdOrThrow(userId);
            userToUpdate.setName(updateNameReqDto.getName());
            User savedUser = User.repository().save(userToUpdate);
            
            (new UserNameUpdated(savedUser)).publish();

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            CustomLogger.errorObject(e, updateNameReqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}