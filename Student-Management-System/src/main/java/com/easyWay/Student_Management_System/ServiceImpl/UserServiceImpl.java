package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.*;
import com.easyWay.Student_Management_System.Entity.SchoolCreationEntity;
import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Enums.Role;
import com.easyWay.Student_Management_System.Helper.EmailHelper;
import com.easyWay.Student_Management_System.Repo.SchoolCreationRepo;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Security.JWTService;
import com.easyWay.Student_Management_System.Security.LoggedInUser;
import com.easyWay.Student_Management_System.Security.UserDeatilsServices;
import com.easyWay.Student_Management_System.Service.UserService;
import com.google.gson.Gson;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.security.Permission;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(11);

    // Character pool for the password
    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "0123456789" +
                    "!@#$%^&*()-_+=<>?";

    private static final SecureRandom RANDOM = new SecureRandom();


    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTService jwtService ;
    @Autowired
    private UserDeatilsServices userDeatilsServices;

    @Autowired
    ClaimService claimService;

    @Autowired
    SchoolCreationRepo schoolCreationRepo;

//    @Autowired
//    private  MailService mailService;
//
//    @Autowired
//    DatabaseService databaseService;

    @Autowired
    Gson gson;

    @Autowired
    UsersRepo usersRepo;

    @Override
    public String registerUser(UsersDto userDto) {
        Users users = new Users();
        users.setEmail(userDto.getEmail().toLowerCase());
        users.setSchoolCode(userDto.getEmail().substring(1,4).toUpperCase()+RANDOM.nextInt(9999));
        users.setPassword(encoder.encode(userDto.getPassword()));
        users.setPermission(userDto.getPermission());
        usersRepo.save(users);
        return "Register Successfully !";
    }

    @Override
    public String registerSchool(SchoolDto schoolDto) throws BadRequestException {

       Users exist = usersRepo.findUsersByEmail(schoolDto.getEmail());
       if(exist!=null){
               throw new RuntimeException("Email already exist ");
       }
       if(!EmailHelper.isValidEmail(schoolDto.getEmail())){
           throw new BadRequestException("Invalid email format");
       }

        Optional<String> loggedInUserEmail = claimService.getLoggedInUserEmail();
        Users admin = null;
        if (loggedInUserEmail.isPresent()) {
            admin = usersRepo.findUsersByEmail(loggedInUserEmail.get());
       }else {
            throw new RuntimeException("Illegal user");
        }
//        if(!admin.getRole().equals(Role.SUPERUSER)) throw new BadRequestException("Not authorized to create school");

        Users users = new Users();
        Permissions permissions= new Permissions();
        SchoolCreationEntity schoolCreation = new SchoolCreationEntity();


         StudentPermissionsDto student = new StudentPermissionsDto();
         FinancePermissionsDto finance = new FinancePermissionsDto();
         FacultyPersmissionsDto faculty = new FacultyPersmissionsDto();
         NotificationPermissionDto notification = new NotificationPermissionDto();
         SubjectPermissionDto subject = new SubjectPermissionDto();

        subjectPermission(subject);
        studentPermission(student);
        financePermission(finance);
        facultyPermission(faculty);
        notificationPermission(notification);

        permissions.setStudent(student);
        permissions.setFinance(finance);
        permissions.setFaculty(faculty);
        permissions.setNotification(notification);
        permissions.setSubject(subject);

        convertDtoToEntity(schoolDto,schoolCreation);

        users.setEmail(schoolDto.getEmail().toLowerCase());
        users.setSchoolCode(schoolDto.getEmail().substring(1,4).toUpperCase()+RANDOM.nextInt(9999));
        users.setPassword(encoder.encode(schoolDto.getPassword()));
        users.setRole(Role.USER);
        users.setPermission(gson.toJson(permissions));
        users.setSchoolCreation(schoolCreation);
       users = usersRepo.save(users);
       schoolCreation.setSchoolCode(users.getSchoolCode());
       schoolCreation.setUsersInfo(users);
       schoolCreationRepo.save(schoolCreation);

        return "School Register Successfully !";
    }
    public void convertDtoToEntity(SchoolDto dto, SchoolCreationEntity entity) {

        entity.setSchoolName(dto.getSchoolName());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setGstNumber(dto.getGstNumber());
        entity.setEstablishmentYear(dto.getEstablishmentYear());
        entity.setSchoolAddress(dto.getSchoolAddress());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPincode(dto.getPincode());
        entity.setContactNumber(dto.getContactNumber());
        entity.setAffiliationBoard(dto.getAffiliationBoard());
        entity.setPanNumber(dto.getPanNumber());
        entity.setMediumOfInstruction(dto.getMediumOfInstruction());
        entity.setSchoolType(dto.getSchoolType());

    }


    private static void subjectPermission(SubjectPermissionDto subject) {
        subject.setSaveSubjectsToClasses(true);
    }

    @Override
    public String loginUser(UsersDto dto) throws BadRequestException {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail().toLowerCase(), dto.getPassword()));
        System.out.println(authentication.getAuthorities().toString());
        return jwtService.generateToken(dto.getEmail().toLowerCase() , String.valueOf(authentication.getAuthorities().stream().toList().get(0)), dto.getSchoolCode());

    }

    @Override
    public String forgetUserPassword(UsersDto userDto) {

        Users users = usersRepo.findUsersByEmail(userDto.getEmail());
        String newPassword = generatePassword();
        users.setPassword(encoder.encode(newPassword));
        usersRepo.save(users);
//      usersRepo.save(users);
//      mailService.newPasswordMail(users.getEmail() , "New Password " , newPassword , " ... ");
        return "New Password sent to your email";
    }



    private static void notificationPermission(NotificationPermissionDto notification) {
        notification.setCreateNotification(true);
        notification.setNotificationList(true);
        notification.setHolidayFormController(true);
    }

    private static void facultyPermission(FacultyPersmissionsDto faculty) {
        faculty.setFacultyAttendanceEdit(true);
        faculty.setFacultyAttendanceSave(true);
        faculty.setFacultyAttendanceShow(true);
        faculty.setFacultySalaryController(true);
        faculty.setFacultyRegistrationForm(true);
        faculty.setFacultySalaryDetails(true);
        faculty.setFacultyAttendanceEditSave(true);
    }

    private static void financePermission(FinancePermissionsDto finance) {
        finance.setAdminFees(true);
    }

    private static void studentPermission(StudentPermissionsDto student) {
        student.setStudentAttendance(true);
        student.setStudentFees(true);
        student.setStudentAttendanceEdit(true);
        student.setStudentAttendenceManagement(true);
        student.setStudentAttendanceShow(true);
        student.setStudentRegistrationController(true);
        student.setStudentAttendanceEditSave(true);
    }

    public static String generatePassword() {
        StringBuilder password = new StringBuilder(10); // Fixed length: 10

        // Generate 10 random characters from the pool
        for (int i = 0; i < 10; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return password.toString();
    }







}
