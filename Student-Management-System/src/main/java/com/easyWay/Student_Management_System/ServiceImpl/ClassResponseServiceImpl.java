package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.ClassAndSubjetDataDto;
import com.easyWay.Student_Management_System.Dto.ClassResponseDto;
import com.easyWay.Student_Management_System.Entity.CLassInfo;
import com.easyWay.Student_Management_System.Repo.ClassInfoRepo;
import com.easyWay.Student_Management_System.Service.ClassResponseService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassResponseServiceImpl implements ClassResponseService {

    @Autowired
    ClassInfoRepo classInfoRepo;

    @Autowired
    Gson gson;

    @Override
    public ClassResponseDto getData() {
        String schoolName = "ITM";
        ClassResponseDto result = new ClassResponseDto();
        List<CLassInfo> classData = classInfoRepo.getBySchoolName(schoolName);
        List<ClassAndSubjetDataDto> dataList = new ArrayList<>();
        for (CLassInfo classInfo : classData) {
            ClassAndSubjetDataDto data = new ClassAndSubjetDataDto();
            try {
                ArrayList<String> subjectList = gson.fromJson(classInfo.getSubject(), new TypeToken<ArrayList<String>>(){}.getType());
                data.setClassName(classInfo.getClassName());
                data.setSubject(subjectList);
            } catch (JsonSyntaxException e) {
                System.err.println("Failed to parse subjects for class: " + classInfo.getClassName());
                e.printStackTrace();
            }
            dataList.add(data);
        }
        result.setClassData(dataList);
        return result;
    }

    @Override
    public String saveData(ClassResponseDto response) {
        for (ClassAndSubjetDataDto data : response.getClassData()){
            CLassInfo classInfo = new CLassInfo();
            classInfo.setClassName(data.getClassName());
            classInfo.setSubject(gson.toJson(data.getSubject()));
            classInfo.setSchoolName("ITM");
            classInfoRepo.save(classInfo);
        }
        return null;
    }
}
