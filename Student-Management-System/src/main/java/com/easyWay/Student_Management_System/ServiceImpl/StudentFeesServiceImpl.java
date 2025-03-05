package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.StudentFeesDto;
import com.easyWay.Student_Management_System.Entity.StudentFeeInfo;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.StudentFeesInfoRepo;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.StudentFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentFeesServiceImpl implements StudentFeesService {

    @Autowired
    StudentInfoRepo studentInfoRepo;

    @Autowired
    StudentFeesInfoRepo studentFeesInfoRepo;

    @Autowired
    ClaimService claimService;

    @Override
    public String saveStudentFees(StudentFeesDto studentFees) {

        if(studentFees == null || ObjectUtils.isEmpty(studentFees.getId())) {
              throw new BadRequestException("Invalid student fees id");
        }


        Optional<StudentInfo> studentInfo = studentInfoRepo.findById(studentFees.getId());

        if(studentInfo.isPresent()) {
            if(studentInfo.get().getRemainingFees() == 0){
                throw new BadRequestException("No remaining fees");
            }
            StudentFeeInfo studentFeeInfo = new StudentFeeInfo();
            studentFeeInfo.setFee(studentFees.getFee());
            studentFeeInfo.setStudentInfo(studentInfo.get());
            studentFeesInfoRepo.save(studentFeeInfo);
            float remaining = studentInfo.get().getRemainingFees();
            remaining = remaining- studentFees.getFee();
            studentInfo.get().setRemainingFees(remaining);
            studentFeeInfo.setSchoolCode(claimService.getLoggedInUserSchoolCode());
            studentInfoRepo.save(studentInfo.get());
        }
        return "Fees Added successfully";
    }

    @Override
    public String deleteFees(UUID id) {

        studentFeesInfoRepo.deleteById(id);
        return "deleted successfully";
    }

    @Override
    public String editFees(StudentFeesDto dto) {
        StudentFeeInfo entity = studentFeesInfoRepo.getById(dto.getId());

        if (ObjectUtils.isEmpty(entity)){
            throw new BadRequestException("Not data found");
        }
        float rem = dto.getFee()-entity.getFee();// 900 -899 = 1
        float oldRem = entity.getStudentInfo().getRemainingFees();//280
        entity.getStudentInfo().setRemainingFees(oldRem-rem);
        entity.setFee(dto.getFee());
        studentFeesInfoRepo.save(entity);
        return "data edit successfully";
    }


}
