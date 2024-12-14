package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.ClassAndSubjetDataDto;
import com.easyWay.Student_Management_System.Dto.ClassResponseDto;
import com.easyWay.Student_Management_System.Entity.CLassInfo;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.ClassInfoRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.ClassResponseService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassResponseServiceImpl implements ClassResponseService {

    @Autowired
    ClassInfoRepo classInfoRepo;

    @Autowired
    Gson gson;

    @Autowired
    ClaimService claimService;

    @Override
    public ClassResponseDto getData() {

        ClassResponseDto result = new ClassResponseDto();
        List<CLassInfo> classData = classInfoRepo.getBySchoolName(claimService.getLoggedInUserSchoolCode());
        List<ClassAndSubjetDataDto> dataList = new ArrayList<>();
        for (CLassInfo classInfo : classData) {
            ClassAndSubjetDataDto data = new ClassAndSubjetDataDto();
            try {
                ArrayList<String> subjectList = gson.fromJson(classInfo.getSubject(), new TypeToken<ArrayList<String>>(){}.getType());
                data.setClassName(classInfo.getClassName());
                data.setSubject(subjectList);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
            dataList.add(data);
        }
        result.setClassData(dataList);
        return result;
    }

    @Override
    public String saveData(ClassResponseDto response) {
        for (ClassAndSubjetDataDto data : response.getClassData()) {
            if (checkClassValidation(data)) {
                CLassInfo classInfo = new CLassInfo();
                classInfo.setClassName(data.getClassName());
                classInfo.setSubject(gson.toJson(data.getSubject()));
                classInfo.setSchoolCode(claimService.getLoggedInUserSchoolCode());
                classInfoRepo.save(classInfo);
            }
        }

        return "Data saved successfully";
    }
    boolean checkClassValidation(ClassAndSubjetDataDto dto) {

        if (dto.getClassName() == null) {
            throw new BadRequestException("Class name cannot be null");

        }
           if ((dto.getClassName().equalsIgnoreCase("Nursary") ||
                dto.getClassName().equalsIgnoreCase("LKG") ||
                dto.getClassName().equalsIgnoreCase("UKG") ||
                dto.getClassName().equalsIgnoreCase("1") ||
                dto.getClassName().equalsIgnoreCase("2") ||
                dto.getClassName().equalsIgnoreCase("3") ||
                dto.getClassName().equalsIgnoreCase("4") ||
                dto.getClassName().equalsIgnoreCase("5") ||
                dto.getClassName().equalsIgnoreCase("6") ||
                dto.getClassName().equalsIgnoreCase("7") ||
                dto.getClassName().equalsIgnoreCase("8") ||
                dto.getClassName().equalsIgnoreCase("9") ||
                dto.getClassName().equalsIgnoreCase("10") ||
                dto.getClassName().equalsIgnoreCase("11") ||
                dto.getClassName().equalsIgnoreCase("12"))){

                List<CLassInfo> savedData = classInfoRepo.getBySchoolName(claimService.getLoggedInUserSchoolCode());
                if(ObjectUtils.isEmpty(savedData)){
                    return true;
                }
                boolean check = false;
                for (CLassInfo cls: savedData){
                   if (cls.getClassName().equalsIgnoreCase(dto.getClassName())){
                       throw new BadRequestException("Class is already present");
                   }
                   else {
                       check = true;
                   }
                }
               return check;

        }else {
            throw new BadRequestException("This is not a valid class");
        }

    }
}


