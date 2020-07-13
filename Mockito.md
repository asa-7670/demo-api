### Mockito
    Mockito est framework Jav qui permet de réaliser de vais tests unitaires.
    https://doktapepa.wordpress.com/2015/03/21/tests-unitaires-avec-mockito-couple-a-junit-et-spring/
    https://www.baeldung.com/mockito-annotations
#### Test unitaire
    Permet de verifier qu'une méthode prise individuellment fait bien ce qu'elleest censé faire.
    Les tests unitaires sont à la charge et à la résponsabilité du développeur
#### Tes d'integration:
    Permet de démontrer que les différentes parties de 'application fonctionnent bien ensemble.
#### Dépendance
    <dependency>
       <groupId>org.mockito</groupId>
       <artifactId>mockito-all</artifactId>
       <version>1.9.5</version>
       <scope>test</scope>
    </dependency>
#### Injection dans la classe de test
    @RunWith(MockitoJunitRunner.class)
    public class MyTestClass {
    }
#### Creer et injecter des mocks
    @Mock
    private UserDao userDaoMock
    @InjectMocks
    private UserManagementServiceImpl userManagementService;   
#### Définir le comportement d'un mock
    // Ici, isAdmin() retournera toujours vrai si elle est appelée avec "userA" et toujours faux si elle est appelée avec "userB"
    when(userDAOMock.isAdmin("userA").thenReturn(true);
    when(userDAOMock.isAdmin("userB").thenReturn(false);
     
    // Ici, isAdmin() retournera toujours vrai quelque soit l'argument passé en paramètre
    when(userDAOMock.isAdmin(anyString()).thenReturn(true);
     
    // Ici, isAdmin() retournera vrai puis toujours faux quelque soit l'argument passé en paramètre
    when(userDAOMock.isAdmin(anyString()).thenReturn(true, false);
     
    // Ici, isAdmin() lèvera une exception si l'argument passé en paramètre est null
    when(userDAOMock.isAdmin(null).thenThrow(new UserException("User not found"));
##### Matchers
    any(Class clazz), anyString(), anyLong(), anyCollection(),....
    // Ici, on ne cible que les arguments qui ont plus de 5 caractères.
    
    Pour aller encore plus loin, Mockito propose ArgumentMatcher :
    argThat(new ArgumentMatcher<String>() {
        @Override
        public boolean matches(Object arg) {
            return (StringUtils.length((String) arg) > 5);
        }
    });
#### Answers
    // Ici, on retourne directement l'argument qui a été passé en argument.
    when(userDAOMock.persist(any(User.class))).thenReturn(returnsFirstArg());
     
    // Ici, on modifie l'argument passé en paramètre avant de le retourner.
    when(userDAOMock.persist(any(User.class))).thenAnswer(new Answer<User>() {
        @Override
        public User answer(InvocationOnMock invoc){
            User user = (User) invoc;
            user.setId(1L);
            return user;
        }
    });
#### Vérification
    // Ici, on vérifie que la méthode isAdmin() a bien été appelée 1 fois avec l'argument "userA"
    // La vérification peut également se faire avec un Matcher (vu plus haut)
    verify(userDAOMock).isAdmin("userA")
     
    // Ici, on vérifie que la méthode update() n'a jamais été appelée
    verify(userDAOMock, never()).update(any(User.class));
     
    // Ici, on vérifie que la méthode increment() a été appelée 2 fois
    verify(userDAOMock, times(2)).increment();
     
    // Ici, on vérifie que la méthode increment() a été appelée au moins 2 fois
    verify(userDAOMock, atLeast(2)).increment();
     
    // Ici, on vérifie que les appels sont exécutés dans un certain ordre
    InOrder inOrder = Mockito.inOrder(addressDAOMock, userDAOMock);
    inOrder.verify(addressDAOMock).create(any(Address.class));
    inOrder.verify(userDAOMock).create(any(User.class));
#### ArgumentCaptor
    Les capteurs d'arguments permettent de vérifier l'argument d'un appel de manière plus simple qu'un Matcher.
    
    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    verify(userDAOMock).update(captor.capture());
     
    User user = captorProc.getValue();
    assertEqual("userB", user.getUsername());
    assertEqual(3, user.getRoles().size());
    assertTrue(user.isEnabled()); 