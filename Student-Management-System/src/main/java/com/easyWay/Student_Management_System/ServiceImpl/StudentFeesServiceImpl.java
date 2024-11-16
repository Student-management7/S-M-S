package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.StudentFeesDto;
import com.easyWay.Student_Management_System.Entity.StudentFeeInfo;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.StudentFeesInfoRepo;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Service.StudentFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class StudentFeesServiceImpl implements StudentFeesService {

    @Autowired
    StudentInfoRepo studentInfoRepo;

    @Autowired
    StudentFeesInfoRepo studentFeesInfoRepo;

    @Override
    public String saveStudentFees(StudentFeesDto studentFees) {

        if(studentFees == null || ObjectUtils.isEmpty(studentFees.getId())) {
              throw new BadRequestException("Invalid student fees id");
        }

        Optional<StudentInfo> studentInfo = studentInfoRepo.findById(studentFees.getId());

        if(studentInfo.isPresent()) {
            StudentFeeInfo studentFeeInfo = new StudentFeeInfo();
            studentFeeInfo.setFee(studentFees.getFee());
            studentFeeInfo.setStudentInfo(studentInfo.get());
            studentFeesInfoRepo.save(studentFeeInfo);
            int remaining = studentInfo.get().getRemainingFees();
            remaining = remaining- studentFees.getFee();
            studentInfo.get().setRemainingFees(remaining);
            studentInfoRepo.save(studentInfo.get());
        }
        return "Fees Added successfully";
    }
}
