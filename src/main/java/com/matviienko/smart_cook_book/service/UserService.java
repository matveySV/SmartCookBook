package com.matviienko.smart_cook_book.service;

import com.matviienko.smart_cook_book.repository.Entity.UserEntity;
import com.matviienko.smart_cook_book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser (String username, String email, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        System.out.println(userEntity);
        userRepository.save(userEntity);
    }
    public UserEntity createUser(UserEntity userEntity) {
    //TODO зашифруй пароль
        return userRepository.save(userEntity);
    }

    public int getCurrentUserId (){
      //TODO return security context user
        return 3;
    }

//    public StudentEntity updateStudent(StudentEntity studentEntity) {
//
//        StudentEntity entity = studentRepository.findById(studentEntity.getId())
//                .orElseThrow(StudentNotFoundException::new);
//
//        if (studentEntity.getName() != null){
//            entity.setName(studentEntity.getName());
//        }
//
//        if (studentEntity.getEmail() != null){
//            entity.setEmail(studentEntity.getEmail());
//        }
//
//        if (studentEntity.getPhotos() != null  && studentEntity.getPhotos().isEmpty()){
//            entity.setPhotos(studentEntity.getPhotos());
//        }
//
//        return studentRepository.save(entity);
//    }
//
//
    public List<UserEntity> getUsers () {
        return userRepository.findAll();
    }
//
//    public StudentEntity getStudent(Long studentId){
//        return studentRepository.findById(studentId)
//                .orElseThrow(StudentNotFoundException::new);
//    }
//    public void deleteStudent(Long studentId){
//
//        StudentEntity entity = studentRepository.findById(studentId)
//                .orElseThrow(StudentNotFoundException::new);
//
//        studentRepository.delete(entity);
//    }
}


//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public UserEntity saveUser(UserEntity userEntity) {
//        return userRepository.save(userEntity);
//    }
//
//    public List<UserEntity> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public Optional<UserEntity> getUserById(int userId) {
//        return userRepository.findById(userId);
//    }
//
//    public void deleteUser(int userId) {
//        userRepository.deleteById(userId);
//    }
//}