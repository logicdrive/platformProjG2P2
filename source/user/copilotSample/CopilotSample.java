// Define JPA entity class sample code
// Update createdDate and updatedDate fields by using @PrePersist and @PreUpdate annotations with LoggedEntity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "App_User")
public class User extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private Date createdDate;
    
    private Date updatedDate;


    public static UserRepository repository() {
        return BootApplication.applicationContext.getBean(
            UserRepository.class
        );
    }


    @PrePersist
    public void onPrePersist() {
        this.createdDate = new Date();
        this.updatedDate = new Date();

        super.onPrePersist();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedDate = new Date();

        super.onPreUpdate();
    }
}

// Define JPA repository class sample code
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository
    extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);
}



@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class UserEvent extends AbstractEvent {
    protected Long id;
    protected String email;
    protected String password;
    protected Date createdDate;
    protected Date updatedDate;

    public UserEvent(User aggregate) {
        super(aggregate);
    }

    public UserEvent() {
        super();
    }
}

// Kafka event class sample code
// Add @EventNameAnnotation(eventName="SignUpCompleted") annotation to name of the kafa event
// Extend UserEvent class to get all User attributes
@EventNameAnnotation(eventName="SignUpCompleted")
@NoArgsConstructor
public class SignUpCompleted extends UserEvent {
    public SignUpCompleted(User aggregate) {
        super(aggregate);
    }
}


// Request Dto class sample code
@Data
@ToString
class SignUpReqDto {
    private String email;
    private String password;
    private String name;
}

// Response Dto class sample code
@Getter
@ToString
class SignUpResDto {
    private final Long id;

    public SignUpResDto(User user) {
        this.id = user.getId();
    }
}


// Http end point class sample code
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/users")
public class SignUpEndPoints {

    // Include try ~ catch block to handle exception and Log by using CustomLogger.errorObject
    @PutMapping("/signUp")
    public ResponseEntity<SignUpResDto> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        try {

            // Log ReqDto class by using CustomLogger.debugObject at front
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

            // Log ResDto class by using CustomLogger.debugObject at back
            SignUpResDto signUpResDto = new SignUpResDto(savedUser);
            CustomLogger.debugObject(CustomLoggerType.EXIT, signUpResDto);

            return ResponseEntity.ok(signUpResDto);
            
        } catch(Exception e) {

            CustomLogger.errorObject(e, signUpReqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

    @PutMapping("/updateName")
    public ResponseEntity<Void> updateName(@RequestHeader("User-Id") Long userId, @RequestBody UpdateNameReqDto updateNameReqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, updateNameReqDto);

            // Using UserManageService.getInstance() to query user
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